package com.esmile.controller;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esmile.model.dao.SampleDao;;

@Controller // (1)
public class EchoController {
	    @RequestMapping
	    public String showMessage(Model model) { 
	     // ①コンテキスト取得
	        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
	        // ②インスタンスの取得
	        SampleDao dao = context.getBean(SampleDao.class);
	        
	        // ③DAOメソッド実行
	        List names = dao.select();
	        
	        model.addAttribute("name",names); // (8)
	                
	        return "showMessage";
	    }
	    
}
