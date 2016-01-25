import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @since 12/08/2015
 */
public class SCRSImpl implements SCRS {
    /*
     * Common Interfaces Methods
	 */

    /**
     * This method implements corresponding method in interface:
     * 1. Can be used to perform generic class query;
     * 2. Can be used to perform auto fill out relevant information for the users GUI;
     *
     * @param courseID       can be used by administrator for fast class query
     * @param courseName
     * @param location
     * @param term
     * @param department
     * @param classType
     * @param instructorName
     * @return A list of ArrayList, in the ArrayList, each entry stores one query result in the table property order.
     * E.g in Course Table, the proper order is ID, Name, Credits, etc. so in one entry of ArrayList, it should stores
     * the value in this order, which is ID, Name, Credits, etc.
     * Empty list will be returned if the query is failed.
     */
    public List<ArrayList<String>> queryClass(int courseID, String courseName, String location, String term, String department,
                                              String classType, String instructorName) {
        SearchCourseSystem searchCourseSystem = new SearchCourseSystem();
        return searchCourseSystem.queryCourse(courseID, courseName, location, term, department, classType, instructorName);
    }

    /**
     * This method implements corresponding method in interface:
     *
     * @param token
     * @param studentID
     * @return Student basic personal data, such as name, id, age, gender, degree, advisor(if applicable), total credits, etc.
     * Empty list will be returned if the query is failed.
     */
    public List<ArrayList<String>> queryStudentPersonalData(ShibbolethAuth.Token token, int studentID) {
        if (token.type != ShibbolethAuth.Token.RoleType.STUDENT && token.type != ShibbolethAuth.Token.RoleType.ADMIN && token.type != ShibbolethAuth.Token.RoleType.BOTH) {
            System.out.println("Unauthorized Type!");
            return new ArrayList<ArrayList<String>>();
        }
        InfoSystem infoSystem = new InfoSystem();
        return infoSystem.queryStudentPersonalData(studentID);
    }

    /**
     * This method implements corresponding method in interface:
     *
     * @param token
     * @param studentID
     * @return ClassID, ClassName, Registration Time(term), Status(Finished, Dropped), Credits
     * Empty list will be returned if the query is failed.
     */
    public List<ArrayList<String>> queryStudentRegistrationHistory(ShibbolethAuth.Token token, int studentID) {
        if (token.type != ShibbolethAuth.Token.RoleType.STUDENT && token.type != ShibbolethAuth.Token.RoleType.ADMIN && token.type != ShibbolethAuth.Token.RoleType.BOTH) {
            System.out.println("Unauthorized Type!");
            return new ArrayList<ArrayList<String>>();
        }
        InfoSystem infoSystem = new InfoSystem();
        return infoSystem.queryStudentRegistrationHistory(studentID);
    }

    /**
     * This method implements corresponding method in interface:
     *
     * @param token Only Admin can invoke this function
     * @return Admin ID, Admin Name, Admin Department, etc.
     * Empty list will be returned if the query is failed.
     */
    public List<ArrayList<String>> queryAdminPersonalData(ShibbolethAuth.Token token) {
        if (token.type != ShibbolethAuth.Token.RoleType.ADMIN && token.type != ShibbolethAuth.Token.RoleType.BOTH) {
            System.out.println("Unauthorized Type!");
            return new ArrayList<ArrayList<String>>();
        }
        InfoSystem infoSystem = new InfoSystem();
        return infoSystem.queryAdminPersonalData(token.id);
    }

    /**
     * This method implements corresponding method in interface:
     *
     * @param token
     * @param instructorID -1 if all instructors information need to be returned
     * @return Store a designated instructor's basic information in database table property order.
     * Empty list will be returned if the query is failed.
     */
    public List<ArrayList<String>> queryInstructor(ShibbolethAuth.Token token, int instructorID) {
        if (token.type != ShibbolethAuth.Token.RoleType.STUDENT && token.type != ShibbolethAuth.Token.RoleType.ADMIN && token.type != ShibbolethAuth.Token.RoleType.BOTH) {
            System.out.println("Unauthorized Type!");
            return new ArrayList<ArrayList<String>>();
        }
        InfoSystem infoSystem = new InfoSystem();
        return infoSystem.queryInstructor(instructorID);
    }

	/*
     * Student Interfaces Methods
	 */

    /**
     * This method implements corresponding method in interface:
     *
     * @param token
     * @param courseID
     * @param grading
     * @param courseTerm
     * @return Return true if the operation is successfully, false otherwise
     */

    public boolean studentAddClass(ShibbolethAuth.Token token, int courseID, String grading, String courseTerm){
        if(token.type !=ShibbolethAuth.Token.RoleType.STUDENT&& token.type != ShibbolethAuth.Token.RoleType.BOTH){
			return false;
		}
        AddCourseSystem add = new AddCourseSystem();
        return add.studentAddCourse(token, courseID,grading,courseTerm);
    }

    /**
     * This method implements corresponding method in interface:
     *
     * @param token
     * @param courseID
     * @return Return true if the operation is successfully, false otherwise
     */


    public boolean studentDropClass(ShibbolethAuth.Token token, int courseID){// Return true if operation is successfully, false otherwise
        if(token.type !=ShibbolethAuth.Token.RoleType.STUDENT&& token.type != ShibbolethAuth.Token.RoleType.BOTH){
			return false;
		}
        DropCourseSystem drop = new DropCourseSystem();
        return drop.studentDrop(token, courseID);

    }

