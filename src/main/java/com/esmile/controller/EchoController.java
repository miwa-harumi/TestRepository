package com.esmile.controller;


import java.text.ParseException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esmile.model.dao.SampleDao;
import com.esmile.model.dto.SampleDto;

@Controller // (1)
public class EchoController {
	    @RequestMapping
	    public String showMessage(Model model) throws ParseException { 
	     // ①コンテキスト取得
	        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
	        // ②インスタンスの取得
	        SampleDao dao = context.getBean(SampleDao.class);
	        
	        SampleDto a = new SampleDto();
	        
	        a.setId(2);
	        a.setLast_Name("井手");
	        a.setFirst_Name("啓介");
	        a.setPhone_Number("09040860921");
	        a.setMail("unko@gmail.com");
	        a.setSex(1);
	        a.setBirthdate("2017/03/02 01:23:45");
	        a.setBirthplace("風が吹いているどこか");
	        a.setIntroduction("なんで負けたか明日までに考えといてください。ほないただきます。");
	        a.setSkill("野球とじゃんけん勝率99.6%");
	        a.setOpen_Range(2);
	        a.setPasswprd("password");
	        a.setIcon_Image("icon");
	        a.setHeader_Image("hedaer");
	        a.setCompany_Id(1);
	        a.setCreated_at(null);
	        a.setUpdated_at(null);
	        // ③DAOメソッド実行
	        int names = dao.regist(a);
	        
	        model.addAttribute("name",names); // (8)
	                
	        return "showMessage";
	    }
	    
}
