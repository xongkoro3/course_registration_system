import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShibbolethAuth {
	static public class Token{
		enum RoleType{
			STUDENT,
			ADMIN,
			BOTH,
			UNDEFINED
		}
		
		Token(int id, RoleType type, String timeStamp) {
			this.id = id;
			this.type = type;
			this.timeStamp = timeStamp;
		}
		
		final int id;
		final RoleType type;
		final String timeStamp;
	}
	
	private final DBCoordinator dbCoordinator = new DBCoordinator();
	
	public Token tokenGenerator(String x500, String password) throws ClassNotFoundException, SQLException {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		List<ArrayList<Object>> res = dbCoordinator.queryData("SELECT * FROM SHIBBOLETHAUTH WHERE X500ACCOUNT=\"" + x500 + "\" AND X500PASSWORD=\"" + password + "\"");
		
		Token undefinedToken = new Token(-1,Token.RoleType.UNDEFINED, "");
		if(res.size() != 1) return undefinedToken;
		
		String userType = (String)res.get(0).get(4);
		int userID = (int)res.get(0).get(3);
		Token newToken = null;
		switch(userType) {
		case "STUDENT":
			newToken = new Token(userID, Token.RoleType.STUDENT, timeStamp);
			break;
		case "ADMIN":
			newToken = new Token(userID, Token.RoleType.ADMIN, timeStamp);
			break;
		case "BOTH":
			newToken = new Token(userID, Token.RoleType.BOTH, timeStamp);
			break;
		default:
			return undefinedToken;
		}

		if(TokenAuth(newToken)) return newToken;
		else return undefinedToken;
	}
	
	private boolean TokenAuth(Token token) throws ClassNotFoundException, SQLException {
		List<ArrayList<Object>> tmp;
		if(token.type == Token.RoleType.STUDENT) {
			tmp = dbCoordinator.queryData("SELECT * FROM STUDENT WHERE ID=\"" + token.id + "\"");
			if(tmp.size() == 0) return false;
		}else if(token.type == Token.RoleType.ADMIN) {
			tmp = dbCoordinator.queryData("SELECT * FROM ADMINISTRATOR WHERE ID=\"" + token.id + "\"");
			if(tmp.size() == 0) return false;
		}else if(token.type == Token.RoleType.BOTH){
			boolean isValid = true;
			tmp = dbCoordinator.queryData("SELECT * FROM ADMINISTRATOR WHERE ID=\"" + token.id + "\"");
			if(tmp.size() == 0) isValid &= false;
			tmp = dbCoordinator.queryData("SELECT * FROM STUDENT WHERE ID=\"" + token.id + "\"");
			if(tmp.size() == 0) isValid &= false;
			if(!isValid) return false;
		}
		
		return true;
	}
}
