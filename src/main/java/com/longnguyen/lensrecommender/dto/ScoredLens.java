package com.longnguyen.lensrecommender.dto;

import com.longnguyen.lensrecommender.model.entity.Lens;

/**
 * DTO storing the lens information and it's calculated scores
 *
 * @param lens Lens entity
 * @param focalLengthScore score based on focal length suitability
 * @param apertureScore score based on aperture suitability
 * @param usabilityScore score based on general usability factors
 */
public record ScoredLens(
	Lens lens,
	int focalLengthScore,
	int apertureScore,
	int usabilityScore
) {}