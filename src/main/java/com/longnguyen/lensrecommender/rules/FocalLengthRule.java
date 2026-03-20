package com.longnguyen.lensrecommender.rules;

import com.longnguyen.lensrecommender.model.entity.Camera;
import com.longnguyen.lensrecommender.model.entity.Lens;
import com.longnguyen.lensrecommender.profile.PurposeProfile;
import org.springframework.stereotype.Component;

@Component
public class FocalLengthRule implements RecommendationRule
{
    @Override
    public RuleScore score(Camera camera, Lens lens, PurposeProfile profile) {

        // Camera crop factor
        double crop = camera.getCropFactor();

        // Is zoom preferred?
        boolean isZoomPreferred = profile.isZoomPreferred();

        // Lens (effective) focal range
        double lensMin = lens.getFocalLengthMin() * crop;
        double lensMax = lens.getFocalLengthMax() * crop;
        double lensRange = Math.max(1e-6, lensMax - lensMin);

        // Ideal focal range from purpose profile
        int idealMin = profile.getIdealFocalRangeMin();
        int idealMax = profile.getIdealFocalRangeMax();
        double idealRange = Math.max(1e-6, idealMax - idealMin);

        // ----------------------------------------
        // 1. Handle prime lens
        // ----------------------------------------
        if (!lens.isZoom()) {

            double primeFocal = lensMin;

            // Prime focal length outside of ideal range
            double distanceFromRange = 0;
            if (primeFocal < idealMin) {
                distanceFromRange = idealMin - primeFocal;
            } else if (primeFocal > idealMax) {
                distanceFromRange = primeFocal - idealMax;
            }

            double tolerance = idealRange * 0.5;
            double normalised = 1.0 - Math.min(distanceFromRange / tolerance, 1.0);

            double baseScore = normalised * 10;

            if (primeFocal < idealMin * 0.8) {
                baseScore *= 0.6;
            }

            int weightedScore = applyWeight((int) Math.round(baseScore), profile.getFocalWeight());

            return new RuleScore(ScoreType.FOCAL_LENGTH, weightedScore);
        }

        // ----------------------------------------
        // 2. No overlap
        // ----------------------------------------
        if (lensMin > idealMax || lensMax < idealMin)
        {
            return new RuleScore(ScoreType.FOCAL_LENGTH, 0);
        }

        // ----------------------------------------
        // 3. Overlap calculation
        // ----------------------------------------
        double overlapStart = Math.max(lensMin, idealMin);
        double overlapEnd = Math.min(lensMax, idealMax);
        double overlapLength = overlapEnd - overlapStart;

        // overlap quality (balanced: avoids zoom bias)
        double overlapRatioLens = overlapLength / lensRange;
        double overlapRatioIdeal = overlapLength / idealRange;
        double overlapSignificance = overlapLength / idealRange;

        double overlapScore = Math.min(overlapRatioLens, overlapRatioIdeal) * overlapSignificance;

        // ----------------------------------------
        // 4. Centre alignment bonus
        // ----------------------------------------
        double lensCentre = (lensMin + lensMax) / 2.0;
        double idealCentre = (idealMin + idealMax) / 2.0;
        double distance = Math.abs(lensCentre - idealCentre);
        double maxDistance = idealRange / 2.0;

        double centreScore = 1.0 - Math.min(distance / maxDistance, 1.0);

        double coverage = Math.min(1.0, (lensMax - lensMin) / idealRange);
        double coverageWeight = isZoomPreferred ? 0.4 : 0.2;

        // ----------------------------------------
        // 5. Combine overlap, centre scores and coverage
        // ----------------------------------------
        double baseScore = (overlapScore * 0.4
                + centreScore * 0.2
                + coverage * coverageWeight
        ) * 10;
        if (isZoomPreferred) { // Only zoom lens will reach this point in code
            baseScore += 1.5;
        }
        int weightedScore = applyWeight((int) Math.round(baseScore), profile.getFocalWeight());

        return new RuleScore(ScoreType.FOCAL_LENGTH, weightedScore);
    }
}