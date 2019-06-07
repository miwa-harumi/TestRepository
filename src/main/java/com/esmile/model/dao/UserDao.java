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
		String data = a.getBirthdate();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = sdFormat.parse(data);
		
		System.out.println("Date型 = " + date);
		
		jdbcTemplate.update(
				"insert into user(user_id,last_name,first_name,phone_number,"
						+ "mail,sex,birthdate,birthplace,introduction,skill,open_range,password,"
						+ "icon_image,header_image,company_id,created_at,update_at)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				a.getId(), a.getLast_Name(), a.getFirst_Name(), a.getPhone_Number(), a.getMail(), a.getSex(), date,
				a.getBirthplace(), a.getIntroduction(), a.getSkill(), a.getOpen_Range(), a.getPassword(),
				a.getIcon_Image(), a.getHeader_Image(), a.getCompany_Id(), a.getCreated_at(), a.getUpdated_at());
		
		System.out.println("insert");
		return 0;
	}
}