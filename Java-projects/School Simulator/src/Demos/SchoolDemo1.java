package Demos;

import School.School;

public class SchoolDemo1
{
  public static void main(String[] args)
  {
    School SCD = new School("SCD");

    System.out.println("===== Read Data 1 =====");
    SCD.readData("tmp\\test1.txt");

    System.out.println("\n===== School.School Info 1 =====");
    SCD.schoolInfo();

    System.out.println("\n===== Read Data 2 =====");
    SCD.readData("tmp\\test2.txt");

    System.out.println("\n===== School.School Info 2 =====");
    SCD.schoolInfo();

    System.out.println("\n===== Search by email =====");
    SCD.searchByEmail("ybyun@csumb.edu");

    System.out.println("\n===== Search by email (fail) =====");
    SCD.searchByEmail("byun@csumb.edu");

    System.out.println("\n===== End of Demos.SchoolDemo1 =====");
  }
}
