package admin_user.controller;

import java.security.Principal;
import java.util.List;

import admin_user.model.Course;
import admin_user.model.MyCourseList;
import admin_user.service.CourseService;
import admin_user.service.MyCourseListService;
import admin_user.service.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import admin_user.dto.UserDto;
import admin_user.service.UserService;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

@Controller
public class UserController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService service;

	@Autowired
	private MyCourseListService myCourseService;

	@Autowired
	private StreamingService streamingService;

	@GetMapping(value = "video/{title}", produces = "video/mp4")
	public Mono<Resource> getVideos(@PathVariable String title, @RequestHeader("Range") String range) {
		System.out.println("range in bytes() : " + range);
		return streamingService.getVideo(title);
	}


	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/registration")
	public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
		return "register";
	}
	
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
		userService.save(userDto);
		model.addAttribute("message", "Registered Successfuly!");
		return "register";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/my_courses")
	public String getMyCourses(Model model)
	{
		List<MyCourseList>list=myCourseService.getAllMyCourses();
		model.addAttribute("course",list);
		return "myCourses";
	}

	
	@GetMapping("user-page")
	public String userPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "home";
	}

	
	@GetMapping("admin-page")
	public String adminPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "courseRegister";
	}

	@GetMapping("/course_register")
	public String courseRegister() {
		return "courseRegister";
	}

	@RequestMapping("/deleteMyList/{id}")
	public String deleteMyList(@PathVariable("id") int id) {
		service.deleteById(id);
		return "redirect:/my_courses";
	}
	@RequestMapping("/editCourse/{id}")
	public String editCourse(@PathVariable("id") int id,Model model) {
		Course b=service.getCourseById(id);
		model.addAttribute("course",b);
		return "courseEdit";
	}

	@PostMapping("/save")
	public String addCourse(@ModelAttribute Course b) {
		service.save(b);
		return "redirect:/available_courses";
	}

	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Course b=service.getCourseById(id) ;
		MyCourseList mb=new MyCourseList(b.getId(),b.getName(),b.getTutor(),b.getPrice());
		myCourseService.saveMyCourses(mb);
		return "redirect:/my_courses";
	}
	@GetMapping("/available_courses")
	public ModelAndView getAllCourses() {
		List<Course> list=service.getAllCourse();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("courseList");
//		m.addObject("course",list);
		return new ModelAndView("courseList","course",list);
	}
}
