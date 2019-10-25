import School.*;

public class TestDriver {
    //====================== PROJECT 1 TEST CASES ======================
    // T1: Test readData() and schoolInfo().
    public static void main(String[] args){
        T1.test();
        T2.test();
        T3.test();
        T4.test();
        T5.test();
        T6.test();
        T7.test();
        T8.test();
        T9.test();
        T10.test();
        T11.test();
        T12.test();
    }

    public static class T1
    {
        public static void test()
        {
            School SCD = new School("SCD");

            SCD.readData("tmp\\test1.txt");

            System.out.println("\n\n===== ANSWER: 1 School.Instructor (Y. Byun), 1 School.Course (CST338), 1 School.Student (Alice Otter) =====\n");
            SCD.schoolInfo();
            System.out.println("==================end of test1=======================");
        }
    }

/* This is "C:\\tmp\\test1.txt"
1
100,Y. Byun,ybyun@csumb.edu,111-111-1111
1
338,CST338 - Software Design,100,BIT 104
1
7777,Alice Otter
*/

    // T2: Test readData() with incorrect data.
    public static class T2
    {
        public static void test()
        {
            School SCD = new School("SCD");

            SCD.readData("tmp\\test1.txt");
            System.out.println("\n\n===== ANSWER: 2 fail messages. One instructor fail, one student fail. =====\n");
            SCD.readData("tmp\\test2.txt");
            System.out.println("==================end of test2=======================");
        }
    }

        /* This is "C:\\tmp\\test2.txt"
        5
        200,B. BBB,bbb@csumb.edu,222-222-2222
        300,C. CCC,ccc@csumb.edu,333-333-3333
        100,A. AAA,aaa@csumb.edu,111-111-0000
        400,D. DDD,ddd@csumb.edu,444-444-4444
        500,E. EEE,eee@csumb.edu,555-555-5555
        7
        301,CST301 - School.Course 301,50,BIT 101
        302,CST302 - School.Course 302,40,BIT 102
        303,CST303 - School.Course 303,30,BIT 104
        338,CST338 - School.Course 338,10,BIT 104
        305,CST305 - School.Course 305,10,BIT 105
        306,CST306 - School.Course 306,10,BIT 101
        307,CST307 - School.Course 307,20,BIT 102
        2
        8888,Bob Otter
        7777,Unknown Otter
        */



    // T3: Test addCourse().
    public static class T3
    {
        public static void test()
        {
            School SCD = new School("SCD");

            SCD.readData("tmp\\test3.txt");

            System.out.println("\n\n===== ANSWER: The program should display three failed course additions. =====\n");
            SCD.addCourse(301, "CST301 - Incorrect School.Course A", 10, "ERROR301");
            SCD.addCourse(308, "CST308 - School.Course 308", 30, "BIT103");
            SCD.addCourse(309, "CST309 - School.Course 309", 40, "BIT104");
            SCD.addCourse(308, "CST308 - Incorrect School.Course B", 10, "ERROR308");
            SCD.addCourse(305, "CST305 - Incorrect School.Course C", 10, "ERROR305");
            SCD.addCourse(310, "CST310 - School.Course 310", 50, "BIT105");

            System.out.println("==================end of test3=======================");
        }
    }

        /* This is "C:\\tmp\\test3.txt"
        5
        100,A. AAA,aaa@csumb.edu,111-111-1111
        200,B. BBB,bbb@csumb.edu,222-222-2222
        300,C. CCC,ccc@csumb.edu,333-333-3333
        400,D. DDD,ddd@csumb.edu,444-444-4444
        500,E. EEE,eee@csumb.edu,555-555-5555
        7
        301,CST301 - School.Course 301,50,BIT 101
        302,CST302 - School.Course 302,40,BIT 102
        303,CST303 - School.Course 303,30,BIT 104
        304,CST304 - School.Course 304,20,BIT 104
        305,CST305 - School.Course 305,10,BIT 105
        306,CST306 - School.Course 306,10,BIT 101
        307,CST307 - School.Course 307,20,BIT 102
        1
        7777,Alice Otter
        */



