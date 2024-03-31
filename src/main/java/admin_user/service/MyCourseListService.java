package admin_user.service;

import admin_user.model.MyCourseList;
import admin_user.repositories.MyCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class MyCourseListService {
	
	@Autowired
	private MyCourseRepository myCourse;
	
	public void saveMyCourses(MyCourseList course) {
		myCourse.save(course);
	}
	
	public List<MyCourseList> getAllMyCourses(){
		return myCourse.findAll();
	}
	
	public void deleteById(int id) { myCourse.deleteById(id);
	}
}
