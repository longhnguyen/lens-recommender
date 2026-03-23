package com.longnguyen.lensrecommender.service;

import com.longnguyen.lensrecommender.model.entity.Camera;
import com.longnguyen.lensrecommender.model.entity.Lens;
import com.longnguyen.lensrecommender.model.enums.CompatibilityFlag;
import com.longnguyen.lensrecommender.model.enums.SensorSize;
import org.springframework.stereotype.Service;

/**
 * Handles compatibility between lenses and cameras
 */
@Service
public class CompatibilityService {

    /**
     * Checks if the given camera and lens can be used with each other
     *
     * @param camera the camera entity to compare against the lens
     * @param lens the lens entity to compare against the camera
     * @return true if lens matches sensor size and mount type of camera
     */
    public boolean isCameraCompatible(Camera camera, Lens lens) {

        if (lens.getCompatibilityFlag() == CompatibilityFlag.FULL_FRAME_ONLY
            && camera.getSensorSize() == SensorSize.APS_C) {
            return false;
        }

        return camera.getMountType().equals(lens.getMountType());
    }
}