package no.hvl.dat107.project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProjectDAO {

    private EntityManagerFactory emf;

	public ProjectDAO() {
		emf = Persistence.createEntityManagerFactory("oblig3PersistenceUnit");
	}

	public Project retrieveProject(int id) {
		EntityManager em = emf.createEntityManager();
		Project pro;

		try {
			pro = em.find(Project.class, id);
		} finally {
			em.close();
		}

		return pro;
	}

    public Project addNewProject(String name, String desc) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Project pro = new Project(name, desc);

		try {
			tx.begin();
			em.persist(pro);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive())
				tx.rollback();
			return null;
		} finally {
			em.close();
		}

		return pro;
    }
}
