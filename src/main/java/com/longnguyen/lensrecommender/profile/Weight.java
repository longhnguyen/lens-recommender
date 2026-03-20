package com.longnguyen.lensrecommender.profile;

import lombok.Getter;

@Getter
public enum Weight
{
    LOW (1),
    MEDIUM (2),
    HIGH (3);

    /** Variables */
    private final int multiplier;

    Weight(int multiplier) {
        this.multiplier = multiplier;
    }
}