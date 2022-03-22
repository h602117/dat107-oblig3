package no.hvl.dat107;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import no.hvl.dat107.department.*;
import no.hvl.dat107.employee.*;
import no.hvl.dat107.project.*;
import no.hvl.dat107.projectwork.ProjectWork;
import no.hvl.dat107.projectwork.ProjectWorkDAO;

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
		int departmentId;
		try {
			System.out.print("DepartmentId: ");
			departmentId = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			return;
		}

		EmployeeDAO edao = new EmployeeDAO();
		Employee emp = edao.addNewEmployee(username, firstname, lastname, hiredDate, position, monthlySalary,
				departmentId);
		System.out.println("New employee added: " + emp);
	}

	private static void newDepartment(Scanner sc) {
		System.out.print("Department name: ");
		String name = sc.nextLine();
		int leaderId;
		try {
			System.out.print("Leader id: ");
			leaderId = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			return;
		}

		DepartmentDAO ddao = new DepartmentDAO();
		Department dep = ddao.addNewDepartment(name, leaderId);
		System.out.println("New department added: " + dep);
	}

	private static void allDepartments() {
		DepartmentDAO ddao = new DepartmentDAO();
		List<Department> d = ddao.retrieveAllDepartments();
		prettyPrintList(d);
	}

	private static void allEmployeesInDepartment(Scanner sc) {
		int departmentId;
		try {
			System.out.print("Department id: ");
			departmentId = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			return;
		}

		EmployeeDAO edao = new EmployeeDAO();
		DepartmentDAO ddao = new DepartmentDAO();
		List<Employee> emps = edao.retrieveAllEmployees(departmentId);
		Employee leader = ddao.retrieveLeader(departmentId);

		emps.forEach((emp) -> {
			System.out.println(emp.getId().equals(leader.getId()) ? "LEADER: " + emp : emp);
		});
	}

	private static void updateEmployeeDepartment(Scanner sc) {
		int empId;
		int depId;
		try {
			System.out.print("Employee id: ");
			empId = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			return;
		}
		try {
			System.err.print("Department id: ");
			depId = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			return;
		}

		EmployeeDAO edao = new EmployeeDAO();
		Employee emp = edao.updateEmployeeDepartment(edao.retrieveEmployee(empId),
				(new DepartmentDAO()).retrieveDepartment(depId));
		System.out.println("Updated department of: " + emp);
	}

	private static void projectById(Scanner sc) {
		int id;
		try {
			System.out.print("Project id: ");
			id = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			return;
		}

		Project pro = (new ProjectDAO()).retrieveProject(id);
		System.out.println(pro);
	}

	private static void newProject(Scanner sc) {
		System.out.print("Project name: ");
		String name = sc.nextLine();
		System.out.print("Project description: ");
		String desc = sc.nextLine();

		Project pro = (new ProjectDAO()).addNewProject(name, desc);
		System.out.println("New project added: " + pro);
	}

	private static void addEmployeeToProject(Scanner sc) {
		int employeeId;
		try {
			System.out.print("Employee id: ");
			employeeId = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			return;
		}
		int projectId;
		try {
			System.out.print("Project id: ");
			projectId = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			return;
		}

		System.out.print("Role: ");
		String role = sc.nextLine();

		ProjectWork pw = (new ProjectWorkDAO()).addNewProjectWork(
				(new EmployeeDAO()).retrieveEmployee(employeeId),
				(new ProjectDAO()).retrieveProject(projectId),
				role);
		System.out.println("New department added: " + pw);
	}

	private static void registerHours(Scanner sc) {

	}

	private static void printProjectInfo(Scanner sc) {

	}

	private static <T> void prettyPrintList(List<T> list) {
		list.forEach((n) -> System.out.println(n));
	}

	private static boolean menu(Scanner sc) {
		System.out.println("1)  Search for employee by id");
		System.out.println("2)  Search for employee by username");
		System.out.println("3)  Print all employees");
		System.out.println("4)  Update employee position");
		System.out.println("5)  Add new employee");
		System.out.println("6)  Add new department");
		System.out.println("7)  Print all departments");
		System.out.println("8)  Print all employees in a department");
		System.out.println("9)  Update employee department");
		System.out.println("10) Search for project");
		System.out.println("11) Add new project");
		System.out.println("12) Register employee to project");
		System.out.println("13) Register workhours for an employee to a project");
		System.out.println("14) Print all info about a project");

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
			case 6:
				newDepartment(sc);
				break;
			case 7:
				allDepartments();
				break;
			case 8:
				allEmployeesInDepartment(sc);
				break;
			case 9:
				updateEmployeeDepartment(sc);
				break;
			case 10:
				projectById(sc);
				break;
			case 11:
				newProject(sc);
				break;
			case 12:
				addEmployeeToProject(sc);
				break;
			case 13:
				registerHours(sc);
				break;
			case 14:
				printProjectInfo(sc);
				break;
			default:
				return false;
		}

		return true;
	}

}
