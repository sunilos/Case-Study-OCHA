package in.co.sunrays.util;

import java.util.HashMap;

/**
 * Class that build Application Email messages
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

 public class EmailBuilder {
	 /**
		 * Returns Successful User Registration Message
		 * 
		 * @param map
		 *            : Message parameters
		 * @return
		 */
	public static String getUserRegistrationMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();


		msg.append("<HTML><BODY>");
		msg.append("Registration is successful for OCHA Project SUNRAYS Technologies");
		msg.append("<H1>Hi! Greetings from Our College!</H1>");
		msg.append("<P>Congratulations for registering on OCHA! You can now access your OCHA account online - anywhere, anytime and enjoy the flexibility to check the Student Details.</P>");
		msg.append("<P><B>Login Id : " + map.get("login") + "<BR>"
				+ " Password : " + map.get("password") + "</B></p>");
		msg.append("<P> As a security measure, we recommended that you change your password after you first log in.</p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}
    
	/**
	 * Returns Email message of Forget Password
	 * 
	 * @param map
	 *            : params
	 * @return
	 */
	
	public static String getForgetPasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("<H1>Your password is reccovered !! " + map.get("firstName")
				+ " " + map.get("lastName") + "</H1>");
		/*msg.append("<P>To access account user login ID : " + map.get("login")
				+ " and password " + map.get("password") + "</P>");*/
		msg.append("<P><B>To access account user Login Id : " + map.get("login") + "<BR>"
				+ " Password : " + map.get("password") + "</B></p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}
	
	/**
	 * Returns Email message of Change Password
	 * 
	 * @param map
	 * @return
	 */
	public static String getChangePasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("<H1>Your Password has been changed Successfully !! " + map.get("firstName")
				+ " " + map.get("lastName") + "</H1>");
		/*msg.append("<P>To access account user login ID : " + map.get("login")
				+ " and password " + map.get("password") + "</P>");*/
		msg.append("<P><B>To access account user Login Id : " + map.get("login") + "<BR>"
				+ " Password : " + map.get("password") + "</B></p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}

}
