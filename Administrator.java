import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Administrator {
    School school;      //the school the administrator runs

    /*
    a constructor setting the school
     */
    Administrator(School givenSchool) {
        school = givenSchool;
    }

    /*
    administrator constructor creating a simulation from a config file
     */
    Administrator(String config) {
        //spliting the file on lines and creating other strings to help with reading
        String[] configFile = config.split("\n");
        String[] line;
        String[] objDesc;

        //going through each line
        for (String s : configFile) {
            line = s.split(":");

            //checking what kind of object to create and add to simulation
            if (line[0].equals("school")) {
                school = new School(line[1]);
            }
            if (line[0].equals("subject")) {
                objDesc = line[1].split(",");
                try {
                    Subject subject = new Subject(Integer.parseInt(objDesc[1]), Integer.parseInt(objDesc[2]), Integer.parseInt(objDesc[3]));
                    subject.setDescription(objDesc[0]);
                    school.add(subject);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (line[0].equals("student")) {
                objDesc = line[1].split(",");
                try {
                    school.add(new Student(objDesc[0], objDesc[1].charAt(0), Integer.parseInt(objDesc[2])));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (line[0].equals("Teacher")) {
                objDesc = line[1].split(",");
                try {
                    school.add(new Teacher(objDesc[0], objDesc[1].charAt(0), Integer.parseInt(objDesc[2])));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (line[0].equals("Demonstrator")) {
                objDesc = line[1].split(",");
                try {
                    school.add(new Demonstrator(objDesc[0], objDesc[1].charAt(0), Integer.parseInt(objDesc[2])));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (line[0].equals("OOTrainer")) {
                objDesc = line[1].split(",");
                try {
                    school.add(new OOTrainer(objDesc[0], objDesc[1].charAt(0), Integer.parseInt(objDesc[2])));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (line[0].equals("GUITrainer")) {
                objDesc = line[1].split(",");
                try {
                    school.add(new GUITrainer(objDesc[0], objDesc[1].charAt(0), Integer.parseInt(objDesc[2])));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /*
    main method reading files set as arguments and running it the number of days set as second argument
     */
    public static void main(String[] args) {
        String configFileName = args[0];
        String configFile = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(configFileName));
            while (bufferedReader.ready()) {
                configFile = configFile + bufferedReader.readLine() + "\n";
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Administrator myAdministrator = new Administrator(configFile);
        myAdministrator.run(Integer.parseInt(args[1]));
    }


    /*
    method for running the school
     */
    void run() {
        Random random = new Random();
        //adding new students
        for (int i = 1; i < random.nextInt(3); i++) {
            try {
                school.add(new Student(nameGenerator(), genderGenerator(), 18 + random.nextInt(82)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        //checking if a new teacher arrives
        if (random.nextInt(100) < 20) {
            try {
                school.add(new Teacher(nameGenerator(), genderGenerator(), 24 + random.nextInt(76)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        //checking if a new Demostrator arrives
        if (random.nextInt(100) < 10) {
            try {
                school.add(new Demonstrator(nameGenerator(), genderGenerator(), 24 + random.nextInt(76)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        //checking if a new OOTrainer arrives
        if (random.nextInt(100) < 5) {
            try {
                school.add(new OOTrainer(nameGenerator(), genderGenerator(), 24 + random.nextInt(76)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        //checking if a new GUITrainer arrives
        if (random.nextInt(100) < 5) {
            try {
                school.add(new GUITrainer(nameGenerator(), genderGenerator(), 24 + random.nextInt(76)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        //simulating a day at school
        school.aDayAtSchool();
        for (Student student : school.getStudents()) {
            boolean graduated = true;
            //checking if someone has graduated
            for (Subject subject : school.getSubjects()) {
                if (student.hasCertificate(subject) != true) {
                    graduated = false;
                    break;
                }
            }
            //graduating the student and checking if someone leaves
            if (graduated) school.remove(student);
            else if (student.getCourse() == null && random.nextInt(100) < 5) {
                school.remove(student);
            }
        }
        //checking if an instructor leaves
        for (Instructor instructor : school.getInstructors()) {
            if (instructor.getAssignedCourse() == null && random.nextInt(100) < 20) {
                school.remove(instructor);
            }
        }
    }

    /*
    a method for selecting a random gender
     */
    private char genderGenerator() {
        Random random = new Random();
        char newPersonGender;
        if (random.nextInt(2) == 0) newPersonGender = 'F';
        else newPersonGender = 'M';
        return newPersonGender;
    }

    /*
    method for creating a random string name between 2 and 12 character
     */
    private String nameGenerator() {
        Random random = new Random();
        String newPersonName = "";
        for (int j = 0; j <= random.nextInt(12) + 1; j++) {
            newPersonName = newPersonName + (char) (65 + random.nextInt(26));
        }
        newPersonName = newPersonName.substring(0, 1).toUpperCase() + newPersonName.substring(1).toLowerCase();
        return newPersonName;
    }

    /*
    method for running the simulation a set number of days
     */
    void run(int numberOfDays) {
        for (int i = 1; i <= numberOfDays; i++) {
            run();
        }
    }

    /*
    method for loading a simulation from a previously created file
     */
    void load() {
        byte[] encodedSave;
        String save = new String();
        try {
            encodedSave = Files.readAllBytes(Paths.get("save.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            save = new String(encodedSave, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        String lines[] = save.split("\n");
        String line[];
        String objDesc[];
        String objDesc1[];
        String objDesc2[];
        String objDesc3[];

        for (String s : lines) {
            line = s.split(":");

            //checking what kind of object to create and add to simulation
            if (line[0].equals("school")) {
                school = new School(line[1]);
            }
            if (line[0].equals("subject")) {
                objDesc = line[1].split(",");
                try {
                    Subject subject = new Subject(Integer.parseInt(objDesc[1]), Integer.parseInt(objDesc[2]), Integer.parseInt(objDesc[3]));
                    subject.setDescription(objDesc[0]);
                    school.add(subject);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (line[0].equals("student")) {

                objDesc1 = line[1].split("_");
                objDesc2 = objDesc1[1].split("-");
                objDesc = objDesc1[0].split(",");
                try {
                    Student student = new Student(objDesc[0], objDesc[1].charAt(0), Integer.parseInt(objDesc[2]));

                    for (String s2 : objDesc2[1].split(",")) {
                        student.getCertificates().add(Integer.parseInt(s2));
                    }
                    school.add(student);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (line[0].equals("Teacher")) {
                objDesc = line[1].split(",");
                try {
                    school.add(new Teacher(objDesc[0], objDesc[1].charAt(0), Integer.parseInt(objDesc[2])));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (line[0].equals("Demonstrator")) {
                objDesc = line[1].split(",");
                try {
                    school.add(new Demonstrator(objDesc[0], objDesc[1].charAt(0), Integer.parseInt(objDesc[2])));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (line[0].equals("OOTrainer")) {
                objDesc = line[1].split(",");
                try {
                    school.add(new OOTrainer(objDesc[0], objDesc[1].charAt(0), Integer.parseInt(objDesc[2])));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (line[0].equals("GUITrainer")) {
                objDesc = line[1].split(",");
                try {
                    school.add(new GUITrainer(objDesc[0], objDesc[1].charAt(0), Integer.parseInt(objDesc[2])));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            try {
                if (line[0].equals("Course")) {
                    objDesc = line[2].split(",");
                    Course course = null;
                    for (Subject subject: school.getSubjects()) {
                        if(subject.getID()==Integer.parseInt(objDesc[0])&&subject.getSpecialism()== Integer.parseInt(objDesc[1])&&subject.getDuration()== Integer.parseInt(objDesc[2])&&subject.getDescription().equals(objDesc[3])){
                            if(Integer.parseInt(line[1])<0){
                                course = new Course(subject,-Integer.parseInt(line[1]));
                            }
                            else {
                                course = new Course(subject, 0);
                                course.setDaysToRun(Integer.parseInt(line[1]));
                            }
                        }
                    }
                    for (int i=0;i< Integer.parseInt(line[3]);i++){
                        objDesc3 = line[4].split("@");
                        objDesc = objDesc3[i].split("_");
                        objDesc1 =objDesc[0].split(",");
                        objDesc2 = objDesc[1].split("-");
                        Student student = new Student(objDesc1[0], objDesc1[1].charAt(0), Integer.parseInt(objDesc1[2]));
                        objDesc3 = objDesc2[1].split(",");
                        for (int j = 0; j <Integer.parseInt(objDesc2[0]) ; j++) {
                            student.getCertificates().add(Integer.parseInt(objDesc3[i]));
                        }
                        course.enrolStudent(student);
                        school.add(student);

                    }
                    if(line[5].equals("Teacher")){
                        objDesc = line[6].split(",");
                        Teacher instructor = new Teacher(objDesc[0], objDesc[1].charAt(0), Integer.parseInt(objDesc[2]));
                        course.setInstructor(instructor);
                    }
                    if(line[5].equals("Demonstrator")){
                        objDesc = line[6].split(",");
                        Demonstrator instructor = new Demonstrator(objDesc[0], objDesc[1].charAt(0), Integer.parseInt(objDesc[2]));
                        course.setInstructor(instructor);
                    }
                    if(line[5].equals("GUITrainer")){
                        objDesc = line[6].split(",");
                        GUITrainer instructor = new GUITrainer(objDesc[0], objDesc[1].charAt(0), Integer.parseInt(objDesc[2]));
                        course.setInstructor(instructor);
                    }
                    if(line[5].equals("OOTrainer")){
                        objDesc = line[6].split(",");
                        OOTrainer instructor = new OOTrainer(objDesc[0], objDesc[1].charAt(0), Integer.parseInt(objDesc[2]));
                        course.setInstructor(instructor);
                    }
                    school.add(course);

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /*
    method for saving going through every course subject student and instructor
    structuring it in a way that can be read
    by the program later
     */
    void save() {
        String save = "";
        save = "school:" + school.getName() + "\n";
        for (Subject subject : school.getSubjects()) {
            save = save + "subject:" + subject.getID() + "," + subject.getSpecialism() + "," + subject.getDuration() + "," + subject.getDescription();
            save = save + "\n";
        }
        for (Course course : school.getCourses()) {

            save = save + "Course:" + course.getStatus() + ":";
            save = save + course.getSubject().getID() + "," + course.getSubject().getSpecialism() + "," + course.getSubject().getDuration() + "," + course.getSubject().getDescription()+":";

            save = save + course.getStudents().length + ":";
            for (Student student : course.getStudents()) {
                save = save + student.getName() + "," + student.getGender() + "," + student.getAge();
                save = save + "_" + student.getCertificates().size() + "-";
                for (Integer i : student.getCertificates()) {
                    save = save + i + ",";
                }
                save = save.substring(0, save.length() - 1);
                save = save + "@";
            }
            save = save + ":";
            if (course.hasInstructor()) {
                save = save + course.getInstructor().getName() + "," + course.getInstructor().getGender() + course.getInstructor().getAge();
            } else save = save + "N/A";
            save = save + "\n";
        }
        for (Student student : school.getStudents()) {
            if (student.getCourse() == null) {
                save = save + "student:";
                save = save + student.getName() + "," + student.getGender() + "," + student.getAge();
                save = save + "_" + student.getCertificates().size() + "-";
                for (Integer i : student.getCertificates()) {
                    save = save + i + ",";
                }
                save = save.substring(0, save.length() - 1);
                save = save + "\n";
            }
        }
        for (Instructor instructor : school.getInstructors()) {
            if (instructor.getAssignedCourse() == null) {
                if (instructor instanceof OOTrainer) {
                    save = save + "OOTrainer";
                } else if (instructor instanceof GUITrainer) {
                    save = save + "GUITrainer";
                } else if (instructor instanceof Demonstrator) {
                    save = save + "Demonstrator";
                } else if (instructor instanceof Teacher) {
                    save = save + "Teacher";
                }
                save = save + ":";
                save = save + instructor.getName() + "," + instructor.getGender() + "," + instructor.getAge() + "\n";
            }

        }
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream("save.txt"));
            printStream.write(save.getBytes(StandardCharsets.UTF_8));
            printStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
