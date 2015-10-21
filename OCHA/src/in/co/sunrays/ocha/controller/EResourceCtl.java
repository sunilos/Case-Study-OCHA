package in.co.sunrays.ocha.controller;

import in.co.sunrays.common.controller.BaseCtl;
import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.AppRole;
import in.co.sunrays.ocha.model.CollegeModel;
import in.co.sunrays.ocha.model.EResourceModel;
import in.co.sunrays.util.AccessUtility;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Contains navigation logic for Comment Views.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class EResourceCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(EResourceCtl.class);

	/**
	 * Validates Input data
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("EResourceCtl Method validate Started");

		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("tablesContains"))) {
			request.setAttribute("tablesContains",
					PropertyReader.getValue("error.require", "Table Contains"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require", "Link"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("detail"))) {
			request.setAttribute("detail",
					PropertyReader.getValue("error.require", "Detail"));
			pass = false;
		}

		log.debug("EResourceCtl Method validate Ended");

		return pass;

	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("EResourceModel Method populatebean Started");

		EResourceModel model = new EResourceModel();

		model.setId(DataUtility.getLong(request.getParameter("id")));

		model.setTablesContains(DataUtility.getString(request
				.getParameter("tablesContains")));
		model.setName(DataUtility.getString(request.getParameter("name")));
		model.setDetail(DataUtility.getString(request.getParameter("detail")));
		model.setCreatedOn(DataUtility.getCurrentTimestamp());

		model = populateModel(model, request);
		return model;
	}
	/**
	 * Contains Display Logic
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		EResourceModel model = new EResourceModel();

		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(ORSView.ERESOURCE_VIEW, request, response);
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
		
		log.debug("CollegeCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		
		EResourceModel model = (EResourceModel) populateModel(request);

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
				ServletUtility.redirect(ORSView.ERESOURCE_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.ERESOURCE_LIST_CTL, request,
					response);
			return;
		} else { // View page

			if (id > 0 || op != null) {

				try {
					model = model.findByPK(id);
					ServletUtility.setModel(model, request);
				} catch (ApplicationException e) {
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}

		ServletUtility.forwardView(ORSView.ERESOURCE_VIEW, request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.ERESOURCE_VIEW;
	}
	@Override
	protected void setAccess(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.setAccess(request);
		
		AccessUtility.setAddAccess("" + AppRole.ADMIN, request);
		
		AccessUtility.setWriteAccess("" + AppRole.ADMIN, request);

		
	}
}
