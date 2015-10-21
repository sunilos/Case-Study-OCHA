package in.co.sunrays.ocha.model;

import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DatabaseException;
import in.co.sunrays.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * JDBC Implementation of Staff Model
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class StaffModel  extends BaseModel {

	private static Logger log = Logger.getLogger(StaffModel.class);

	/**
	 * First Name of Student
	 */
	private String firstName;
	private String lastName;
	private String fatherName;
	private String motherName;
	private String collegeId;
	private String departement;
	private int semester;
	private int year;
	private Date dob;
	private String gender;
	private String mobileNo;
	private String address;
	private long userId;

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * Add a Student
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add() throws ApplicationException {
		log.debug("Model add Started");

		Connection conn = null;

		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_STAFF (ID,FIRST_NAME,LAST_NAME,"
							+ "FATHER_NAME,MOTHER_NAME,COLLEGE_ID,DEPARTEMENT,"
							+ "SEMESTER,YEAR,DATE_OF_BIRTH,GENDER,MOBILE_NO,ADDRESS,"
							+ "USER_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, firstName);
			pstmt.setString(3, lastName);
			pstmt.setString(4, fatherName);
			pstmt.setString(5, motherName);
			pstmt.setString(6, collegeId);
			pstmt.setString(7, departement);
			pstmt.setInt(8, semester);
			pstmt.setInt(9, year);
			pstmt.setDate(10, new java.sql.Date(dob.getTime()));
			pstmt.setString(11, gender);
			pstmt.setString(12, mobileNo);
			pstmt.setString(13, address);
			pstmt.setLong(14, userId);

			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
			this.setId(pk);
			updateCreatedInfo();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			JDBCDataSource.trnRollback(conn);
			throw new ApplicationException(e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a Student
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */
	public void delete() throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM ST_STAFF WHERE ID=?");

			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception "
								+ ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in delete Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Find Student by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public StaffModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_STAFF  WHERE ID=?");

		StaffModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new StaffModel();
				model.setId(rs.getLong(1));
				model.setFirstName(rs.getString(2));
				model.setLastName(rs.getString(3));
				model.setFatherName(rs.getString(4));
				model.setMotherName(rs.getString(5));
				model.setCollegeId(rs.getString(6));
				model.setDepartement(rs.getString(7));
				model.setSemester(rs.getInt(8));
				model.setYear(rs.getInt(9));
				model.setDob(rs.getDate(10));
				model.setGender(rs.getString(11));
				model.setMobileNo(rs.getString(12));
				model.setAddress(rs.getString(13));
				model.setUserId(rs.getLong(14));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return model;
	}

	
	public StaffModel findByUserId(long userId) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_STAFF  WHERE USER_ID=?");

		StaffModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new StaffModel();
				model.setId(rs.getLong(1));
				model.setFirstName(rs.getString(2));
				model.setLastName(rs.getString(3));
				model.setFatherName(rs.getString(4));
				model.setMotherName(rs.getString(5));
				model.setCollegeId(rs.getString(6));
				model.setDepartement(rs.getString(7));
				model.setSemester(rs.getInt(8));
				model.setYear(rs.getInt(9));
				model.setDob(rs.getDate(10));
				model.setGender(rs.getString(11));
				model.setMobileNo(rs.getString(12));
				model.setAddress(rs.getString(13));
				model.setUserId(rs.getLong(14));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return model;
	}
	
	/**
	 * Update a Student
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */

	public void update() throws ApplicationException {
		log.debug("Model update Started");
		Connection conn = null;
		// get College Name
		// CollegeModel cModel = new CollegeModel();
		// CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
		// bean.setCollegeName(collegeBean.getName());

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE ST_STAFF  SET FIRST_NAME=?,"
							+ "LAST_NAME=?,FATHER_NAME=?,MOTHER_NAME=?,COLLEGE_ID=?,DEPARTEMENT=?,"
							+ "SEMESTER=?,YEAR=?,DATE_OF_BIRTH=?,GENDER=?,MOBILE_NO=?,"
							+ "ADDRESS=?,USER_ID=? WHERE ID=?");

			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, fatherName);
			pstmt.setString(4, motherName);
			pstmt.setString(5, collegeId);
			pstmt.setString(6, departement);
			pstmt.setInt(7, semester);
			pstmt.setInt(8, year);
			pstmt.setDate(9, new java.sql.Date(dob.getTime()));
			pstmt.setString(10, gender);
			pstmt.setString(11, mobileNo);
			pstmt.setString(12, address);
			pstmt.setLong(13, userId);
			pstmt.setLong(14, id);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
			updateModifiedInfo();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			JDBCDataSource.trnRollback(conn);
			throw new ApplicationException(e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search Student
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List search() throws ApplicationException {
		return search(0, 0);
	}

	/**
	 * Search Student with pagination
	 * 
	 * @return list : List of Students
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public List search(int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STAFF WHERE 1=1");
				 

		if (id > 0) {
			sql.append(" AND id = " + id);
		}
			if (firstName!= null
					&& firstName.length() > 0) {
				sql.append(" AND FIRST_NAME like '" + firstName
						+ "%'");
			}
			if (lastName != null && lastName.length() > 0) {
				sql.append(" AND LAST_NAME like '" + lastName + "%'");
			}
			if (fatherName != null
					&& fatherName.length() > 0) {
				sql.append(" AND FATHER_NAME like '" + fatherName
						+ "%'");
			}
			if (motherName != null
					&& motherName.length() > 0) {
				sql.append(" AND MOTHER_NAME like '" + motherName
						+ "%'");
			}
			if (collegeId != null && collegeId.length() > 0) {
				sql.append(" AND COLLEGE_ID like '" + collegeId
						+ "%'");
			}
			if (departement != null
					&& departement.length() > 0) {
				sql.append(" AND DEPARTMENT like '" + departement
						+ "%'");
			}
			if (semester != 0 && semester > 0) {
				sql.append(" AND SEMESTER like '" + semester + "%'");
			}
			if (dob!= null && dob.getDate() > 0) {
				sql.append(" AND DOB = " + dob);
			}
			if (mobileNo != null && mobileNo.length() > 0) {
				sql.append(" AND MOBILE_NO like '" + mobileNo + "%'");
			}

			if (address != null && address.length() > 0) {
				sql.append(" AND ADDRESS like = " + address);
			}

		

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StaffModel	model = new StaffModel();
				model.setId(rs.getLong(1));
				model.setFirstName(rs.getString(2));
				model.setLastName(rs.getString(3));
				model.setFatherName(rs.getString(4));
				model.setMotherName(rs.getString(5));
				model.setCollegeId(rs.getString(6));
				model.setDepartement(rs.getString(7));
				model.setSemester(rs.getInt(8));
				model.setYear(rs.getInt(9));
				model.setDob(rs.getDate(10));
				model.setGender(rs.getString(11));
				model.setMobileNo(rs.getString(12));
				model.setAddress(rs.getString(13));

				model.setUserId(rs.getLong(14));

				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return firstName + " " + lastName;
	}

	@Override
	public String getTableName() {
		return "ST_STAFF";
	}
	
}