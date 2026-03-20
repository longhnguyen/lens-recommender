package com.longnguyen.lensrecommender.rules;

import com.longnguyen.lensrecommender.model.entity.Camera;
import com.longnguyen.lensrecommender.model.entity.Lens;
import com.longnguyen.lensrecommender.profile.PurposeProfile;
import com.longnguyen.lensrecommender.profile.Weight;

@FunctionalInterface
public interface RecommendationRule
{
    RuleScore score(Camera camera, Lens lens, PurposeProfile purposeProfile);

    default int applyWeight(int score, Weight weight) {
        return switch (weight) {
            case LOW -> (int) (score * 0.7);
            case MEDIUM -> score;
            case HIGH -> (int) Math.min(10, score * 1.2);
        };
    }
}