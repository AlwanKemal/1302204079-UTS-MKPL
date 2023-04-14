package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class refactEmployee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;

	private LocalDate timeJoined;
	private boolean isForeigner;
	private boolean isMale;

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private String spouseName;
	private String spouseIdNumber;

	private List<Child> children;

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate timeJoined, boolean isForeigner, boolean isMale) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.timeJoined = timeJoined;
		this.isForeigner = isForeigner;
		this.isMale = isMale;

		children = new LinkedList<>();
	}

	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */

	public void setMonthlySalary(int grade) {	
	switch (grade) {
		case 1:
			monthlySalary = 3000000;
			break;
		case 2:
			monthlySalary = 5000000;
			break;
		case 3:
			monthlySalary = 7000000;
			break;
		default:
			throw new IllegalArgumentException("Invalid grade");
	}
	if (isForeigner) {
		monthlySalary *= 1.5;
	}
      }

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}

	public void addChild(String childName, String childIdNumber) {
		children.add(new Child(childName, childIdNumber));
	}

	public int getAnnualIncomeTax() {
		// Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate currentDate = LocalDate.now();
		int monthsWorkingInCurrentYear = currentDate.getMonthValue() - timeJoined.getMonthValue();
		int monthsInFullYear = 12;

		if (currentDate.getYear() == timeJoined.getYear()) {
			monthsInFullYear = monthsWorkingInCurrentYear;
		}

		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthsInFullYear, annualDeductible, spouseIdNumber.equals(""), children.size());
	}

	private class Child {
		private String name;Emp
		private String idNumber;

		public Child(String name, String idNumber) {
			this.name = name;
			this.idNumber = idNumber;
		}
	}
}
