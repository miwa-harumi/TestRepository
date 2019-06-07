package hello.spring.mvc.echo;

import java.io.File;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.spring.mvc.SampleDao;

@Controller // (1)
public class EchoController {

	 @ModelAttribute // (2)
	    public EchoForm setUpEchoForm() {
	        EchoForm form = new EchoForm();
	        return form;
	    }

	    @RequestMapping // (3)
	    public String index(Model model) {
	    	// ①コンテキスト取得
	        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
	        // ②インスタンスの取得
	        SampleDao dao = context.getBean(SampleDao.class);
	        
	        // ③DAOメソッド実行
	        List names = dao.select();
	        
	        // ④検索結果を出力
	        System.out.println(names);
	        
	        
	        return "echo/input"; // (4)
	    }

	    @RequestMapping(value = "echo/echo", method = RequestMethod.POST) // (5)
	    public String echo(@Validated EchoForm form, BindingResult result, Model model) { // (6)

	        if (result.hasErrors()) { // (7)
	            return "echo/input";
	        }

	        model.addAttribute("name", form.getName()); // (8)
	        
	        
	        return "echo/echo";
	    }
	    
}
