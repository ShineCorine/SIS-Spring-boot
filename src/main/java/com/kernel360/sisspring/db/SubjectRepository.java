package com.kernel360.sisspring.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Optional<Subject> findBySubjectName(String subjectName);
}
