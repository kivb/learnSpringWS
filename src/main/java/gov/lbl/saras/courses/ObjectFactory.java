//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.21 at 05:47:10 PM PST 
//


package gov.lbl.saras.courses;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gov.lbl.saras.courses package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetCourseDetailsResponse_QNAME = new QName("http://saras.lbl.gov/courses", "GetCourseDetailsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gov.lbl.saras.courses
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllCourseDetailsResponse }
     * 
     */
    public GetAllCourseDetailsResponse createGetAllCourseDetailsResponse() {
        return new GetAllCourseDetailsResponse();
    }

    /**
     * Create an instance of {@link CourseDetailsType }
     * 
     */
    public CourseDetailsType createCourseDetailsType() {
        return new CourseDetailsType();
    }

    /**
     * Create an instance of {@link GetAllCourseDetailsRequest }
     * 
     */
    public GetAllCourseDetailsRequest createGetAllCourseDetailsRequest() {
        return new GetAllCourseDetailsRequest();
    }

    /**
     * Create an instance of {@link GetCourseDetailsRequest }
     * 
     */
    public GetCourseDetailsRequest createGetCourseDetailsRequest() {
        return new GetCourseDetailsRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CourseDetailsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://saras.lbl.gov/courses", name = "GetCourseDetailsResponse")
    public JAXBElement<CourseDetailsType> createGetCourseDetailsResponse(CourseDetailsType value) {
        return new JAXBElement<CourseDetailsType>(_GetCourseDetailsResponse_QNAME, CourseDetailsType.class, null, value);
    }

}
