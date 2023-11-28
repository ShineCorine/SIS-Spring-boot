package com.kernel360.sisspring.service;


import com.kernel360.sisspring.db.Major;
import com.kernel360.sisspring.db.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;

    public String getFocusSubjectName(String majorName){
        Major major = majorRepository.findByMajorName(majorName).orElseThrow(
                () -> new RuntimeException("해당하는 전공 이름이 없습니다: "+majorName)
        );
        return major.getFocusSubject();
    }

    public List<String> getMajorList() {
        return majorRepository.findAll()
                .stream()
                .map(Major::getMajorName)
                .toList();
    }
}
