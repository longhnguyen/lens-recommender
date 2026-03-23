package com.longnguyen.lensrecommender.dto;

import com.longnguyen.lensrecommender.model.enums.CameraBrand;
import com.longnguyen.lensrecommender.profile.PurposeProfile;

/**
 * Inbound API request of the recommendation service
 *
 * @param cameraBrand brand of camera
 * @param cameraModel model of camera
 * @param purpose use case for lens recommendations
 */
public record RecommendationRequest(
	CameraBrand cameraBrand,
	String cameraModel,
	PurposeProfile purpose
) {}