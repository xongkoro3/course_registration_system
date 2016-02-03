import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @version 1.0
 * @since 12/08/2015
 */
public class DropCourseSystem {
    /**
     * connect to the sqlite db
     */
    private final DBCoordinator dbCoordinator = new DBCoordinator();

    /**
     *
     * @param token
     * @param courseID
     * @return Return true if the operation is successfully, false otherwise
     */
    public boolean studentDrop(ShibbolethAuth.Token token, int courseID) {
        String sqlCmd = "DELETE FROM STUDENTANDCOURSE WHERE COURSEID = ? AND STUDENTID = ?; ";

        ArrayList<String> dataList = new ArrayList<String>();
        ArrayList<Constants.PrimitiveDataType> typeList = new ArrayList<Constants.PrimitiveDataType>();

        dataList.add(Integer.toString(courseID));
        typeList.add(Constants.PrimitiveDataType.INT);
        dataList.add(Integer.toString(token.id));
        typeList.add(Constants.PrimitiveDataType.INT);

        try {
            dbCoordinator.deleteData(sqlCmd, dataList, typeList);
        } catch (ClassNotFoundException err) {
            System.err.println("ClassNotFoundException: " + err.getMessage());
            return false;
        } catch (SQLException err) {
            System.err.println("SQLException: " + err.getMessage());
            return false;
        } catch (java.text.ParseException err) {
            System.err.println("ParseException: " + err.getMessage());
            return false;
        }

        ArrayList<String> dataList1 = new ArrayList<String>();
        ArrayList<Constants.PrimitiveDataType> typeList1 = new ArrayList<Constants.PrimitiveDataType>();


        String sqlCmd1 = "SELECT CREDITS FROM STUDENT WHERE ID = \"" + Integer.toString(token.id) + "\"";
        List<ArrayList<Object>> creditsList1 = new ArrayList<>();
        try {
            creditsList1 = dbCoordinator.queryData(sqlCmd1);
        } catch (ClassNotFoundException err) {
            System.err.println("ClassNotFoundException: " + err.getMessage());
            return false;
        } catch (SQLException err) {
            System.err.println("SQLException: " + err.getMessage());
            return false;
        }
        int studentcredits = Integer.valueOf(creditsList1.get(0).get(0).toString());

        String sqlCmd2 = "SELECT CREDITS FROM COURSE WHERE ID = \"" + Integer.toString(courseID) + "\"";
        List<ArrayList<Object>> creditsList2 = new ArrayList<>();
        try {
            creditsList2 = dbCoordinator.queryData(sqlCmd2);
        } catch (ClassNotFoundException err) {
            System.err.println("ClassNotFoundException: " + err.getMessage());
            return false;
        } catch (SQLException err) {
            System.err.println("SQLException: " + err.getMessage());
            return false;
        }
        if(creditsList2.size() == 0)
            return false;
        int coursecredits = Integer.valueOf(creditsList2.get(0).get(0).toString());

        int fcredits = studentcredits - coursecredits;
        String sqlCmd3 = "UPDATE STUDENT SET CREDITS = ? WHERE ID = ?";

        dataList1.add(Integer.toString(fcredits));
        typeList1.add(Constants.PrimitiveDataType.INT);

        try {
            dbCoordinator.updateData(sqlCmd3, dataList1, typeList1);
        } catch (ClassNotFoundException err) {
            System.err.println("ClassNotFoundException: " + err.getMessage());
            return false;
        } catch (SQLException err) {
            System.err.println("SQLException: " + err.getMessage());
            return false;
        } catch (java.text.ParseException err) {
            System.err.println("ParseException: " + err.getMessage());
            return false;
        }

        return true;
    }

