import java.util.ArrayList;
import java.util.Collections;

class Student extends Person {
    private ArrayList<Integer> certificates;    //an ArrayList containing the subject's IDs which the student has passed
    Course course;

    /*
    a constructor calling the super with the parameters
     */
    Student(String givenName, char givenGender, int givenAge) throws Exception {
        super(givenName, givenGender, givenAge);
        certificates = new ArrayList<Integer>();
    }

    /*
    method for graduating a course in a subject adding its ID to the other certificates
     */
    void graduate(Subject subject) {
        certificates.add(subject.getID());
        course = null;
    }

    /*
    method for enrolling in a course
     */
    void enrollInCourse(Course givenCourse){
        course = givenCourse;
    }
    /*
    get method returning the cartificates ArrayList
     */
    ArrayList<Integer> getCertificates() {
        return certificates;
    }

    /*
    a boolean method checking if the student has passed the subject sent as a parameter
     */
    boolean hasCertificate(Subject subject) {
        for (Integer i : certificates) {
            if (i == subject.getID()) return true;
        }
        return false;
    }

    /*
    get method returning the course the student attends
     */
    Course getCourse(){
        return course;
    }
}