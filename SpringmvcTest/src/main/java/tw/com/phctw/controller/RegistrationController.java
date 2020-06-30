package tw.com.phctw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tw.com.phctw.model.Student;
import tw.com.phctw.service.StudentService;

@Controller
public class RegistrationController {
	
	@Autowired
	public StudentService service;
	
	@GetMapping(value = "/register")
	public ModelAndView showRegister(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("student", new Student());
		
		return mv;
	}
	
	@PostMapping(value="/registerProcess")
	public ModelAndView addStudent(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("student")Student student) {
		System.out.println("in process...");
		ModelAndView mv = new ModelAndView();
		
		
		//check if student is exist
		if(!service.checkSaccExist(student.getSacc())) {
			service.register(student);
			System.out.println(student);
			//mv = new ModelAndView("redirect:/student/get/"+student.getSno());
			//mv.addObject("student", student);
			mv = new ModelAndView("redirect:/");
		} else {
			mv = new ModelAndView("register");
			mv.addObject("message", "AccountName or Password is wrong!!");
		}
		
		
		return mv;
	}
	
	@ResponseBody  
	@PostMapping(value = "/student/isExist", produces = "application/json;charset=UTF-8")   
	public boolean isExist(Model model,@RequestParam("sacc") String sacc) { 
		System.out.println(sacc);
	    boolean exist = service.checkSaccExist(sacc);
	    System.out.println(exist);
	    if(exist) {  
	        return true;  
	    }  
	    return false;  
	} 

	
}
