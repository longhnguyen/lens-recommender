package com.longnguyen.lensrecommender.model.entity;

import com.longnguyen.lensrecommender.model.enums.*;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Lens
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private LensBrand brand;

    private String name;

    @Enumerated(EnumType.STRING)
    private MountType mountType;

    @Enumerated(EnumType.STRING)
    private CompatibilityFlag compatibilityFlag;

    private int focalLengthMin;
    private int focalLengthMax;
    private double maxAperture;
    private int weight;
    private boolean isZoom;
}