import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhixingma on 12/8/15.
 */
public class SCRSImplTest {

    SCRSImpl test = new SCRSImpl();
    ShibbolethAuth.Token tokenStudent = new ShibbolethAuth.Token(0, ShibbolethAuth.Token.RoleType.STUDENT, "11/21/2013");
    ShibbolethAuth.Token tokenStudent1 = new ShibbolethAuth.Token(0, ShibbolethAuth.Token.RoleType.STUDENT, "11/21/2013");
    ShibbolethAuth.Token tokenAdmin = new ShibbolethAuth.Token(0, ShibbolethAuth.Token.RoleType.ADMIN, "11/21/2013");

    @Test
    public void testQueryClass_Exist() throws Exception {
        ArrayList<String> alist = new ArrayList<>();
        alist.add("0");
        alist.add("Computer Graphics");
        alist.add("1");
        alist.add("1");
        alist.add("Fall2013");
        alist.add("1378875600000");
        alist.add("1389420000000");
        alist.add("09:00 Am");
        alist.add("11:00 Am");
        alist.add("Tu, Fri");
        alist.add("East Campus");
        alist.add("Seminar");
        alist.add("");
        alist.add("Good Good Good Class");
        alist.add("CS");
        List<ArrayList<String>> expected = new ArrayList<>();
        List<ArrayList<String>> actual = test.queryClass(0, "Computer Graphics", "East Campus", "Fall2013", "CS", "Seminar", "Lily");
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryClass_NonExist() throws Exception {
        List<ArrayList<String>> expected = new ArrayList<>();
        List<ArrayList<String>> actual = test.queryClass(1, "Computerdf Graphics", "East Campus", "Fall2013", "CS", "Seminar", "Lily");
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryStudentPersonalDataByStudent_Self() throws Exception {
        ArrayList<String> alist = new ArrayList<>();
        alist.add("0");
        alist.add("Jack");
        alist.add("Stark");
        alist.add("-62140154400000");
        alist.add("Undergrad");
        alist.add("Female");
        alist.add("N/A");
        alist.add("5");
        alist.add("CS");
        List<ArrayList<String>> expected = new ArrayList<>();
        expected.add(alist);
        List<ArrayList<String>> actual = test.queryStudentPersonalData(tokenStudent, 0);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryStudentPersonalDataByStudent_Other() throws Exception {
        List<ArrayList<String>> expected = new ArrayList<>();
        List<ArrayList<String>> actual = test.queryStudentPersonalData(tokenStudent, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryStudentPersonalDataByStudentByAdmin_Exist() throws Exception {
        ArrayList<String> alist = new ArrayList<>();
        alist.add("0");
        alist.add("Jack");
        alist.add("Stark");
        alist.add("-62140154400000");
        alist.add("Undergrad");
        alist.add("Female");
        alist.add("N/A");
        alist.add("5");
        alist.add("CS");
        List<ArrayList<String>> expected = new ArrayList<>();
        expected.add(alist);
        List<ArrayList<String>> actual = test.queryStudentPersonalData(tokenAdmin, 0);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryStudentPersonalDataByStudentByAdmin_NonExist() throws Exception {
        List<ArrayList<String>> expected = new ArrayList<>();
        List<ArrayList<String>> actual = test.queryStudentPersonalData(tokenAdmin, 87);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryStudentRegistrationHistoryByStudent_Self() throws Exception {
        ArrayList<String> alist = new ArrayList<>();
        alist.add("0");
        alist.add("0");
        alist.add("AUD");
        alist.add("TestingCourseTermEdited");
        alist.add("0");
        List<ArrayList<String>> expected = new ArrayList<>();
        expected.add(alist);
        List<ArrayList<String>> actual = test.queryStudentRegistrationHistory(tokenStudent1, 0);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryStudentRegistrationHistoryByStudent_Other() throws Exception {
        List<ArrayList<String>> expected = new ArrayList<>();
        List<ArrayList<String>> actual = test.queryStudentRegistrationHistory(tokenStudent1, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryStudentRegistrationHistoryByAdmin_Exist() throws Exception {
        ArrayList<String> alist = new ArrayList<>();
        alist.add("1");
        alist.add("1");
        alist.add("AUD");
        alist.add("Fall2015");
        alist.add("1");
        List<ArrayList<String>> expected = new ArrayList<>();
        expected.add(alist);
        List<ArrayList<String>> actual = test.queryStudentRegistrationHistory(tokenAdmin, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryStudentRegistrationHistoryByAdmin_NonExist() throws Exception {
        List<ArrayList<String>> expected = new ArrayList<>();
        List<ArrayList<String>> actual = test.queryStudentRegistrationHistory(tokenAdmin, 87);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryAdminPersonalDataByStudent() throws Exception {
        List<ArrayList<String>> expected = new ArrayList<>();
        List<ArrayList<String>> actual = test.queryAdminPersonalData(tokenStudent);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryAdminPersonalDataByAdmin() throws Exception {
        ArrayList<String> alist = new ArrayList<>();
        alist.add("0");
        alist.add("Daniel");
        alist.add("Jack");
        alist.add("-62137562400000");
        alist.add("Female");
        alist.add("CS");
        List<ArrayList<String>> expected = new ArrayList<>();
        expected.add(alist);
        List<ArrayList<String>> actual = test.queryAdminPersonalData(tokenAdmin);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryInstructorByStudent_Exist() throws Exception {
        ArrayList<String> alist = new ArrayList<>();
        alist.add("0");
        alist.add("Stark");
        alist.add("Joshua");
        alist.add("-62141018400000");
        alist.add("Female");
        alist.add("Professor");
        alist.add("91992");
        alist.add("CS");
        List<ArrayList<String>> expected = new ArrayList<>();
        expected.add(alist);
        List<ArrayList<String>> actual = test.queryInstructor(tokenStudent, 0);
    }

    @Test
    public void testQueryInstructorByStudent_NonExist() throws Exception {
        List<ArrayList<String>> expected = new ArrayList<>();
        List<ArrayList<String>> actual = test.queryInstructor(tokenStudent, 0);
    }

    @Test
    public void testQueryInstructorByAdmin_Exist() throws Exception {
        ArrayList<String> alist = new ArrayList<>();
        alist.add("0");
        alist.add("Stark");
        alist.add("Joshua");
        alist.add("-62141018400000");
        alist.add("Female");
        alist.add("Professor");
        alist.add("91992");
        alist.add("CS");
        List<ArrayList<String>> expected = new ArrayList<>();
        expected.add(alist);
        List<ArrayList<String>> actual = test.queryInstructor(tokenAdmin, 0);
    }

    @Test
    public void testQueryInstructorByAdmin_NonExist() throws Exception {
        List<ArrayList<String>> expected = new ArrayList<>();
        List<ArrayList<String>> actual = test.queryInstructor(tokenAdmin, 0);
    }

    @Test
    public void testAddClassByStudent() throws Exception {
        boolean actual = test.studentAddClass(tokenStudent, 1, "S/N", "TestingCourseTerm");
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void testDropClassByStudent() throws Exception {
        boolean actual = test.studentDropClass(tokenStudent, 1);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void testEditClassByStudent() throws Exception {
        boolean actual = test.studentEditClass(tokenStudent, 0, "AUD", "TestingCourseTermEdited");
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testAddClassByAdmin() throws Exception {
        test.adminDeleteClass(tokenAdmin, 99);
        boolean actual = test.adminAddClass(tokenAdmin, 99, "CourseNameTest", 3, 30, "TermTest", 5, "FDayTest","LDayTest", "BeginTest","EndTest","WDays","LocationTest", "Seminar","prereqTest","descTest","CS");
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testAdminDeleteClass() throws Exception {
        boolean actual = test.adminDeleteClass(tokenAdmin, 99);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testAdminEditClass() throws Exception {
        test.adminAddClass(tokenAdmin, 99, "CourseNameTest", 3, 30, "TermTest", 5, "FDayTest","LDayTest", "BeginTest","EndTest","WDays","LocationTest", "Seminar","prereqTest","descTest","CS");
        boolean actual = test.adminEditClass(tokenAdmin,99,"Computer Graphics",1,1,"1378875600000","1389420000000","09:00 Am","11:00 Am","Tu, Fri","East Campus","Lecture","preTesting","Good Good Good Class","CS");
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testAdminAddStudentToClass() throws Exception {
        boolean actual = test.adminAddStudentToClass(tokenAdmin, 0, 99, "AUD", "TestingCourseTerm");
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testAdminEditStudentRegisteredClass() throws Exception {
        boolean actual = test.adminEditStudentRegisteredClass(tokenAdmin, 0, 99, "A-F", "TermEdited");
        boolean expected = true;
        assertEquals(expected, actual);
    }
    @Test
    public void testAdminEditStudentUnregisteredClass() throws Exception {
        boolean actual = test.adminEditStudentRegisteredClass(tokenAdmin, 0, 3, "A-F", "TermEdited");
        boolean expected = true;
        assertEquals(expected, actual);
    }
    @Test
    public void testAdminDropStudentRegisteredClass() throws Exception {
        boolean actual = test.adminDropStudentRegisteredClass(tokenAdmin, 0, 99);
        boolean expected = true;
        assertEquals(expected, actual);
    }

}