package no.hvl.dat107;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import no.hvl.dat107.employee.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		while (running) {
			running = menu(sc);
			System.out.println();
		}
		sc.close();
	}

	private static void employeeById(Scanner sc) {
		int id;
		try {
			System.out.print("Search for employee by id: ");
			id = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			return;
		}
		EmployeeDAO edao = new EmployeeDAO();
		Employee e = edao.retrieveEmployee(id);
		System.out.println(e);
	}

	private static void employeeByUsername(Scanner sc) {
		System.out.print("Search for employee by username: ");
		String username = sc.nextLine();
		EmployeeDAO edao = new EmployeeDAO();
		Employee e = edao.retrieveEmployee(username);
		System.out.println(e);
	}

	private static void allEmployees() {
		EmployeeDAO edao = new EmployeeDAO();
		List<Employee> e = edao.retrieveAllEmployees();
		prettyPrintList(e);
	}

	private static void updateEmployeePosition(Scanner sc) {
		EmployeeDAO edao = new EmployeeDAO();
		System.out.print("Enter employee id: ");
		int id;
		try {
			id = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			return;
		}
		Employee e = edao.retrieveEmployee(id);
		System.out.print("New position: ");
		String position = sc.nextLine();
		e = edao.updateEmployeePosition(e, position);
		System.out.println(e);
	}

	private static void newEmployee(Scanner sc) {
		System.out.print("Username: ");
		String username = sc.nextLine();
		System.out.print("Firstname: ");
		String firstname = sc.nextLine();
		System.out.print("Lastname: ");
		String lastname = sc.nextLine();
		LocalDate hiredDate = LocalDate.now();
		System.out.print("Position: ");
		String position = sc.nextLine();
		BigDecimal monthlySalary;
		try {
			System.out.print("Salary: ");
			monthlySalary = new BigDecimal(Double.parseDouble(sc.nextLine()));
		} catch (NumberFormatException e) {
			return;
		}

		Employee emp = new Employee(username, firstname, lastname, hiredDate, position, monthlySalary);
		EmployeeDAO edao = new EmployeeDAO();
		edao.addNewEmployee(emp);
		System.out.println("New employee added: " + emp);
	}

	private static <T> void prettyPrintList(List<T> list) {
		list.forEach((n) -> System.out.println(n));
	}

	private static boolean menu(Scanner sc) {
		System.out.println("1) Search for employee by id");
		System.out.println("2) Search for employee by username");
		System.out.println("3) Print all employees");
		System.out.println("4) Update employee position");
		System.out.println("5) Add new employee");

		int idx;
		System.out.println("Anything else to quit.");
		System.out.print("Enter a number: ");
		try {
			idx = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			return false;
		}

		switch (idx) {
			case 1:
				employeeById(sc);
				break;
			case 2:
				employeeByUsername(sc);
				break;
			case 3:
				allEmployees();
				break;
			case 4:
				updateEmployeePosition(sc);
				break;
			case 5:
				newEmployee(sc);
				break;
			default:
				return false;
		}

		return true;
	}

}
