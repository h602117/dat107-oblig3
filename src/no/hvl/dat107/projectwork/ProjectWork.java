package no.hvl.dat107.projectwork;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import no.hvl.dat107.employee.Employee;
import no.hvl.dat107.project.Project;

@Entity
@Table(schema = "oblig3")
public class ProjectWork {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Employee employee;
	@ManyToMany
	@JoinTable(name = "project",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
	private Project project;
	private Integer hoursWorked;
	private String role;

	public ProjectWork() {
	}

	public ProjectWork(Employee employee, Project project, Integer hoursWorked, String role) {
		this.employee = employee;
		this.project = project;
		this.hoursWorked = hoursWorked;
		this.role = role;
	}

	public ProjectWork(Integer id, Employee employee, Project project, Integer hoursWorked, String role) {
		this.id = id;
		this.employee = employee;
		this.project = project;
		this.hoursWorked = hoursWorked;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(Integer hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "ProjectWork [employee = " + employee + ", hoursWorked = " + hoursWorked + ", id = " + id
				+ ", project = " + project + ", role = " + role + "]";
	}

}
