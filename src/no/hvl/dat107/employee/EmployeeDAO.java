package no.hvl.dat107.employee;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
			tx.rollback();
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
			tx.rollback();
		} finally {
			em.close();
		}

		return emp;
	}

}
