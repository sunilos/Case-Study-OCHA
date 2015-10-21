package in.co.sunrays.common.controller;

import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.common.model.RoleModel;
import in.co.sunrays.common.model.UserModel;
import in.co.sunrays.ocha.controller.ORSView;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
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

/**
 * User registration functionality Controller. Performs operation for User
 * Registration
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class UserRegistrationCtl extends BaseCtl {

	public static final String OP_SIGN_UP = "SignUp";

	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(UserRegistrationCtl.class);

	/**
	 * Loads pre-loaded data like Dropdown List.
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		RoleModel model = new RoleModel();
		try {
			List l = model.search();
			request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}

	/**
	 * Validates Input data
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserRegistrationCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
		String dob = request.getParameter("dob");

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("error.firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("error.lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}
		if (DataValidator.isNull(login)) {
			request.setAttribute("error.login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		}
		// if (DataValidator.isNull(request.getParameter("collegeId"))) {
		// request.setAttribute("error.collegeId",
		// PropertyReader.getValue("error.require", "College Id"));
		// pass = false;
		// }
		if (DataValidator.isNull(request.getParameter("department"))) {
			request.setAttribute("error.branch",
					PropertyReader.getValue("error.require", "Department"));
			pass = false;
		}
		// if (DataValidator.isNull(request.getParameter("year"))) {
		// request.setAttribute("error.year",
		// PropertyReader.getValue("error.require", "Year"));
		// pass = false;
		// }
		if (DataValidator.isNull(request.getParameter("fatherName"))) {
			request.setAttribute("error.fatherName",
					PropertyReader.getValue("error.require", "Father Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("motherName"))) {
			request.setAttribute("error.motherName",
					PropertyReader.getValue("error.require", "Mother Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("error.address",
					PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}

		else if (!DataValidator.isEmail(login)) {
			request.setAttribute("error.login",
					PropertyReader.getValue("error.email", "Login "));
			pass = false;
		}

		String password = request.getParameter("password");
		String conPass = request.getParameter("confirmPassword");

		if (DataValidator.isNull(password)) {
			request.setAttribute("error.password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;
		} else {
			if (!password.equals(conPass)) {
				request.setAttribute("error.confirmPassword",
						"Password does not match with confirm password.");
				pass = false;
			}
		}
		if (DataValidator.isNull(conPass)) {
			request.setAttribute("error.confirmPassword", PropertyReader
					.getValue("error.require", "Confirm Password"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("error.gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(dob)) {
			request.setAttribute("error.dob",
					PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(dob)) {
			request.setAttribute("error.dob",
					PropertyReader.getValue("error.date", "Date Of Birth"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("error.mobileNo",
					PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		}

		log.debug("UserRegistrationCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {
		log.debug("UserRegistrationCtl Method populateModel Started");
		UserModel model = new UserModel();
		model.setId(DataUtility.getLong(request.getParameter("id")));
		// bean.setRoleId(RoleBean.STUDENT);
		model.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
		model.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));
		model.setLastName(DataUtility.getString(request
				.getParameter("lastName")));
		// model.setCollegeId(DataUtility.getString(request
		// .getParameter("collegeId")));
		// model.setCollegeCode(DataUtility.getString(request
		// .getParameter("collegeCode")));
		model.setDepartent(DataUtility.getString(request
				.getParameter("department")));
		model.setYear(DataUtility.getInt(request.getParameter("year")));
		model.setFatherName(DataUtility.getString(request
				.getParameter("fatherName")));
		model.setMotherName(DataUtility.getString(request
				.getParameter("motherName")));
		model.setAddress(DataUtility.getString(request.getParameter("address")));
		model.setLogin(DataUtility.getString(request.getParameter("login")));
		model.setMobileNo(DataUtility.getString(request
				.getParameter("mobileNo")));
		model.setPassword(DataUtility.getString(request
				.getParameter("password")));
		model.setConfirmPassword(DataUtility.getString(request
				.getParameter("confirmPassword")));
		model.setGender(DataUtility.getString(request.getParameter("gender")));
		model.setDob(DataUtility.getDate(request.getParameter("dob")));
		
		populateModel(model, request);
		
		log.debug("UserRegistrationCtl Method populateModel Ended");
		return model;
	}

	/**
	 * Contains Display Logic
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long id = DataUtility.getLong(request.getParameter("id"));
		UserModel model = new UserModel();
		if (id > 0) {
			try {
				UserModel dbModel = model.findByPK(id);
				ServletUtility.setModel(dbModel, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(ORSView.USER_REGISTRATION_VIEW, request,
				response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserRegistrationCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			UserModel populateModel = (UserModel) populateModel(request);
			try {
				long pk = populateModel.registerUser();
				populateModel.setId(pk);
				request.getSession().setAttribute("UserBean", populateModel);
				ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setModel(populateModel, request);
				ServletUtility.setErrorMessage("Login id already exists",
						request);
				ServletUtility.forwardView(ORSView.USER_REGISTRATION_VIEW,
						request, response);
			}
		} else {
			ServletUtility.forwardView(ORSView.USER_REGISTRATION_VIEW, request,
					response);
		}
		log.debug("UserRegistrationCtl Method doGet Ended");
	}

	/**
	 * Returns View page of Controller.
	 */

	@Override
	protected String getView() {
		return ORSView.USER_REGISTRATION_VIEW;
	}

}