package com.kernel360.sisspring.model;

import com.kernel360.sisspring.db.Score;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateScoreDto {
    private List<Score> scoreList;
}
