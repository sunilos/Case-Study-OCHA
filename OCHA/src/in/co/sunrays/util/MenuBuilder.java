package in.co.sunrays.util;

import java.util.HashMap;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import in.co.sunrays.ocha.controller.ORSView;
import in.co.sunrays.ocha.model.AppRole;

public class MenuBuilder {

	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	public static final String separator = " | ";

	public static String getLink(String text, String url) {
		return "<a href='" + url + "'>" + text + "</a>";
	}

	public static String getHorizontalLink(HashMap<String, String> hmap) {
		StringBuffer sb = new StringBuffer(separator + "");
		Iterator<String> keys = hmap.keySet().iterator();

		String key = null;
		String value = null;
		while (keys.hasNext()) {
			key = keys.next();
			value = hmap.get(key);
			sb.append(getLink(key, value) + separator);
		}
		return sb.toString();
	}

	public static String getVericalLink(HashMap<String, String> hmap) {
		/*
		 * <UL> <LI> </LI> </UL>
		 */
		StringBuffer sb = new StringBuffer("<UL>");

		Iterator<String> keys = hmap.keySet().iterator();
		String key = null;
		String value = null;
		while (keys.hasNext()) {
			key = keys.next();
			value = hmap.get(key);
			sb.append("<LI>" + getLink(key, value) + "</LI>");
		}
		sb.append("</UL>");
		return sb.toString();
	}

	public static String getMenu(long roleId) {
		return getMenu(roleId, HORIZONTAL);
	}
	public static String getMenu(long roleId, int i) {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("Home", ORSView.WELCOME_CTL);

		if (roleId == AppRole.STAFF || roleId == AppRole.ADMIN) {

			map.put("User List", ORSView.USER_LIST_CTL);
			map.put("Add Notice", ORSView.NOTICE_CTL);
			map.put("Notice List", ORSView.NOTICE_LIST_CTL);
			map.put("Add EResource", ORSView.ERESOURCE_CTL);
			map.put("EResource List", ORSView.ERESOURCE_LIST_CTL);
			map.put("Comment List", ORSView.COMMENT_LIST_CTL);
			map.put("Add Role", ORSView.ROLE_CTL);
			map.put("Role List", ORSView.ROLE_LIST_CTL);
			map.put("Add Attendance", ORSView.ATTENDENCE_CTL);
			map.put("Attendance List", ORSView.ATTENDENCE_LIST_CTL);
			map.put("Add Time Table", ORSView.TIMETABLE_CTL);
			map.put("Time Table List", ORSView.TIMETABLE_LIST_CTL);
			map.put("Change Password", ORSView.CHANGE_PASSWORD_CTL);
			map.put("MyProfile", ORSView.MY_PROFILE_CTL);
			map.put("Student Information", ORSView.STUDENT_CTL);
			map.put("Student List", ORSView.STUDENT_LIST_CTL);
			map.put("College Name", ORSView.COLLEGE_CTL);
			map.put("College List", ORSView.COLLEGE_LIST_CTL);
			map.put("Staff", ORSView.STAFF_CTL);
			map.put("Staff List", ORSView.STAFF_LIST_CTL);
			map.put("Departement", ORSView.DEPARTEMENT_CTL);
			map.put("Departement List", ORSView.DEPARTEMENT_LIST_CTL);
			

		} else if (roleId == AppRole.STUDENT) {
			map.put("Notice List", ORSView.NOTICE_LIST_CTL);
			map.put("EResource Link", ORSView.ERESOURCE_LINK_CTL);
			map.put("Time Table List", ORSView.TIMETABLE_LIST_CTL);
			map.put("MyProfile", ORSView.MY_PROFILE_CTL);
			map.put("Change Password", ORSView.CHANGE_PASSWORD_CTL);
			
		}

		if (i == HORIZONTAL) {
			return getHorizontalLink(map);
		} else {
			return getVericalLink(map);
		}
	}

