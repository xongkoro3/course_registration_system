import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhixingma on 12/8/15.
 */
public class SCRSImplTest {
    SCRSImpl test = new SCRSImpl();

    @Test
    public void testQueryClass() throws Exception {
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
        expected.add(alist);
        List<ArrayList<String>> actual = test.queryClass(0, "Computer Graphics", "East Campus", "Fall2013", "CS", "Seminar", "Lily");
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryStudentPersonalData() throws Exception {
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
        List<ArrayList<String>> actual = test.queryStudentPersonalData(new ShibbolethAuth.Token(0, ShibbolethAuth.Token.RoleType.STUDENT, "11/21/2013"), 0);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryStudentRegistrationHistory() throws Exception {
        ArrayList<String> alist = new ArrayList<>();
        alist.add("0");
        alist.add("0");
        alist.add("A-F");
        alist.add("Fall2015");
        alist.add("0");
        List<ArrayList<String>> expected = new ArrayList<>();
        expected.add(alist);
        List<ArrayList<String>> actual = test.queryStudentRegistrationHistory(new ShibbolethAuth.Token(0, ShibbolethAuth.Token.RoleType.STUDENT, "11/21/2013"), 0);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryAdminPersonalData() throws Exception {
        ArrayList<String> alist = new ArrayList<>();
        alist.add("0");
        alist.add("Daniel");
        alist.add("Jack");
        alist.add("-62137562400000");
        alist.add("Female");
        alist.add("CS");
        List<ArrayList<String>> expected = new ArrayList<>();
        expected.add(alist);
        ShibbolethAuth.Token token = new ShibbolethAuth.Token(0, ShibbolethAuth.Token.RoleType.ADMIN, "11/21/2013");
        List<ArrayList<String>> actual = test.queryAdminPersonalData(token);
        assertEquals(expected, actual);
    }

    //@Test
    public void testQueryInstructor() throws Exception {

    }

    //@Test
    public void testStudentAddClass() throws Exception {

    }

    //@Test
    public void testStudentDropClass() throws Exception {

    }

    //@Test
    public void testStudentEditClass() throws Exception {

    }

    //@Test
    public void testAdminAddClass() throws Exception {

    }

    //@Test
    public void testAdminDeleteClass() throws Exception {

    }

    //@Test
    public void testAdminEditClass() throws Exception {

    }

    //@Test
    public void testAdminAddStudentToClass() throws Exception {

    }

    //@Test
    public void testAdminEditStudentRegisteredClass() throws Exception {

    }

    //@Test
    public void testAdminDropStudentRegisteredClass() throws Exception {

    }

    //@Test
    public void testAdminEditClass1() throws Exception {

    }
}