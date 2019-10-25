package School;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
    Author: Jason Buckley

    Abstract: This class handles the storage, additions, and removals of the students, courses, and Instructors.
    It provides the necessary methods for adding an instructor, or student to a course within the school.  As
    well, it allows the user to acquire information about the entire school, or a specific access information
    about a specific course, student, or instructor.  Lastly it provides helpful errors for when the user tries
    to add a student, course, or instructor with the same identifying number, or they try to search something
    that does not exists.

    Date: 12 October 2019
 */

public class School {
    private String name;
    private ArrayList<Course> courses;
    private ArrayList<Instructor> instructors;
    private ArrayList<Student> students;

    public School(String name){
        this.name = name;
        courses = new ArrayList<Course>();
        instructors = new ArrayList<Instructor>();
        students = new ArrayList<Student>();
    }

    public void readData(String fileName){ // reads in school data from a text file
        File file = new File(fileName);
        try {
            Scanner read = new Scanner(file);

            int length = Integer.parseInt(read.nextLine());
            for(int i = 0; i < length; i++){ //reads in n instructors
                String[] instructorInfoArr = read.nextLine().split(",");
                if(getIndexOfInstructor(Integer.parseInt(instructorInfoArr[0])) > -1) {
                    System.out.println("School.Instructor info reading failed – Employee number " + instructorInfoArr[0] +  " already used.");
                }else{
                    instructors.add(new Instructor(Integer.parseInt(instructorInfoArr[0]), instructorInfoArr[1],
                            instructorInfoArr[2], instructorInfoArr[3]));
                }
            }

            length = Integer.parseInt(read.nextLine());
            for(int i = 0; i < length; i++) { //reads in n courses
                String[] courseInfoArr = read.nextLine().split(",");
                if (getIndexOfCourse(Integer.parseInt(courseInfoArr[0])) > -1){
                    System.out.println("School.Course info reading failed - School.Course number " + courseInfoArr[0] + " already used.");
                }else{
                    courses.add(new Course(Integer.parseInt(courseInfoArr[0]), courseInfoArr[1],
                            Integer.parseInt(courseInfoArr[2]), courseInfoArr[3]));
                }
            }

            length = Integer.parseInt(read.nextLine());
            for(int i = 0; i < length; i++){ //reads in n students
                String[] studentInfoArr = read.nextLine().split(",");
                if(getIndexOfStudent(Integer.parseInt(studentInfoArr[0])) > -1){
                    System.out.println("School.Student info reading failed - School.Student ID " + studentInfoArr[0] + " already used.");
                }else{
                    students.add(new Student(Integer.parseInt(studentInfoArr[0]), studentInfoArr[1]));
                }

            }

            System.out.println("Done.");
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }
    }

    public void schoolInfo(){ //prints the school info
        System.out.println("School.School Name: " + name);
        printInstructors();
        printCourses();
        printStudents();
    }

    private void printInstructors(){ //prints the instructors
        System.out.println("School.Instructor Information:");
        for(Instructor instructor: instructors) {
            System.out.println(instructor.getName());
        }
    }

    private void printCourses(){ //prints the courses
        System.out.println("School.Course Information:");
        for(Course course: courses) {
            System.out.println(course.getTitle());
        }
    }

    private void printStudents(){ //prints the students
        System.out.println("School.Student Information:");
        for(Student student: students) {
            System.out.println(student.getName());
        }
    }

    public void addCourse(int number, String title, int capacity, String location){ //adds a course if it is not already added
        if(getIndexOfCourse(number) > -1){
            System.out.println("School.Course addition failed - School.Course number " + number + " already used.");
        }else {
            courses.add(new Course(number, title, capacity, location));
        }
    }

    public void addInstructor(int instructorID, String name, String email, String number){//adds a instructor if not already added
        if(getIndexOfInstructor(instructorID) > -1){
            System.out.println("School.Instructor already in school");
        }else {
            instructors.add(new Instructor(instructorID, name, email, number));
        }
    }

    public void addStudent(int studentID, String name){ //adds a student if not already added
        if(getIndexOfStudent(studentID) > -1){
            System.out.println("School.Student is already there");
        }else {
            students.add(new Student(studentID, name));
        }
    }

    public void assignInstructor(int courseNumber, int instructorID){ // assigns a instructor to a course
        Instructor instructor = getInstructor(instructorID);
        Course course = getCourse(courseNumber);

        if(course == null){
            System.out.println("School.Course " + courseNumber + " does not exist.");
            return;
        }

        if(instructor != null){
            instructor.assignCourse(getCourse(courseNumber));
        }else{
            System.out.println("School.Instructor " + instructorID + " does not exist.");
        }
    }

    //register(), putScore(), getCourse(), updateLocation(), and courseInfo()

