abstract class Person {
    private String name;    //the person's name
    private char gender;    //the person's gender M or F
    private int age;        //the person's age

    /*
    constructor with 3 parameters setting the attributes and checking if they are valid
     */
    Person(String givenName, char givenGender, int givenAge) throws Exception
    {
        if(givenAge<=0)throw  new Exception("Invalid age");
        if(givenGender!='M'&&givenGender!='F') throw new Exception("invalid gender");

        name = givenName;
        gender = givenGender;
        age = givenAge;
    }

    /*
    a get method returning the name of the person
     */
    String getName() {
        return name;
    }

    /*
    method returning the person's gender
     */
    char getGender() {
        return gender;
    }

    /*
    method returning the person's age
     */
    int getAge() {
        return age;
    }

    /*
    method replacing the age attribute with the parameter
     */
    void setAge(int givenAge) {
        age = givenAge;
    }
}