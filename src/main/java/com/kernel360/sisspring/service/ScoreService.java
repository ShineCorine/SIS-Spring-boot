package com.kernel360.sisspring.service;


import com.kernel360.sisspring.db.Score;
import com.kernel360.sisspring.db.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final ScoreRepository scoreRepository;


    public List<Score> getListBySubjectId(Long subjectId) {
        return scoreRepository.findAllBySubjectId(subjectId);
    }

    public void updateScores(List<Score> scores) {
        for(Score score: scores){
            updateScore(score);
        }
        scoreRepository.saveAll(scores);
    }

    public void updateScore(Score score){
        Score existScore = scoreRepository.findByStudentAndSubject(score.getStudent(), score.getSubject());

        if(existScore != null){
            existScore.setPoint(score.getPoint());
            scoreRepository.save(existScore);
        }
    }


}
