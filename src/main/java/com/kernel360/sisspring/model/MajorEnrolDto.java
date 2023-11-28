package com.kernel360.sisspring.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MajorEnrolDto {
    private String majorName;
    private String focusSubject;
}