    public void register(int courseNumber, int studentID){ // registers a student to a course if it is not full
        Course course = getCourse(courseNumber);
        Student student = getStudent(studentID);

        if(course == null){
            System.out.println("School.Course " + courseNumber + " does not exist.");
            return;
        }

        if(course.amountEnrolled() == course.getCapacity()){
            System.out.println("Registration Failed - Class is full.");
            return;
        }

        if(student != null){
            course.register(student);
        }else{
            System.out.println("School.Student " + studentID + " does not exist.");
        }
    }

    public void unRegister(int courseNumber, int studentID){ // removes a student from a course
        Student student = getStudent(studentID);
        Course course = getCourse((courseNumber));

        if(course != null){
            if(!course.unRegister(student)){
                System.out.println("School.Student " + studentID + " does not exist");
            }
        }else{
            System.out.println("School.Course " + courseNumber + " does not exists");
        }
    }

    public void putScore(int courseNumber, int studentID, double score){ //applies a score to a student in a course
        Student student = getStudent(studentID);
        Course course = getCourse((courseNumber));

        if(course == null){
            System.out.println("School.Course Error - " + courseNumber + " not found");
            return;
        }

        if(student == null){
            System.out.println("School.Student Error - " + studentID + " not found");
            return;
        }

        if(!course.updateScore(student,score)){
            System.out.println("School.Student " + student.getID() + " (" + student.getName() + ") is not enrolled in " + courseNumber);
        }
    }

    public int getIndexOfCourse(int courseNumber){ //returns the index of the course if it is in the list
        for(int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseNumber() == courseNumber) {
                return i;
            }
        }

        return -1;
    }

    public Course getCourse(int courseNumber){ // gets the course by number if it exists
        int index = getIndexOfCourse(courseNumber);
        return index > -1 ? courses.get(index) : null;
    }

    public void courseInfo(int courseNumber){ // prints out the course info for a given course number
        Course course = getCourse(courseNumber);
        System.out.println("School.Course Number: " + courseNumber);

        if(course != null){
            course.details();
        }else{
            System.out.println("School.Course " + courseNumber + " does not exist");
        }

    }

    public void courseInfo(){ //prints out all the courses, and their enrollment amount
        System.out.println("Number of courses: " + courses.size());

        for(Course course : courses){
            System.out.println(course.getCourseNumber() + ": " + course.amountEnrolled() + " enrolled");
        }

        System.out.println();
    }

    public void deleteCourse(int courseNumber){ //removes the course from the school if no students are enrolled
        int indexOfCourse = getIndexOfCourse(courseNumber);

        if(indexOfCourse != -1){
            if(courses.get(indexOfCourse).amountEnrolled() < 1){
                courses.remove(indexOfCourse);
            }else{
                System.out.println("School.Course deletion failed – Enrolled student(s) in the class");
            }
        }else{
            System.out.println("School.Course " + courseNumber + " does not exist");
        }
    }

    public int getIndexOfStudent(int studentID){ // returns the index of the student if they exist
        for(int i = 0; i < students.size(); i++) {
            if (students.get(i).getID() == studentID) {
                return i;
            }
        }

        return -1;
    }

    public Student getStudent(int studentID){ // returns the student if they exist
        int index = getIndexOfStudent(studentID);
        return index > -1 ? students.get(index) : null;
    }

    public int getIndexOfInstructor(int instructorID){ //returns the index of the instructor if they exist
        for(int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getID() == instructorID) {
                return i;
            }
        }

        return -1;
    }

    public Instructor getInstructor(int instructorID){ //returns the instructor if they exists
        int index = getIndexOfInstructor(instructorID);
        return index > -1 ? instructors.get(index) : null;
    }

    public void searchByEmail(String email){ //searches for a instructor by email
        System.out.println("Search Key: " + email);

        for(Instructor instructor: instructors){
            if(instructor.getEmail().equals(email)){
                System.out.println("Employee Number: " + instructor.getID()
                        + "\nName: " + instructor.getName() + "\nPhone: " + instructor.getPhoneNumber());
                return;
            }
        }
        //prints only if none of the instructors have the corresponding email
        System.out.println("No employee with email " + email);
    }

    public void graduateStudent(int studentID){ // removes the student from the school and courses enrolled in
        int index = getIndexOfStudent(studentID);
        if(index > -1) {
            Student student = students.get(index);
            Course[] studentCourses = student.getEnrolledCourses();
            for(Course course: studentCourses) {
                course.unRegister(student);
            }

            students.remove(index);
        }else{ //prints only if none of the students have the corresponding student ID
            System.out.println("School.Student Error - Cannot graduate student " + studentID + ", because they do not exist");
        }
    }

}