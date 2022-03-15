import java.util.ArrayList;

public class School {
    private String name;                                //the name of the school
    private ArrayList<Instructor> instructors;  //an ArrayList for all the instructors
    private ArrayList<Student> students;        //an ArrayList for all the students
    private ArrayList<Subject> subjects;        //an ArrayList for all the subjects
    private ArrayList<Course> courses;          //an ArrayList for all the courses

    /*
    constructor initialising the arrays
     */
    School() {
        instructors = new ArrayList<Instructor>();
        students = new ArrayList<Student>();
        subjects = new ArrayList<Subject>();
        courses = new ArrayList<Course>();
    }

    /*
    constructor initialising the arrays and setting the name
     */
    School(String givenName) {
        name = givenName;
        instructors = new ArrayList<Instructor>();
        students = new ArrayList<Student>();
        subjects = new ArrayList<Subject>();
        courses = new ArrayList<Course>();
    }

    /*
    method for adding a student sent as a parameter
     */
    void add(Student student) {
        students.add(student);
    }

    /*
    method for removing the student sent as a parameter
     */
    void remove(Student student) {
        students.remove(student);
    }

    /*
    a get method for the students ArrayList
     */
    ArrayList<Student> getStudents() {
        return students;
    }

    /*
    method for adding a course sent as a parameter
    */
    void add(Course course) {
        courses.add(course);
    }

    /*
    method for removing the course sent as a parameter
     */
    void remove(Course course) {
        courses.remove(course);
    }

    /*
    a get method for the courses ArrayList
     */
    ArrayList<Course> getCourses() {
        return courses;
    }

    /*
    method for adding a subject sent as a parameter
    */
    void add(Subject subject) {
        subjects.add(subject);
    }

    /*
    method for removing the subject sent as a parameter
     */
    void remove(Subject subject) {
        subjects.remove(subject);
    }

    /*
    a get method for the subjects ArrayList
     */
    ArrayList<Subject> getSubjects() {
        return subjects;
    }

    /*
    method for adding a instructor sent as a parameter
    */
    void add(Instructor instructor) {
        instructors.add(instructor);
    }

    /*
    method for removing the instructor sent as a parameter
     */
    void remove(Instructor instructor) {
        instructors.remove(instructor);
    }

    /*
    a get method for the instructors ArrayList
     */
    ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    /*
    a method describing the current status at the school;
     */
    @Override
    public String toString() {
        String description = "the school " + name + " has the following instructors teaching the following courses \n";
        for (Instructor instructor : instructors) {
            if (instructor.getAssignedCourse() != null) {
                description = description +
                        instructor.getName() + " teaches course " + instructor.getAssignedCourse().getSubject().getDescription();
                if (instructor.getAssignedCourse().getStudents().length > 0) {
                    description = description + " to students :";
                    for (Student student : instructor.getAssignedCourse().getStudents()) {
                        description = description + student.getName() + ", ";
                    }
                } else {
                    description = description + " but has no enrolled students";
                }
                if (instructor.getAssignedCourse().getStatus() < 0) {
                    description = description + " which has " + (-instructor.getAssignedCourse().getStatus()) + "to start";
                }else {
                    description = description + "which has "+instructor.getAssignedCourse().getStatus()+"days left";
                }
            } else {
                description = description + "the instructor " + instructor.getName() + " is currently unassigned";
            }
            description = description + "\n";
        }
        for (Course course : courses) {
            if (course.hasInstructor() != true) {
                description = description + "the course on " + course.getSubject().getDescription() + "doesn't have instructor";
            }
            if (course.getStudents().length > 0) {
                description = description + "but has the following students enrolled ";
                for (Student student : course.getStudents()) {
                    description = description + student.getName() + ", ";
                }
            } else {
                description = description + "and has no enrolled students";
            }
            description = description + " which has " + (course.getStatus()) + "to start";
            description = description + "\n";
        }
        for (Student student : students) {
            if (student.getCourse() == null) {
                description = description + "the student " + student.getName() + " is currently not enrolled in any course ";
            }
        }
        return description;
    }

    /*
    a get method for the name
     */
    String getName() {
        return name;
    }

    /*
    method for simulating a day passing
     */
    void aDayAtSchool() {
        for (Course course : courses) {                                             //going through every course
            if (course.hasInstructor() != true) {
                for (Instructor instructor : instructors) {                         //if the course doesn't have appointed instructor  going to find one available
                    if (instructor.getAssignedCourse() == null && instructor.canTeach(course.getSubject())) {
                        course.setInstructor(instructor);
                        break;
                    }
                }
            }
            if (course.getStudents().length < 3) {
                for (Student student : students) {                                    //if the course doesn't have enough students enrolling available ones
                    if (student.getCourse() != course && student.hasCertificate(course.getSubject()) != true && course.getStatus() < 0) {
                        course.enrolStudent(student);
                    }
                }
            }
            course.aDayPasses();                                                   //simulating a day passing for the course
            if (course.getStatus() == 0 || course.isCancelled()) {                                         //removing the course if it has been finished or cancelled
                courses.remove(course);
            }
        }
    }
}