    // T4: Test addInstructor(), assignInstructor(), and addCourse().
    public static class T4
    {
        public static void test()
        {
            School SCD = new School("SCD");

            SCD.readData("tmp\\test4.txt");

            SCD.addInstructor(200, "B. BBB", "bbb@csumb.edu", "222-222-2222");
            SCD.addInstructor(300, "C. CCC", "ccc@csumb.edu", "333-333-3333");
            SCD.addCourse(302, "CST302 - School.Course 302", 20, "BIT102");
            SCD.addCourse(303, "CST303 - School.Course 303", 30, "BIT103");
            SCD.addCourse(304, "CST304 - School.Course 304", 10, "BIT104");
            SCD.addCourse(305, "CST305 - School.Course 305", 20, "BIT105");
            SCD.assignInstructor (301, 100);
            SCD.assignInstructor (302, 200);
            SCD.assignInstructor (303, 300);
            System.out.println("\n\n===== ANSWER: Two error messages for non-existing instructors. =====\n");
            SCD.assignInstructor (304, 400);
            SCD.assignInstructor (305, 500);

            System.out.println("==================end of test4=======================");
        }
    }

        /* This is "C:\\tmp\\test4.txt"
        1
        100,A. AAA,aaa@csumb.edu,111-111-1111
        1
        301,CST301 - School.Course 301,100,BIT 101
        1
        7777,Alice Otter
        */



    // T5: Test register(), putScore(), getCourse(), updateLocation(), and courseInfo().
    public static class T5
    {
        public static void test()
        {
            School SCD = new School("SCD");
            Course course305;

            SCD.readData("tmp\\test5.txt");

            SCD.assignInstructor (305, 100);
            SCD.register (305, 1111);
            SCD.register (305, 2222);
            SCD.putScore (305, 1111, 100.0);
            SCD.putScore (305, 2222, 50);

            course305 = SCD.getCourse(305);
            course305.updateLocation("ERROR ROOM");
            course305.updateLocation("BIT 104");

            System.out.println("\n\n===== ANSWER: CST305 (A. AAA, School.Course 305) at BIT 104 with 2 students (AVG: 75.00) =====\n");
            SCD.courseInfo(305);

            System.out.println("==================end of test5=======================");
        }
    }

        /* This is "C:\\tmp\\test5.txt"
        5
        100,A. AAA,aaa@csumb.edu,111-111-1111
        200,B. BBB,bbb@csumb.edu,222-222-2222
        300,C. CCC,ccc@csumb.edu,333-333-3333
        400,D. DDD,ddd@csumb.edu,444-444-4444
        500,E. EEE,eee@csumb.edu,555-555-5555
        7
        301,CST301 - School.Course 301,50,BIT 101
        302,CST302 - School.Course 302,40,BIT 102
        303,CST303 - School.Course 303,30,BIT 104
        304,CST304 - School.Course 304,20,BIT 104
        305,CST305 - School.Course 305,10,ERROR
        306,CST306 - School.Course 306,10,BIT 101
        307,CST307 - School.Course 307,20,BIT 102
        2
        1111,AAA AAAAA
        2222,FFF FFFFF
        */



    // T6: Test courseInfo() for all courses.
    public static class T6
    {
        public static void test()
        {
            School SCD = new School("SCD");

            SCD.readData("tmp\\test6.txt");
            SCD.assignInstructor (301, 300);
            SCD.assignInstructor (302, 300);
            SCD.assignInstructor (303, 300);
            SCD.register (301, 1111);
            SCD.register (301, 2222);
            SCD.register (301, 3333);
            SCD.register (302, 3333);
            SCD.register (302, 2222);
            SCD.register (302, 1111);
            SCD.register (303, 1111);
            SCD.register (303, 2222);
            SCD.register (303, 3333);
            SCD.unRegister  (303, 1111);
            SCD.unRegister  (303, 2222);
            SCD.unRegister  (303, 3333);

            System.out.println("\n\n===== ANSWER: 3 Courses with 301 (3 enrolled), 302 (3 enrolled) and 303 (0 enrolled) =====\n");
            SCD.courseInfo();

            System.out.println("==================end of test6=======================");
        }
    }

