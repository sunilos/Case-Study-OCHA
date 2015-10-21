package in.co.sunrays.common.controller;

import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.common.model.UserModel;
import in.co.sunrays.ocha.model.AppRole;
import in.co.sunrays.util.AccessUtility;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Base controller class of project. It contain (1) Generic operations (2)
 * Generic constants (3) Generic work flow
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public abstract class BaseCtl extends HttpServlet {

	public static final String OP_SAVE = "Save";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_GO = "Go";
	public static final String OP_BACK = "Back";
	public static final String OP_LOG_OUT = "Logout";

	/**
	 * Success message key constant
	 */
	public static final String MSG_SUCCESS = "success";

	/**
	 * Error message key constant
	 */
	public static final String MSG_ERROR = "error";

	/**
	 * Validates input data entered by User
	 * 
	 * @param request
	 * @return
	 */
	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	protected void preload(HttpServletRequest request) {
	}

	/**
	 * Populates generic attributes in Model object from request parameters
	 * 
	 * @param request
	 * @return
	 */

	protected <T extends BaseModel> T populateModel(T model,
			HttpServletRequest request) {

		model.setCreatedBy("root");
		model.setModifiedBy("root");
		
		if (ServletUtility.isLoggedIn(request)) {
			UserModel userbean = ServletUtility.getUserModel(request);
			if(model.getId() > 0){
				model.setModifiedBy(userbean.getLogin());
			}else{
				model.setCreatedBy(userbean.getLogin());
			}
		}
		return model;
	}

	/**
	 * Override by Child classes to populate Model from request object
	 * 
	 * @param request
	 * @return
	 */
	protected BaseModel populateModel(HttpServletRequest request) {
		return null;
	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	protected abstract String getView();

	/**
	 * Set Access Permission of this Controller By default access permissions
	 * are given to Read : Guest, Staff, Student, Admin Add : Staff, Student,
	 * Admin Write : Staff, Student, Admin Delete : Admin
	 */
	protected void setAccess(HttpServletRequest request) {

		AccessUtility.setReadAccess(AppRole.GUEST + "" + AppRole.STAFF + ""
				+ AppRole.STUDENT + "" + AppRole.ADMIN, request);

		AccessUtility.setWriteAccess(AppRole.GUEST + "" + AppRole.STAFF + ""
				+ AppRole.ADMIN, request);

		AccessUtility.setAddAccess(AppRole.GUEST + "" + AppRole.STAFF + ""
				+ AppRole.ADMIN, request);

		AccessUtility.setDeleteAccess("" + AppRole.ADMIN, request);

	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Set Access Permissions
		setAccess(request);

		// Load the preloaded data required to display at HTML form
		preload(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		// Check if operation is not DELETE, VIEW, CANCEL, and NULL then
		// perform input data validation

		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op)
				&& !OP_VIEW.equalsIgnoreCase(op)
				&& !OP_DELETE.equalsIgnoreCase(op)) {
			// Check validation, If fail then send back to page with error
			// messages

			if (!validate(request)) {
				BaseModel model = (BaseModel) populateModel(request);
				ServletUtility.setModel(model, request);
				ServletUtility.forwardView(getView(), request, response);
				return;
			}
		}
		super.service(request, response);
	}
}
