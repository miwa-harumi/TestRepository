package com.esmile.model.dao;

import com.esmile.model.dto.ChatDto;
import com.esmile.model.dto.UserDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class CompanyDao {

	// JdbcTemplateの変数宣言
	private JdbcTemplate jdbcTemplate;

	// JdbcTemplateのsetter
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//会社一覧API
	public List<Map<String, Object>> list(JsonNode data) {
		// 引数指定なし
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from company");
		for (Map<String, Object> map : list) {
			
			//会社IDと会社名を表示
			System.out.println(map.get("company_id").toString());
			System.out.println(map.get("name").toString());
		}

		return list;
	}
}