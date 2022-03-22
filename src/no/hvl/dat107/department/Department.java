package no.hvl.dat107.department;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import no.hvl.dat107.employee.Employee;

@Entity
@Table(schema = "oblig3")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@OneToOne
	@JoinColumn(name = "leaderId", referencedColumnName = "id")
	private Employee leader;

	public Department() {
	}

	public Department(String name, Employee leader) {
		this.name = name;
		this.leader = leader;
	}

	public Department(int id, String name, Employee leader) {
		this.id = id;
		this.name = name;
		this.leader = leader;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getLeader() {
		return leader;
	}

	public void setLeader(Employee leader) {
		this.leader = leader;
	}

	@Override
	public String toString() {
		return "Department [id = " + id + ", leader = " + leader.getUsername() + ", name = " + name + "]";
	}

}
