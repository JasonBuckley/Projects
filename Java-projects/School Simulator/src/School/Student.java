package School;

import School.Course;

import java.util.HashMap;

/*
    Author: Jason Buckley

    Abstract:  This is a simple class that stores a student's information such as their student ID, name, and
    courses they are enrolled in.  As well, it provides the necessary methods for retrieving, and updating
    the students information.

    Date: 12 October 2019
 */

public class Student {
    private int studentID;
    private String name;
    private HashMap<Course,Double> enrolledCourses;

    protected Student(int id, String name){
        studentID = id;
        this.name = name;
        enrolledCourses = new HashMap<Course,Double>();
    }

    public int getID(){
        return this.studentID;
    }

    public void addCourse(Course course, Double grade){
        enrolledCourses.put(course,grade);
    }

    public boolean updateScore(Course course, Double grade){ // updates the student's grade for a corresponding course
        if(enrolledCourses.containsKey(course)){
            enrolledCourses.replace(course,grade);
            return true;
        }

        return false;
    }

    public boolean removeScore(Course course){ //removes the course, and score from the student's enrolled courses
        return enrolledCourses.remove(course) != null;
    }

    public Course[] getEnrolledCourses(){
        return enrolledCourses.keySet().toArray(new Course[0]);
    }

    public String getName(){
        return this.name;
    }

    private double getCoursesAverage(){ //returns the student's average grade across all enrolled courses
        double total = 0;
        for(Double score: enrolledCourses.values()){
            total+=score;
        }

                        //prevents division by zero
        return total/ (enrolledCourses.size() > 0 ? enrolledCourses.size() : 1);
    }

    @Override
    public String toString(){
        String str = "School.Student Number: " + studentID + "\nName: " + name + "\nCourses Enrolled: ";
        for(Course course: enrolledCourses.keySet()){
            str+= "\n" + course.getCourseNumber() + ": " + enrolledCourses.get(course);
        }

        str+= "\nCourses Average: " + getCoursesAverage();
        return str;
    }

}
