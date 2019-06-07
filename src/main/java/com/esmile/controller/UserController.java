package com.esmile.controller;


import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esmile.model.dao.SampleDao;
import com.esmile.service.UserService;;

@Controller 
@RequestMapping("UserController")
public class UserController {
	
	@Autowired
	private UserService UserService;
	
	@RequestMapping
	public String showMessage(HttpServletResponse res,Model model) { 
		 // ①コンテキスト取得
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
		// ②インスタンスの取得
		SampleDao dao = context.getBean(SampleDao.class);
		
		// ③DAOメソッド実行
		List names = dao.select();
		
		model.addAttribute("name",names); 
		System.out.println(res);
		        
		return "showMessage";
	}
	
	@RequestMapping(value = "List", method = RequestMethod.POST)
	@ResponseBody
	public String getTestData() {
		
		System.out.println("List");

	    String data = "test1";

	    return data;
	}
}
