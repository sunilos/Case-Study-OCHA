package in.co.sunrays.ocha.controller;

import in.co.sunrays.common.controller.BaseCtl;
import in.co.sunrays.common.model.BaseModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.AppRole;
import in.co.sunrays.ocha.model.EResourceModel;
import in.co.sunrays.util.AccessUtility;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class EResourceListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(EResourceListCtl.class);

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		EResourceModel model = new EResourceModel();

		model.setTablesContains(DataUtility.getString(request
				.getParameter("tablesContains")));
		model.setName(DataUtility.getString(request.getParameter("name")));
		model.setDetail(DataUtility.getString(request.getParameter("detail")));

		return model;

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("EResourceListCtl doGet Start");

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;

		String op = DataUtility.getString(request.getParameter("operation"));

		// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");

		EResourceModel model = (EResourceModel) populateModel(request);

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
					|| "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}
			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.ERESOURCE_CTL, request,
						response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					EResourceModel deletebean = new EResourceModel();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						deletebean.delete();
					}
				} else {
					ServletUtility.setErrorMessage(
							"Select at least one record", request);
				}
			}

			List list = model.search(pageNo, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);

			ServletUtility.forwardView(ORSView.ERESOURCE_List_VIEW, request,
					response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}

		log.debug("EResourceListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.ERESOURCE_List_VIEW;
	}
	@Override
	protected void setAccess(HttpServletRequest request) {
		super.setAccess(request);
		AccessUtility.setAddAccess(""+ AppRole.ADMIN,
				request);
		AccessUtility.setWriteAccess(""+ AppRole.ADMIN,
				request);
	}
}
