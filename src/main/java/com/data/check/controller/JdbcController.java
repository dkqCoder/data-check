package com.data.check.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/testMerChant")
public class JdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/userList")
    @ResponseBody
    public List<Map<String, Object>> userList() {
        String sql = "select * from merchant_info";
        List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);
        return list_maps;
    }

    //增加一名用户
    @GetMapping("/addUser")
    public String addUser() {
        String sql = "insert into merchant_info(n_merchant_id,s_nick_name,s_password) values (4,'小明','123456')";
        jdbcTemplate.update(sql);
        return "update-ok";
    }
}
