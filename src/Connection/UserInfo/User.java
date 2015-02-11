package Connection.UserInfo;

import java.util.LinkedList;

//import Course

/**
 * @author Forrest Sun
 * @author Wesley Pollek
 */

public class User {
	private String username;
	private String password;
	private String email;
	private String major;
//	private LinkedList<Schedule> courses;
	private int id;

	User(String u, String p, String e, String m, int id){
		username=u;
		password=p;
		email=e;
		major=m;
		this.id=id;
	}
	
	public String getUsername(){return this.username;}
	public String getPassword(){return this.password;}
	public String getEmail(){return this.email;}
	public String getMajor(){return this.major;}

	public void setPassword(String password){
		this.password=password;
		UsersConnection.setPassword(id, password);
	}
	
	public void setEmail(String email){
		this.email=email;
		UsersConnection.setEmail(id, email);
	}
	
	public void setMajor(String major){
		this.major=major;
		UsersConnection.setMajor(id, major);
	}

}
