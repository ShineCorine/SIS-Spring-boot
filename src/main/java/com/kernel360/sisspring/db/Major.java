package com.kernel360.sisspring.db;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String majorName;
    private String focusSubject;
//
//    @OneToMany(mappedBy = "major")
//    List<Student> studentList;
}
