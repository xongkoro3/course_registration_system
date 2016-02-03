/**
 * Created by Gary on 12/7/15.
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @version 1.0
 * @since 12/08/2015
 */
public class AddCourseSystem {
    /**
     * Connect to the sqlist db
     */
    private final DBCoordinator dbCoordinator = new DBCoordinator();

    /**
     *
     * @param token
     * @param courseID
     * @param courseName
     * @param courseCredits
     * @param courseCapacity
     * @param term
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
    public boolean adminAddCourse(ShibbolethAuth.Token token, int courseID, String courseName, int courseCredits, int courseCapacity, String term, int instructorID, String firstDay,
                                  String lastDay, String classBeginTime, String classEndTime, String weekDays, String location, String type,
                                  String prerequisite, String description, String department) {

        String sqlCmd = "INSERT INTO COURSE(ID,NAME,CREDITS,CAPACITY,TERM,FIRSTDAY,LASTDAY,CLASSBEGINTIME,CLASSENDTIME,ROUTINES,LOCATION,TYPE,PREREQUISITE,DESCRIPTION,DEPARTMENT) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        ArrayList<String> dataList = new ArrayList<>();
        ArrayList<Constants.PrimitiveDataType> typeList = new ArrayList<>();

        dataList.add(Integer.toString(courseID));
        typeList.add(Constants.PrimitiveDataType.INT);
        dataList.add(courseName);
        typeList.add(Constants.PrimitiveDataType.STRING);
        dataList.add(Integer.toString(courseCredits));
        typeList.add(Constants.PrimitiveDataType.INT);
        dataList.add(Integer.toString(courseCapacity));
        typeList.add(Constants.PrimitiveDataType.INT);
        dataList.add(term);
        typeList.add(Constants.PrimitiveDataType.STRING);
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

        try {
            dbCoordinator.insertData(sqlCmd, dataList, typeList);
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

        String sqlCmd1 = "INSERT INTO INSTRUCTORANDCOURSE(COURSEID,INSTRUCTORID) VALUES (?, ?);";

        ArrayList<String> dataList1 = new ArrayList<>();
        ArrayList<Constants.PrimitiveDataType> typeList1 = new ArrayList<>();

        dataList1.add(Integer.toString(courseID));
        typeList1.add(Constants.PrimitiveDataType.INT);
        dataList1.add(String.valueOf(instructorID));
        typeList1.add(Constants.PrimitiveDataType.STRING);

        try {
            dbCoordinator.insertData(sqlCmd1, dataList1, typeList1);
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
     * @param grading
     * @param courseTerm
     * @return Return true if the operation is successfully, false otherwise
     */
    public boolean studentAddCourse(ShibbolethAuth.Token token, int courseID, String grading, String courseTerm) {
        String sqlCmd = "INSERT INTO STUDENTANDCOURSE (COURSEID,GRADING,COURSETERM,STUDENTID) VALUES (?, ?, ?, ?);";

        ArrayList<String> coursePropertyValue = new ArrayList<String>();
        ArrayList<Constants.PrimitiveDataType> coursePropertyType = new ArrayList<Constants.PrimitiveDataType>();

        coursePropertyValue.add(Integer.toString(courseID));
        coursePropertyType.add(Constants.PrimitiveDataType.INT);

        coursePropertyValue.add(grading);
        coursePropertyType.add(Constants.PrimitiveDataType.STRING);

        coursePropertyValue.add(courseTerm);
        coursePropertyType.add(Constants.PrimitiveDataType.STRING);

        coursePropertyValue.add(Integer.toString(token.id));
        coursePropertyType.add(Constants.PrimitiveDataType.INT);

        try {
            dbCoordinator.insertData(sqlCmd, coursePropertyValue, coursePropertyType);
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
        if(creditsList2.size() == 0)
            return false;
        int coursecredits = Integer.valueOf(creditsList2.get(0).get(0).toString());

        int fcredits = studentcredits + coursecredits;
        String sqlCmd3 = "UPDATE STUDENT SET CREDITS = ? WHERE ID = ?";

        ArrayList<String> coursePropertyValue1 = new ArrayList<String>();
        ArrayList<Constants.PrimitiveDataType> coursePropertyType1 = new ArrayList<Constants.PrimitiveDataType>();
        coursePropertyValue1.add(Integer.toString(fcredits));
        coursePropertyType1.add(Constants.PrimitiveDataType.INT);

        try {
            dbCoordinator.updateData(sqlCmd3, coursePropertyValue1, coursePropertyType1);
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
     * @param grading
     * @param courseTerm
     * @return Return true if the operation is successfully, false otherwise
     */
    public boolean adminAddStudentToClass(ShibbolethAuth.Token token, int studentID, int courseID, String grading, String courseTerm) {
        String sqlCmd = "INSERT INTO STUDENTANDCOURSE (COURSEID,GRADING,COURSETERM,STUDENTID) VALUES (?, ?, ?, ?);";

        ArrayList<String> coursePropertyValue = new ArrayList<String>();
        ArrayList<Constants.PrimitiveDataType> coursePropertyType = new ArrayList<Constants.PrimitiveDataType>();

        coursePropertyValue.add(Integer.toString(courseID));
        coursePropertyType.add(Constants.PrimitiveDataType.INT);

        coursePropertyValue.add(grading);
        coursePropertyType.add(Constants.PrimitiveDataType.STRING);

        coursePropertyValue.add(courseTerm);
        coursePropertyType.add(Constants.PrimitiveDataType.STRING);

        coursePropertyValue.add(Integer.toString(token.id));
        coursePropertyType.add(Constants.PrimitiveDataType.INT);

        try {
            dbCoordinator.insertData(sqlCmd, coursePropertyValue, coursePropertyType);
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
        }catch (ClassNotFoundException err) {
            System.err.println("ClassNotFoundException: " + err.getMessage());
            return false;
        } catch (SQLException err) {
            System.err.println("SQLException: " + err.getMessage());
            return false;
        }
        int coursecredits = Integer.valueOf(creditsList2.get(0).get(0).toString());

        int fcredits = studentcredits + coursecredits;
        String sqlCmd3 = "UPDATE STUDENT SET CREDITS = ? WHERE ID = ?";

        ArrayList<String> coursePropertyValue1 = new ArrayList<String>();
        ArrayList<Constants.PrimitiveDataType> coursePropertyType1 = new ArrayList<Constants.PrimitiveDataType>();

        coursePropertyValue1.add(Integer.toString(fcredits));
        coursePropertyType1.add(Constants.PrimitiveDataType.INT);


        try {
            dbCoordinator.updateData(sqlCmd3, coursePropertyValue1, coursePropertyType1);
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

