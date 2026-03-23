package com.longnguyen.lensrecommender.dto;

/**
 * API response representing a scored lens recommendation
 *
 * @param name name of the lens
 * @param focalLengthScore score based on focal length suitability
 * @param apertureScore score based on aperture suitability
 * @param usabilityScore score based on general usability factors
 * @param totalScore combined score used for ranking
 */
public record LensResponse(
    String name,
    int focalLengthScore,
    int apertureScore,
    int usabilityScore,
    int totalScore
) {

    /**
     * Maps a ScoredLens domain object into a response DTO
     */
    public static LensResponse fromLens(ScoredLens scoredLens) {
        return new LensResponse(scoredLens.lens().getName(), scoredLens.focalLengthScore(),
            scoredLens.apertureScore(), scoredLens.usabilityScore(),
            scoredLens.focalLengthScore() + scoredLens.apertureScore() + scoredLens.usabilityScore());
    }
}