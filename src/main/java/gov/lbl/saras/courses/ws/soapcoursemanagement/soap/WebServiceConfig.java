package gov.lbl.saras.courses.ws.soapcoursemanagement.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import gov.lbl.saras.courses.CourseDetailsType;

/*
 * GOAL: Register a bean in the spring application context. This ServletRegistrationBean contains the MessageDispatcherServlet
 * instance and the URI mapping to dispatch to. 
 * 
 * When a SOAP message comes in, the ServletRegistrationBean is looked up to determine which messageDispatcherServlet is 
 * configured to handle the request (destined for a specific URI).  The MessageDispatcherServlet instance is contained in the 
 * ServletRegistrationBean. 
 * 
 * To configure this, we define a method that takes in the ApplicationContext and returns an instance of
 * ServletRegistrationBean. This method is annotated as @Bean, so Spring Context will pick it up during component scan. 
 * 
 * In the method itself, we instantiate the MessageDispatcherServlet and use its setter to set the passed in ApplicationContext 
 * as a property. We then instantiate a ServletRegistrationBean object and pass it the MessageDispatcherServlet object 
 * and the URI (as String) that it will dispatch SOAP requests to. The method returns ServletRegistrationBean object.
 * 
 * The class level annotation @Configuration indicates to Spring that this class has one or more @Bean annotations that 
 * need to be processed.
 * 
 */

@EnableWs // Add this annotation to an @Configuration class to have the Spring Web
			// Services configuration defined in WsConfigurationSupport imported.
@Configuration
public class WebServiceConfig {
	// Configure MessageDispatcherServlet to dispatch to URI /ws/*
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) { // should really use a better
																							// name
		// for this method.
		// Get an instance of MessageDispatcherServlet and set its application context
		// to the one for this app.
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		// Transform relative address locations in the WSDL using the request URI
		messageDispatcherServlet.setTransformWsdlLocations(true);
		// Register the messageDispatcherServlet and its mapped URI with spring
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}

	@Bean(name = "courses") // generates courses.wsdl public
	DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("CoursePort");
		definition.setTargetNamespace("http://saras.lbl.gov/courses");
		definition.setLocationUri("/ws");
		definition.setSchema(coursesSchema);
		return definition;
	}

	@Bean
	public XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}
	
	@Bean
	public CourseDetailsType courseDetailsBean() {
		return new CourseDetailsType();
	}

	/*
	 * To use a pre-generated WSDL instead. Available at
	 * http://localhost:8080/ws/ucpath.wsdl
	 */
	/*
	 * @Bean public SimpleWsdl11Definition ucpath() { // available as
	 * methodName.wsdl return new SimpleWsdl11Definition(new
	 * ClassPathResource("WSDLs/v1_05/UCIDMService_V1.05.wsdl")); }
	 */
}
