package com.kernel360.sisspring.model;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeDto {
    Long id;
    int studentId;
    String studentName;
    int score;
    char    grade;
}
