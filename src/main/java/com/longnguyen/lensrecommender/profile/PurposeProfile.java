package com.longnguyen.lensrecommender.profile;

import lombok.Getter;

@Getter
public enum PurposeProfile {
    FOOD (
            35,
            100,
            Weight.MEDIUM,
            AperturePreference.WIDE,
            Weight.HIGH,
            false,
            Weight.LOW
    ),
    GENERAL (
            24,
            105,
            Weight.MEDIUM,
            AperturePreference.ANY,
            Weight.LOW,
            true,
            Weight.HIGH
    ),
    LANDSCAPE (
            16,
            100,
            Weight.MEDIUM,
            AperturePreference.ANY,
            Weight.LOW,
            false,
            Weight.LOW
    ),
    MACRO (
            85,
            105,
            Weight.HIGH,
            AperturePreference.ANY,
            Weight.MEDIUM,
            false,
            Weight.LOW
    ),
    PORTRAIT (
            85,
            135,
            Weight.HIGH,
            AperturePreference.WIDE,
            Weight.HIGH,
            true,
            Weight.MEDIUM
    ),
    PRODUCT (
            50,
            100,
            Weight.MEDIUM,
            AperturePreference.ANY,
            Weight.MEDIUM,
            false,
            Weight.LOW
    ),
    REAL_ESTATE (
            14,
            24,
            Weight.HIGH,
            AperturePreference.ANY,
            Weight.LOW,
            false,
            Weight.MEDIUM
    ),
    SPORTS (
            100,
            400,
            Weight.HIGH,
            AperturePreference.WIDE,
            Weight.HIGH,
            true,
            Weight.HIGH
    ),
    STREET (
            28,
            50,
            Weight.HIGH,
            AperturePreference.WIDE,
            Weight.MEDIUM,
            false,
            Weight.LOW
    ),
    TRAVEL (
            24,
            105,
            Weight.MEDIUM,
            AperturePreference.ANY,
            Weight.LOW,
            true,
            Weight.HIGH
    ),
    VIDEO (
            16,
            35,
            Weight.HIGH,
            AperturePreference.ANY,
            Weight.MEDIUM,
            true,
            Weight.HIGH
    ),
    VLOG (
            16,
            35,
            Weight.HIGH,
            AperturePreference.ANY,
            Weight.MEDIUM,
            false,
            Weight.LOW
    ),
    WILDLIFE (
            100,
            400,
            Weight.HIGH,
            AperturePreference.ANY,
            Weight.LOW,
            true,
            Weight.HIGH
    ),;

    /** Variables */
    private final int idealFocalRangeMin;
    private final int idealFocalRangeMax;
    private final Weight focalWeight;
    private final AperturePreference aperturePreference;
    private final Weight apertureWeight;
    private final boolean zoomPreferred;
    private final Weight usabilityWeight;

    /** Constructor */
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