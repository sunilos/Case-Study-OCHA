package in.co.sunrays.common.model;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DatabaseException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.exception.RecordNotFoundException;
import in.co.sunrays.ocha.model.AppRole;
import in.co.sunrays.ocha.model.StaffModel;
import in.co.sunrays.ocha.model.StudentModel;
import in.co.sunrays.util.EmailBuilder;
import in.co.sunrays.util.EmailMessage;
import in.co.sunrays.util.EmailUtility;
import in.co.sunrays.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * JDBC Implementation of UserModel
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class UserModel extends BaseModel {
	private static Logger log = Logger.getLogger(UserModel.class);

	/**
	 * Lock Active constant for User
	 */
	public static final String ACTIVE = "Active";
	/**
	 * Lock Inactive constant for User
	 */
	public static final String INACTIVE = "Inactive";
	private String firstName;
	private String lastName;
	private String fatherName;
	private String motherName;
	private String login;
	private String password;
	private String confirmPassword;
	private String collegeId;
	private String collegeCode;
	private String departent;
	private int semester;
	private int year;
	private Date dob;
	private String gender;
	private String mobileNo;
	private String address;
	private Timestamp lastLogin;
	private String lock = INACTIVE;
	/**
	 * IP Address of User from where User was registred.
	 */
	private String registeredIP;
	/**
	 * IP Address of User of his last login
	 */
	private String lastLoginIP;
	/**
	 * Role of User
	 */
	private long roleId;
	/**
	 * Number of unsuccessful login attempt
	 */
	private int unSuccessfulLogin;

	/**
	 * accessor
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	public String getDepartent() {
		return departent;
	}

	public void setDepartent(String departent) {
		this.departent = departent;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getCollegeCode() {
		return collegeCode;
	}

	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getRegisteredIP() {
		return registeredIP;
	}

	public void setRegisteredIP(String registeredIP) {
		this.registeredIP = registeredIP;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public int getUnSuccessfulLogin() {
		return unSuccessfulLogin;
	}

	public void setUnSuccessfulLogin(int unSuccessfulLogin) {
		this.unSuccessfulLogin = unSuccessfulLogin;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return firstName + " " + lastName;
	}

	/**
	 * Add a User
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add() throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		long pk = 0;
		UserModel existModel = findByLogin(login);
		if (existModel != null) {
			throw new DuplicateRecordException("Login Id already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_USER (ID,FIRST_NAME,LAST_NAME,"
							+ "FATHER_NAME,MOTHER_NAME,LOGIN,PASSWORD,COLLEGE_ID,DEPARTMENT,SEMESTER,"
							+ "YEAR,DOB,GENDER,MOBILE_NO,ADDRESS,LAST_LOGIN,USER_LOCK,REGISTERED_IP,"
							+ "LAST_LOGIN_IP,ROLE_ID,UNSUCCESSFUL_LOGIN) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, firstName);

			pstmt.setString(3, lastName);
			pstmt.setString(4, fatherName);
			pstmt.setString(5, motherName);
			pstmt.setString(6, login);
			pstmt.setString(7, password);
			if (collegeCode != null) {
				pstmt.setString(8, collegeCode + "" + departent + "" + year
						+ "" + collegeId);
			} else {
				pstmt.setString(8, "");
			}
			pstmt.setString(9, departent);
			pstmt.setInt(10, semester);
			pstmt.setInt(11, year);
			System.out.println("in model..............." + dob);
			pstmt.setDate(12, new java.sql.Date(dob.getTime()));
			pstmt.setString(13, gender);
			pstmt.setString(14, mobileNo);
			pstmt.setString(15, address);
			pstmt.setDate(16, new java.sql.Date(new Date().getTime()));
			pstmt.setString(17, lock);

			pstmt.setString(18, registeredIP);
			pstmt.setString(19, lastLoginIP);
			pstmt.setLong(20, roleId);
			pstmt.setInt(21, unSuccessfulLogin);

			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
			this.setId(pk);
			updateCreatedInfo();
			updateModifiedInfo();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			JDBCDataSource.trnRollback(conn);
			throw new ApplicationException(e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");

		// Make Student or Staff Entry
		if (roleId == AppRole.STUDENT) {
			StudentModel studentModel = new StudentModel();
			studentModel.setId(pk);

			studentModel.setFirstName(firstName);
			studentModel.setLastName(lastName);
			studentModel.setFatherName(fatherName);
			studentModel.setMotherName(motherName);
			studentModel.setCollegeId(collegeId);
			studentModel.setDepartement(departent);
			studentModel.setSemester(semester);
			studentModel.setYear(year);
			studentModel.setDob(dob);
			studentModel.setGender(gender);
			studentModel.setMobileNo(mobileNo);
			studentModel.setAddress(address);

			studentModel.add();

		} else if (roleId == AppRole.STAFF) {
			StaffModel staffModel = new StaffModel();
			staffModel.setId(pk);
			
			staffModel.setFirstName(firstName);
			staffModel.setLastName(lastName);
			staffModel.setFatherName(fatherName);
			staffModel.setMotherName(motherName);
			staffModel.setCollegeId(collegeId);
			staffModel.setDepartement(departent);
			staffModel.setSemester(semester);
			staffModel.setYear(year);
			staffModel.setDob(dob);
			staffModel.setGender(gender);
			staffModel.setMobileNo(mobileNo);
			staffModel.setAddress(address);

			staffModel.add();
		}

		return pk;
	}

	/**
	 * Delete a User
	 * 
	 * @param model
	 * @throws DatabaseException
	 */
	public void delete() throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM ST_USER WHERE ID=?");
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			JDBCDataSource.trnRollback(conn);
			throw new ApplicationException(e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Find User by Login
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public UserModel findByLogin(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_USER WHERE LOGIN=?");
		UserModel model = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = populateModel(new UserModel(), rs);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			JDBCDataSource.trnRollback(conn);
			throw new ApplicationException(e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return model;
	}

	/**
	 * Find User by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public UserModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE ID=?");
		UserModel model = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				model = populateModel(new UserModel(), rs);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			JDBCDataSource.trnRollback(conn);
			throw new ApplicationException(e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return model;
	}

	/**
	 * Update a user
	 * 
	 * @param model
	 * @throws DatabaseException
	 */
	public void update() throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");

		Connection conn = null;

		UserModel modelExist = findByLogin(login);
		// Check if updated LoginId already exist
		if (modelExist != null && !(modelExist.getId() == id)) {

			throw new DuplicateRecordException("LoginId is already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE ST_USER SET FIRST_NAME=?,LAST_NAME=?,"
							+ "FATHER_NAME=?,MOTHER_NAME=?,LOGIN=?,PASSWORD=?,"
							+ "COLLEGE_ID=?,DEPARTMENT=?,SEMESTER=?,YEAR=?,DOB=?,"
							+ "GENDER=?,MOBILE_NO=?,ADDRESS=?,LAST_LOGIN=?,"
							+ "USER_LOCK=?,REGISTERED_IP=?,LAST_LOGIN_IP=?,"
							+ "ROLE_ID=?,UNSUCCESSFUL_LOGIN=? WHERE ID=?");

			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, fatherName);
			pstmt.setString(4, motherName);
			pstmt.setString(5, login);
			pstmt.setString(6, password);
			if (collegeCode != null) {
				pstmt.setString(7, collegeCode + "" + departent + "" + year
						+ "" + collegeId);
			} else {
				pstmt.setString(7, "");
			}
			pstmt.setString(8, departent);
			pstmt.setInt(9, semester);
			pstmt.setInt(10, year);
			pstmt.setDate(11, new java.sql.Date(dob.getTime()));
			pstmt.setString(12, gender);
			pstmt.setString(13, mobileNo);
			pstmt.setString(14, address);
			pstmt.setTimestamp(15, lastLogin);
			pstmt.setString(16, lock);
			pstmt.setString(17, registeredIP);
			pstmt.setString(18, lastLoginIP);
			pstmt.setLong(19, roleId);
			pstmt.setInt(20, unSuccessfulLogin);
			pstmt.setLong(21, id);
			pstmt.executeUpdate();

			conn.commit(); // End transaction
			pstmt.close();

			updateModifiedInfo();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception "
								+ ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search User
	 * 
	 * @param model
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List<UserModel> search() throws ApplicationException {
		return search(0, 0);
	}

	/**
	 * Search User with pagination
	 * 
	 * @return list : List of Users
	 * @param model
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public List<UserModel> search(int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE 1=1");

		if (id > 0) {
			sql.append(" AND id = " + id);
		}
		if (firstName != null && firstName.length() > 0) {
			sql.append(" AND FIRST_NAME like '" + firstName + "%'");
		}
		if (lastName != null && lastName.length() > 0) {
			sql.append(" AND LAST_NAME like '" + lastName + "%'");
		}
		if (login != null && login.length() > 0) {
			sql.append(" AND LOGIN like '" + login + "%'");
		}
		if (password != null && password.length() > 0) {
			sql.append(" AND PASSWORD like '" + password + "%'");
		}
		if (dob != null && dob.getDate() > 0) {
			sql.append(" AND DOB = " + dob);
		}
		if (mobileNo != null && mobileNo.length() > 0) {
			sql.append(" AND MOBILE_NO = " + mobileNo);
		}
		if (roleId > 0) {
			sql.append(" AND ROLE_ID = " + roleId);
		}
		if (unSuccessfulLogin > 0) {
			sql.append(" AND UNSUCCESSFUL_LOGIN = " + unSuccessfulLogin);
		}
		if (gender != null && gender.length() > 0) {
			sql.append(" AND GENDER like '" + gender + "%'");
		}
		if (lastLogin != null && lastLogin.getTime() > 0) {
			sql.append(" AND LAST_LOGIN = " + lastLogin);
		}
		if (registeredIP != null && registeredIP.length() > 0) {
			sql.append(" AND REGISTERED_IP like '" + registeredIP + "%'");
		}
		if (lastLoginIP != null && lastLoginIP.length() > 0) {
			sql.append(" AND LAST_LOGIN_IP like '" + lastLoginIP + "%'");
		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println(sql);
		List<UserModel> list = new ArrayList<UserModel>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserModel model = populateModel(new UserModel(), rs);
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Get List of User
	 * 
	 * @return list : List of User
	 * @throws DatabaseException
	 */

	public List<UserModel> list() throws ApplicationException {
		return list(0, 0);
	}

	public List<UserModel> studentlist() throws ApplicationException {
		return studentlist(0, 0);
	}

	/**
	 * Get List of User with pagination
	 * 
	 * @return list : List of users
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public List<UserModel> list(int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model list Started");
		List<UserModel> list = new ArrayList<UserModel>();
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				UserModel model = populateModel(new UserModel(), rs);
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}

	public List<UserModel> studentlist(int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model list Started");
		List<UserModel> list = new ArrayList<UserModel>();
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_USER WHERE ROLE_ID=2");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + "," + pageSize);
		}
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				UserModel model = populateModel(new UserModel(), rs);
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;
	}

	/**
	 * @param id
	 *            : long id
	 * @param old
	 *            password : String oldPassword
	 * @param new password : String newPassword
	 * @throws DatabaseException
	 */
	public UserModel authenticate(String login, String password)
			throws ApplicationException {
		log.debug("Model authenticate Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_USER WHERE LOGIN = ? AND PASSWORD = ?");

		log.info("SQL : " + sql);

		UserModel model = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = populateModel(new UserModel(), rs);
			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model authenticate End");
		return model;
	}

	/**
	 * Lock User for certain time duration
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 *             : if user not found
	 */

	public boolean lock(String login) throws RecordNotFoundException,
			ApplicationException {
		log.debug("Service lock Started");
		boolean flag = false;
		UserModel modelExist = null;
		try {
			modelExist = findByLogin(login);
			if (modelExist != null) {
				modelExist.setLock(ACTIVE);
				modelExist.update();
				flag = true;
			} else {
				throw new RecordNotFoundException("LoginId not exist");
			}
		} catch (DuplicateRecordException e) {
			log.error("Application Exception..", e);
			throw new ApplicationException("Database Exception");
		}
		log.debug("Service lock End");
		return flag;
	}

	/**
	 * Get User Roles
	 * 
	 * @return List : User Role List
	 * @param model
	 * @throws ApplicationException
	 */

	public List<UserModel> getRoles(UserModel model)
			throws ApplicationException {
		log.debug("Model get roles Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_USER WHERE ROLE_ID=?");
		Connection conn = null;
		List<UserModel> list = new ArrayList<UserModel>();
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, model.getRoleId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = populateModel(new UserModel(), rs);
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model get roles End");
		return list;
	}

	/**
	 * @param id
	 *            : long id
	 * @param old
	 *            password : String oldPassword
	 * @param newpassword
	 *            : String newPassword
	 * @throws DatabaseException
	 */

	public boolean changePassword(Long id, String oldPassword,
			String newPassword) throws RecordNotFoundException,
			ApplicationException {
		log.debug("model changePassword Started");
		boolean flag = false;
		UserModel modelExist = null;
		modelExist = findByPK(id);
		if (modelExist != null && modelExist.getPassword().equals(oldPassword)) {
			modelExist.setPassword(newPassword);
			try {
				modelExist.update();
			} catch (DuplicateRecordException e) {
				log.error(e);
				throw new ApplicationException("LoginId is already exist");
			}
			flag = true;
		} else {
			throw new RecordNotFoundException("Login not exist");
		}

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("login", modelExist.getLogin());
		map.put("password", modelExist.getPassword());
		map.put("firstName", modelExist.getFirstName());
		map.put("lastName", modelExist.getLastName());

		String message = EmailBuilder.getChangePasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(modelExist.getLogin());
		msg.setSubject("SGSITS OCHA  Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		log.debug("Model changePassword End");
		return flag;

	}

	public UserModel updateAccess(UserModel model) throws ApplicationException {
		return null;
	}

	/**
	 * Register a user
	 * 
	 * @param model
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when user already exists
	 */
	public long registerUser() throws ApplicationException,
			DuplicateRecordException {

		log.debug("Model add Started");

		long pk = add();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", login);
		map.put("password", password);

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(login);
		msg.setSubject("Registration is successful for OCHA Project SGSITS INDORE");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		return pk;
	}

	/**
	 * Reset Password of User with auto generated Password
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 *             : if user not found
	 */
	public boolean resetPassword() throws ApplicationException {
		String newPassword = String.valueOf(new Date().getTime()).substring(0,
				4);
		UserModel userData = findByPK(id);
		userData.setPassword(newPassword);

		try {
			userData.update();
		} catch (DuplicateRecordException e) {
			return false;
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", login);
		map.put("password", password);
		map.put("firstName", firstName);
		map.put("lastName", lastName);

		String message = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(login);
		msg.setSubject("Password has been reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		return true;
	}

	/**
	 * Send the password of User to his Email
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 *             : if user not found
	 */

	public boolean forgetPassword(String login) throws ApplicationException,
			RecordNotFoundException {
		UserModel userData = findByLogin(login);
		boolean flag = false;
		if (userData == null) {
			throw new RecordNotFoundException("Email ID does not exists !");
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", userData.getLogin());
		map.put("password", userData.getPassword());
		map.put("firstName", userData.getFirstName());
		map.put("lastName", userData.getLastName());
		String message = EmailBuilder.getForgetPasswordMessage(map);
		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("SGSITS INDORE Password reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
		flag = true;
		return flag;
	}

	@Override
	protected <T extends BaseModel> T populateModel(T m, ResultSet rs)
			throws SQLException {
		super.populateModel(m, rs);
		UserModel model = (UserModel) m;
		model.setId(rs.getLong("ID"));
		model.setFirstName(rs.getString("FIRST_NAME"));
		model.setLastName(rs.getString("LAST_NAME"));
		model.setFatherName(rs.getString("FATHER_NAME"));
		model.setMotherName(rs.getString("MOTHER_NAME"));
		model.setLogin(rs.getString("LOGIN"));
		model.setPassword(rs.getString("PASSWORD"));
		model.setCollegeId(rs.getString("COLLEGE_ID"));
		model.setDepartent(rs.getString("DEPARTMENT"));
		model.setSemester(rs.getInt("SEMESTER"));
		model.setYear(rs.getInt("YEAR"));
		model.setDob(rs.getDate("DOB"));
		model.setGender(rs.getString("GENDER"));
		model.setMobileNo(rs.getString("MOBILE_NO"));
		model.setAddress(rs.getString("ADDRESS"));
		model.setLastLogin(rs.getTimestamp("LAST_LOGIN"));
		model.setLock(rs.getString("USER_LOCK"));
		model.setRegisteredIP(rs.getString("REGISTERED_IP"));
		model.setLastLoginIP(rs.getString("LAST_LOGIN_IP"));
		model.setRoleId(rs.getLong("ROLE_ID"));
		model.setUnSuccessfulLogin(rs.getInt("UNSUCCESSFUL_LOGIN"));

		return m;
	}

	@Override
	public String getTableName() {
		return "ST_USER";
	}

}