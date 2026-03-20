package com.longnguyen.lensrecommender.model.entity;

import com.longnguyen.lensrecommender.model.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Camera
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private CameraBrand brand;

    private String model;

    @Enumerated(EnumType.STRING)
    private MountType mountType;

    @Enumerated(EnumType.STRING)
    private SensorSize sensorSize;

    private double cropFactor;

    @OneToMany(mappedBy = "camera", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CameraAlias> aliases;
}