package in.co.sunrays.ocha.controller;

import in.co.sunrays.common.controller.BaseCtl;
import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.AppRole;
import in.co.sunrays.ocha.model.NoticeModel;
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

public class NoticeCtl extends BaseCtl {

	/**
	 * Logger to log the messages.
	 */
	public static final String OP_SAVE_UP = "Save";
	private static Logger log = Logger.getLogger(NoticeCtl.class);

	/**
	 * Loads pre-loaded data like Dropdown List.
	 */
	@Override
	protected void preload(HttpServletRequest request) {
	
	}

	/**
	 * Validates Input data
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("NoticeCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("subject"))) {
			request.setAttribute("subject",
					PropertyReader.getValue("error.require", "Subject"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("details"))) {
			request.setAttribute("details",
					PropertyReader.getValue("error.require", "Details"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("createdOn"))) {
			request.setAttribute("createdOn",
					PropertyReader.getValue("error.require", "Created On"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("expireDate"))) {
			request.setAttribute("expireDate",
					PropertyReader.getValue("error.require", "Expire Date "));
			pass = false;
		}

		log.debug("NoticeCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("NoticeCtl Method populatebean Started");

		NoticeModel model = new NoticeModel();

		model.setId(DataUtility.getLong(request.getParameter("id")));

		// bean.setRoleId(RoleBean.STUDENT);
		model.setSubject(DataUtility.getString(request.getParameter("subject")));
		model.setDetails(DataUtility.getString(request.getParameter("details")));
		model.setCreatedOn(DataUtility.getDate(request
				.getParameter("createdOn")));
		model.setExpireDate(DataUtility.getDate(request
				.getParameter("expireDate")));

		populateModel(model, request);

		log.debug("NoticeCtl Method populatemodel Ended");

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

		log.debug("NoticeCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		NoticeModel model = (NoticeModel) populateModel(request);

		long id = model.getId();

		if (OP_SAVE_UP.equalsIgnoreCase(op)) {
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
				ServletUtility.redirect(ORSView.NOTICE_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				NoticeModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.NOTICE_VIEW, request, response);
		log.debug("NoticeCtl Method doGet Ended");
	}

	/**
	 * Contains Display Logic
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		NoticeModel model = new NoticeModel();

		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(ORSView.NOTICE_VIEW, request, response);
	}

	@Override
	protected String getView() {
		return ORSView.NOTICE_VIEW;
	}
	
	@Override
	protected void setAccess(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.setAccess(request);
		
		AccessUtility.setAddAccess("" + AppRole.ADMIN, request);
		
		AccessUtility.setWriteAccess("" + AppRole.ADMIN, request);
		
	}
			
}
