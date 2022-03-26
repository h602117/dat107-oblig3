package no.hvl.dat107.projectparticipation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import no.hvl.dat107.employee.Employee;
import no.hvl.dat107.project.Project;

@Entity
@Table(schema = "oblig3")
public class ProjectParticipation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int hoursWorked;
	private String role;
	@ManyToOne
	@JoinColumn(name = "employeeId", referencedColumnName = "id")
	private Employee employee;
	@ManyToOne
	@JoinColumn(name = "projectId", referencedColumnName = "id")
	private Project project;

	public ProjectParticipation() {
	}

	public ProjectParticipation(Employee emp, Project pro, int hoursWorked, String role) {
		this.employee = emp;
		this.project = pro;
		this.hoursWorked = hoursWorked;
		this.role = role;

		this.employee.addProjectParticipation(this);
		this.project.addProjectParticipation(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return project.getName() + " Participant: " + employee.getFirstname() + " " + employee.getLastname() + " ("
				+ role + ")" + " " + hoursWorked + " hours";
	}

}
