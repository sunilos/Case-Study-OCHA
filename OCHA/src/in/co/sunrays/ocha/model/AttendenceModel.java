package in.co.sunrays.ocha.model;

import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DatabaseException;
import in.co.sunrays.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Model contains Attendence attributes and its Create, Read, Update and Delete
 * methods.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class AttendenceModel extends BaseModel {

	private static Logger log = Logger.getLogger(AttendenceModel.class);
	private String studentId;
	private String studentName;
	private String branchName;
	private String month;
	private String subject;
	private int attendence;
	private int year;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getAttendence() {
		return attendence;
	}

	public void setAttendence(int attendence) {
		this.attendence = attendence;
	}

	private String subject1;
	private String subject2;
	private String subject3;
	private String subject4;
	private String subject5;
	private String subject6;
	private String subject7;
	private String subject8;
	private String subject9;
	private String subject10;
	private int attendence1;
	private int attendence2;
	private int attendence3;
	private int attendence4;
	private int attendence5;
	private int attendence6;
	private int attendence7;
	private int attendence8;
	private int attendence9;
	private int attendence10;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getSubject1() {
		return subject1;
	}

	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}

	public String getSubject2() {
		return subject2;
	}

	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}

	public String getSubject3() {
		return subject3;
	}

	public void setSubject3(String subject3) {
		this.subject3 = subject3;
	}

	public String getSubject4() {
		return subject4;
	}

	public void setSubject4(String subject4) {
		this.subject4 = subject4;
	}

	public String getSubject5() {
		return subject5;
	}

	public void setSubject5(String subject5) {
		this.subject5 = subject5;
	}

	public String getSubject6() {
		return subject6;
	}

	public void setSubject6(String subject6) {
		this.subject6 = subject6;
	}

	public String getSubject7() {
		return subject7;
	}

	public void setSubject7(String subject7) {
		this.subject7 = subject7;
	}

	public String getSubject8() {
		return subject8;
	}

	public void setSubject8(String subject8) {
		this.subject8 = subject8;
	}

	public String getSubject9() {
		return subject9;
	}

	public void setSubject9(String subject9) {
		this.subject9 = subject9;
	}

	public String getSubject10() {
		return subject10;
	}

	public void setSubject10(String subject10) {
		this.subject10 = subject10;
	}

	public int getAttendence1() {
		return attendence1;
	}

	public void setAttendence1(int attendence1) {
		this.attendence1 = attendence1;
	}

	public int getAttendence2() {
		return attendence2;
	}

	public void setAttendence2(int attendence2) {
		this.attendence2 = attendence2;
	}

	public int getAttendence3() {
		return attendence3;
	}

	public void setAttendence3(int attendence3) {
		this.attendence3 = attendence3;
	}

	public int getAttendence4() {
		return attendence4;
	}

	public void setAttendence4(int attendence4) {
		this.attendence4 = attendence4;
	}

	public int getAttendence5() {
		return attendence5;
	}

	public void setAttendence5(int attendence5) {
		this.attendence5 = attendence5;
	}

	public int getAttendence6() {
		return attendence6;
	}

	public void setAttendence6(int attendence6) {
		this.attendence6 = attendence6;
	}

	public int getAttendence7() {
		return attendence7;
	}

	public void setAttendence7(int attendence7) {
		this.attendence7 = attendence7;
	}

	public int getAttendence8() {
		return attendence8;
	}

	public void setAttendence8(int attendence8) {
		this.attendence8 = attendence8;
	}

	public int getAttendence9() {
		return attendence9;
	}

	public void setAttendence9(int attendence9) {
		this.attendence9 = attendence9;
	}

	public int getAttendence10() {
		return attendence10;
	}

	public void setAttendence10(int attendence10) {
		this.attendence10 = attendence10;
	}

	/**
	 * Adds a record
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	public long add() throws ApplicationException {
		log.debug("Model add Started");
		Connection conn = null;
		long pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			conn.setAutoCommit(false); // Begin transaction
			String sql = "INSERT INTO ST_ATTENDENCE (ID,STUDENT_ID,STUDENT_NAME,"
					+ "BRANCH_NAME,MONTH,YEAR,SUBJECT1,SUBJECT2,SUBJECT3,SUBJECT4,SUBJECT5,"
					+ "SUBJECT6,SUBJECT7,SUBJECT8,SUBJECT9,SUBJECT10,ATTENDENCE1,ATTENDENCE2,"
					+ "ATTENDENCE3,ATTENDENCE4,ATTENDENCE5,ATTENDENCE6,ATTENDENCE7,ATTENDENCE8,"
					+ "ATTENDENCE9,ATTENDENCE10) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			log.info("SQL : " + sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pk);
			pstmt.setString(2, studentId);
			pstmt.setString(3, studentName);
			pstmt.setString(4, branchName);
			pstmt.setString(5, month);
			pstmt.setInt(6, year);
			pstmt.setString(7, subject1);
			pstmt.setString(8, subject2);
			pstmt.setString(9, subject3);
			pstmt.setString(10, subject4);
			pstmt.setString(11, subject5);
			pstmt.setString(12, subject6);
			pstmt.setString(13, subject7);
			pstmt.setString(14, subject8);
			pstmt.setString(15, subject9);
			pstmt.setString(16, subject10);
			pstmt.setInt(17, attendence1);
			pstmt.setInt(18, attendence2);
			pstmt.setInt(19, attendence3);
			pstmt.setInt(20, attendence4);
			pstmt.setInt(21, attendence5);
			pstmt.setInt(22, attendence6);
			pstmt.setInt(23, attendence7);
			pstmt.setInt(24, attendence8);
			pstmt.setInt(25, attendence9);
			pstmt.setInt(26, attendence10);
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
	 * Deletes a record
	 * 
	 * @throws ApplicationException
	 */
	public void delete() throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			String sql = "DELETE FROM ST_ATTENDENCE WHERE ID=?";
			log.info("SQL : " + sql);

			PreparedStatement pstmt = conn.prepareStatement(sql);
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
					"Exception : Exception in delete Attendence");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Finds record by Primary Key ( ID)
	 * 
	 * @param pk
	 * @return
	 * @throws ApplicationException
	 */
	public AttendenceModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPk Started");

		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_ATTENDENCE WHERE ID=?");

		log.info("SQL : " + sql);

		AttendenceModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new AttendenceModel();
				model.setId(rs.getLong(1));
				model.setStudentId(rs.getString(2));
				model.setStudentName(rs.getString(3));
				model.setBranchName(rs.getString(4));
				model.setMonth(rs.getString(5));
				model.setYear(rs.getInt(6));
				model.setSubject1(rs.getString(7));
				model.setSubject2(rs.getString(8));
				model.setSubject3(rs.getString(9));
				model.setSubject4(rs.getString(10));
				model.setSubject5(rs.getString(11));
				model.setSubject6(rs.getString(12));
				model.setSubject7(rs.getString(13));
				model.setSubject8(rs.getString(14));
				model.setSubject9(rs.getString(15));
				model.setSubject10(rs.getString(16));
				model.setAttendence1(rs.getInt(17));
				model.setAttendence2(rs.getInt(18));
				model.setAttendence3(rs.getInt(19));
				model.setAttendence4(rs.getInt(20));
				model.setAttendence5(rs.getInt(21));
				model.setAttendence6(rs.getInt(22));
				model.setAttendence7(rs.getInt(23));
				model.setAttendence8(rs.getInt(24));
				model.setAttendence9(rs.getInt(25));
				model.setAttendence10(rs.getInt(26));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Attendence by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByName End");
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

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE ST_ATTENDENCE  SET STUDENT_ID=?,"
							+ "STUDENT_NAME=?,BRANCH_NAME=?,MONTH=?,YEAR=?,SUBJECT1=?,"
							+ "SUBJECT2=?,SUBJECT3=?,SUBJECT4=?,SUBJECT5=?,SUBJECT6=?,"
							+ "SUBJECT7=?,SUBJECT8=?,SUBJECT9=?,SUBJECT10=?,ATTENDENCE1=?,"
							+ "ATTENDENCE2=?,ATTENDENCE3=?,ATTENDENCE4=?,ATTENDENCE5=?,"
							+ "ATTENDENCE6=?,ATTENDENCE7=?,ATTENDENCE8=?,"
							+ "ATTENDENCE9=?,ATTENDENCE10=? WHERE ID=?");

			pstmt.setString(1, studentId);
			pstmt.setString(2, studentName);
			pstmt.setString(3, branchName);
			pstmt.setString(4, month);
			pstmt.setInt(5, year);
			pstmt.setString(6, subject1);
			pstmt.setString(7, subject2);
			pstmt.setString(8, subject3);
			pstmt.setString(9, subject4);
			pstmt.setString(10, subject5);
			pstmt.setString(11, subject6);
			pstmt.setString(12, subject7);
			pstmt.setString(13, subject8);
			pstmt.setString(14, subject9);
			pstmt.setString(15, subject10);
			pstmt.setInt(16, attendence1);
			pstmt.setInt(17, attendence2);
			pstmt.setInt(18, attendence3);
			pstmt.setInt(19, attendence4);
			pstmt.setInt(20, attendence5);
			pstmt.setInt(21, attendence6);
			pstmt.setInt(22, attendence7);
			pstmt.setInt(23, attendence8);
			pstmt.setInt(24, attendence9);
			pstmt.setInt(25, attendence10);
			pstmt.setLong(26, id);
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
	 * Searches records on the basis of model NOT NULL attributes with
	 * pagination.
	 * 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws ApplicationException
	 */
	public List search(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_ATTENDENCE  WHERE 1=1");

		if (id > 0) {
			sql.append(" AND ID = " + id);
		}
		if (studentId != null && studentId.length() > 0) {
			sql.append(" AND STUDENT_ID = '" + studentId + "'");
		}
		if (studentName != null && studentName.length() > 0) {
			sql.append(" AND STUDENT_NAME LIKE '" + studentName + "%'");
		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		log.info("SQL : " + sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AttendenceModel model = new AttendenceModel();
				model.setId(rs.getLong(1));
				model.setStudentId(rs.getString(2));
				model.setStudentName(rs.getString(3));
				model.setBranchName(rs.getString(4));
				model.setMonth(rs.getString(5));
				model.setYear(rs.getInt(6));
				model.setSubject1(rs.getString(7));
				model.setSubject2(rs.getString(8));
				model.setSubject3(rs.getString(9));
				model.setSubject4(rs.getString(10));
				model.setSubject5(rs.getString(11));
				model.setSubject6(rs.getString(12));
				model.setSubject7(rs.getString(13));
				model.setSubject8(rs.getString(14));
				model.setSubject9(rs.getString(15));
				model.setSubject10(rs.getString(16));
				model.setAttendence1(rs.getInt(17));
				model.setAttendence2(rs.getInt(18));
				model.setAttendence3(rs.getInt(19));
				model.setAttendence4(rs.getInt(20));
				model.setAttendence5(rs.getInt(21));
				model.setAttendence6(rs.getInt(22));
				model.setAttendence7(rs.getInt(23));
				model.setAttendence8(rs.getInt(24));
				model.setAttendence9(rs.getInt(25));
				model.setAttendence10(rs.getInt(26));
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Attendence");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Searches records on the basis of model NOT NULL attributes
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	public List search(AttendenceModel model) throws ApplicationException {
		return search(0, 0);
	}

	/**
	 * Finds record by Student ID
	 * 
	 * @param pk
	 * @return
	 * @throws ApplicationException
	 */
	public List findByStudentPk(AttendenceModel model, int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model list Started");
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM  ST_ATTENDENCE WHERE STUDENT_ID = '"
						+ model.getStudentId() + "'");

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + "," + pageSize);
		}
		log.info("SQL : " + sql);
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new AttendenceModel();
				model.setId(rs.getLong(1));
				model.setStudentId(rs.getString(2));
				model.setStudentName(rs.getString(3));
				model.setBranchName(rs.getString(4));
				model.setMonth(rs.getString(5));
				model.setYear(rs.getInt(6));
				model.setSubject1(rs.getString(7));
				model.setSubject2(rs.getString(8));
				model.setSubject3(rs.getString(9));
				model.setSubject4(rs.getString(10));
				model.setSubject5(rs.getString(11));
				model.setSubject6(rs.getString(12));
				model.setSubject7(rs.getString(13));
				model.setSubject8(rs.getString(14));
				model.setSubject9(rs.getString(15));
				model.setSubject10(rs.getString(16));
				model.setAttendence1(rs.getInt(17));
				model.setAttendence2(rs.getInt(18));
				model.setAttendence3(rs.getInt(19));
				model.setAttendence4(rs.getInt(20));
				model.setAttendence5(rs.getInt(21));
				model.setAttendence6(rs.getInt(22));
				model.setAttendence7(rs.getInt(23));
				model.setAttendence8(rs.getInt(24));
				model.setAttendence9(rs.getInt(25));
				model.setAttendence10(rs.getInt(26));
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

	public List findByStudentPk(AttendenceModel model)
			throws ApplicationException {
		return findByStudentPk(model, 0, 0);
	}

	/**
	 * Returns Drop Down List key
	 */
	@Override
	public String getKey() {
		return id + "";
	}

	/**
	 * Returns Drop Down List Value
	 */
	@Override
	public String getValue() {
		return studentId;
	}

	/**
	 * Returns name of table
	 */
	@Override
	public String getTableName() {
		return "ST_ATTENDENCE";
	}

}
