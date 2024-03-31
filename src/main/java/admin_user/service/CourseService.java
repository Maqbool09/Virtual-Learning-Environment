package admin_user.service;

import admin_user.model.Course;
import admin_user.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseService {
	
	@Autowired
	private CourseRepository cRepo;
	
	public void save(Course b) {
		cRepo.save(b);
	}
	
	public List<Course> getAllCourse(){
		return cRepo.findAll();
	}
	
	public Course getCourseById(int id) {
		return cRepo.findById(id).get();
	}
	public void deleteById(int id) {
		cRepo.deleteById(id);
	}
}
