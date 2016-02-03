import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DBCoordinator {
	private Connection con;
	private Statement stmt;

	// The query results will be store in List<ArrayList<Object>>, you need to
	// do the type casting in your code
	public List<ArrayList<Object>> queryData(String sqlCmd) throws ClassNotFoundException, SQLException {
		List<ArrayList<Object>> res = new ArrayList<ArrayList<Object>>();
		String originStr = sqlCmd;
		sqlCmd = sqlCmd.toUpperCase();

		if ((sqlCmd.matches(".*\\sINSERT\\s.*") || sqlCmd.matches("INSERT\\s.*") || sqlCmd.matches(".*\\sUPDATE\\s.*")
				|| sqlCmd.matches("UPDATE\\s.*") || sqlCmd.matches(".*\\sDELETE\\s.*")
				|| sqlCmd.matches("DELETE\\s.*")) == true)
			throw new IllegalArgumentException("SQL contains non select command, such as Insert, Update, Delete.");

		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:SCRSDataBase.db");
		con.setAutoCommit(false);
		//System.out.println("Opened database successfully");

		sqlCmd = originStr;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sqlCmd);
		while (rs.next()) {
			ArrayList<Object> tmpRes = new ArrayList<Object>();
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				tmpRes.add(rs.getObject(i + 1));
			}
			res.add(tmpRes);
		}
		rs.close();
		stmt.close();

		con.commit();
		con.close();

		System.out.println("Operation done successfully");

		return res;
	}

	public void deleteData(String sqlCmd, ArrayList<String> dataList, ArrayList<Constants.PrimitiveDataType> typeList)
			throws ClassNotFoundException, SQLException, ParseException {
		sqlCmd = sqlCmd.toUpperCase();

		if ((sqlCmd.matches(".*\\sINSERT\\s.*") || sqlCmd.matches("INSERT\\s.*") || sqlCmd.matches(".*\\sUPDATE\\s.*")
				|| sqlCmd.matches("UPDATE\\s.*") || sqlCmd.matches(".*\\sSELECT\\s.*")
				|| sqlCmd.matches("SELECT\\s.*")) == true)
			throw new IllegalArgumentException("SQL contains non select command, such as Insert, Update, Select.");

		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:SCRSDataBase.db");
		con.setAutoCommit(false);
		System.out.println("Opened database successfully");

		PreparedStatement prepStmt = con.prepareStatement(sqlCmd);
		for (int i = 0; i < dataList.size(); i++) {
			switch (typeList.get(i)) {
			case STRING:
				prepStmt.setString(i + 1, dataList.get(i));
				break;
			case INT:
				prepStmt.setInt(i + 1, Integer.parseInt(dataList.get(i)));
				break;
			case DATE:
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				prepStmt.setDate(i + 1, new java.sql.Date(format.parse(dataList.get(i)).getTime()));
				break;
			}
		}
		prepStmt.execute();
		con.commit();

		prepStmt.close();
		con.close();

		System.out.println("Operation done successfully");
	}

	public void insertData(String sqlCmd, ArrayList<String> dataList, ArrayList<Constants.PrimitiveDataType> typeList)
			throws ClassNotFoundException, SQLException, ParseException {
		sqlCmd = sqlCmd.toUpperCase();
		if ((sqlCmd.matches(".*\\sSELECT\\s.*") || sqlCmd.matches("SELECT\\s.*") || sqlCmd.matches(".*\\sUPDATE\\s.*")
				|| sqlCmd.matches("UPDATE\\s.*") || sqlCmd.matches(".*\\sDELETE\\s.*")
				|| sqlCmd.matches("DELETE\\s.*")) == true)
			throw new IllegalArgumentException("SQL contains non select command, such as Select, Update, Delete.");

		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:SCRSDataBase.db");
		con.setAutoCommit(false);
		System.out.println("Opened database successfully");

		PreparedStatement prepStmt = con.prepareStatement(sqlCmd);
		for (int i = 0; i < dataList.size(); i++) {
			switch (typeList.get(i)) {
			case STRING:
				prepStmt.setString(i + 1, dataList.get(i));
				break;
			case INT:
				prepStmt.setInt(i + 1, Integer.parseInt(dataList.get(i)));
				break;
			case DATE:
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				prepStmt.setDate(i + 1, new java.sql.Date(format.parse(dataList.get(i)).getTime()));
				break;
			}
		}
		prepStmt.execute();
		con.commit();

		prepStmt.close();
		con.close();

		System.out.println("Records created successfully");
	}

	public void updateData(String sqlCmd, ArrayList<String> dataList, ArrayList<Constants.PrimitiveDataType> typeList)
			throws ClassNotFoundException, SQLException, ParseException {
		sqlCmd = sqlCmd.toUpperCase();

		if ((sqlCmd.matches(".*\\sINSERT\\s.*") || sqlCmd.matches("INSERT\\s.*") || sqlCmd.matches(".*\\sSELECT\\s.*")
				|| sqlCmd.matches("SELECT\\s.*") || sqlCmd.matches(".*\\sDELETE\\s.*")
				|| sqlCmd.matches("DELETE\\s.*")) == true)
			throw new IllegalArgumentException("SQL contains non select command, such as Insert, Select, Delete.");

		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:SCRSDataBase.db");
		con.setAutoCommit(false);
		System.out.println("Opened database successfully");

		PreparedStatement prepStmt = con.prepareStatement(sqlCmd);
		for (int i = 0; i < dataList.size(); i++) {
			switch (typeList.get(i)) {
			case STRING:
				prepStmt.setString(i + 1, dataList.get(i));
				break;
			case INT:
				prepStmt.setInt(i + 1, Integer.parseInt(dataList.get(i)));
				break;
			case DATE:
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				prepStmt.setDate(i + 1, new java.sql.Date(format.parse(dataList.get(i)).getTime()));
				break;
			}
		}
		prepStmt.execute();
		con.commit();

		prepStmt.close();
		con.close();

		System.out.println("Operation done successfully");
	}
}
