package no.hvl.dat107.projectparticipation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import no.hvl.dat107.employee.Employee;
import no.hvl.dat107.project.Project;
import no.hvl.dat107.project.ProjectDAO;

public class ProjectParticipationDAO {

	private EntityManagerFactory emf;

	public ProjectParticipationDAO() {
		this.emf = Persistence.createEntityManagerFactory("oblig3PersistenceUnit");
	}

	public ProjectParticipation retrieve(int id) {
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(ProjectParticipation.class, id);
		} finally {
			em.close();
		}
	}

	public List<ProjectParticipation> retrieveByProjectId(int id) {
		EntityManager em = emf.createEntityManager();
		Project pro = (new ProjectDAO()).retrieveProject(id);
		try {
			return em.createQuery("SELECT p FROM ProjectParticipation p WHERE p.project = :project",
					ProjectParticipation.class).setParameter("project", pro).getResultList();
		} finally {
			em.close();
		}
	}

	public void create(Employee emp, Project pro, String role) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		ProjectParticipation pp = new ProjectParticipation(emp, pro, 0, role);

		try {
			tx.begin();
			em.merge(pp.getEmployee());
			em.merge(pp.getProject());
			em.persist(pp);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive())
				tx.rollback();
			return;
		} finally {
			em.close();
		}
	}

	public void updateHoursWorked(int id, int hours) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		ProjectParticipation pp = this.retrieve(id);
		if (pp != null) {
			pp.setHoursWorked(hours);
			try {
				tx.begin();
				em.merge(pp);
				tx.commit();
			} catch (Throwable e) {
				e.printStackTrace();
				if (tx.isActive())
					tx.rollback();
				return;
			} finally {
				em.close();
			}
		}
	}
}
