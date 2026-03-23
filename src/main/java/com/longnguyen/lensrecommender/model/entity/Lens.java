package com.longnguyen.lensrecommender.model.entity;

import com.longnguyen.lensrecommender.model.enums.*;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * A camera lens entity with optical and compatibility properties
 */
@Entity
@Getter
public class Lens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Manufacturer of the lens */
    @Enumerated(EnumType.STRING)
    private LensBrand brand;

    /** Full marketing name of the lens */
    private String name;

    /** Mount type required to attach the lens */
    @Enumerated(EnumType.STRING)
    private MountType mountType;

    /** Compatibility with sensor formats (e.g. FULL_FRAME_ONLY, APS_C_OPTIMISED) */
    @Enumerated(EnumType.STRING)
    private CompatibilityFlag compatibilityFlag;

    /** Minimum focal length in mm */
    private int focalLengthMin;

    /** Maximum focal length in mm */
    private int focalLengthMax;

    /** Maximum aperture (lower number = bigger opening = generally better) */
    private double maxAperture;

    /** Weight in grams */
    private int weight;

    /** Whether the lens can zoom */
    private boolean isZoom;
}