package com.kernel360.sisspring.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
@JsonNaming(value= PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudentRegisterDto {

    private int studentId;
    private String studentName;
    private String major;
}
