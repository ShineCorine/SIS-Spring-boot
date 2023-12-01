package com.kernel360.sisspring.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum GradeType {
    AB(0),
    SAB(1),
    PF(2);
    int code;
}
