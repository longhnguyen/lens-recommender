package com.longnguyen.lensrecommender.profile;

import lombok.Getter;

/**
 * Profiles for each purpose (use case) containing:
 * - ideal focal range of lens
 * - preference for aperture size
 * - preference for zoom vs prime lenses
 * - weight (importance) multiplier for all of the above
 */
@Getter
public enum PurposeProfile {

    /** Astrophotography profile */
    ASTRO (
        14,
        24,
        Weight.HIGH,
        AperturePreference.WIDE,
        Weight.HIGH,
        false,
        Weight.LOW
    ),

    /** Food photography profile */
    FOOD (
        35,
        100,
        Weight.MEDIUM,
        AperturePreference.WIDE,
        Weight.HIGH,
        false,
        Weight.LOW
    ),

    /** General-use photography profile */
    GENERAL (
        24,
        105,
        Weight.MEDIUM,
        AperturePreference.ANY,
        Weight.LOW,
        true,
        Weight.HIGH
    ),

    /** Landscape photography profile */
    LANDSCAPE (
        16,
        100,
        Weight.MEDIUM,
        AperturePreference.ANY,
        Weight.LOW,
        false,
        Weight.LOW
    ),

    /** Macrophotography profile */
    MACRO (
        85,
        105,
        Weight.HIGH,
        AperturePreference.ANY,
        Weight.MEDIUM,
        false,
        Weight.LOW
    ),

    /** Portrait photography profile */
    PORTRAIT (
        85,
        135,
        Weight.HIGH,
        AperturePreference.WIDE,
        Weight.HIGH,
        true,
        Weight.MEDIUM
    ),

    /** Product photography profile */
    PRODUCT (
        50,
        100,
        Weight.MEDIUM,
        AperturePreference.ANY,
        Weight.MEDIUM,
        false,
        Weight.LOW
    ),

    /** Real estate photography profile */
    REAL_ESTATE (
        14,
        24,
        Weight.HIGH,
        AperturePreference.ANY,
        Weight.LOW,
        false,
        Weight.MEDIUM
    ),

    /** Sports photography profile */
    SPORTS (
        100,
        400,
        Weight.HIGH,
        AperturePreference.WIDE,
        Weight.HIGH,
        true,
        Weight.HIGH
    ),

    /** Street photography profile */
    STREET (
        28,
        50,
        Weight.HIGH,
        AperturePreference.WIDE,
        Weight.MEDIUM,
        false,
        Weight.LOW
    ),

    /** Travel photography profile */
    TRAVEL (
        24,
        105,
        Weight.MEDIUM,
        AperturePreference.ANY,
        Weight.LOW,
        true,
        Weight.HIGH
    ),

    /** Videography profile */
    VIDEO (
        16,
        35,
        Weight.HIGH,
        AperturePreference.ANY,
        Weight.MEDIUM,
        true,
        Weight.HIGH
    ),

    /** Vlogging profile */
    VLOG (
        16,
        35,
        Weight.HIGH,
        AperturePreference.ANY,
        Weight.MEDIUM,
        false,
        Weight.LOW
    ),

    /** Wildlife photography profile */
    WILDLIFE (
        100,
        400,
        Weight.HIGH,
        AperturePreference.ANY,
        Weight.LOW,
        true,
        Weight.HIGH
    ),;

    /** Minimum ideal focal length in mm */
    private final int idealFocalRangeMin;

    /** Maximum ideal focal length in mm */
    private final int idealFocalRangeMax;

    /** Weight of focal length score */
    private final Weight focalWeight;

    /** Preference for aperture size */
    private final AperturePreference aperturePreference;

    /** Weight of aperture score */
    private final Weight apertureWeight;

    /** Preference for zoom lens */
    private final boolean zoomPreferred;

    /** Weight of usability score */
    private final Weight usabilityWeight;

    /**
     * Constructor
     */
    PurposeProfile(int min, int max, Weight focalWeight, AperturePreference aperturePreference,
        Weight apertureWeight, boolean zoomPreferred, Weight usabilityWeight) {

        this.idealFocalRangeMin = min;
        this.idealFocalRangeMax = max;
        this.focalWeight = focalWeight;
        this.aperturePreference = aperturePreference;
        this.apertureWeight = apertureWeight;
        this.zoomPreferred = zoomPreferred;
        this.usabilityWeight = usabilityWeight;
    }
}