package School;

import School.Instructor;

import java.util.HashMap;

/*
    Author: Jason Buckley

    Abstract: This class is capable of holding course information such as the title, course number, size, location,
    students enrolled, and the teacher assigned.  As well, it provides simple methods for updating, and retrieving
    course information.

    Date: 12 October 2019
 */

public class Course {
    private int courseNumber;
    private String title;
    private int capacity;
    private String location;
    private HashMap<Student,Double> studentsEnrolled;
    private Instructor teacher;

    protected Course(int number, String title, int capacity, String location){
        courseNumber = number;
        this.title = title;
        this.capacity = capacity;
        this.location = location;
        studentsEnrolled = new HashMap<Student,Double>();
    }

    public void updateLocation(String location){
        this.location = location;
    }

    public HashMap<Student,Double> getStudentsEnrolled(){
        return this.studentsEnrolled;
    }

    public int getCourseNumber(){
        return this.courseNumber;
    }

    public void register(Student student){ // adds the student to the course
        this.studentsEnrolled.put(student,0.0);
        student.addCourse(this,0.0);
    }

    public boolean unRegister(Student student){ // removes the student from the course
        student.removeScore(this);
        return studentsEnrolled.remove(student) != null;
    }

    public void setTeacher(Instructor teacher){
        this.teacher = teacher;
    }

    public boolean updateScore(Student student, Double grade){ // updates a students score
        if(student != null && studentsEnrolled.containsKey(student)){
            studentsEnrolled.replace(student,grade);
            student.updateScore(this,grade);
            return true;
        }

        return false;
    }

    public Integer amountEnrolled(){
        return studentsEnrolled.size();
    }

    public Integer getCapacity(){
        return capacity;
    }

    public String getTitle(){
        return title;
    }

    public void details(){ // prints course information
                                            //If the teacher is not null it prints the name, else it prints none
        System.out.println("School.Instructor: " + (teacher != null ? teacher.getName() : "None"));
        System.out.println("School.Course title: " + title);
        System.out.println("Room: " + location);
        System.out.println("Total enrolled: " + studentsEnrolled.size());
        System.out.println("School.Course Average: " + courseAverage());
    }

    private double courseAverage(){ //returns the average grade
        if(studentsEnrolled.size() < 1){
            return 0;
        }

        double sum = 0;

        for(Double score : studentsEnrolled.values()){
            sum+= score;
        }

        return sum/studentsEnrolled.size();
    }

    @Override
    public String toString(){
        return courseNumber + ": " + amountEnrolled() + " enrolled";
    }
}
