package no.hvl.dat107.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

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

	public Employee() {
	}

	public Employee(String username, String firstname, String lastname, LocalDate hiredDate,
			String position, BigDecimal monthlySalary) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.hiredDate = hiredDate;
		this.position = position;
		this.monthlySalary = monthlySalary;
	}

	public Employee(Integer id, String username, String firstname, String lastname, LocalDate hiredDate,
			String position, BigDecimal monthlySalary) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.hiredDate = hiredDate;
		this.position = position;
		this.monthlySalary = monthlySalary;
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

	@Override
	public String toString() {
		return "Employee [firstname = " + firstname + ", hiredDate = " + hiredDate + ", id = " + id + ", lastname = "
				+ lastname
				+ ", monthlySalary = " + monthlySalary + ", position = " + position + ", username = " + username + "]";
	}

}
