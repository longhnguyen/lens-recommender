package com.longnguyen.lensrecommender.model.entity;

import com.longnguyen.lensrecommender.model.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * A camera entity with mount and sensor information
 */
@Entity
@Getter
@Setter
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Brand name of the camera */
    @Enumerated(EnumType.STRING)
    private CameraBrand brand;

    /** Model name of the camera */
    private String model;

    /** Mount type of the camera (e.g. EF, RF) */
    @Enumerated(EnumType.STRING)
    private MountType mountType;

    /** Sensor size of the camera (e.g. APS_C, FULL_FRAME) */
    @Enumerated(EnumType.STRING)
    private SensorSize sensorSize;

    /** Crop factor of the sensor(e.g. 1.00, 1.60) */
    private double cropFactor;

    /** List of other model names that the camera is also known by */
    @OneToMany(mappedBy = "camera", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CameraAlias> aliases;
}