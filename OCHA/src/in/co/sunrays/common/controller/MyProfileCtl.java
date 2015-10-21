package in.co.sunrays.common.controller;

import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.common.model.UserModel;
import in.co.sunrays.ocha.controller.ORSView;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * My Profile functionality Controller. Performs operation for update your
 * Profile
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class MyProfileCtl extends BaseCtl {

	public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";

	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(MyProfileCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MyProfileCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op) || op == null) {

			return pass;
		}

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			System.out.println("firstName" + request.getParameter("firstName"));
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.require", "MobileNo"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		}

		log.debug("MyProfileCtl Method validate Ended");

		return pass;

	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {
		log.debug("MyProfileCtl Method populatebean Started");

		UserModel model = new UserModel();

		model.setId(DataUtility.getLong(request.getParameter("id")));

		model.setLogin(DataUtility.getString(request.getParameter("login")));

		model.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));

		model.setLastName(DataUtility.getString(request
				.getParameter("lastName")));

		model.setMobileNo(DataUtility.getString(request
				.getParameter("mobileNo")));

		model.setGender(DataUtility.getString(request.getParameter("gender")));

		model.setDob(DataUtility.getDate(request.getParameter("dob")));

		populateModel(model, request);

		return model;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		UserModel sessionModel = (UserModel) session.getAttribute("user");
		long id = sessionModel.getId();

		UserModel model = new UserModel();

		if (id > 0) {
			System.out.println("in id > 0  condition");
			try {
				UserModel dbModel = model.findByPK(id);
				ServletUtility.setModel(dbModel, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forwardView(ORSView.MY_PROFILE_VIEW, request, response);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log.debug("MyprofileCtl Method doGet Started");

		UserModel sessionModel = (UserModel) session.getAttribute("user");
		long id = sessionModel.getId();
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModel model = new UserModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			UserModel populateModel = (UserModel) populateModel(request);
			try {
				if (id > 0) {
					sessionModel.setFirstName(populateModel.getFirstName());
					sessionModel.setLastName(populateModel.getLastName());
					sessionModel.setGender(populateModel.getGender());
					sessionModel.setMobileNo(populateModel.getMobileNo());
					sessionModel.setDob(populateModel.getDob());
					sessionModel.update();
				}
				ServletUtility.setModel(populateModel, request);
				ServletUtility.setSuccessMessage(
						"Profile has been updated Successfully. ", request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setModel(populateModel, request);
				ServletUtility.setErrorMessage("Login id already exists",
						request);
			}
		} else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request,
					response);
			return;

		} else { // View page
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				UserModel dbModel;
				try {
					dbModel = model.findByPK(id);
					ServletUtility.setModel(dbModel, request);
				} catch (ApplicationException e) {
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				}
			}

		}

		ServletUtility.forwardView(ORSView.MY_PROFILE_VIEW, request, response);

		log.debug("MyProfileCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.MY_PROFILE_VIEW;
	}

}