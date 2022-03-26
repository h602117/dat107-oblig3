package no.hvl.dat107.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import no.hvl.dat107.department.Department;
import no.hvl.dat107.projectparticipation.ProjectParticipation;

@Entity
@Table(schema = "oblig3")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String firstname;
	private String lastname;
	private LocalDate hiredDate;
	private String position;
	private BigDecimal monthlySalary;
	@ManyToOne
	@JoinColumn(name = "departmentId", referencedColumnName = "id")
	private Department department;
	@OneToMany(mappedBy = "employee")
	private List<ProjectParticipation> projectParticipations;

	public Employee() {
	}

	public Employee(String username, String firstname, String lastname, LocalDate hiredDate, String position,
			BigDecimal monthlySalary, Department department) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.hiredDate = hiredDate;
		this.position = position;
		this.monthlySalary = monthlySalary;
		this.department = department;
	}

	public Employee(Integer id, String username, String firstname, String lastname, LocalDate hiredDate,
			String position, BigDecimal monthlySalary, Department department) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.hiredDate = hiredDate;
		this.position = position;
		this.monthlySalary = monthlySalary;
		this.department = department;
	}

	public void addProjectParticipation(ProjectParticipation pw) {
		this.projectParticipations.add(pw);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getHiredDate() {
		return hiredDate;
	}

	public void setHiredDate(LocalDate hiredDate) {
		this.hiredDate = hiredDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public BigDecimal getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(BigDecimal monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [firstname = " + firstname + ", hiredDate = " + hiredDate + ", id = " + id + ", lastname = "
				+ lastname + ", monthlySalary = " + monthlySalary + ", position = " + position + ", username = "
				+ username + ", department = " + department.getName() + "]";
	}

}
