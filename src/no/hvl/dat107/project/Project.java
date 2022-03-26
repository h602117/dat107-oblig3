package no.hvl.dat107.project;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import no.hvl.dat107.projectparticipation.ProjectParticipation;

@Entity
@Table(schema = "oblig3")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	@OneToMany(mappedBy = "project")
	private List<ProjectParticipation> projectParticipations;

	public Project() {
	}

	public Project(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Project(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public void addProjectParticipation(ProjectParticipation pw) {
		projectParticipations.add(pw);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Project [description = " + description + ", id = " + id + ", name = " + name + "]";
	}

}
