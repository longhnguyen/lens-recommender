package com.longnguyen.lensrecommender.rules;

import com.longnguyen.lensrecommender.model.entity.Camera;
import com.longnguyen.lensrecommender.model.entity.Lens;
import com.longnguyen.lensrecommender.profile.PurposeProfile;
import org.springframework.stereotype.Component;

@Component
public class UsabilityRule implements RecommendationRule
{
    @Override
    public RuleScore score(Camera camera, Lens lens, PurposeProfile profile) {

        int baseScore;

        if (lens.isZoom()) {
            baseScore = profile.isZoomPreferred() ? 9 : 6;
        } else {
            baseScore = profile.isZoomPreferred() ? 6 : 8;
        }

        int weightedScore = applyWeight(baseScore, profile.getUsabilityWeight());

        return new RuleScore(ScoreType.USABILITY, weightedScore);
    }
}
