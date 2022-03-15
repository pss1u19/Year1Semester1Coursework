abstract class Instructor extends Person {
    private Course assignedCourse;      //the course the instructor teaches

    /*
    constructor with 3 parameter calling the super
     */
    Instructor(String givenName, char givenGender, int givenAge) throws Exception {
        super(givenName, givenGender, givenAge);
    }


    /*
    method for assaigning the parameter course to the instuctor
     */
    void assignCourse(Course course) {
        assignedCourse = course;
    }

    /*
    method for unassaigning the course
     */
    void unassignCourse() {
        assignedCourse.unassignInstructor();
        assignedCourse = null;
    }

    /*
    method returning the course the instructor is teaching
     */
    Course getAssignedCourse() {
        return assignedCourse;
    }

    /*
    an abstract method to be overriden in child classes
     */
    abstract boolean canTeach(Subject subject);
}