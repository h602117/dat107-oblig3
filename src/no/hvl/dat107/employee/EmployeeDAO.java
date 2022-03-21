package no.hvl.dat107.employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

}
