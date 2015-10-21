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
import java.util.List;

import org.apache.log4j.Logger;

public class TimeTableModel extends BaseModel {

	private static Logger log = Logger.getLogger(TimeTableModel.class);
	private long id;
	private String branch;
	private int semester;
	private int year;
	private String subject;
	private Date date;
	private String time;
	private String faculty;
	private Date fromTime;
	private Date toTime;

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public Date getToTime() {
		return toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public long add() throws ApplicationException {
		log.debug("Model add Started");
		Connection conn = null;
		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_TIMETABLE (ID,BRANCH,SEMESTER,YEAR,"
							+ "SUBJECT,DATE,TIME,FACULTY) VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, branch);
			pstmt.setInt(3, semester);
			pstmt.setInt(4, year);
			pstmt.setString(5, subject);
			pstmt.setDate(6, new java.sql.Date(date.getTime()));
			pstmt.setString(7, time);
			pstmt.setString(8, faculty);
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
	 * Updates a records
	 * 
	 * @throws ApplicationException
	 */
	public void update() throws ApplicationException {
		log.debug("Model update Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction

			String sql = "UPDATE ST_TIMETABLE SET BRANCH=?,SEMESTER=?,"
					+ "YEAR=?,SUBJECT=?,DATE=?,TIME=?,FACULTY=? WHERE ID=?";
				 
			log.info("SQL : " + sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, branch);
			pstmt.setInt(2, semester);
			pstmt.setInt(3, year);
			pstmt.setString(4, subject);
			pstmt.setDate(5, new java.sql.Date(date.getTime()));
			pstmt.setString(6, time);
			pstmt.setString(7, faculty);
			pstmt.setLong(8, id);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
			
			updateModifiedInfo();
		} catch (Exception e) {
			log.error(e);
			JDBCDataSource.trnRollback(conn);
			throw new ApplicationException(e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Delete a Timetable
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
					.prepareStatement("DELETE FROM ST_TIMETABLE WHERE ID=?");
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
					"Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Get List of Timetable
	 * 
	 * @return list : List of Timetable
	 * @throws DatabaseException
	 */
	public List<TimeTableModel> list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Timetable with pagination
	 * 
	 * @return list : List of Timetable
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List<TimeTableModel> list(int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model list Started");
		List<TimeTableModel> list = new ArrayList<TimeTableModel>();
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE");
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
				TimeTableModel model = new TimeTableModel();
				model.setId(rs.getLong(1));
				model.setBranch(rs.getString(2));
				model.setSemester(rs.getInt(3));
				model.setYear(rs.getInt(4));
				model.setSubject(rs.getString(5));
				model.setDate(rs.getDate(6));
				model.setTime(rs.getString(7));
				model.setFaculty(rs.getString(8));
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
	 * Find User by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public TimeTableModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_TIMETABLE WHERE ID=?");
		TimeTableModel model = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new TimeTableModel();
				model.setId(rs.getLong(1));
				model.setBranch(rs.getString(2));
				model.setSemester(rs.getInt(3));
				model.setYear(rs.getInt(4));
				model.setSubject(rs.getString(5));
				model.setDate(rs.getDate(6));
				model.setTime(rs.getString(7));
				model.setFaculty(rs.getString(8));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Timetable by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return model;
	}

	/**
	 * Search User
	 * 
	 * @param model
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List<TimeTableModel> search()
			throws ApplicationException {
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

	public List<TimeTableModel> search(int pageNo,
			int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_TIMETABLE WHERE 1=1");

		
			if (id > 0) {
				sql.append(" AND ID = " + id);
			}
			if (semester != 0) {
				sql.append(" AND SEMESTER = " + semester);
			}
			if (subject != null && subject.length() > 0) {
				sql.append(" AND SUBJECT like '" + subject + "%'");
			}
		
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		System.out.println(sql);
		List<TimeTableModel> list = new ArrayList<TimeTableModel>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TimeTableModel	model = new TimeTableModel();
				model.setId(rs.getLong(1));
				model.setBranch(rs.getString(2));
				model.setSemester(rs.getInt(3));
				model.setYear(rs.getInt(4));
				model.setSubject(rs.getString(5));
				model.setDate(rs.getDate(6));
				model.setTime(rs.getString(7));
				model.setFaculty(rs.getString(8));
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

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return branch;
	}

	@Override
	public String getTableName() {
		return "ST_TIMETABLE";
	}

}
