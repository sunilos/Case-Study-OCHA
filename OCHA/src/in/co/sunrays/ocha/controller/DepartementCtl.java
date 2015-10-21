package in.co.sunrays.ocha.controller;

import in.co.sunrays.common.controller.BaseCtl;
import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.AppRole;
import in.co.sunrays.ocha.model.CollegeModel;
import in.co.sunrays.ocha.model.DepartementModel;
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

/**
 * Student functionality Controller. Performs operation for add, update, delete
 * and get Student
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class DepartementCtl extends BaseCtl {

	/**
	 * Logger to log the messages.
	 */
	
	private static Logger log = Logger.getLogger(DepartementCtl.class);

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
	}

	/**
	 * Validates Input data
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("DecriptionCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("code"))) {
			request.setAttribute("code",
					PropertyReader.getValue("error.require", "Code"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("decription"))) {
			request.setAttribute("decription",
					PropertyReader.getValue("error.require", "Decription"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId",
					PropertyReader.getValue("error.require", "College Id"));
			pass = false;
		}

		log.debug("DecriptionCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("DecriptionCtl Method populatebean Started");

		DepartementModel model = new DepartementModel();

		model.setId(DataUtility.getLong(request.getParameter("id")));

		// bean.setRoleId(RoleBean.STUDENT);
		model.setCode(DataUtility.getInt(request.getParameter("code")));
		model.setName(DataUtility.getString(request.getParameter("name")));
		model.setDecription(DataUtility.getString(request
				.getParameter("decription")));
		model.setCollegeId(DataUtility.getString(request
				.getParameter("collegeId")));

		populateModel(model, request);
		log.debug("DecriptionCtl Method populatemodel Ended");

		return model;
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

		log.debug("DecriptionCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		DepartementModel model = (DepartementModel) populateModel(request);

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
				ServletUtility.redirect(ORSView.DEPARTEMENT_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				DepartementModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.DEPARTEMENT_VIEW, request, response);
		log.debug("DecriptionCtl Method doGet Ended");
	}

	/**
	 * Contains Display Logic
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		DepartementModel model = new DepartementModel();

		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(ORSView.DEPARTEMENT_VIEW, request, response);
	}

	@Override
	protected String getView() {
		return ORSView.DEPARTEMENT_VIEW;
	}
	
	@Override
	protected void setAccess(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.setAccess(request);
		
		AccessUtility.setAddAccess("" + AppRole.ADMIN, request);
		
		AccessUtility.setWriteAccess("" + AppRole.ADMIN, request);

		
	}
			
}
