package gov.lbl.saras.courses.ws.soapcoursemanagement.soap;

import org.springframework.stereotype.Component;

import gov.lbl.saras.courses.CourseDetailsType;

@Component
public class GetCourseDetailsResponse {
	private CourseDetailsType courseDetails;
	

	public GetCourseDetailsResponse(CourseDetailsType courseDetails) {
		super();
		this.courseDetails = courseDetails;
	}

	public CourseDetailsType getCourseDetails() {
		return courseDetails;
	}

	public void setCourseDetails(CourseDetailsType courseDetails) {
		this.courseDetails = courseDetails;
	}
	
}
