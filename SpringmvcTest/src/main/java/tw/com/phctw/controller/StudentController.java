package tw.com.phctw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tw.com.phctw.model.Student;
import tw.com.phctw.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService service;
	
	
	@GetMapping(value = "/query")
	public ModelAndView query(){
		ModelAndView mv = new ModelAndView("showInfo");
		List<Student> students = service.getStudentDaoImpl().getAllStudents();
		mv.addObject("students", students);
		
		return mv;
	}
	
	@RequestMapping(value = "/student/get/{sno}")
	public ModelAndView get(@PathVariable("sno") String sno){
		ModelAndView mv = new ModelAndView("showOneInfo");
		Student student = service.getStudentDaoImpl().getStudentBySno(sno);
		mv.addObject("student", student);
		
		return mv;
	}
	
	@RequestMapping(value = "/student/{confirm}")
	public ModelAndView confirmPage(@PathVariable("confirm") String confirm,HttpServletRequest req, HttpServletResponse resp) throws IOException{
		ModelAndView mv = null;
		System.out.println("confirming...");
		Student s = service.getStudentDaoImpl().getStudentByConf(confirm);
		System.out.println(s);
		if(s==null) {
			mv = new ModelAndView("redirect:/login");
			mv.addObject("message", "No Account Found.");
		}else if(service.confirmAcc(s)) {
			
			mv = new ModelAndView("redirect:/login");
			mv.addObject("message", "Confirm Successfull, please log in again.");
//			mv.addObject("student", s);
		}else {
			mv = new ModelAndView("redirect:/login");
			mv.addObject("message", "Confirm Failed");
		}
		return mv;
	}
}
