class Teacher extends Instructor {

    /*
    a constructor calling the super with the parameters
    */
    Teacher(String givenName, char givenGender, int givenAge) throws Exception {
        super(givenName, givenGender, givenAge);
    }

    /*
    boolean method returning if the subject sent as a parameter can be taught by the teacher
     */
    @Override
    boolean canTeach(Subject subject) {
        if (subject.getSpecialism() == 1 || subject.getSpecialism() == 2) return true;
        else return false;
    }

}