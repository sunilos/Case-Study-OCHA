package in.co.sunrays.ocha.test;

import in.co.sunrays.common.model.RoleModel;
import in.co.sunrays.common.model.UserModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.exception.RecordNotFoundException;
import in.co.sunrays.ocha.model.AppRole;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * User Model Test classes
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
public class UserModelTest {

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
	public static void main(String[] args) throws ParseException,
			DuplicateRecordException {
		testAdd();
		// testDelete();
		// testUpdate();
		// testFindByPK();
		// testFindByLogin();
		// testSearch();
		// testGetRoles();
		// testList();
		// testAuthenticate();
		// testRegisterUser();
		// testchangePassword();
		// testforgetPassword();
		// testresetPassword();

	}

	/**
	 * Tests add a User
	 * 
	 * @throws ParseException
	 * @throws DuplicateRecordException
	 */
	public static void testAdd() throws ParseException,
			DuplicateRecordException {
		try {
			UserModel model = new UserModel();
			model.setFirstName("mohit");
			model.setLastName("jain");
			model.setFatherName("ABC");
			model.setMotherName("ABC");
			model.setLogin("mohitjain18@gmail.com");
			model.setPassword("pass1234");
			model.setCollegeId("0801CS12665");
			model.setDepartent("CSE");
			model.setSemester(6);
			model.setYear(3);
			model.setDob(new Date());
			model.setGender("Male");
			model.setMobileNo("123456");
			model.setAddress("Indore");
			model.setLastLogin(new Timestamp(new Date().getTime()));
			model.setLock(UserModel.INACTIVE);
			model.setRegisteredIP("192.1.2.3");
			model.setLastLoginIP("192.1.2.3");
			model.setRoleId(AppRole.STUDENT);
			model.setUnSuccessfulLogin(1);
			model.setCreatedBy("Admin");
			model.setModifiedBy("Admin");
			model.setCreatedDatetime(new Timestamp(new Date().getTime()));
			model.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add();
			UserModel addedModel = model.findByPK(pk);
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
			UserModel model = new UserModel();
			long pk = 0L;
			model.setId(pk);
			model.delete();
			System.out.println("Test Delete success " + model.getId());
			UserModel deletedModel = model.findByPK(pk);
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
			UserModel model = new UserModel();
			model = model.findByPK(1L);
			model.setFirstName("Ram");
			model.setLastName("Sharma");

			model.update();

			UserModel updatedModel = model.findByPK(1L);
			if (!"Ram".equals(updatedModel.getLogin())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests find a User by PK.
	 */
	public static void testFindByPK() {
		try {
			UserModel model = new UserModel();
			model = model.findByPK(1L);
			if (model == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(model.getId());
			System.out.println(model.getFirstName());
			System.out.println(model.getLastName());
			System.out.println(model.getLogin());
			System.out.println(model.getPassword());
			System.out.println(model.getDob());
			System.out.println(model.getRoleId());
			System.out.println(model.getUnSuccessfulLogin());
			System.out.println(model.getGender());
			System.out.println(model.getLastLogin());
			System.out.println(model.getLock());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by Login.
	 */
	public static void testFindByLogin() {
		try {
			UserModel model = new UserModel();
			model = model.findByLogin("rahul.sahu@nenosystems.com");
			if (model == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(model.getId());
			System.out.println(model.getFirstName());
			System.out.println(model.getLastName());
			System.out.println(model.getLogin());
			System.out.println(model.getPassword());
			System.out.println(model.getDob());
			System.out.println(model.getRoleId());
			System.out.println(model.getUnSuccessfulLogin());
			System.out.println(model.getGender());
			System.out.println(model.getLastLogin());
			System.out.println(model.getLock());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get Roles.
	 */
	public static void testGetRoles() {
		try {
			UserModel model = new UserModel();
			List list = new ArrayList();
			model.setRoleId(2L);
			list = model.getRoles(model);
			if (list.size() < 0) {
				System.out.println("Test Get Roles fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				model = (UserModel) it.next();
				System.out.println(model.getId());
				System.out.println(model.getFirstName());
				System.out.println(model.getLastName());
				System.out.println(model.getLogin());
				System.out.println(model.getPassword());
				System.out.println(model.getDob());
				System.out.println(model.getRoleId());
				System.out.println(model.getUnSuccessfulLogin());
				System.out.println(model.getGender());
				System.out.println(model.getLastLogin());
				System.out.println(model.getLock());
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
			UserModel model = new UserModel();
			List list = new ArrayList();
			model.setFirstName("r");
			list = model.search(0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				model = (UserModel) it.next();
				System.out.println(model.getId());
				System.out.println(model.getFirstName());
				System.out.println(model.getLastName());
				System.out.println(model.getLogin());
				System.out.println(model.getPassword());
				System.out.println(model.getDob());
				System.out.println(model.getRoleId());
				System.out.println(model.getUnSuccessfulLogin());
				System.out.println(model.getGender());
				System.out.println(model.getLastLogin());
				System.out.println(model.getLock());
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
			UserModel model = new UserModel();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				model = (UserModel) it.next();
				System.out.println(model.getId());
				System.out.println(model.getFirstName());
				System.out.println(model.getLastName());
				System.out.println(model.getLogin());
				System.out.println(model.getPassword());
				System.out.println(model.getDob());
				System.out.println(model.getRoleId());
				System.out.println(model.getUnSuccessfulLogin());
				System.out.println(model.getGender());
				System.out.println(model.getLastLogin());
				System.out.println(model.getLock());
				System.out.println(model.getMobileNo());
				System.out.println(model.getCreatedBy());
				System.out.println(model.getModifiedBy());
				System.out.println(model.getCreatedDatetime());
				System.out.println(model.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests authenticate User.
	 */
	public static void testAuthenticate() {
		try {
			UserModel model = new UserModel();
			model = model
					.authenticate("rahul.sahu@nenosystems.com", "pass1234");
			if (model != null) {
				System.out.println("Successfully login");
			} else {
				System.out.println("Invalied login Id & password");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests add a User register
	 * 
	 * @throws ParseException
	 */

	public static void testRegisterUser() throws ParseException {
		try {
			UserModel model = new UserModel();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			model.setFirstName("Rahul");
			model.setLogin("rahul.sahu@nenosystems.com");
			model.setPassword("pass1234");
			model.setDob(sdf.parse("11/20/2015"));
			model.setGender("Male");
			model.setRoleId(2);
			long pk = model.registerUser();
			UserModel registerModel = model.findByPK(pk);
			if (registerModel != null) {
				System.out.println("Successfully register");
			} else {
				System.out.println("Test registation fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests changepassword
	 * 
	 * @throws ParseException
	 */
	public static void testchangePassword() {

		try {
			UserModel model = new UserModel();

			model = model.findByLogin("rahul.sahu@nenosystems.com");
			String oldPassword = model.getPassword();
			model.setId(15l);
			model.setPassword("pass");
			String newPassword = model.getPassword();
			try {
				model.changePassword(15L, oldPassword, newPassword);
				System.out.println("password has been change successfully");
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests fogetPassword
	 * 
	 * @throws ParseException
	 */
	public static void testforgetPassword() {
		try {
			boolean b = model.forgetPassword("rahul.sahu@nenosystems.com");
			System.out.println("Suucess : Test Forget Password Success");
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests resetPassword
	 * 
	 * @throws ParseException
	 */
	public static void testresetPassword() {
		UserModel model = new UserModel();
		try {
			model = model.findByLogin("rahul.sahu@nenosystems.com");
			if (model != null) {
				boolean pass = model.resetPassword();
				if (pass = false) {
					System.out.println("Test Update fail");
				}
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}
}
