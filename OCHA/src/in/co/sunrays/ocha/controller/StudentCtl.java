package in.co.sunrays.ocha.controller;
import in.co.sunrays.common.controller.BaseCtl;
import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.AppRole;
import in.co.sunrays.ocha.model.CollegeModel;
import in.co.sunrays.ocha.model.StudentModel;
import in.co.sunrays.util.AccessUtility;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Student functionality Controller. Performs operation for add, update, delete
 * and get Student
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class StudentCtl extends BaseCtl {

	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(StudentCtl.class);

	/**
	 * Loads pre-loaded data like Dropdown List.
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModel model = new CollegeModel();
		try {
			List list = model.search();
			request.setAttribute("collegeList", list);
		} catch (ApplicationException e) {
			log.error(e);
		}

		LinkedHashMap<String, String> semesterMap = new LinkedHashMap<String, String>();
		semesterMap.put("", "--Select--");
		semesterMap.put("1", "1");
		semesterMap.put("2", "2");
		semesterMap.put("3", "3");
		semesterMap.put("4", "4");
		semesterMap.put("5", "5");
		semesterMap.put("6", "6");
		semesterMap.put("7", "7");
		semesterMap.put("8", "8");
		request.setAttribute("semesterMap", semesterMap);

		LinkedHashMap<String, String> yearMap = new LinkedHashMap<String, String>();
		yearMap.put("", "--Select--");
		yearMap.put("1", "1");
		yearMap.put("2", "2");
		yearMap.put("3", "3");
		yearMap.put("4", "4");

		request.setAttribute("yearMap", yearMap);

		LinkedHashMap<String, String> departementMap = new LinkedHashMap<String, String>();
		departementMap.put("", "--Select--");
		departementMap.put("Information Technology", "Information Technology");
		departementMap.put("Computer Science", "Computer Science");
		departementMap
				.put("Electronic Comunication", "Electronic Comunication");
		departementMap.put("Civil Engg", "Civil Engg");
		departementMap.put("Mechanical Engg", "Mechanical Engg");

		request.setAttribute("departementMap", departementMap);

	}

	/**
	 * Validates Input data
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("StudentCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("fatherName"))) {
			request.setAttribute("fatherName",
					PropertyReader.getValue("error.require", "Father Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("motherName"))) {
			request.setAttribute("motherName",
					PropertyReader.getValue("error.require", "Mother Name"));
			
			pass = false;
			
		}
		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId",
					PropertyReader.getValue("error.require", "College Id"));
			
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("departement"))) {
			request.setAttribute("departement",
					PropertyReader.getValue("error.require", "Departement"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("semester"))) {
			request.setAttribute("semester",
					PropertyReader.getValue("error.require", "Semester"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("year"))) {
			request.setAttribute("year",
					PropertyReader.getValue("error.require", "Year"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address",
					PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("userId"))) {
			request.setAttribute("userId",
					PropertyReader.getValue("error.require", "User Id"));
			pass = false;
		}

		

		log.debug("StudentCtl Method validate Ended    " +pass);

		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("StudentCtl Method populatebean Started");

		StudentModel model = new StudentModel();

		model.setId(DataUtility.getLong(request.getParameter("id")));

		// bean.setRoleId(RoleBean.STUDENT);
		model.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));
		model.setLastName(DataUtility.getString(request
				.getParameter("lastName")));
		model.setFatherName(DataUtility.getString(request
				.getParameter("fatherName")));
		model.setMotherName(DataUtility.getString(request
				.getParameter("motherName")));
		model.setCollegeId(DataUtility.getString(request
				.getParameter("collegeId")));
		model.setDepartement(DataUtility.getString(request
				.getParameter("departement")));
		model.setSemester(DataUtility.getInt(request.getParameter("semester")));
		model.setYear(DataUtility.getInt(request.getParameter("year")));

		model.setDob(DataUtility.getDate(request.getParameter("dob")));
		model.setGender(DataUtility.getString(request.getParameter("gender")));
		model.setMobileNo(DataUtility.getString(request
				.getParameter("mobileNo")));
		model.setAddress(DataUtility.getString(request.getParameter("address")));
		model.setUserId(DataUtility.getLong(request.getParameter("userId")));
		
		populateModel(model, request);

		log.debug("StudentCtl Method populatemodel Ended");

		return model;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("StudentCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		StudentModel model = (StudentModel) populateModel(request);

		long id = model.getId();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				if (id > 0) {
					model.update();
				} else {
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
				ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				StudentModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.STUDENT_VIEW, request, response);
		log.debug("StudentCtl Method doGet Ended");
	}
	
	/**
	 * Contains Display Logic
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		StudentModel model = new StudentModel();

		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(ORSView.STUDENT_VIEW, request, response);
	}

	@Override
	protected String getView() {
		return ORSView.STUDENT_VIEW;
	}
	
	@Override
	protected void setAccess(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.setAccess(request);
		
		AccessUtility.setAddAccess("" + AppRole.ADMIN, request);
		
		AccessUtility.setWriteAccess("" + AppRole.ADMIN, request);
		
	}
}
