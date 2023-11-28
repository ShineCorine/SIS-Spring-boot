package com.kernel360.sisspring.db;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Score {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	int studentId;   //학번
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	Subject subject; //과목
	int point;      //점수

	public Score(Student student, Subject subject, int point) {
		this.student = student;
		this.subject = subject;
		this.point = point;
	}


	public String toString(){
		return "학번:" + student.getStudentId() + "," + subject.getSubjectName() + ":" + point;
	}
}
