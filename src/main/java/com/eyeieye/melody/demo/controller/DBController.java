package com.eyeieye.melody.demo.controller;


import com.eyeieye.melos.util.StringUtil;
import com.eyeieye.melos.web.url.URLBroker;
import com.mongodb.DBObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("db")
public class DBController {


    @Autowired
    URLBroker appServer;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping("introduce")
    public void introPage(ModelMap modelMap){

        System.out.println(123);

    }


    @RequestMapping(value="mysql", method = RequestMethod.GET)
    public void mysqlTestPage(ModelMap modelMap){
       List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from test_table");
        System.out.printf("123123");
        modelMap.put("result",list);
    }

    @RequestMapping(value="mysql",method = RequestMethod.POST)
    public String mysqlTest(String name, String tip, ModelMap modelMap){
        if(StringUtil.isEmpty(name) == true){
            return "redirect:"+appServer.get("/db/mysql.htm");
        }
        if(StringUtil.isEmpty(tip) == true){
            tip = "";
        }
        String sql = "insert into test_table (name,tips) values ('"+name+"','"+tip+"')";
        System.out.printf(sql);
        jdbcTemplate.execute(sql);
        return "redirect:"+appServer.get("/db/mysql.htm");
    }

    @RequestMapping(value="mongo",method = RequestMethod.GET)
    public void mongoTestPage(ModelMap modelMap){
        List<DBObject> list = mongoTemplate.getCollection("books").find().toArray();
        modelMap.put("books",list);
    }

    @RequestMapping(value = "mongo", method = RequestMethod.POST)
    public String mongoTest(ModelMap modelMap, String bookName, String price){
        Book book = new Book();
        book.setBookName(bookName);
        book.setPrice(price);
        mongoTemplate.insert(book,"books");
        return "redirect:"+appServer.get("/db/mongo.htm");
    }



    class Book{
        private Integer id;
        private String bookName;
        private String price;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

    @RequestMapping("mongodb/main")
    public void mongoMain(ModelMap modelMap){

    }
    @RequestMapping("mongodb/config")
    public void mongoConfig(ModelMap modelMap){

    }
    @RequestMapping("mysql/main")
    public void mysqlMain(ModelMap modelMap){

    }
    @RequestMapping("mysql/config")
    public void mysqlConfig(ModelMap modelMap){

    }




}
