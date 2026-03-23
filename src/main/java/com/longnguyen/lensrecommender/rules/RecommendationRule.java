package com.longnguyen.lensrecommender.rules;

import com.longnguyen.lensrecommender.model.entity.Camera;
import com.longnguyen.lensrecommender.model.entity.Lens;
import com.longnguyen.lensrecommender.profile.PurposeProfile;
import com.longnguyen.lensrecommender.profile.Weight;

/**
 * Defines a scoring rule used to evaluate a lens for a given camera and purpose.
 * Implementations contribute a weighted score that forms part of the overall recommendation
 */
@FunctionalInterface
public interface RecommendationRule {

    RuleScore score(Camera camera, Lens lens, PurposeProfile purposeProfile);

    /**
     * Multiply the weight value against a base score to determine the weighted score
     *
     * @param score value of the base score
     * @param weight the weight of the specific score type
     * @return the weighted score
     */
    default int applyWeight(int score, Weight weight) {
        return switch (weight) {
            case LOW -> (int) (score * 0.7);
            case MEDIUM -> score;
            case HIGH -> (int) Math.min(10, score * 1.2);
        };
    }
}