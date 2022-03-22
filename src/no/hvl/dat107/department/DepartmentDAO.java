package no.hvl.dat107.department;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import no.hvl.dat107.employee.Employee;
import no.hvl.dat107.employee.EmployeeDAO;

public class DepartmentDAO {

	private EntityManagerFactory emf;

	public DepartmentDAO() {
		emf = Persistence.createEntityManagerFactory("oblig3PersistenceUnit");
	}

	public Department retrieveDepartment(int id) {
		EntityManager em = emf.createEntityManager();
		Department d = null;

		try {
			d = em.find(Department.class, id);
		} finally {
			em.close();
		}

		return d;
	}

	public List<Department> retrieveAllDepartments() {
		EntityManager em = emf.createEntityManager();
		List<Department> deps;
		try {
			deps = em.createQuery("SELECT d FROM Department d").getResultList();
		} finally {
			em.close();
		}

		return deps;
	}

	public Employee retrieveLeader(int departmentId) {
		Department dep = retrieveDepartment(departmentId);
		return dep.getLeader();
	}

	public Department addNewDepartment(String name, int leaderId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		EmployeeDAO edao = new EmployeeDAO();
		Employee emp = edao.retrieveEmployee(leaderId);
		Department dep = new Department(name, emp);

		try {
			tx.begin();
			em.persist(dep);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive())
				tx.rollback();
			return null;
		} finally {
			em.close();
		}

		edao.updateEmployeeDepartment(emp, dep);

		return dep;
	}

	public Department addNewDepartment(Department dep) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(dep);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive())
				tx.rollback();
			return null;
		} finally {
			em.close();
		}

		return dep;
	}

}
