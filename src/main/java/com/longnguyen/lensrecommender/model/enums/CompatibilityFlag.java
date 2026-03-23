package com.longnguyen.lensrecommender.model.enums;

/**
 * Defines what sensor size is most compatible with the lens
 */
public enum CompatibilityFlag {

    /** The lens only works with Full Frame sensor cameras */
    FULL_FRAME_ONLY,

    /** The lens can work with Full Frame but works the best with APS-C sensor cameras */
    APS_C_OPTIMISED,

    /** The lens is optimised for both Full Frame and APS-C sensor cameras */
    FULL_FRAME_AND_APS_C
}
