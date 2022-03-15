class Subject {
    private int id;             //the unique ID of the subject
    private int specialism;     //the specialism ID of the subject
    private int duration;       //the number of days required for any course covering the subject
    private String description; //string attribute describing the subject

    /*
    a constuctor with 3 parameters checking if they are valid and setting the attributes
     */
    Subject(int givenID, int givenSpecialism, int givenDuration) throws Exception
    {
        if (givenID<=0)throw new Exception("Invalid ID");
        if (givenSpecialism<=0)throw new Exception("Invalid specialism ID");
        if (givenDuration<=0)throw  new Exception("Invalid Duration");
        id = givenID;
        specialism = givenSpecialism;
        duration = givenDuration;
    }

    /*
    a get method returning the ID of the subject
     */
    int getID() {
        return id;
    }

    /*
    a get method returning the specialism ID of the subject
     */
    int getSpecialism() {
        return specialism;
    }

    /*
    a get method returning the number of days a course covering the subject would last
     */
    int getDuration() {
        return duration;
    }

    /*
    a get method returning the description
     */
    String getDescription() {
        return description;
    }

    /*
    a set method to replace the description with a new one
     */
    void setDescription(String newDescription) {
        description = newDescription;
    }

}