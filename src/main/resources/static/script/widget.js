/**
 * 主对象
 * @param obj
 * @varructor
 */
var Melody = function(obj){
    if(!obj.el){
        var currentScript = document.currentScript;
        if(!currentScript){
            var scripts = document.getElementsByTagName("script")
            currentScript = scripts[scripts.length - 1];
        }
        var parentNode = currentScript.parentNode;
        obj.el = currentScript.el || parentNode;
    }
    function removeElement(_element){
        var _parentElement = _element.parentNode;
        if(_parentElement){
            _parentElement.removeChild(_element);
        }
    }
    if(
        currentScript.remove){

        currentScript.remove();
    }else{
        removeElement(currentScript)
    }
    return Melody.Component.createByType(obj);
};
(function(window,Melody,Object){
    'use strict';
    /**
     * 类型组,存放类型相关的数据
     * @type {{}}
     */
    var TYPES =  {};
    /**
     * 按Id查找
     * @type {{}}
     */
    var COMPS = {};
    /**
     * 按Dom查找Melody对象
     * @type {{}}
     */
    var ELMAP = {};



    /**
     * 工具类,判断各种类型
     */
    ( function($) {
        'use strict';

        var types = 'Array Object String Date RegExp Function Boolean Number Null Undefined'.split(' ');

        function type () {
            return Object.prototype.toString.call(this).slice(8, -1);
        };
        for (var i = types.length; i--;) {
            $['is' + types[i]] = (function (self) {
                return function (elem) {
                    return type.call(elem) === self;
                };
            })(types[i]);
        }

        return $;
    })(Melody);

    /**
     * 深度复制,除了function,其他直接copy
     * @param obj
     * @param deep
     * @returns {*}
     */
    Melody.copy = function(obj,deep){
        if (obj === null || (typeof obj !== "object")) {
            return obj;
        } else {
            var name, target = Melody.isArray(obj) ? [] : {}, value;

            for (name in obj) {
                value = obj[name];

                if (value === obj) {
                    continue;
                }

                if (deep) {
                    if (Melody.isArray(value) || Melody.isObject(value)) {
                        target[name] = copy(value,deep);
                    } else {
                        target[name] = value;
                    }
                } else {
                    target[name] = value;
                }
            }
            return target;
        }
    };

    /**
     * 继承方法
     * 如果继承的是对象,则做深度copy
     * 如果继承的是Function,则做原型继承
     * @param dist 目标
     * @param src 来源
     * @param override 重写(新增)
     * @returns {*} 继承后的对象
     */
    Melody.extend =  function(dist,src,override)  {
        if(Melody.isFunction(src)) {
            dist.prototype = Object.create(src.prototype);
            dist = dist.prototype;
        }else{
            var srcCopy = Melody.copy(src,true);
            for(var i in srcCopy){
                dist[i] = src[i];
            }

        }
        if(override) {
            var overCopy = Melody.copy(override, true)
            for (var i in overCopy) {
                dist[i] = overCopy[i];
            }
        }
        return dist;
    };

    /**
     * 通过此方法获取对象
     *
     * @param key 可以是id,也可以是dom对象(通过其他方式获取到的dom)
     * @returns {*}
     */
    Melody.get  =  function(key) {
        if(key instanceof HTMLElement){
            return ELMAP[key];
        }else{
            return COMPS[key];
        }
    };

    /**
     * 所有组件的根
     * @param obj
     * @varructor
     */
    var Component = function(obj) {

    };

    Component.createByType=function(obj)  {
        var types =  [];
        if(obj.lazy){
            types.push("lazy");
        }
        if(obj.reload){
            types.push("reload");
        }
        var cmp ;
        for(var i = 0 ;i< types.length ;i ++ ){
            var type = TYPES[types[i]];
            if(type){
                var tmp = new TYPES[types[i]];
                tmp.create(obj,cmp);
                cmp = tmp;
            }
        }
        return cmp;
    };
    Melody.extend(Component,Object,{
        create: function(obj,comp) {
            if(comp) {
                Melody.extend(this, comp);
                // Melody.extend(this, comp.prototype);
            }
            var id = obj.id || obj.el.getAttribute("id");
            if(obj.id){
                obj.el = document.getElementById(obj.id);
            }
            this.el = obj.el
            if(id){
                COMPS[id]=this;
            }
            ELMAP[this.el] = this;
        }
    });
    Component.register = function(alias,type){
        TYPES[alias] = type;
        Melody[type.name] = type;
    };
    Melody.Component = Component;

    window.Melody = Melody;
    var Reloadable = function () {

    } ;
    Melody.extend(Melody , {
        ajax: function(params) {
            params = params || {};
            params.data = params.data || {};
            if(params.type == "CONTAIN"){
                var headers = params.headers || {};
                headers["_melody"] = "melody";
                params.headers = headers;
            }
            // 判断是ajax请求还是jsonp请求
            var json = params.jsonp ? jsonp(params) : json(params);
            // ajax请求
            function json(params) {
                // 请求方式，默认是GET
                params.type = (params.type || 'GET').toUpperCase();
                // 避免有特殊字符，必须格式化传输数据
                params.data = formatParams(params.data);
                var xhr = null;


                // 实例化XMLHttpRequest对象
                if(window.XMLHttpRequest) {
                    xhr = new XMLHttpRequest();
                } else {
                    // IE6及其以下版本
                    xhr = new ActiveXObjcet('Microsoft.XMLHTTP');
                };


                // 监听事件，只要 readyState 的值变化，就会调用 readystatechange 事件
                xhr.onreadystatechange = function() {
                    // readyState属性表示请求/响应过程的当前活动阶段，4为完成，已经接收到全部响应数据
                    if(xhr.readyState == 4) {
                        var status = xhr.status;
                        // status：响应的HTTP状态码，以2开头的都是成功
                        if(status >= 200 && status < 300) {
                            var response = '';
                            // 判断接受数据的内容类型
                            var type = xhr.getResponseHeader('Content-type');
                            if(type.indexOf('xml') !== -1 && xhr.responseXML) {
                                response = xhr.responseXML; //Document对象响应
                            } else if(type === 'application/json') {
                                response = JSON.parse(xhr.responseText); //JSON响应
                            } else {
                                response = xhr.responseText; //字符串响应
                            };
                            // 成功回调函数
                            params.success && params.success(response);
                        } else {
                            params.error && params.error(status);
                        }
                    }
                };

                // 连接和传输数据
                if(params.type == 'GET') {
                    // 三个参数：请求方式、请求地址(get方式时，传输数据是加在地址后的)、是否异步请求(同步请求的情况极少)；
                    xhr.open(params.type, params.url + '?' + params.data, true);
                    for(var header in params.headers){
                        xhr.setRequestHeader(header,params.headers[header]);
                    }
                    xhr.send(null);
                } else {
                    xhr.open(params.type, params.url, true);
                    for(var header in params.headers){
                        xhr.setRequestHeader(header,params.headers[header]);
                    }
                    //必须，设置提交时的内容类型
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
                    // 传输数据
                    xhr.send(params.data);
                }
            }

            //格式化参数
            function formatParams(data) {
                var arr = [];
                for(var name in data) {
                    // encodeURIComponent() ：用于对 URI 中的某一部分进行编码
                    arr.push(encodeURIComponent(name) + '=' + encodeURIComponent(data[name]));
                };
                // 添加一个随机数参数，防止缓存
                arr.push('v=' + random());
                return arr.join('&');
            };

            // 获取随机数
            function random() {
                return Math.floor(Math.random() * 10000 + 500);
            };

        }
    });
    Melody.extend(Reloadable ,Melody.Component , {
        create: function(obj){
            Melody.Component.prototype.create.apply(this,arguments);
            this.url = obj.url;
        },
        refresh: function(callback){
            var _this = this;
            var el = this.el;
            Melody.ajax({
                url: this.url,  // 请求地址
                type: 'CONTAIN',  // 请求类型，默认"GET"，还可以是"POST"
                data: {},  // 传输数据
                success: function(res){  // 请求成功的回调函数
                    el.innerHTML = res;
                    if(res.indexOf("</script>") != -1){
                        var myDivScript = el.getElementsByTagName("script");
                        for(var i = 0 ; i<myDivScript.length ; i++){
                            var newScript = document.createElement("SCRIPT");
                            newScript.innerHTML = myDivScript[i].innerHTML;
                            newScript.el =  myDivScript[i].parentNode;
                            // myDivScript[i].remove();
                            document.getElementsByTagName("HEAD").item(0).append(newScript);
                        }
                    }
                    callback && callback.call(_this,res);
                },
                error: function(error) {}  // 请求失败的回调函数
            });
        }
    });

    Melody.Component.register("reload",Reloadable);
    /**
     * lazyload的contain对象.在滚动到出现时加载
     * @varructor
     */
    var Lazy = function () {

    } ;
    var refresh = function(){
        for(var i = 0 ;i<Lazy.comps.length ; i++){
            var comp = Lazy.comps[i];

            if(!comp.loaded && comp.visible() ){
                //顺序加载,由于某些滚动时尚未加载的界面可能还没有加载,因此高度为0,当某一个调用完时需要再load一下
                comp.load(refresh);
                break;
            }
        }
    };
    window.addEventListener("load",refresh);
    window.addEventListener("scroll",refresh);
    Lazy.comps = [];
    Melody.extend(Lazy ,Reloadable , {
        create: function(obj){
            Reloadable.prototype.create.apply(this,arguments);
            this.url = obj.url;
            Lazy.comps.push(this);

        },
        visible:function(){
            var rect = this.el.getBoundingClientRect();
            return (
                rect.top >= 0 &&
                rect.left >= 0 &&
                rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && /*or $(window).height() */
                rect.right <= (window.innerWidth || document.documentElement.clientWidth) /*or $(window).width() */
            );
        },
        load: function(callback){
            this.refresh(callback);
            this.loaded = true;
        }
    });

    Melody.Component.register("lazy",Lazy);

})(window,Melody,Object);
