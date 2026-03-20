package com.longnguyen.lensrecommender.service;

import com.longnguyen.lensrecommender.model.entity.Camera;
import com.longnguyen.lensrecommender.model.entity.Lens;
import com.longnguyen.lensrecommender.model.enums.CompatibilityFlag;
import com.longnguyen.lensrecommender.model.enums.SensorSize;
import org.springframework.stereotype.Service;

@Service
public class CompatibilityService
{
    public boolean isCameraCompatible(Camera camera, Lens lens) {

        if (lens.getCompatibilityFlag() == CompatibilityFlag.FULL_FRAME_ONLY
            && camera.getSensorSize() == SensorSize.APS_C) {
            return false;
        }

        return camera.getMountType().equals(lens.getMountType());
    }
}