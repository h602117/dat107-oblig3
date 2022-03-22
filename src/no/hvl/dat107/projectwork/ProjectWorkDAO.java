package no.hvl.dat107.projectwork;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import no.hvl.dat107.employee.Employee;
import no.hvl.dat107.project.Project;

public class ProjectWorkDAO {

	private EntityManagerFactory emf;

	public ProjectWorkDAO() {
		emf = Persistence.createEntityManagerFactory("oblig3PersistenceUnit");
	}

	public ProjectWork retrieveProjectWork(int id) {
		EntityManager em = emf.createEntityManager();
		ProjectWork proWor;

		try {
			proWor = em.find(ProjectWork.class, id);
		} finally {
			em.close();
		}

		return proWor;
	}

	public List<ProjectWork> retrieveAllProjectWork(Project pro) {
		EntityManager em = emf.createEntityManager();
		List<ProjectWork> proWor;

		try {
			proWor = em.createQuery("SELECT p FROM ProjectWork p WHERE p.project = :pro")
			 	.setParameter("pro", pro)
				.getResultList();
		} finally {
			em.close();
		}

		return proWor;
	}
	public ProjectWork addNewProjectWork(Employee emp, Project pro, String role) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		ProjectWork pw = new ProjectWork(emp, pro, 0, role);

		try {
			tx.begin();
			em.persist(pw);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive())
				tx.rollback();
			return null;
		} finally {
			em.close();
		}

		return pw;
	}

	public ProjectWork registerHours(int id, int hours) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		ProjectWork pw = retrieveProjectWork(id);

		try {
			tx.begin();
			pw = em.merge(pw);
			pw.setHoursWorked(hours);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive())
				tx.rollback();
			return null;
		} finally {
			em.close();
		}

		return pw;
	}

}
