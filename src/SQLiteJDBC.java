import java.sql.*;
import java.text.ParseException;

public class SQLiteJDBC
{
  public static void main( String args[] ) throws ParseException
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:SCRSDataBase.db");
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String createStudentTableSql = "CREATE TABLE STUDENT " +
                   "(ID INT PRIMARY KEY     NOT NULL," +
                   " FIRSTNAME      TEXT    NOT NULL, " + // Only contains alphabets
                   " LASTNAME       TEXT    NOT NULL, " + // Only contains alphabets
                   " DATEOFBIRTH    DATE    NOT NULL, " + // Format: mm/dd/yyyy
                   " TYPE           CHAR(10) NOT NULL CHECK (TYPE IN ('Undergrad', 'Master', 'PHD')), " +  // Seminar or Lecture  
                   " GENDER         CHAR(15) CHECK (GENDER IN ('Male', 'Female', 'Transgender')), " + // Male, Female or Transgender
                   " ADVISOR        CHAR(20), " + 
                   " CREDITS        INT      NOT NULL CHECK (CREDITS >= 0), " + // Credits' value in [0, 1]
                   " DEPARTMENT     CHAR(50) NOT NULL CHECK (DEPARTMENT IN ('CS')))"; 
      stmt.executeUpdate(createStudentTableSql);
      
      String createAdminTableSql = "CREATE TABLE ADMINISTRATOR " +
              "(ID INT PRIMARY KEY     NOT NULL," +
              " FIRSTNAME      TEXT    NOT NULL, " + // Only contains alphabets
              " LASTNAME       TEXT    NOT NULL, " + // Only contains alphabets
              " DATEOFBIRTH    DATE    NOT NULL, " + // Format: mm/dd/yyyy
              " GENDER         CHAR(15) CHECK (GENDER IN ('Male', 'Female', 'Transgender')), " + 
              " DEPARTMENT     CHAR(50) NOT NULL CHECK (DEPARTMENT IN ('CS')))"; 
      stmt.executeUpdate(createAdminTableSql);
      
      String createInstructorTableSql = "CREATE TABLE INSTRUCTOR " +
              "(ID INT PRIMARY KEY     NOT NULL," +
              " FIRSTNAME      TEXT    NOT NULL, " +  // Only contains alphabets
              " LASTNAME       TEXT    NOT NULL, " +  // Only contains alphabets
              " DATEOFBIRTH    DATE    NOT NULL, " +  // Format: mm//dd/yyyy
              " GENDER         CHAR(15) CHECK (GENDER IN ('Male', 'Female', 'Transgender')), " + 
              " TITLE          CHAR(20) CHECK (TITLE IN ('Professor')), " +
              " SALARY         INT(1), " +
              " DEPARTMENT     CHAR(50) NOT NULL CHECK (DEPARTMENT IN ('CS')))"; 
      stmt.executeUpdate(createInstructorTableSql);
      
      String createCourseTableSql = "CREATE TABLE COURSE " +
              "(ID INT PRIMARY KEY     NOT NULL," +
              " NAME      	   CHAR(50) NOT NULL, " + // Only contains alphabets
              " CREDITS        INT NOT NULL CHECK (CREDITS > 0 AND CREDITS <= 4), " + 
              " CAPACITY       INT NOT NULL CHECK (CAPACITY > 0 AND CAPACITY <= 30), " +
              " TERM           CHAR(20) NOT NULL, " +
              " FIRSTDAY       DATE NOT NULL, " + // Format: mm/dd/yyyy
              " LASTDAY        DATE NOT NULL, " + // Format: mm/dd/yyyy
              " CLASSBEGINTIME CHAR(10) NOT NULL, " + // Format: "hh:mm"
              " CLASSENDTIME   CHAR(10) NOT NULL, " + // Format: "hh:mm"
              " ROUTINES       CHAR(15) NOT NULL, " + // Format: "Tu, Fri"
              " LOCATION       CHAR(100) NOT NULL , " + // Format: E.g. East Bank KHKH3-301
              " TYPE           CHAR(20) NOT NULL CHECK (TYPE IN ('Seminar', 'Lecture')), " + // Seminar or Lecture
              " PREREQUISITE   TEXT, " +
              " DESCRIPTION    TEXT NOT NULL, " +
              " DEPARTMENT     CHAR(50) NOT NULL CHECK (DEPARTMENT IN ('CS')))"; 
      stmt.executeUpdate(createCourseTableSql);

      String createStudentCourseTableSql = "CREATE TABLE STUDENTANDCOURSE " +
              "(ID INTEGER PRIMARY KEY AUTOINCREMENT    NOT NULL," +
              " COURSEID  INT REFERENCES COURSE(ID) ON UPDATE CASCADE, " +
              " GRADING CHAR(10) NOT NULL CHECK (GRADING IN ('A-F', 'S/N', 'AUD')), " + // A-F S/N Aud
              " COURSETERM CHAR(20) NOT NULL, " + // Format: E.g. Spring2015, Fall2016
              " STUDENTID INT REFERENCES STUDENT(ID) ON UPDATE CASCADE)";
      stmt.executeUpdate(createStudentCourseTableSql);
      
      String createInstructorCourseTableSql = "CREATE TABLE INSTRUCTORANDCOURSE " +
              "(ID INTEGER PRIMARY KEY AUTOINCREMENT    NOT NULL," +
              " COURSEID  INT REFERENCES COURSE(ID) ON UPDATE CASCADE, " +
              " INSTRUCTORID INT REFERENCES INSTRUCTOR(ID) ON UPDATE CASCADE)";
      stmt.executeUpdate(createInstructorCourseTableSql);
      
      String createShibbolethAuthTableSql = "CREATE TABLE SHIBBOLETHAUTH " +
              "(ID INTEGER PRIMARY KEY AUTOINCREMENT    NOT NULL," +
              " X500ACCOUNT    CHAR(50) NOT NULL, " + // Only contains alphabets and numbers
              " X500PASSWORD   CHAR(20) NOT NULL, " + // Only contains alphabets and numbers
              " USERID         INT NOT NULL," + // If the user is either student and administrator, we just store its administrator's ID
              " USERTYPE       CHAR(10) NOT NULL CHECK (USERTYPE IN ('STUDENT', 'ADMIN', 'BOTH')))"; // STUDENT, ADMIN, and BOTH
            
      stmt.executeUpdate(createShibbolethAuthTableSql);
      
      stmt.close();
    } catch ( Exception e ) {
    	e.printStackTrace();
      //System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      //System.exit(0);
    }
    final {
      c.close();
    }
    System.out.println("Table created successfully");
  }
}