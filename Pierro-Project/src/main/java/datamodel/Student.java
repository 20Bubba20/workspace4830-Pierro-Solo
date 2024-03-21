package datamodel;

import javax.persistence.*;

/**
 * @since J2SE-1.8
 * CREATE TABLE student (
 * id INT NOT NULL AUTO_INCREMENT,
 * name VARCHAR(30) NOT NULL,
 * semester INT NOT NULL,
 * email VARCHAR(60) NOT NULL,
 * college VARCHAR(30) NOT NULL,
 * PRIMARY KEY (id));
 */
@Entity
@Table(name = "student")
public class Student {
	public Student() {
	}
	/*
	 * Database creation and management
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "FULLNAME")
	private String fullName;
	
	@Column(name = "SEMESTER")
	private Integer semester;
	
	@Column(name = "COLLEGE")
	private String college;
	
	@Column(name = "EMAIL")
	private String email;
	
	/*
	 * Overloaded Student constructor
	 */
	public Student(Integer id, String fullName, int semester, String college, String email) {
		this.id = id;
		this.fullName = fullName;
		this.semester = semester;
		this.college = college;
		this.email = email;
	}

	public Student(String fullName, int semester, String college, String email) {
		this.fullName = fullName;
		this.semester = semester;
		this.college = college;
		this.email = email;
	}

	
	/*
	 * Getters for Student
	 */
	public Integer getID() {
		return id;
	}
	public String getFullName() {
		return fullName;
	}
	public String getCollege() {
		return college;
	}
	public String getEmail() {
		return email;
	}
	public Integer getSemester() {
		return semester;
	}
	
	/*
	 * Setters for Students
	 */
	public void setID(Integer id) {
		this.id = id;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		String result = "";

		result = "Student: " + this.id + ", " + this.fullName + ", " + this.semester + ", " + this.email + ", " + this.college;

		return result;
	}
	
}