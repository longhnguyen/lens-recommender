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

/**
 * Orchestrates the execution of all {@link RecommendationRule} implementations to produce
 * aggregated scores for a lens.
 *
 * Combines individual rule scores into a final score map grouped by {@link ScoreType}
 */
@Service
public class RulesEngine {

    private final List<RecommendationRule> rules;

    public RulesEngine(List<RecommendationRule> rules) {
        this.rules = rules;
    }

    /**
     * Applies all registered rules to a lens and aggregates their scores by type.
     *
     * @param camera the camera providing context (e.g. crop factor)
     * @param lens the lens being evaluated
     * @param purposeProfile the intended use case guiding scoring preferences
     * @return a map of {@link ScoreType} to total score
     */
    public Map<ScoreType, Integer> score(Camera camera, Lens lens, PurposeProfile purposeProfile) {
        return rules.stream()
                .map(rule -> rule.score(camera, lens, purposeProfile))
                .collect(Collectors.groupingBy(
                        RuleScore::type,
                        Collectors.summingInt(RuleScore::value)
                ));
    }
}