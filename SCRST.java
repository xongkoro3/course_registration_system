
import java.util.ArrayList;
import java.util.List;

public class SCRST {
    public static void main(String[] args){

        SCRSImpl test = new SCRSImpl();
        ShibbolethAuth.Token tokenStudent = new ShibbolethAuth.Token(0, ShibbolethAuth.Token.RoleType.STUDENT, "11/21/2013");
        ShibbolethAuth.Token tokenAdmin = new ShibbolethAuth.Token(0, ShibbolethAuth.Token.RoleType.ADMIN, "11/21/2013");

//        //Common methods
//        System.out.println("Testing queryClass:");
//        System.out.println("Class 2:");
//        List<ArrayList<String>> classData = test.queryClass(2, "Parallel Computing","East Campus","Fall2015","CS","Lecture"," ");
//        for(ArrayList<String> strs : classData){
//            for(String str : strs) {
//                System.out.print(str + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("==============================");
//        System.out.println("Testing queryStudentPersonalData:");
//        List<ArrayList<String>> PersonalData = test.queryStudentPersonalData(tokenStudent, 0);
//        for(ArrayList<String> strs : PersonalData){
//            for(String str : strs) {
//                System.out.println(str);
//            }
//        }
//        System.out.println("==============================");
//        System.out.println("Testing queryStudentRegistrationHistory:");
//        System.out.println("Student 0's registration history");
//        List<ArrayList<String>> RegisterData = test.queryStudentRegistrationHistory(tokenStudent, 0);
//        for(ArrayList<String> strs : RegisterData){
//            for(String str : strs) {
//                System.out.println(str);
//            }
//        }
//        System.out.println("==============================");
//        System.out.println("Testing queryAdminPersonalData:");
//        System.out.println("Admin 0's data");
//        List<ArrayList<String>> adminData = test.queryAdminPersonalData(tokenAdmin);
//        for(ArrayList<String> strs : adminData){
//            for(String str : strs) {
//                System.out.println(str);
//            }
//        }
//        System.out.println("==============================");
//        //Not implemented
//        System.out.println("Testing queryClass:");
//
//
//        //Student methods Done
//        System.out.println("==============================");
//        System.out.println("Testing studentAddClass:");
//        boolean studentAddClass = test.studentAddClass(tokenStudent, 99, "S/N", "TestingCourseTerm");
//        System.out.println("Student 0 adds course 9: " + String.valueOf(studentAddClass));
//
//        System.out.println("==============================");
//        System.out.println("Testing studentDropCourse:");
//        boolean studentDropCourse = test.studentDropClass(tokenStudent, 99);
//        System.out.println("Student 0 drops course 99 " + String.valueOf(studentDropCourse));
//
//        System.out.println("==============================");
//        System.out.println("Testing studentEditClass:");
//        boolean studentEditCourse = test.studentEditClass(tokenStudent, 99, "AUD", "TestingCourseTermEdited");
//        System.out.println("Student 0 edits course 99 " + String.valueOf(studentEditCourse));
//
//
//        //Admin methods
//        System.out.println("==============================");
//        System.out.println("Testing adminAddClass:");
//        boolean adminAddClass = test.adminAddClass(tokenAdmin, 99, "CourseNameTest", 3, 30, "TermTest", 5, "FDayTest","LDayTest", "BeginTest","EndTest","WDays","LocationTest", "Seminar","prereqTest","descTest","CS");
//        System.out.println("Add Course 99 to Course table: " + String.valueOf(adminAddClass));
//
//        System.out.println("==============================");
//        System.out.println("Testing adminDeleteCourse:");
//        boolean adminDelete = test.adminDeleteClass(tokenAdmin, 99);
//        System.out.println("Delete Course 99: " + String.valueOf(adminDelete));
//
//        System.out.println("==============================");
//        System.out.println("Testing adminDropStudent:");
//        boolean adminDropStudent = test.adminDropStudentRegisteredClass(tokenAdmin, 0, 99);
//        System.out.println("Drop student 0 from course 99: " + String.valueOf(adminDropStudent));
//
//        System.out.println("==============================");
//        System.out.println("Testing adminAddStudent:");
//        //Add student 0 to course 2
//        boolean adminAddStudent = test.adminAddStudentToClass(tokenAdmin, 0, 99, "AUD", "TestingCourseTerm");
//        System.out.println("Add student 0 to course 99: " + String.valueOf(adminAddStudent));
//
//        System.out.println("==============================");
//        System.out.println("Testing adminEditStudent:");
//        //Changing grading and courseTerm
//        boolean adminEditStudent = test.adminEditStudentRegisteredClass(tokenAdmin, 0, 99, "A-F", "TermEdited");
//        System.out.println("Changes student 0 course 99 grading and courseTerm: " + String.valueOf(adminEditStudent));
//
        System.out.println("==============================");
        System.out.println("Testing adminEditClass:");
        boolean adminEditClass = test.adminEditClass(tokenAdmin,99,"Computer Graphics",1,1,"1378875600000","1389420000000","09:00 Am","11:00 Am","Tu, Fri","East Campus","Lecture","preTesting","Good Good Good Class","CS");
        System.out.println("Editing description and type to \"Testing\": " + String.valueOf(adminEditClass));

    }
}
