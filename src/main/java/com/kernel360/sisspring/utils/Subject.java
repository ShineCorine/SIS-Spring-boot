package com.kernel360.sisspring.utils;

import lombok.Getter;

@Getter
public enum Subject {
    KOREAN(1001, "국어"),
    MATH(2001, "수학"),
    DANCE(3001, "방송 댄스"),
    COMPUTERSCIENCE(4001, "컴퓨터 공학"),
    BASIC_COMPUTER(5001, "컴퓨터 고양"),
    SOCIETY(6001, "사회"),
    SCIENCE(7001, "과학"),
    ENCRYPT(10001, "암호학")
    ;

    private final int code;
    private final String name;

    Subject(int code, String name){
        this.code  = code;
        this.name = name;
    }
}
