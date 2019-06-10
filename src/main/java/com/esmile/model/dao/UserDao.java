package com.esmile.model.dao;

import com.esmile.model.dto.UserDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

	// ①JdbcTemplateの変数宣言
	private JdbcTemplate jdbcTemplate;

	// ②JdbcTemplateのsetter
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// ③検索処理
	public List select() {
		List ret = jdbcTemplate.queryForList("select id from test", String.class);
		return ret;
	}

	public int regist(UserDto a) throws ParseException {
		jdbcTemplate.update(
				"insert into user(last_name,first_name,phone_number,"
						+ "mail,sex,birthdate,password,"
						+ "company_id,created_at,update_at)"
						+ " values(?,?,?,?,?,?,?,?,?,?)",
				a.getLast_Name(), a.getFirst_Name(), a.getPhone_Number(), a.getMail(), a.getSex(),
				"2010/10/10 10:10:10",a.getPassword(), a.getCompany_Id(), null, null);
		
		return 0;
	}
}