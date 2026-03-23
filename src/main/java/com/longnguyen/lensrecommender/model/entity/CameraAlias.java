package com.longnguyen.lensrecommender.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * An alias of a camera model, linked to the Camera entity
 */
@Entity
@Getter
@Setter
public class CameraAlias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Alternative name of the camera */
    private String alias;

    /** Link to the Camera entity */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camera_id", nullable = false)
    private Camera camera;
}
