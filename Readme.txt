Coursework 2019 by Pavel Stoyanov pss1u19

11 classes
Person and Instructor are abstract
Instructor extends Person
Student extends Person
Teacher extends Person
OOTrainer extends Teacher
GUITrainer extends Teacher
Demonstrator extends Instructor

Subject has 4 private atributes 
ID a number value
SpecialismID a number value
Duration a number value 
Description a String 

constructor setting them wtihout the description checking if the parameters are correct and throwing exception with appropiate message
get methods
and a set method for Description

The abstract class Person has 3 private atributes
name a String 
gender a char
age a number

constructor setting them checking if the parameters are correct and throwing exception with appropriate message
3 get methods for every attribute
1 set method for age

The abstract class Instructor extends Person has additional private attribute
assigned Course the course the instructor teaches

method for getting and setting the course
method for unassignig the course setting it to null and setting the instructor attribute in course to null as well
abstract boolean method canTeach taking Subject as an argument and returning whether the instructor can teach it

Classes Demonstrator and Teacher extend Instructor and overriding the canTeach method

Classes OOTrainer and GUITrainers extend Teacher and overriding the canTeach method

Class Course has 6 private attributes
Subject one for the subject on which the course is on
daysUntillStarts a number value
daysToRun a number value
ArrayList object of students for easy manipulation
Instructor object for the instructor teaching the course and easier manipulation
boolean cancelled if the course is cancelled

constructor setting the attributes, initialising the ArrayList and checking the parameters
method for cancelling the course
method checking if the course is cancelled 
method for simulationg a day
methods for getting and setting the attributes
method for setInstructor has a few checks
method returning a student array build from the ArrayList
method for enrolling students if possible 

class School for manipulating the different courses students subjects and instructors
Has 5 private attributes a name and 4 arrayLists for each one
has 2 constructors initialiasing the ArrayLists and the other one getting a String parameter and setting the name to it

a get set method for every attribute 
Overloading add and remove methods for every type of ArrayList
Overriden toString method pulling information on the current courses instructors and students
aDayAtSchool method coupling appropirate instructors and courses and finding students for them if possible and simulating a passing day by calling every courses' simulation 

The Administrator class manipulates the School object it has as a attribute
it has 2 constructors one with School setting the attribute
and one with a String config creating a new School object and Student Course Instructor and Subject classes to be manipulating it

it has 2 methods for generating a random String and an either 'F' or 'M' char used in the run method for the new arrivals

run method for simulation a day at the school new Arriving students and or intructors

OverLoaded method run which runs the simulation for the amount of days sent as parameters

a load and save method which pull all the data of the current simulation and save it in an encripted save.txt

main method in which the arguments sent through the console for file name and a number to be used to be generated a config and the simulation to be run for the said number of days 




 