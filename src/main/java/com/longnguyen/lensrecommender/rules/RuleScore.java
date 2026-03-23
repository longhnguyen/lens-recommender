package com.longnguyen.lensrecommender.rules;

/**
 * The score value and its associated score type (FOCAL_LENGTH, APERTURE, USABILITY)
 *
 * @param type the category of score
 * @param value the score value
 */
public record RuleScore(ScoreType type, int value) {}