package in.co.sunrays.ocha.controller;

import in.co.sunrays.common.controller.BaseCtl;
import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.common.model.UserModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.AppRole;
import in.co.sunrays.ocha.model.TimeTableModel;
import in.co.sunrays.util.AccessUtility;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
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
public class TimeTableCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AttendenceCtl.class);

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forwardView(ORSView.TIMETABLE_VIEW, request, response);
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("AttendenceCtl Method validate Started");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("semester"))) {
			request.setAttribute("error.semester",
					PropertyReader.getValue("error.require", "Semester"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("year"))) {
			request.setAttribute("error.year",
					PropertyReader.getValue("error.require", "Year"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("branch"))) {
			request.setAttribute("error.branch",
					PropertyReader.getValue("error.require", "Branch"));
			pass = false;
		}
		// if (DataValidator.isNull(request.getParameter("date"))) {
		// request.setAttribute("error.date",
		// PropertyReader.getValue("error.require", "Date"));
		// pass = false;
		// }
		// if (DataValidator.isNull(request.getParameter("faculty"))) {
		// request.setAttribute("error.faculty",
		// PropertyReader.getValue("error.require", "Faculty"));
		// pass = false;
		// }
		// if (DataValidator.isNull(request.getParameter("subject"))) {
		// request.setAttribute("error.subject",
		// PropertyReader.getValue("error.require", "Subject"));
		// pass = false;
		// }
		// if (DataValidator.isNull(request.getParameter("time"))) {
		// request.setAttribute("error.fromTime",
		// PropertyReader.getValue("error.require", "Time"));
		// pass = false;
		// }
		log.debug("AttendenceCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {
		log.debug("TimetableCtl Method populatebean Started");
		TimeTableModel model = new TimeTableModel();
		model.setId(DataUtility.getLong(request.getParameter("id")));
		model.setSemester(DataUtility.getInt(request.getParameter("semester")));
		model.setYear(DataUtility.getInt(request.getParameter("year")));
		model.setBranch(DataUtility.getString(request.getParameter("branch")));
		log.debug("AttendenceCtl Method populatebean End");
		return model;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("TimetableCtl Method doGet Started");

		// model.setFaculty(DataUtility.getString(request.getParameter("faculty")));
		// model.setSubject(DataUtility.getString(request.getParameter("subject")));
		// model.setTime(DataUtility.getString(request.getParameter("time")));

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		TimeTableModel model = (TimeTableModel) populateModel(request);

		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				String[] time = request.getParameterValues("time");
				String[] subject = request.getParameterValues("subject");
				String[] faculty = request.getParameterValues("faculty");

				for (int i = 0; i < subject.length; i++) {
					model.setDate(DataUtility.getDate(request
							.getParameter("date" + (i + 1))));
					model.setTime(time[i]);
					model.setSubject(subject[i]);
					model.setFaculty(faculty[i]);
					model.add();
				}

				UserModel userModel = new UserModel();
				userModel.setDepartent(model.getBranch());
				userModel.setSemester(model.getSemester());
				List list = userModel.search(0, 0);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					UserModel itModel = (UserModel) it.next();
					String sendurl = PropertyReader.getValue("sms.url");
					String msg = PropertyReader.getValue("sms.msg") + " "
							+ itModel.getDepartent() + " "
							+ itModel.getSemester() + " Semester";
					String encodedmsg = URLEncoder.encode(msg);
					String url = MessageFormat.format(sendurl,
							itModel.getMobileNo(), encodedmsg,
							PropertyReader.getValue("sms.company"));
					System.out.println(url);
					// SmsThread thread = new SmsThread(url);
					// thread.start();
				}

				// long pk = model.add();
				// model.setId(pk);
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
		ServletUtility.forwardView(ORSView.TIMETABLE_VIEW, request, response);
	}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_VIEW;
	}

	@Override
	protected void setAccess(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.setAccess(request);
		
		AccessUtility.setAddAccess("" + AppRole.ADMIN, request);
		
		AccessUtility.setWriteAccess("" + AppRole.ADMIN, request);
		
	}
}