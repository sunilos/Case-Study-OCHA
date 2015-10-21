package in.co.sunrays.ocha.test;

import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.AttendenceModel;

public class AttendenceModelTest {

	public static AttendenceModel model = new AttendenceModel();

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 * @throws DuplicateRecordException
	 */
	public static void main(String[] args) throws DuplicateRecordException {
		testAdd();
		// testDelete();
		// testUpdate();
		// testFindByPK();
		// testSearch();

	}

	private static void testAdd() {
		try {
			AttendenceModel model = new AttendenceModel();
			model.setStudentId("255645");
			model.setStudentName("Rahul");
			model.setBranchName("CSE");
			model.setMonth("July");
			model.setYear(2015);
			model.setSubject1("BME");
			model.setSubject2("BCE");
			model.setSubject3("M1");
			model.setSubject4("M2");
			model.setSubject5("M3");
			model.setSubject6("M4");
			model.setSubject7("OOT");
			model.setSubject8("DS");
			model.setSubject9("OS");
			model.setSubject10("ADA");
			model.setAttendence1(1);
			model.setAttendence2(1);
			model.setAttendence3(1);
			model.setAttendence4(1);
			model.setAttendence5(1);
			model.setAttendence6(1);
			model.setAttendence7(1);
			model.setAttendence8(1);
			model.setAttendence9(1);
			model.setAttendence10(1);
			model.add();
			System.out.println("Attendence is added successfully");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
