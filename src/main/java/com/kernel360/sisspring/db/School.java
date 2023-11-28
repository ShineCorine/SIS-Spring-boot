package com.kernel360.sisspring.db;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Getter
@ToString
public class School {

	private static School instance = new School();
	private static final String SCHOOL_NAME = "SilverBell School";
	private final List<Student> studentList = new ArrayList<>();
	private List<Subject> subjectList = new ArrayList<>();
	
	private School(){}

	public static School getInstance(){
		if(instance == null) 
			instance = new School();
		return instance;
	}

	public void addStudent(Student student){
		studentList.add(student);
	}

	public void addSubject(Subject subject) {
		subjectList.add(subject);
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}
}
