package com.kernel360.sisspring.db;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int studentId;    		//학번
	private String studentName;		//이름
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subject majorSubject;	//중점 과목

//	@ManyToMany
//	@JoinTable(name = "score",
//			joinColumns = @JoinColumn(name = "student_id"),
//			inverseJoinColumns = @JoinColumn(name = "subject_id"))
//	private List<Subject> subjects;
	
	//학생의 성적 리스트 
	//addSubjectSocre() 메서드가 호출되면 리스트에 추가 됨

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Score> scoreList = new ArrayList<>();

	public Student( int studentId, String studentName, Subject majorSubject){
		this.studentId = studentId;
		this.studentName = studentName;
		this.majorSubject = majorSubject;
	}

	public void addSubjectScore(Score score){
		scoreList.add(score);
		score.setStudent(this);
	}

//	public void enrollInSubject(Subject subject) {
//		subjects.add(subject);
//	}
}
