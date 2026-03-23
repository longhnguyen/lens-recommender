package com.longnguyen.lensrecommender.profile;

import lombok.Getter;

/**
 * Weight value to be multiplied against a base score to determine the resulting weighted score
 */
@Getter
public enum Weight {

    LOW,
    MEDIUM,
    HIGH
}