package hello.spring.mvc.echo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // (1)
public class EchoController {

	 @ModelAttribute // (2)
	    public EchoForm setUpEchoForm() {
	        EchoForm form = new EchoForm();
	        return form;
	    }

	    @RequestMapping // (3)
	    public String index(Model model) {
	        return "echo/input"; // (4)
	    }

	    @RequestMapping(value = "echo/echo/RTA", method = RequestMethod.POST) // (5)
	    public String echo(@Validated EchoForm form, BindingResult result, Model model) { // (6)

	        if (result.hasErrors()) { // (7)
	            return "echo/input";
	        }

	        model.addAttribute("name", form.getName()); // (8)
	        return "echo/echo/RTA";
	    }
	    
}
