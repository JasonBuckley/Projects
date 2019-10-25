package School;

import java.util.ArrayList;

/*
    Author: Jason Buckley

    Abstract:  This is a simple instructor class that holds a instructors ID number, name, email, phone number,
    and what courses they are teaching.  It also provides the needed methods for retrieving the instructors
    information, and methods to assign them courses to teach.

    Date: 12 October 2019
 */

public class Instructor {
    private int instructorID;
    private String name;
    private String email;
    private String phoneNumber;
    private ArrayList<Course> coursesTeaching;

    protected Instructor(int id, String name, String email, String number){
        instructorID = id;
        this.name = name;
        this.email = email;
        phoneNumber = number;
        coursesTeaching = new ArrayList<Course>();
    }

    public int getID(){
        return this.instructorID;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void assignCourse(Course course){ //assigns an instructor a course
        coursesTeaching.add(course);
        course.setTeacher(this);
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        String str = "School.Instructor Number: " + instructorID + "\nSchool.Instructor: " + name + "\nCourses Teaching:";
        for(Course course: coursesTeaching){
            str+=  "\n" +  course;
        }

        return str;
    }
}
