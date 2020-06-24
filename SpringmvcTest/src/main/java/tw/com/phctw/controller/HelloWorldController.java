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
public class HelloWorldController {
	String message = "Welcome to Spring MVC!";
	
	@Autowired
	StudentDaoImpl dao;
	 
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
 
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		
		//test
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MVCConfig.class);
//		StudentDao studentDao = context.getBean(StudentDao.class);
//		studentDao.getAllStudents().forEach(System.out::println);
		
		
//		List<Student> students = dao.getAllStudents();
//		students.forEach(System.out::println);
		
		
		return mv;
	}
	
	
	
	

}