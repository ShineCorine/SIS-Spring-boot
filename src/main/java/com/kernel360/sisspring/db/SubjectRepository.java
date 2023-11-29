package com.kernel360.sisspring.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findBySubjectName(String subjectName);
    List<Subject> findByIdNotIn(List<Long> enrolledSubjectIds);
}
