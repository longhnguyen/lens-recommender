package com.longnguyen.lensrecommender.dto;

import com.longnguyen.lensrecommender.model.entity.Lens;

public record ScoredLens(Lens lens, int focalLengthScore, int apertureScore, int usabilityScore)
{
}