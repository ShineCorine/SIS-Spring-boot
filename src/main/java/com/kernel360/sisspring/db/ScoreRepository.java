package com.kernel360.sisspring.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByStudentId(Long studentId);

    Optional<Score> findByStudentIdAndSubjectId(Long studentId, Long subjectId);
    Score findByStudentAndSubject(Student student, Subject subject);

    List<Score> findAllBySubjectId(Long subjectId);
}
