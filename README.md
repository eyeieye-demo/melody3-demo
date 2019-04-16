
![logo](https://raw.githubusercontent.com/eyeieye-demo/melody3-demo/master/src/main/resources/static/favicon.ico)
# Melody-DEMO
### demo版本更新

- 更新了Melody版本至4.0.0
- 该版本修复了国际化页面显示错误的问题，更新了国际化页面的匹配规则：
	- 外文页面可以按照“外文页面文件--默认语言页面文件”的顺序进行适配
	- Layout可以按照“外文Layout文件--默认语言Layout文件--缺省Layout文件”的顺序进行适配
- 更新了国际化的使用说明和示例
- 完善了各页面Layout的国家化显示内容
- 变更了页面文件的位置，从classpath:views/目录移动到了classpath:templates/目录下
- 补充了URLBroker在JavaConfig中的配置方法
- 调整了部分文字描述
- 添加了Melody的logo

项目链接： [https://github.com/eyeieye-demo/melody3-demo](https://github.com/eyeieye-demo/melody3-demo)


# Melody是什么
- Melody是一个基于Springboot的MVC框架，其在Springboot的基础上集成了Thymeleaf、redis、HttpClient等工具。
- 得益于Thymeleaf良好的开发模式，使用Melody可以便捷得实现前后端分离开发模式，加快项目开发速度。
- Melody使用了nosession技术，解决了微服务模式下不同服务间session不同步的问题。
- Melody提供了便捷实现页面国际化的方法，通过简单配置即可使用国际化功能。
- Melody还提供了多种工具类，简化了项目开发中类型处理的难度。
- 在页面制作方面，Facwall使用了Thymeleaf作为模板语言，并与后端进行页面渲染，其中包括以下便捷功能：
 - Layout： 可复用的页面布局模板；
 - contain： 利用后端渲染实现的页面动态拼接功能；
 - Reload： 页面局部刷新功能；
 - Lazyload： 随滚动条触发的页面延迟加载功能。
### 此外，还有更多功能请在Melody-DEMO中查看。
