package com.kernel360.sisspring.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SubjectRepositoryTest {

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void testFindByIdNotIn() {
        // Given
        // 가정: 데이터베이스에 저장된 과목이 있다고 가정
        Subject subject1 = new Subject("국어", 1001);
        Subject subject2 = new Subject("수학", 2001);
        Subject subject3 = new Subject("방송댄스", 3001);

        subject1.setId(1L);
        subject2.setId(2L);
        subject3.setId(3L);

        // 저장된 과목 ID 목록
        List<Long> enrolledSubjectIds = Arrays.asList(1L, 2L);

        // When
        List<Subject> availableSubjects = subjectRepository.findByIdNotIn(enrolledSubjectIds);

        // Then
        // 가정: subject3은 수강 중이지 않은 과목이어야 함
        assertEquals(6, availableSubjects.size());
        assertEquals(subject3.getSubjectName(), availableSubjects.get(0).getSubjectName());
    }

}