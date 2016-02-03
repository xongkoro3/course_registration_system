import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @version 1.0
 * @since 12/08/2015
 */
public class SearchCourseSystem {

    /**
     * connect to sqlite db
     */
    private final DBCoordinator dbCoordinator = new DBCoordinator();

    /**
     *
     * @param courseID
     * @param courseName
     * @param location
     * @param term
     * @param department
     * @param classType
     * @param instructorName
     * @return A list of ArrayList, in the ArrayList, each entry stores one query result in the table property order.
     */
    public List<ArrayList<String>> queryCourse(int courseID, String courseName, String location, String term, String department,
                                        String classType, String instructorName) {
        List<ArrayList<Object>> retFromDb = new ArrayList<>();
        try {
            retFromDb = dbCoordinator.queryData("SELECT * FROM COURSE WHERE ID=\"" + String.valueOf(courseID) +
                    "\" AND NAME=\"" + courseName + "\" AND LOCATION =\"" + location + "\" AND TERM=\"" + term + "\" AND DEPARTMENT=\"" + department +
                    "\" AND TYPE=\"" + classType + "\"");
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
