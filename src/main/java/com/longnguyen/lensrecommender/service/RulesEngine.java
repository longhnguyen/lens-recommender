package com.longnguyen.lensrecommender.service;

import com.longnguyen.lensrecommender.model.entity.Camera;
import com.longnguyen.lensrecommender.model.entity.Lens;
import com.longnguyen.lensrecommender.profile.PurposeProfile;
import com.longnguyen.lensrecommender.rules.RecommendationRule;
import com.longnguyen.lensrecommender.rules.RuleScore;
import com.longnguyen.lensrecommender.rules.ScoreType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RulesEngine
{
    private final List<RecommendationRule> rules;

    public RulesEngine(List<RecommendationRule> rules) {
        this.rules = rules;
    }

    public Map<ScoreType, Integer> score(Camera camera, Lens lens, PurposeProfile purposeProfile) {
        return rules.stream()
                .map(rule -> rule.score(camera, lens, purposeProfile))
                .collect(Collectors.groupingBy(
                        RuleScore::type,
                        Collectors.summingInt(RuleScore::value)
                ));
    }
}