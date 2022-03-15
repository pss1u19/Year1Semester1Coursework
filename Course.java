import java.util.ArrayList;
import java.util.Collections;

class Course {
    private Subject subject;                    //subject object containing the subject the course is on
    private int daysUntilStarts;                //the number of days until the course starts
    private int daysToRun;                      //the number of days left until the course is finished
    private ArrayList<Student> students;        //an array containing the student objects attending the course
    private Instructor instructor;              //the instructor teaching the course
    private boolean cancelled = false;          //boolean storing if the course is cancelled

    /*
    a constructor with parameters setting the attributes and checking if they are valid
     */
    Course(Subject givenSubject, int givenDaysUntilStart) throws Exception
    {
        if(givenDaysUntilStart<0) throw new Exception("Invalid days input");
        subject = givenSubject;
        daysUntilStarts = givenDaysUntilStart;
        daysToRun = subject.getDuration();
        students = new ArrayList<Student>();
    }

    /*
    method returning the subject the course is on
     */
    Subject getSubject() {
        return subject;
    }

    /*
    method to cancel the course and release the instructor and students;
     */
    void cancel() {
        if (hasInstructor()) {
            instructor.unassignCourse();
        }
        students.clear();
        cancelled = true;
    }

    /*
    a void method for passing a day with appropriate changes in attributes
     */
    void aDayPasses() {
        if (daysUntilStarts > 1) {
            daysUntilStarts--;
            return;
        } else if (daysUntilStarts == 1) {
            daysUntilStarts--;
            if (!this.hasInstructor() || students.size() == 0) {
                cancel();
                return;
            }
        } else if (daysToRun > 1) {
            daysToRun--;
            return;
        } else if (daysToRun == 1) {
            daysToRun--;
            for (Student s : students) {
                s.graduate(subject);
            }
            instructor.unassignCourse();
        }
    }

    /*
    method checking if the course is cancelled
     */
    boolean isCancelled() {
        return cancelled;
    }

    /*
    get method returning an array of students
     */
    Student[] getStudents() {
        Student[] students2 = new Student[students.size()];
        for (int i = 0; i < students.size(); i++) {
            students2[i] = students.get(i);
        }
        return students2;
    }

    /*
    method returning the number of students enrolled in the course
     */
    int getSize() {
        return students.size();
    }

    /*
    a method checking if its possible to enroll a student passed as a parameter and doing so if possible returning confirmation
     */
    boolean enrolStudent(Student student) {
        if (students.size() == 3) return false;
        else if (daysUntilStarts == 0) return false;
        else {
            student.enrollInCourse(this);
            students.add(student);
            return true;
        }
    }

    /*
    set method setting the days to run
     */
    void setDaysToRun(int givenDays){
        daysToRun = givenDays;
    }

    /*
    method checking if the course has assaigned instructor
     */
    boolean hasInstructor() {
        if (instructor != null) return true;
        else return false;
    }

    /*
    method for setting the parameter instructor as this course's instructor checking if it is possible
     */
    boolean setInstructor(Instructor givenInstructor) {
        if (givenInstructor.canTeach(subject) && givenInstructor.getAssignedCourse() == null) {
            givenInstructor.assignCourse(this);
            instructor = givenInstructor;
            return true;
        } else return false;
    }

    /*
    get method for the instructor
     */
    Instructor getInstructor(){
        return instructor;
    }

    /*
    method for unassigning instructor
     */
    void unassignInstructor() {
        instructor = null;
    }

    /*
    method returning in an int form how the course is going
     */
    int getStatus() {
        if (daysUntilStarts >= 1) return -daysUntilStarts;
        else return daysToRun;
    }
}