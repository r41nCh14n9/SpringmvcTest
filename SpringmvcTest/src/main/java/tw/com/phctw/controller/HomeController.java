package tw.com.phctw.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tw.com.phctw.config.MVCConfig;
import tw.com.phctw.dao.StudentDao;
import tw.com.phctw.dao.StudentDaoImpl;
import tw.com.phctw.model.Student;
 
@Controller
public class HomeController {
	 
	@RequestMapping("/home")
	public ModelAndView homeView() {
		
		ModelAndView mv = new ModelAndView("home");
//		mv.addObject("message", message);
		
		return mv;
	}
	
	
	
	

}