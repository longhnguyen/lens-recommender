package com.longnguyen.lensrecommender.dto;

public record LensResponse(String name, int focalLengthScore, int apertureScore,
                           int usabilityScore, int totalScore) {
    public static LensResponse fromLens(ScoredLens scoredLens) {
        return new LensResponse(scoredLens.lens().getName(), scoredLens.focalLengthScore(),
                scoredLens.apertureScore(), scoredLens.usabilityScore(),
                scoredLens.focalLengthScore() + scoredLens.apertureScore() + scoredLens.usabilityScore());
    }
}