        /* This is "C:\\tmp\\test6.txt"
        5
        100,A. AAA,aaa@csumb.edu,111-111-1111
        200,B. BBB,bbb@csumb.edu,222-222-2222
        300,C. CCC,ccc@csumb.edu,333-333-3333
        400,D. DDD,ddd@csumb.edu,444-444-4444
        500,E. EEE,eee@csumb.edu,555-555-5555
        3
        301,CST301 - School.Course 301,3,BIT 101
        302,CST302 - School.Course 302,3,BIT 102
        303,CST303 - School.Course 303,3,BIT 104
        3
        1111,AA AAAA
        2222,BB BBBB
        3333,CC CCCC
        */



    // T7: Test deleteCourse().
    public static class T7
    {
        public static void test()
        {
            School SCD = new School("SCD");

            SCD.readData("tmp\\test7.txt");
            SCD.register (301, 1111);
            SCD.register (301, 2222);

            System.out.println("\n\n===== ANSWER: 1 course deletion should be failed. =====\n");
            SCD.deleteCourse(301);
            SCD.deleteCourse(302);

            System.out.println("==================end of test7=======================");
        }
    }

        /* This is "C:\\tmp\\test7.txt"
        1
        300,C. CCC,ccc@csumb.edu,333-333-3333
        2
        301,CST301 - School.Course 301,30,BIT 101
        302,CST302 - School.Course 302,30,BIT 102
        2
        1111,AA AAAA
        2222,BB BBBB
        */



    // T8: Test addStudent().
    public static class T8
    {
        public static void test()
        {
            School SCD = new School("SCD");

            SCD.readData("tmp\\test8.txt");
            SCD.addStudent(2222, "BB BBBB");
            SCD.addStudent(3333, "CC CCCC");
            SCD.addStudent(4444, "DD DDDD");
            SCD.addStudent(5555, "FF FFFF");

            SCD.assignInstructor (301, 300);
            SCD.register (301, 1111);
            SCD.register (301, 2222);
            SCD.register (301, 3333);
            SCD.register (301, 4444);
            SCD.register (301, 5555);

            SCD.putScore (301, 1111, 95.0);
            SCD.putScore (301, 2222, 85.0);
            SCD.putScore (301, 3333, 75.0);
            SCD.putScore (301, 4444, 65.0);
            SCD.putScore (301, 5555, 55.0);

            System.out.println("\n\n===== ANSWER: School.Course 301 with 5 students enrolled (AVG: 75.00) =====\n");
            SCD.courseInfo(301);

            System.out.println("==================end of test8=======================");
        }
    }

        /* This is "C:\\tmp\\test8.txt"
        1
        300,C. CCC,ccc@csumb.edu,333-333-3333
        4
        301,CST301 - School.Course 301,300,BIT 101
        302,CST302 - School.Course 302,300,BIT 102
        303,CST303 - School.Course 303,300,BIT 103
        304,CST304 - School.Course 304,300,BIT 104
        1
        1111,AA AAAA
        */



    // T9: Test getStudent().
    public static class T9
    {
        public static void test()
        {
            School SCD = new School("SCD");

            SCD.readData("tmp\\test9.txt");

            SCD.assignInstructor (301, 300);
            SCD.assignInstructor (302, 300);
            SCD.register (301, 1111);
            SCD.register (301, 2222);
            SCD.register (301, 3333);
            SCD.register (302, 2222);

            SCD.putScore (301, 1111, 95.0);
            SCD.putScore (301, 2222, 85.5);
            SCD.putScore (301, 3333, 75.0);
            SCD.putScore (302, 2222, 84.5);


            Student student1 = SCD.getStudent(2222);
            System.out.println("\n\n===== ANSWER: School.Student BB BBBB - 2 courses enrolled. (AVG: 85.0)  =====\n");
            System.out.println(student1);

            System.out.println("==================end of test9=======================");
        }
    }

