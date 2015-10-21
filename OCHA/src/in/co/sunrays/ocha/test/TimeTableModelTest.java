package in.co.sunrays.ocha.test;

import in.co.sunrays.common.model.UserModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.TimeTableModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TimeTableModelTest {
	/**
	 * Model object to test
	 */

	public static UserModel model = new UserModel();

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 * @throws ParseException
	 * @throws DuplicateRecordException
	 */
	public static void main(String[] args) throws Exception {
		testAdd();
		// testDelete();
		// testUpdate();
		// testFindByPK();
		// testSearch();
		// testList();
	}

	/**
	 * Tests add a User
	 * 
	 * @throws ParseException
	 * @throws DuplicateRecordException
	 */
	public static void testAdd() throws Exception {
		try {
			TimeTableModel model = new TimeTableModel();
			model.setBranch("CS");
			model.setSemester(1);
			model.setYear(2015);
			model.setSubject("DBMS");
			model.setDate(new Date());
			model.setTime("10:00 - 12:00 PM");
			model.setFaculty("Ram");
			long pk = model.add();
			TimeTableModel addedModel = model.findByPK(pk);
			System.out.println("Test add success");
			if (addedModel == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests delete a User
	 */
	public static void testDelete() {
		try {
			TimeTableModel model = new TimeTableModel();
			long pk = 1L;
			model.setId(pk);
			model.delete();
			System.out.println("Test Delete success " + model.getId());
			TimeTableModel deletedModel = model.findByPK(pk);
			if (deletedModel == null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a User
	 */
	public static void testUpdate() {
		try {
			TimeTableModel model = new TimeTableModel();
			model = model.findByPK(1L);
			model.setSubject("OOT");
			model.update();
			TimeTableModel updatedModel = model.findByPK(1L);
			if (!"OOT".equals(updatedModel.getSubject())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests find a User by PK.
	 */
	public static void testFindByPK() {
		try {
			TimeTableModel model = new TimeTableModel();
			model = model.findByPK(1L);
			if (model == null) {
				System.out.println("Test Find By PK fail");
			} else {
				System.out.println(model.getId());
				System.out.println(model.getBranch());
				System.out.println(model.getSemester());
				System.out.println(model.getYear());
				System.out.println(model.getSubject());
				System.out.println(model.getDate());
				System.out.println(model.getTime());
				System.out.println(model.getFaculty());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get Search
	 */
	public static void testSearch() {
		try {
			TimeTableModel model = new TimeTableModel();
			List list = new ArrayList();
			model.setSubject("O");
			list = model.search(0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				model = (TimeTableModel) it.next();
				System.out.println(model.getId());
				System.out.println(model.getBranch());
				System.out.println(model.getSemester());
				System.out.println(model.getYear());
				System.out.println(model.getSubject());
				System.out.println(model.getDate());
				System.out.println(model.getTime());
				System.out.println(model.getFaculty());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests get List.
	 */
	public static void testList() {
		try {
			TimeTableModel model = new TimeTableModel();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				model = (TimeTableModel) it.next();
				System.out.println(model.getId());
				System.out.println(model.getBranch());
				System.out.println(model.getSemester());
				System.out.println(model.getYear());
				System.out.println(model.getSubject());
				System.out.println(model.getDate());
				System.out.println(model.getTime());
				System.out.println(model.getFaculty());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