    /**
     * This method implements corresponding method in interface:
     *
     * @param token
     * @param courseID
     * @param grading    This parameter should just have one of "A/F", "Audit", "S/N" value
     * @param courseTerm
     * @return Return true if the operation is successfully, false otherwise
     */
    public boolean studentEditClass(ShibbolethAuth.Token token, int courseID, String grading, String courseTerm){// Return true if operation is successfully, false otherwise     
        if(token.type !=ShibbolethAuth.Token.RoleType.STUDENT&& token.type != ShibbolethAuth.Token.RoleType.BOTH){
			return false;
		}
		EditSystem studentEdit = new EditSystem();
		return studentEdit.studentEditClass(token, courseID, grading, courseTerm);
    }


	/*
     * Admin Interfaces Methods
	 */

    /**
     * This method implements corresponding method in interface:
     *
     * @param token
     * @param courseID
     * @param courseName
     * @param courseCredits
     * @param instructorID
     * @param firstDay       The first day of the class in the new semester
     * @param lastDay        The last day of the class in the new semester
     * @param classBeginTime E.g. 9:00
     * @param classEndTime   E.g. 16:00
     * @param weekDays       E.g. Tu, Fri
     * @param location
     * @param type
     * @param prerequisite
     * @param description
     * @param department
     * @return Return true if the operation is successfully, false otherwise
     */
    public boolean adminAddClass(ShibbolethAuth.Token token, int courseID, String courseName, int courseCredits, int courseCapacity, String term, int instructorID, String firstDay,
			String lastDay, String classBeginTime, String classEndTime, String weekDays, String location, String type,
			String prerequisite, String description, String department) {

        if (token.type != ShibbolethAuth.Token.RoleType.ADMIN && token.type != ShibbolethAuth.Token.RoleType.BOTH){
            return false;
        }

        AddCourseSystem add = new AddCourseSystem();
        return add.adminAddCourse(token,courseID, courseName, courseCredits, courseCapacity,term ,instructorID, firstDay, lastDay, classBeginTime, classEndTime, weekDays, location, type, prerequisite, description, department);
    }

    /**
     * This method implements corresponding method in interface:
     *
     * @param token
     * @param courseID
     * @return Return true if the operation is successfully, false otherwise
     */
    public boolean adminDeleteClass(ShibbolethAuth.Token token, int courseID) {
        if (token.type != ShibbolethAuth.Token.RoleType.ADMIN && token.type != ShibbolethAuth.Token.RoleType.BOTH){
            return false;
        }
        DropCourseSystem drop = new DropCourseSystem();
        return drop.adminDelete(token, courseID);
    }


    /**
     * This method implements corresponding method in interface:
     *
     * @param token
     * @param studentID
     * @param courseID
     * @param grading
     * @param courseTerm
     * @return Return true if the operation is successfully, false otherwise
     */
    public boolean adminAddStudentToClass(ShibbolethAuth.Token token, int studentID, int courseID, String grading, String courseTerm) {
        if (token.type != ShibbolethAuth.Token.RoleType.ADMIN && token.type != ShibbolethAuth.Token.RoleType.BOTH){
            return false;
        }
        AddCourseSystem add = new AddCourseSystem();
        return add.adminAddStudentToClass(token,studentID,courseID,grading,courseTerm);
    }

    /**
     * This method implements corresponding method in interface:
     *
     * @param token
     * @param studentID
     * @param courseID
     * @param grading
     * @param courseTerm
     * @return Return true if the operation is successfully, false otherwise
     */
    public boolean adminEditStudentRegisteredClass(ShibbolethAuth.Token token, int studentID, int courseID, String grading, String courseTerm) {
        if (token.type != ShibbolethAuth.Token.RoleType.ADMIN && token.type != ShibbolethAuth.Token.RoleType.BOTH){
            return false;
        }
        EditSystem edit = new EditSystem();
        return edit.adminEditStudentRegisteredClass(token,studentID,courseID,grading,courseTerm);
    }

    /**
     * This method implements corresponding method in interface:
     *
     * @param token
     * @param studentID
     * @param courseID
     * @return Return true if the operation is successfully, false otherwise
     */
    public boolean adminDropStudentRegisteredClass(ShibbolethAuth.Token token, int studentID, int courseID) {
        if (token.type != ShibbolethAuth.Token.RoleType.ADMIN && token.type != ShibbolethAuth.Token.RoleType.BOTH){
            return false;
        }
        DropCourseSystem drop = new DropCourseSystem();
        return drop.adminDropStudentRegisteredClass(token,studentID,courseID);
    }

    /**
     * This method implements corresponding method in interface:
     * The admin can only edit the class's description if at least one student registers this class
     * The admin can edit everything of the class if no one registers it
     *
     * @param token
     * @param courseID
     * @param courseName
     * @param courseCredits
     * @param instructorID
     * @param firstDay       The first day of the class in the new semester
     * @param lastDay        The last day of the class in the new semester
     * @param classBeginTime E.g. 9:00
     * @param classEndTime   E.g. 16:00
     * @param weekDays       E.g. Tu, Fri
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
         if (token.type != ShibbolethAuth.Token.RoleType.ADMIN && token.type != ShibbolethAuth.Token.RoleType.BOTH){
            return false;
        }
        EditSystem edit = new EditSystem();
        return edit.adminEditClass(token,courseID,courseName,courseCredits,instructorID,firstDay,lastDay,classBeginTime,classEndTime,weekDays,location,type,prerequisite,description,department);
       
    }
}

