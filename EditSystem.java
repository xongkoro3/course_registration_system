import javax.management.relation.Role;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @since 12/08/2015
 */
public class EditSystem {
    /**
     * connect to sqlite db
     */
    private final DBCoordinator dbCoordinator = new DBCoordinator();

    /**
     * @param token
     * @param courseID
     * @param grading
     * @param courseTerm
     * @return Return true if the operation is successfully, false otherwise
     */
    public boolean studentEditClass(ShibbolethAuth.Token token, int courseID, String grading, String courseTerm) {
        String sqlCmd = "UPDATE STUDENTANDCOURSE SET GRADING = ?, COURSETERM = ? WHERE STUDENTID = ? AND COURSEID = ?; ";

        ArrayList<String> coursePropertyValue = new ArrayList<String>();
        ArrayList<Constants.PrimitiveDataType> coursePropertyType = new ArrayList<Constants.PrimitiveDataType>();

        coursePropertyValue.add(grading);
        coursePropertyType.add(Constants.PrimitiveDataType.STRING);

        coursePropertyValue.add(courseTerm);
        coursePropertyType.add(Constants.PrimitiveDataType.STRING);

        coursePropertyValue.add(String.valueOf(token.id));
        coursePropertyType.add(Constants.PrimitiveDataType.INT);

        coursePropertyValue.add(Integer.toString(courseID));
        coursePropertyType.add(Constants.PrimitiveDataType.INT);

        try {
            dbCoordinator.updateData(sqlCmd, coursePropertyValue, coursePropertyType);
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
     * @param token
     * @param studentID
     * @param courseID
     * @param grading
     * @param courseTerm
     * @return Return true if the operation is successfully, false otherwise
     */
    public boolean adminEditStudentRegisteredClass(ShibbolethAuth.Token token, int studentID, int courseID, String grading, String courseTerm) {
        String sqlCmd = "UPDATE STUDENTANDCOURSE SET GRADING = ?, COURSETERM = ? WHERE STUDENTID = ? AND COURSEID = ?; ";

        ArrayList<String> coursePropertyValue = new ArrayList<String>();
        ArrayList<Constants.PrimitiveDataType> coursePropertyType = new ArrayList<Constants.PrimitiveDataType>();

        coursePropertyValue.add(grading);
        coursePropertyType.add(Constants.PrimitiveDataType.STRING);

        coursePropertyValue.add(courseTerm);
        coursePropertyType.add(Constants.PrimitiveDataType.STRING);

        coursePropertyValue.add(String.valueOf(token.id));
        coursePropertyType.add(Constants.PrimitiveDataType.INT);

        coursePropertyValue.add(Integer.toString(courseID));
        coursePropertyType.add(Constants.PrimitiveDataType.INT);

        try {
            dbCoordinator.updateData(sqlCmd, coursePropertyValue, coursePropertyType);
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
     * @param token
     * @param courseID
     * @param courseName
     * @param courseCredits
     * @param instructorID
     * @param firstDay
     * @param lastDay
     * @param classBeginTime
     * @param classEndTime
     * @param weekDays
     * @param location
     * @param type
     * @param prerequisite
     * @param description
     * @param department
     * @return Return true if the operation is successfully, false otherwise
     */
    public boolean adminEditClass(ShibbolethAuth.Token token, int courseID, String courseName, int courseCredits, int instructorID, String firstDay,
                                  String lastDay, String classBeginTime, String classEndTime, String weekDays, String location, String type,
                                  String prerequisite, String description, String department) {
        String sqlCmd = "UPDATE COURSE SET NAME = ?, CREDITS = ?, FIRSTDAY = ?, LASTDAY = ?, CLASSBEGINTIME = ?, CLASSENDTIME = ?, ROUTINES = ?, LOCATION = ?, TYPE = ?, PREREQUISITE = ?, DESCRIPTION = ?, DEPARTMENT = ? WHERE ID = ?";
        ArrayList<String> dataList = new ArrayList<String>();
        ArrayList<Constants.PrimitiveDataType> typeList = new ArrayList<Constants.PrimitiveDataType>();

        dataList.add(courseName);
        typeList.add(Constants.PrimitiveDataType.STRING);
        dataList.add(Integer.toString(courseCredits));
        typeList.add(Constants.PrimitiveDataType.INT);
        dataList.add(firstDay);
        typeList.add(Constants.PrimitiveDataType.STRING);
        dataList.add(lastDay);
        typeList.add(Constants.PrimitiveDataType.STRING);
        dataList.add(classBeginTime);
        typeList.add(Constants.PrimitiveDataType.STRING);
        dataList.add(classEndTime);
        typeList.add(Constants.PrimitiveDataType.STRING);
        dataList.add(weekDays);
        typeList.add(Constants.PrimitiveDataType.STRING);
        dataList.add(location);
        typeList.add(Constants.PrimitiveDataType.STRING);
        dataList.add(type);
        typeList.add(Constants.PrimitiveDataType.STRING);
        dataList.add(prerequisite);
        typeList.add(Constants.PrimitiveDataType.STRING);
        dataList.add(description);
        typeList.add(Constants.PrimitiveDataType.STRING);
        dataList.add(department);
        typeList.add(Constants.PrimitiveDataType.STRING);
        dataList.add(String.valueOf(courseID));
        typeList.add(Constants.PrimitiveDataType.INT);

        try {
            dbCoordinator.updateData(sqlCmd, dataList, typeList);
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

	
