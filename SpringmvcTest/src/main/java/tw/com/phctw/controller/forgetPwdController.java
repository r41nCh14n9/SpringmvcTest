package tw.com.phctw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tw.com.phctw.model.Login;
import tw.com.phctw.model.Student;
import tw.com.phctw.service.StudentService;

@Controller
public class forgetPwdController {

	@Autowired
	StudentService service;

	@GetMapping(value = "/forgetPwd")
	public ModelAndView showForgetPage(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView("forgetPwd");
		mv.addObject("student", new Student());
		
		return mv;
	}

	@PostMapping(value = "/resetPwdProcess")
	public ModelAndView resetPwdProcess(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("student") Student student) {
		System.out.println("in Process...");
		ModelAndView mv = null;

		Student std = service.checkForgotenStd(student.getSacc(), student.getSmail());
		if (std!=null) {
			service.resetPwd(std);
			mv = new ModelAndView("redirect:/");
		} else {
			mv = new ModelAndView("forgetPwd");
			mv.addObject("msg", "AccountName or Student Mail is invalid!!");
		}
		return mv;
	}
	
	@ResponseBody  
	@PostMapping(value = "/forget/isExist", produces = "application/json;charset=UTF-8")   
	public boolean isExist(Model model,
			@RequestParam("sacc") String sacc, @RequestParam("smail") String smail) { 
		System.out.println(sacc + " : " + smail);
	    boolean exist = (service.checkForgotenStd(sacc, smail)!=null);
	    System.out.println(exist);
	    if(exist) {  
	        return true;  
	    }  
	    return false;  
	} 

}
