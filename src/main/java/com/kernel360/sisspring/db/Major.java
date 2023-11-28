package com.kernel360.sisspring.db;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String majorName;
    private String focusSubject;
}
