package gov.lbl.saras.courses.ws.soapcoursemanagement.soap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import gov.lbl.saras.courses.Course;
import gov.lbl.saras.courses.CourseDetailsType;
import gov.lbl.saras.courses.GetAllCourseDetailsRequest;
import gov.lbl.saras.courses.GetAllCourseDetailsResponse;
import gov.lbl.saras.courses.GetCourseDetailsRequest;
import gov.lbl.saras.courses.ws.soapcoursemanagement.soap.service.CourseDetailsService;

/*
 This class is an Endpoint that can accept a SOAP request and can send a response.
 Request is processed by a method that has the namespace and request datatype that corresponds to the 
 incoming XML message (request). The namespace and the datatype to match up with is specified as a method 
 level annotation @PayloadRoot You also specify a parameter/arg level annotation, @RequestPayload, for 
 the method annotated as @PayloadRoot to indicate the actual java datatype the XML message maps to. Spring WS 
 uses this for binding XML to the corresponding Java object - i.e. to unmarshall XML to java object.
 Finally, you need to do a similar annotation to marshall Java object returned from your processing, to an XML response.
 You do this with a @ResponsePayload annotation at the method level.
*/ 

@Endpoint
public class CourseDetailsEndpoint {
	// Get user input as GetCourseDetailsRequest
	// Return a response as GetCourseDetailsResponse
	
	// Incoming SOAP message's namespace and localPart are used to determine the handler method in the endpoint.
	// The one where @PayloadRoot annotation has the corresponding namespace and localPart attribute. 
	
	// @RequestPayload indicates that the incoming message will be mapped to the method’s request parameter. This is the 
	// unmarshalling of XML to the corresponding JAVA object
	
	@Autowired 
	CourseDetailsService courseDetailsService;
	@Autowired
	GetCourseDetailsResponse response;

	@PayloadRoot(namespace = "http://saras.lbl.gov/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload  
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		Course course = courseDetailsService.getCourseById(request.getId());
		CourseDetailsType cd = (mapCourseDetails(course));
		response.setCourseDetails(cd);
		return response;
		 
	}
	/**
	 * @param course
	 * @return
	 */
	private CourseDetailsType mapCourseDetails(Course course) {
		// Set some values in the response (GetCourseDetailsResponse) object. Note the JAXB generated class
		// from xsd for GetCourseDetailsResponse has a setCourseDetails method that takes a CourseDetails object as arg.
		// The JAXB generated CourseDetails class (also from xsd), has setters and getters for each individual member variables 
		// of the CourseDetail complexType.
		// So, we will instantiate the CourseDetail object, set the properties of its member variables and then assign the object
		// to response (GetCourseDetails) object, via its setCourseDetails method.
		
//		CourseDetailsType response = new CourseDetailsType();
		// set response object properties and return response.
//		response.setCourseDetails(mapCourse(course));
		return mapCourse(course);
	}
	/**
	 * @param course
	 * @return
	 */
	private CourseDetailsType mapCourse(Course course) {
		CourseDetailsType courseDetails = new CourseDetailsType();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
	@PayloadRoot(namespace = "http://saras.lbl.gov/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload 
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
		
		/*
		 * GetAllCourseDetailsResponse is a list of CourseDetails. We need to get a list of Courses, then for each
		 * item in the list, first instantiate a new CourseDetails object. Then, populate each member variable of the
		 * CourseDetails object with the corresponding variable in Courses. Add the CourseDetails object to the 
		 * CourseDetails list and finally, add the CourseDetailsList to Get
		 */
		
//		List<CourseDetails> courseDetailsList = new ArrayList<>();
		
		List<Course> courseList = new ArrayList<>();
		courseList =  courseDetailsService.getAllCourses();
		// Returns a list
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courseList) {
			response.getCourseDetails().add(mapCourse(course));
			
		}
		return response;
	}

}