        /* This is "C:\\tmp\\test9.txt"
        1
        300,C. CCC,ccc@csumb.edu,333-333-3333
        4
        301,CST301 - School.Course 301,300,BIT 101
        302,CST302 - School.Course 302,300,BIT 102
        303,CST303 - School.Course 303,300,BIT 103
        304,CST304 - School.Course 304,300,BIT 104
        3
        1111,AA AAAA
        2222,BB BBBB
        3333,CC CCCC
        */



    // T10: Test searchByEmail().
    public static class T10
    {
        public static void test()
        {
            School SCD = new School("SCD");

            SCD.readData("tmp\\test10.txt");
            System.out.println("\n\n===== ANSWER: No employee with the email =====\n");
            SCD.searchByEmail("ccc@csu.edu");

            System.out.println("\n\n===== ANSWER: Employee Number: 300, Name: CCC =====\n");
            SCD.searchByEmail("ccc@csumb.edu");

            System.out.println("==================end of test10=======================");
        }
    }

        /* This is "C:\\tmp\\test10.txt"
        5
        200,B. BBB,bbb@csumb.edu,222-222-2222
        300,C. CCC,ccc@csumb.edu,333-333-3333
        100,A. AAA,aaa@csumb.edu,111-111-0000
        400,D. DDD,ddd@csumb.edu,444-444-4444
        500,E. EEE,eee@csumb.edu,555-555-5555
        1
        301,CST301 - School.Course 301,300,BIT 101
        1
        1111,AA AAAA
        */


    // T11: Test graduateStudent().
    public static class T11
    {
        public static void test()
        {
            School SCD = new School("SCD");

            SCD.readData("tmp\\test11.txt");

            SCD.assignInstructor (301, 300);
            SCD.assignInstructor (302, 300);
            SCD.register (301, 1111);
            SCD.register (301, 2222);
            SCD.register (302, 1111);
            SCD.register (302, 2222);

            SCD.putScore (301, 1111, 100.0);
            SCD.putScore (301, 2222, 100.0);
            SCD.putScore (302, 1111, 100.0);
            SCD.putScore (302, 2222, 100.0);

            SCD.graduateStudent(1111);

            System.out.println("\n\n===== ANSWER: There are 2 courses. Each course has only one student. =====\n");
            SCD.courseInfo();

            System.out.println("==================end of test11=======================");
        }
    }

        /* This is "C:\\tmp\\test11.txt"
        1
        300,C. CCC,ccc@csumb.edu,333-333-3333
        2
        301,CST301 - School.Course 301,300,BIT 101
        302,CST302 - School.Course 302,300,BIT 102
        3
        1111,AA AAAA
        2222,BB BBBB
        3333,CC CCCC
        */


    // T12: Test deleteCourse().
    public static class T12
    {
        public static void test()
        {
            School SCD = new School("SCD");

            SCD.readData("tmp\\test12.txt");

            SCD.assignInstructor (301, 300);
            SCD.assignInstructor (302, 300);
            SCD.register (301, 1111);
            SCD.register (301, 2222);

            SCD.putScore (301, 1111, 100.0);
            SCD.putScore (301, 2222, 100.0);

            SCD.deleteCourse(301);
            SCD.deleteCourse(302);
            System.out.println("\n\n===== ANSWER: Only 1 course (301 with 2 enrolled). =====\n");
            SCD.courseInfo();

            System.out.println("==================end of test12=======================");
        }
    }

        /* This is "C:\\tmp\\test12.txt"
        1
        300,C. CCC,ccc@csumb.edu,333-333-3333
        2
        301,CST301 - School.Course 301,300,BIT 101
        302,CST302 - School.Course 302,300,BIT 102
        3
        1111,AA AAAA
        2222,BB BBBB
        3333,CC CCCC
        */
}
