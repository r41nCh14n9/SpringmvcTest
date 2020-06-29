package tw.com.phctw.controller;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/student")
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
	
	@RequestMapping(value = "/get/{sno}")
	public ModelAndView get(@PathVariable("sno") String sno){
		ModelAndView mv = new ModelAndView("showOneInfo");
		Student student = service.getStudentDaoImpl().getStudentBySno(sno);
		mv.addObject("student", student);
		
		return mv;
	}
	
}
