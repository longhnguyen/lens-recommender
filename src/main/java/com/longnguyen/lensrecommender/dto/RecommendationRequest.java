package com.longnguyen.lensrecommender.dto;

import com.longnguyen.lensrecommender.model.enums.CameraBrand;
import com.longnguyen.lensrecommender.profile.PurposeProfile;

public record RecommendationRequest(
        CameraBrand cameraBrand,     // e.g. Canon
        String cameraModel,     // e.g. EOS 1500D or EOS Rebel T7
        PurposeProfile purpose          // e.g. portrait
) {}