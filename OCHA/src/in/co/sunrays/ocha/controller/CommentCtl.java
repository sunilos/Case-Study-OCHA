package in.co.sunrays.ocha.controller;

import in.co.sunrays.common.controller.BaseCtl;
import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.AttendenceModel;
import in.co.sunrays.ocha.model.CollegeModel;
import in.co.sunrays.ocha.model.CommentModel;
import in.co.sunrays.ocha.model.EResourceModel;
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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Contains Comment navigation logics .
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class CommentCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(CommentCtl.class);

	/**
	 * Loads pre-loaded data like Dropdown List.
	 */

	@Override
	protected void preload(HttpServletRequest request) {
		EResourceModel model = new EResourceModel();
		try {
			List l = model.search();
			request.setAttribute("resourceList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	/**
	 * Validates Input data
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CommentCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("text"))) {
			request.setAttribute("text",
					PropertyReader.getValue("error.require", "text"));
			pass = false;
		}

		log.debug("CommentCtl Method validate Ended");

		return pass;

	}
	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("CommentCtl Method populatebean Started");

		CommentModel model = new CommentModel();
		model.setId(DataUtility.getLong(request.getParameter("id")));
		model.setText(DataUtility.getString(request.getParameter("text")));
		model.setName(DataUtility.getString(request.getParameter("name")));
		model.setResourceId(DataUtility.getLong(request
				.getParameter("resourceId")));
		
		populateModel(model, request);
		
		log.debug("CommentCtl Method populatemodel Ended");

		return model;
	}
	/**
	 * Contains Display Logic
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CommentModel model = new CommentModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			try {
				CommentModel dbModel = model.findByPK(id);
				ServletUtility.setModel(dbModel, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forwardView(ORSView.COMMENT_VIEW, request, response);
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

		log.debug("CommentCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		CommentModel model = (CommentModel) populateModel(request);

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
				ServletUtility.redirect(ORSView.COMMENT_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				CommentModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.COMMENT_VIEW, request, response);
		log.debug("CommentCtl Method doGet Ended");
	}
	/**
	 * Returns View page of Controller.
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COMMENT_VIEW;
	}
}
