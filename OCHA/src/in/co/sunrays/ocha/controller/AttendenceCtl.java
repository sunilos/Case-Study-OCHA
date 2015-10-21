package in.co.sunrays.ocha.controller;

import in.co.sunrays.common.controller.BaseCtl;
import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.common.model.UserModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.AppRole;
import in.co.sunrays.ocha.model.AttendenceModel;
import in.co.sunrays.util.AccessUtility;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class AttendenceCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(AttendenceCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		UserModel usermodel = new UserModel();
		try {
			List userList = usermodel.studentlist();
			request.setAttribute("userList", userList);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("AttendenceCtl Method validate Started");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("studentId"))) {
			request.setAttribute("error.studentId",
					PropertyReader.getValue("error.require", "Student Id"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("branch"))) {
			request.setAttribute("error.branch",
					PropertyReader.getValue("error.require", "Branch"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("studentName"))) {
			request.setAttribute("error.studentName",
					PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("month"))) {
			request.setAttribute("error.month",
					PropertyReader.getValue("error.require", "Month"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("year"))) {
			request.setAttribute("error.year",
					PropertyReader.getValue("error.require", "Year"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subject1"))) {
			request.setAttribute("error.subject1",
					PropertyReader.getValue("error.require", "Subject 1"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subject2"))) {
			request.setAttribute("error.subject2",
					PropertyReader.getValue("error.require", "Subject 2"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subject3"))) {
			request.setAttribute("error.subject3",
					PropertyReader.getValue("error.require", "Subject 3"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subject4"))) {
			request.setAttribute("error.subject4",
					PropertyReader.getValue("error.require", "Subject 4"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subject5"))) {
			request.setAttribute("error.subject5",
					PropertyReader.getValue("error.require", "Subject 5"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subject6"))) {
			request.setAttribute("error.subject6",
					PropertyReader.getValue("error.require", "Subject 6"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subject7"))) {
			request.setAttribute("error.subject7",
					PropertyReader.getValue("error.require", "Subject 7"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subject8"))) {
			request.setAttribute("error.subject8",
					PropertyReader.getValue("error.require", "Subject 8"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subject9"))) {
			request.setAttribute("error.subject9",
					PropertyReader.getValue("error.require", "Subject 9"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subject10"))) {
			request.setAttribute("error.subject10",
					PropertyReader.getValue("error.require", "Subject 10"));
			pass = false;
		}
		System.out.println("....................in validate");
		log.debug("AttendenceCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {
		log.debug("AttendenceCtl Method populatebean Started");
		AttendenceModel model = new AttendenceModel();
		model.setId(DataUtility.getLong(request.getParameter("id")));
		model.setStudentId(DataUtility.getString(request
				.getParameter("studentId")));
		model.setStudentName(DataUtility.getString(request
				.getParameter("studentName")));
		model.setBranchName(DataUtility.getString(request
				.getParameter("branch")));
		model.setMonth(DataUtility.getString(request.getParameter("month")));
		model.setYear(DataUtility.getInt(request.getParameter("year")));
		model.setSubject1(DataUtility.getString(request
				.getParameter("subject1")));
		model.setSubject2(DataUtility.getString(request
				.getParameter("subject2")));
		model.setSubject3(DataUtility.getString(request
				.getParameter("subject3")));
		model.setSubject4(DataUtility.getString(request
				.getParameter("subject4")));
		model.setSubject5(DataUtility.getString(request
				.getParameter("subject5")));
		model.setSubject6(DataUtility.getString(request
				.getParameter("subject6")));
		model.setSubject7(DataUtility.getString(request
				.getParameter("subject7")));
		model.setSubject8(DataUtility.getString(request
				.getParameter("subject8")));
		model.setSubject9(DataUtility.getString(request
				.getParameter("subject9")));
		model.setSubject10(DataUtility.getString(request
				.getParameter("subject10")));
		model.setAttendence1(DataUtility.getInt(request
				.getParameter("attendance1")));
		model.setAttendence2(DataUtility.getInt(request
				.getParameter("attendance2")));
		model.setAttendence3(DataUtility.getInt(request
				.getParameter("attendance3")));
		model.setAttendence4(DataUtility.getInt(request
				.getParameter("attendance4")));
		model.setAttendence5(DataUtility.getInt(request
				.getParameter("attendance5")));
		model.setAttendence6(DataUtility.getInt(request
				.getParameter("attendance6")));
		model.setAttendence7(DataUtility.getInt(request
				.getParameter("attendance7")));
		model.setAttendence8(DataUtility.getInt(request
				.getParameter("attendance8")));
		model.setAttendence9(DataUtility.getInt(request
				.getParameter("attendance9")));
		model.setAttendence10(DataUtility.getInt(request
				.getParameter("attendance10")));
		
		populateModel(model, request);
		log.debug("AttendenceCtl Method populatebean End");
		return model;
	}

	/**
	 * Contains Display Logic
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AttendenceModel model = new AttendenceModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			try {
				AttendenceModel dbModel = model.findByPK(id);
				ServletUtility.setModel(dbModel, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forwardView(ORSView.ATTENDENCE_VIEW, request, response);
	}
	/**
	 * 
	 * Contains Submit Logic
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("AttendenceCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		AttendenceModel model = (AttendenceModel) populateModel(request);

		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				 if (id > 0) {
				model.update();
				 } else {
				UserModel userModel = new UserModel();
				userModel = userModel.findByPK(DataUtility.getInt(model
						.getStudentName()));
				model.setStudentName(userModel.getValue());
				long pk = model.add();
				model.setId(pk);
				 }
				ServletUtility.setModel(model, request);
				ServletUtility.setSuccessMessage("Data is successfully saved",
						request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			try {
				model.delete();
				ServletUtility.redirect(ORSView.ATTENDENCE_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ATTENDENCE_LIST_CTL, request,
					response);
			return;
		} else { // View page
			if (id > 0 || op != null) {
				AttendenceModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}

		ServletUtility.forwardView(ORSView.ATTENDENCE_VIEW, request, response);
	}

	@Override
	protected String getView() {
		return ORSView.ATTENDENCE_VIEW;
	}
	@Override
	protected void setAccess(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.setAccess(request);
		
		AccessUtility.setAddAccess("" + AppRole.ADMIN, request);
		
		AccessUtility.setWriteAccess("" + AppRole.ADMIN, request);

		
	}

}