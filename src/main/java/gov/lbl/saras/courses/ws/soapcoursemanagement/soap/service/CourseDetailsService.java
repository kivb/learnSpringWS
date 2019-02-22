package gov.lbl.saras.courses.ws.soapcoursemanagement.soap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Component;

import gov.lbl.saras.courses.Course;

@Component
public class CourseDetailsService {

	private List<Course> courses = new ArrayList<>();
	{
		courses.add(new Course(101, "Java Frameworks", "Common Java Frameworks for the rest of us"));
		courses.add(new Course(102, "Spring In Action", "Spring Java framework programmer Guide"));
		courses.add(new Course(103, "Business Analytics", "Develop Stunning Dashboards with Cognos"));
		courses.add(new Course(104, "Data Integration Recepies", "IBM DataStage Design Patterns"));
		courses.add(new Course(105, "Javascript the good parts", "Javascript developement mastery series"));
	}

	public Course getCourseById(int id) {
		ListIterator courseIterator = courses.listIterator();
		while (courseIterator.hasNext()) {
			Course course = (Course) courseIterator.next();
			if(course.getId() == id) {
				return course;
			}		
		}
		return null;	
	}
	
	public List<Course> getAllCourses(){
		return courses;
	}
}