    /**
     *
     * @param token
     * @param studentID
     * @param courseID
     * @return Return true if the operation is successfully, false otherwise
     */
    public boolean adminDropStudentRegisteredClass(ShibbolethAuth.Token token, int studentID, int courseID) {
        String sqlCmd = "DELETE FROM STUDENTANDCOURSE WHERE COURSEID = ? AND STUDENTID = ?;";

        ArrayList<String> dataList = new ArrayList<String>();
        ArrayList<Constants.PrimitiveDataType> typeList = new ArrayList<Constants.PrimitiveDataType>();

        dataList.add(Integer.toString(courseID));
        typeList.add(Constants.PrimitiveDataType.INT);

        dataList.add(Integer.toString(token.id));
        typeList.add(Constants.PrimitiveDataType.INT);

        try {
            dbCoordinator.deleteData(sqlCmd, dataList, typeList);
        } catch (ClassNotFoundException err) {
            System.err.println("ClassNotFoundException: " + err.getMessage());
            return false;
        } catch (SQLException err) {
            System.err.println("SQLException: " + err.getMessage());
            return false;
        } catch (java.text.ParseException err) {
            System.err.println("ParseException: " + err.getMessage());
            return false;
        }
        String sqlCmd1 = "SELECT CREDITS FROM STUDENT WHERE ID = \"" + Integer.toString(token.id) + "\"";
        List<ArrayList<Object>> creditsList1 = new ArrayList<>();
        try {
            creditsList1 = dbCoordinator.queryData(sqlCmd1);
        } catch (ClassNotFoundException err) {
            System.err.println("ClassNotFoundException: " + err.getMessage());
            return false;
        } catch (SQLException err) {
            System.err.println("SQLException: " + err.getMessage());
            return false;
        }
        int studentcredits = Integer.valueOf(creditsList1.get(0).get(0).toString());

        String sqlCmd2 = "SELECT CREDITS FROM COURSE WHERE ID = \"" + Integer.toString(courseID) + "\"";
        List<ArrayList<Object>> creditsList2 = new ArrayList<>();
        try {
            creditsList2 = dbCoordinator.queryData(sqlCmd2);
        } catch (ClassNotFoundException err) {
            System.err.println("ClassNotFoundException: " + err.getMessage());
            return false;
        } catch (SQLException err) {
            System.err.println("SQLException: " + err.getMessage());
            return false;
        }
        int coursecredits = Integer.valueOf(creditsList2.get(0).get(0).toString());

        int fcredits = studentcredits - coursecredits;
        String sqlCmd3 = "UPDATE STUDENT SET CREDITS = ? WHERE ID = ?";

        ArrayList<String> dataList1 = new ArrayList<String>();
        ArrayList<Constants.PrimitiveDataType> typeList1 = new ArrayList<Constants.PrimitiveDataType>();

        dataList1.add(Integer.toString(fcredits));
        typeList1.add(Constants.PrimitiveDataType.INT);


        try {
            dbCoordinator.updateData(sqlCmd3, dataList1, typeList1);
        } catch (ClassNotFoundException err) {
            System.err.println("ClassNotFoundException: " + err.getMessage());
            return false;
        } catch (SQLException err) {
            System.err.println("SQLException: " + err.getMessage());
            return false;
        } catch (java.text.ParseException err) {
            System.err.println("ParseException: " + err.getMessage());
            return false;
        }

        return true;
    }

    /**
     *
     * @param token
     * @param courseID
     * @return Return true if the operation is successfully, false otherwise
     */
    public boolean adminDelete(ShibbolethAuth.Token token, int courseID) {
        String sqlCmd = "DELETE FROM COURSE WHERE ID = ?;";

        ArrayList<String> dataList = new ArrayList<String>();
        ArrayList<Constants.PrimitiveDataType> typeList = new ArrayList<Constants.PrimitiveDataType>();

        dataList.add(Integer.toString(courseID));
        typeList.add(Constants.PrimitiveDataType.INT);

        try {
            dbCoordinator.deleteData(sqlCmd, dataList, typeList);
        } catch (ClassNotFoundException err) {
            System.err.println("ClassNotFoundException: " + err.getMessage());
            return false;
        } catch (SQLException err) {
            System.err.println("SQLException: " + err.getMessage());
            return false;
        } catch (java.text.ParseException err) {
            System.err.println("ParseException: " + err.getMessage());
            return false;
        }
        return true;
    }

}
