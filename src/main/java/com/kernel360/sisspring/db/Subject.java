package com.kernel360.sisspring.db;

import com.kernel360.sisspring.utils.Define;
import com.kernel360.sisspring.utils.GradeType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int subjectId;      // 과목 고유번호
	private String subjectName;  //과목 이름
	private int gradeType;      // 과목 평가 방법 기본은 A,B 방식

	@OneToMany(mappedBy = "subject")
	private List<Score> students = new ArrayList<>();
	
	public Subject(String subjectName, int subjectId){
		this.subjectName = subjectName;
		this.subjectId = subjectId;
		this.gradeType = GradeType.AB.getCode();   //기본적으로 A, B 타입
	}

}
