package no.hvl.dat107.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import no.hvl.dat107.department.Department;
import no.hvl.dat107.department.DepartmentDAO;

public class EmployeeDAO {

	private EntityManagerFactory emf;

	public EmployeeDAO() {
		this.emf = Persistence.createEntityManagerFactory("oblig3PersistenceUnit");
	}

	public Employee retrieveEmployee(int id) {
		EntityManager em = emf.createEntityManager();
		Employee e = null;

		try {
			e = em.find(Employee.class, id);
		} finally {
			em.close();
		}

		return e;
	}

	public Employee retrieveEmployee(String username) {
		EntityManager em = emf.createEntityManager();
		Employee e = null;

		try {
			e = (Employee) em.createQuery("SELECT e FROM Employee e WHERE e.username = :username")
					.setParameter("username", username)
					.getSingleResult();
		} finally {
			em.close();
		}

		return e;
	}

	public List<Employee> retrieveAllEmployees() {
		EntityManager em = emf.createEntityManager();
		List<Employee> emps;
		try {
			emps = em.createQuery("SELECT e FROM Employee e").getResultList();
		} finally {
			em.close();
		}

		return emps;
	}

	public List<Employee> retrieveAllEmployees(int departmentId) {
		EntityManager em = emf.createEntityManager();
		List<Employee> emps;
		Department dep = (new DepartmentDAO()).retrieveDepartment(departmentId);
		try {
			emps = em.createQuery("SELECT e FROM Employee e WHERE e.department = :department")
				.setParameter("department", dep)
				.getResultList();
		} finally {
			em.close();
		}

		return emps;
	}

	public Employee updateEmployeePosition(Employee emp, String position) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			emp = em.merge(emp);
			emp.setPosition(position);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive())
				tx.rollback();
			return null;
		} finally {
			em.close();
		}

		return emp;
	}

	public Employee updateEmployeeDepartment(Employee emp, Department dep) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		Employee leader = (new DepartmentDAO()).retrieveLeader(emp.getDepartment().getId());
		if (leader.getId().equals(emp.getId()))
			return null;

		try {
			tx.begin();
			emp = em.merge(emp);
			emp.setDepartment(dep);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive())
				tx.rollback();
			return null;
		} finally {
			em.close();
		}

		return emp;
	}

	public Employee addNewEmployee(String username, String firstname, String lastname, LocalDate hiredDate,
			String position, BigDecimal monthlySalary, int departmentId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		DepartmentDAO ddao = new DepartmentDAO();
		Department dep = ddao.retrieveDepartment(departmentId);
		Employee emp = new Employee(username, firstname, lastname, hiredDate, position, monthlySalary, dep);

		try {
			tx.begin();
			em.persist(emp);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive())
				tx.rollback();
			return null;
		} finally {
			em.close();
		}

		return emp;
	}

	public Employee addNewEmployee(Employee emp) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(emp);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive())
				tx.rollback();
			return null;
		} finally {
			em.close();
		}

		return emp;
	}

}