	public static String getHMenu1(long roleId) {

		StringBuffer sb = new StringBuffer();

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("Home", ORSView.WELCOME_CTL);

		if (roleId == AppRole.STAFF || roleId == AppRole.ADMIN) {

			map.put("User List", ORSView.USER_LIST_CTL);
			map.put("Notice List", ORSView.NOTICE_LIST_CTL);
			map.put("Add EResource", ORSView.ERESOURCE_CTL);
			map.put("EResource List", ORSView.ERESOURCE_LIST_CTL);
			map.put("Comment List", ORSView.COMMENT_LIST_CTL);
			map.put("Add Role", ORSView.ROLE_CTL);
			map.put("Role List", ORSView.ROLE_LIST_CTL);
			map.put("Add Attendance", ORSView.ATTENDENCE_CTL);
			map.put("Attendance List", ORSView.ATTENDENCE_LIST_CTL);
			map.put("Add Time Table", ORSView.TIMETABLE_CTL);
			map.put("Time Table List", ORSView.TIMETABLE_LIST_CTL);
			map.put("Change Password", ORSView.CHANGE_PASSWORD_CTL);
			map.put("MyProfile", ORSView.MY_PROFILE_CTL);
			map.put("Student Information", ORSView.STUDENT_CTL);
			map.put("Student List", ORSView.STUDENT_LIST_CTL);
			map.put("College Name", ORSView.COLLEGE_CTL);
			map.put("College List", ORSView.COLLEGE_LIST_CTL);
			map.put("Staff", ORSView.STAFF_CTL);
			map.put("Staff List", ORSView.STAFF_LIST_CTL);
			map.put("Departement", ORSView.DEPARTEMENT_CTL);
			map.put("Departement List", ORSView.DEPARTEMENT_LIST_CTL);
			map.put("Logout", ORSView.LOGIN_CTL);

		} else if (roleId == AppRole.STUDENT) {
			map.put("Notice List", ORSView.NOTICE_LIST_CTL);
			map.put("EResource Link", ORSView.ERESOURCE_LINK_CTL);
			map.put("Time Table List", ORSView.TIMETABLE_LIST_CTL);
			map.put("MyProfile", ORSView.MY_PROFILE_CTL);
			map.put("Change Password", ORSView.CHANGE_PASSWORD_CTL);
			map.put("Logout", ORSView.LOGIN_CTL);

		} else {

		}

		// return sb.toString();
		return getHorizontalLink(map);
	}

	/**
	 * public static String getHMenu(long roleId) {
	 * 
	 * StringBuffer sb = new StringBuffer();
	 * 
	 * sb.append(getLink("Home", ORSView.WELCOME_CTL));
	 * 
	 * sb.append(separator);
	 * 
	 * if (roleId == AppRole.STAFF || roleId == AppRole.ADMIN) {
	 * sb.append(getLink("User List", ORSView.USER_LIST_CTL) + separator);
	 * sb.append(getLink("Notice List", ORSView.NOTICE_LIST_CTL) + separator);
	 * sb.append(getLink("Add EResource", ORSView.ERESOURCE_CTL) + separator);
	 * sb.append(getLink("EResource List", ORSView.ERESOURCE_LIST_CTL) +
	 * separator); sb.append(getLink("Comment List", ORSView.COMMENT_LIST_CTL) +
	 * separator); sb.append(getLink("Add Role", ORSView.ROLE_CTL) + separator);
	 * sb.append(getLink("Role List", ORSView.ROLE_LIST_CTL) + separator);
	 * sb.append(getLink("Add Attendance", ORSView.ATTENDENCE_CTL) + separator);
	 * sb.append(getLink("Attendance List", ORSView.ATTENDENCE_LIST_CTL) +
	 * separator); sb.append(getLink("Add Time Table", ORSView.TIMETABLE_CTL) +
	 * separator); sb.append(getLink("Time Table List",
	 * ORSView.TIMETABLE_LIST_CTL) + separator);
	 * sb.append(getLink("Change Password", ORSView.CHANGE_PASSWORD_CTL) +
	 * separator); sb.append(getLink("MyProfile", ORSView.MY_PROFILE_CTL) +
	 * separator); sb.append(getLink("Student Information", ORSView.STUDENT_CTL)
	 * + separator); sb.append(getLink("Student List", ORSView.STUDENT_LIST_CTL)
	 * + separator); sb.append(getLink("College Name", ORSView.COLLEGE_CTL) +
	 * separator); sb.append(getLink("College List", ORSView.COLLEGE_LIST_CTL) +
	 * separator); sb.append(getLink("Staff", ORSView.STAFF_CTL) + separator);
	 * sb.append(getLink("Staff List", ORSView.STAFF_LIST_CTL) + separator);
	 * sb.append(getLink("Departement", ORSView.DEPARTEMENT_CTL) + separator);
	 * sb.append(getLink("Departement List", ORSView.DEPARTEMENT_LIST_CTL) +
	 * separator); sb.append(getLink("Logout", ORSView.LOGIN_CTL) + separator);
	 * 
	 * } else if (roleId == AppRole.STUDENT) {
	 * sb.append(getLink("EResource Link", ORSView.ERESOURCE_LINK_CTL) +
	 * separator); sb.append(getLink("Notice List", ORSView.NOTICE_LIST_CTL) +
	 * separator); sb.append(getLink("Time Table List",
	 * ORSView.TIMETABLE_LIST_CTL) + separator); sb.append(getLink("MyProfile",
	 * ORSView.MY_PROFILE_CTL) + separator);
	 * sb.append(getLink("Change Password", ORSView.CHANGE_PASSWORD_CTL) +
	 * separator); sb.append(getLink("Logout", ORSView.LOGIN_CTL));
	 * 
	 * } else {
	 * 
	 * }
	 * 
	 * return sb.toString(); }
	 * 
	 * public static String getVMenu() { StringBuffer sb = new StringBuffer();
	 * 
	 * return sb.toString(); }
	 **/

}