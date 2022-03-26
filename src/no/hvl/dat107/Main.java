package no.hvl.dat107;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import no.hvl.dat107.department.Department;
import no.hvl.dat107.department.DepartmentDAO;
import no.hvl.dat107.employee.Employee;
import no.hvl.dat107.employee.EmployeeDAO;
import no.hvl.dat107.project.Project;
import no.hvl.dat107.project.ProjectDAO;
import no.hvl.dat107.projectparticipation.ProjectParticipation;
import no.hvl.dat107.projectparticipation.ProjectParticipationDAO;

public class Main {

	public static void main(String[] args) {
		Scan sc = new Scan();
		boolean running = true;
		while (running) {
			running = menu(sc);
			System.out.println();
		}
		sc.close();
	}

	private static void employeeById(Scan sc) {
		int id = sc.integer("Employee id: ");
		EmployeeDAO edao = new EmployeeDAO();
		Employee e = edao.retrieveEmployee(id);
		System.out.println(e);
	}

	private static void employeeByUsername(Scan sc) {
		String username = sc.string("Employee username: ");
		EmployeeDAO edao = new EmployeeDAO();
		Employee e = edao.retrieveEmployee(username);
		System.out.println(e);
	}

	private static void allEmployees() {
		EmployeeDAO edao = new EmployeeDAO();
		List<Employee> e = edao.retrieveAllEmployees();
		prettyPrintList(e);
	}

	private static void updateEmployeePosition(Scan sc) {
		EmployeeDAO edao = new EmployeeDAO();
		int id = sc.integer("Employee id: ");
		Employee e = edao.retrieveEmployee(id);
		String position = sc.string("New position: ");
		e = edao.updateEmployeePosition(e, position);
		System.out.println(e);
	}

	private static void newEmployee(Scan sc) {
		String username = sc.string("Username: ");
		String firstname = sc.string("Firstname: ");
		String lastname = sc.string("Lastname: ");
		LocalDate hiredDate = LocalDate.now();
		String position = sc.string("Position: ");
		BigDecimal monthlySalary = sc.bigdecimal("Monthly salary: ");
		int departmentId = sc.integer("Department id: ");

		EmployeeDAO edao = new EmployeeDAO();
		Employee emp = edao.addNewEmployee(username, firstname, lastname, hiredDate, position, monthlySalary,
				departmentId);
		System.out.println("New employee added: " + emp);
	}

	private static void newDepartment(Scan sc) {
		String name = sc.string("Department name: ");
		int leaderId = sc.integer("Leader id: ");

		DepartmentDAO ddao = new DepartmentDAO();
		Department dep = ddao.addNewDepartment(name, leaderId);
		System.out.println("New department added: " + dep);
	}

	private static void allDepartments() {
		DepartmentDAO ddao = new DepartmentDAO();
		List<Department> d = ddao.retrieveAllDepartments();
		prettyPrintList(d);
	}

	private static void allEmployeesInDepartment(Scan sc) {
		int departmentId = sc.integer("Department id: ");

		EmployeeDAO edao = new EmployeeDAO();
		DepartmentDAO ddao = new DepartmentDAO();
		List<Employee> emps = edao.retrieveAllEmployees(departmentId);
		Employee leader = ddao.retrieveLeader(departmentId);

		emps.forEach((emp) -> {
			System.out.println(emp.getId().equals(leader.getId()) ? "LEADER: " + emp : emp);
		});
	}

	private static void updateEmployeeDepartment(Scan sc) {
		int empId = sc.integer("Employee id: ");
		int depId = sc.integer("Department id: ");

		EmployeeDAO edao = new EmployeeDAO();
		Employee emp = edao.updateEmployeeDepartment(edao.retrieveEmployee(empId),
				(new DepartmentDAO()).retrieveDepartment(depId));
		System.out.println("Updated department of: " + emp);
	}

	private static void projectById(Scan sc) {
		int id = sc.integer("Project id: ");

		Project pro = (new ProjectDAO()).retrieveProject(id);
		System.out.println(pro);
	}

	private static void newProject(Scan sc) {
		String name = sc.string("Project name: ");
		String desc = sc.string("Project description: ");

		Project pro = (new ProjectDAO()).addNewProject(name, desc);
		System.out.println("New project added: " + pro);
	}

	private static void addEmployeeToProject(Scan sc) {
		int empId = sc.integer("Employee id: ");
		int proId = sc.integer("Project id: ");
		String role = sc.string("Role: ");
		Employee emp = (new EmployeeDAO()).retrieveEmployee(empId);
		Project pro = (new ProjectDAO()).retrieveProject(proId);
		(new ProjectParticipationDAO()).create(emp, pro, role);
	}

	private static void registerHours(Scan sc) {
		int ppId = sc.integer("Project participation id: ");
		int hours = sc.integer("Hours: ");
		(new ProjectParticipationDAO()).updateHoursWorked(ppId, hours);
	}

	private static void printProjectInfo(Scan sc) {
		int id = sc.integer("Project id: ");
		List<ProjectParticipation> pps = (new ProjectParticipationDAO()).retrieveByProjectId(id);
		prettyPrintList(pps);
	}

	private static <T> void prettyPrintList(List<T> list) {
		list.forEach((n) -> System.out.println(n));
	}

	private static boolean menu(Scan sc) {
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
		System.out.println("0)  Quit");

		int idx = sc.integer("Enter a number: ");

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
