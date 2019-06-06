package com.esmile.model.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class SampleDao {
    
    // ①JdbcTemplateの変数宣言
    private JdbcTemplate jdbcTemplate;
    
    // ②JdbcTemplateのsetter
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    // ③検索処理
    public List select(){
        List ret = jdbcTemplate.queryForList("select title from task", String.class);
        return ret;
    }
}