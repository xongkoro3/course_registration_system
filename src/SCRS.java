import java.util.ArrayList;
import java.util.List;

public interface SCRS {
	/*
	 * Common Interfaces
	 */
	/**
	 * This interface have several responsibilities: 
	 * 1. Can be used to perform generic class query; 
	 * 2. Can be used to perform auto fill out relevant information for the users GUI;
	 * @param courseID can be used by administrator for fast class query
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
	List<ArrayList<String>> queryClass(int courseID, String courseName, String location, String term, String department,
									   String classType, String instructorName);

	/**
	 * This interface is used for querying student personal data.
	 * @param token
	 * @param studentID
	 * @return Student basic personal data, such as name, id, age, gender, degree, advisor(if applicable), total credits, etc.
	 * Empty list will be returned if the query is failed.
	 */
	List<ArrayList<String>> queryStudentPersonalData(ShibbolethAuth.Token token, int studentID);

	/**
	 * This interface is used for querying student registration history
	 * @param token
	 * @param studentID
	 * @return ClassID, ClassName, Registration Time(term), Status(Finished, Dropped), Credits
	 * Empty list will be returned if the query is failed.
	 */
	List<ArrayList<String>> queryStudentRegistrationHistory(ShibbolethAuth.Token token, int studentID);

	/**
	 * This interface is used for querying admin basic information.
	 * @param token Only Admin can invoke this function
	 * @return Admin ID, Admin Name, Admin Department, etc.
	 * Empty list will be returned if the query is failed.
	 */
	List<ArrayList<String>> queryAdminPersonalData(ShibbolethAuth.Token token);

	/**
	 * This interface is used for querying instructors basic personal information
	 * @param token
	 * @param instructorID -1 if all instructors information need to be returned
	 * @return Store a designated instructor's basic information in database table property order.
	 * Empty list will be returned if the query is failed.
	 */
	List<ArrayList<String>> queryInstructor(ShibbolethAuth.Token token, int instructorID); 

	/*
	 * Student Interfaces
	 */
	/**
	 * This interface should allow the students add one class to the database.
	 * @param token
	 * @param courseID
	 * @param grading
	 * @param courseTerm
	 * @return Return true if the operation is successfully, false otherwise
	 */
	boolean studentAddClass(ShibbolethAuth.Token token, int courseID, String grading, String courseTerm);

	/**
	 * This interface should allow the students drop one class from the database.
	 * @param token
	 * @param courseID
	 * @return Return true if the operation is successfully, false otherwise
	 */
	boolean studentDropClass(ShibbolethAuth.Token token, int courseID); // Return true if operation is successfully, false otherwise

	/**
	 * This interface should allow the students edit one registered class in the database.
	 * @param token
	 * @param courseID
	 * @param grading This parameter should just have one of "A/F", "Audit", "S/N" value
	 * @param courseTerm
	 * @return Return true if the operation is successfully, false otherwise
	 */
	boolean studentEditClass(ShibbolethAuth.Token token, int courseID, String grading, String courseTerm); // Return true if operation is successfully, false otherwise

	/*
	 * Admin Interfaces
	 */
	/**
	 * This interface should allow the admin to add class into the database
	 * @param token
	 * @param courseID
	 * @param courseName
	 * @param courseCredits
	 * @param courseCapacity
	 * @param term
	 * @param instructorID
	 * @param firstDay The first day of the class in the new semester
	 * @param lastDay The last day of the class in the new semester
	 * @param classBeginTime E.g. 9:00
	 * @param classEndTime	E.g. 16:00
	 * @param weekDays E.g. Tu, Fri
	 * @param location
	 * @param type
	 * @param prerequisite
	 * @param description
	 * @param department
	 * @return Return true if the operation is successfully, false otherwise
	 */
	boolean adminAddClass(ShibbolethAuth.Token token, int courseID, String courseName, int courseCredits, int courseCapacity, String term, int instructorID, String firstDay,
						  String lastDay, String classBeginTime, String classEndTime, String weekDays, String location, String type,
						  String prerequisite, String description, String department);

	/**
	 * This interface should allow the admin to delete a class from the database if and only if there is not one register it.
	 * @param token
	 * @param courseID
	 * @return Return true if the operation is successfully, false otherwise
	 */
	boolean adminDeleteClass(ShibbolethAuth.Token token, int courseID);

	/**
	 * This interface should allow the admin to modify the existed class in the database.
	 * The admin can only edit the class's description if at least one student registers this class
	 * The admin can edit everything of the class if no one registers it
	 * @param token
	 * @param courseID
	 * @param courseName
	 * @param courseCredits
	 * @param instructorID
	 * @param firstDay The first day of the class in the new semester
	 * @param lastDay The last day of the class in the new semester
	 * @param classBeginTime E.g. 9:00
	 * @param classEndTime	E.g. 16:00
	 * @param weekDays E.g. Tu, Fri
	 * @param location
	 * @param type
	 * @param prerequisite
	 * @param description
	 * @param department
	 * @return Return true if the operation is successfully, false otherwise
	 */
	boolean adminEditClass(ShibbolethAuth.Token token, int courseID, String courseName, int courseCredits, int instructorID, String firstDay,
						   String lastDay, String classBeginTime, String classEndTime, String weekDays, String location, String type,
						   String prerequisite, String description, String department);

	/**
	 * This interface should allow the admin to add one student to one specific class if exist
	 * @param token
	 * @param studentID
	 * @param courseID
	 * @param grading
	 * @param courseTerm
	 * @return Return true if the operation is successfully, false otherwise
	 */
	boolean adminAddStudentToClass(ShibbolethAuth.Token token, int studentID, int courseID, String grading, String courseTerm);

	/**
	 * This interface should allow the admin to edit one student to one specific class if exist
	 * @param token
	 * @param studentID
	 * @param courseID
	 * @param grading
	 * @param courseTerm
	 * @return Return true if the operation is successfully, false otherwise
	 */
	boolean adminEditStudentRegisteredClass(ShibbolethAuth.Token token, int studentID, int courseID, String grading, String courseTerm);

	/**
	 * This interface should allow the admin to remove one registered class from a student's registered class's list.
	 * @param token
	 * @param studentID
	 * @param courseID
	 * @return Return true if the operation is successfully, false otherwise
	 */
	boolean adminDropStudentRegisteredClass(ShibbolethAuth.Token token, int studentID, int courseID);
}
