import javax.management.relation.Role;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhixingma on 12/7/15.
 */

/**
 * @version 1.0
 * @since 12/08/2015
 */
public class InfoSystem {
    /**
     * connect to sqlite db
     */
    private final DBCoordinator dbCoordinator = new DBCoordinator();

    /**
     *
     * @param studentID
     * @return A list of ArrayList, in the ArrayList, each entry stores one query result in the table property order.
     */
    public List<ArrayList<String>> queryStudentPersonalData(int studentID) {
        List<ArrayList<Object>> retFromDb = new ArrayList<>();
        try {
            retFromDb = dbCoordinator.queryData("SELECT * FROM STUDENT WHERE ID=\"" + String.valueOf(studentID) + "\"");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        List<ArrayList<String>> res = new ArrayList<>();
        for (ArrayList<Object> list : retFromDb) {
            ArrayList<String> temp = new ArrayList<>();
            for (Object obj : list) {
                temp.add(obj.toString());
            }
            res.add(temp);
        }
        return res;
    }

    /**
     *
     * @param studentID
     * @return A list of ArrayList, in the ArrayList, each entry stores one query result in the table property order.
     */
    public List<ArrayList<String>> queryStudentRegistrationHistory(int studentID) {
        List<ArrayList<Object>> retFromDb = new ArrayList<>();
        try {
            retFromDb = dbCoordinator.queryData("SELECT * FROM STUDENTANDCOURSE WHERE ID=\"" + String.valueOf(studentID) + "\"");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        List<ArrayList<String>> res = new ArrayList<>();
        for (ArrayList<Object> list : retFromDb) {
            ArrayList<String> temp = new ArrayList<>();
            for (Object obj : list) {
                temp.add(obj.toString());
            }
            res.add(temp);
        }
        return res;
    }

    /**
     *
     * @param adminID
     * @return A list of ArrayList, in the ArrayList, each entry stores one query result in the table property order.
     */
    public List<ArrayList<String>> queryAdminPersonalData(int adminID) {
        List<ArrayList<Object>> retFromDb = new ArrayList<>();
        try {
            retFromDb = dbCoordinator.queryData("SELECT * FROM ADMINISTRATOR WHERE ID=\"" + String.valueOf(adminID) + "\"");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        List<ArrayList<String>> res = new ArrayList<>();
        for (ArrayList<Object> list : retFromDb) {
            ArrayList<String> temp = new ArrayList<>();
            for (Object obj : list) {
                temp.add(obj.toString());
            }
            res.add(temp);
        }
        return res;
    }

    /**
     *
     * @param instructorID
     * @return A list of ArrayList, in the ArrayList, each entry stores one query result in the table property order.
     */
    public List<ArrayList<String>> queryInstructor(int instructorID) {
        List<ArrayList<Object>> retFromDb = new ArrayList<>();
        try {
            retFromDb = dbCoordinator.queryData("SELECT * FROM INSTRUCTOR WHERE ID=\"" + String.valueOf(instructorID) + "\"");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        List<ArrayList<String>> res = new ArrayList<>();
        for (ArrayList<Object> list : retFromDb) {
            ArrayList<String> temp = new ArrayList<>();
            for (Object obj : list) {
                temp.add(obj.toString());
            }
            res.add(temp);
        }
        return res;
    }
}