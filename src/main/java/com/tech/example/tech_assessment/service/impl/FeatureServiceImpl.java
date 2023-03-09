package com.tech.example.tech_assessment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tech.example.tech_assessment.exception.ServiceException;
import com.tech.example.tech_assessment.model.Feature;
import com.tech.example.tech_assessment.repository.FeatureRepository;
import com.tech.example.tech_assessment.service.FeatureService;

@Service
public class FeatureServiceImpl implements FeatureService {

	@Autowired
	private FeatureRepository featureRepository;

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(FeatureServiceImpl.class);

	private static final String FEATURE_ALREADY_EXIST = "feature.create.alreadExist";

	private static final String FEATURE_INTERNAL_SERVER_ERROR = "feature.internal.server.error";

	private static final String FEATURE_SEARCH_FAILED_NOT_EXIST_BY = "feature.search.notExistBy";

	private static final String FEATURE_UPDATE_FAILED_NOT_EXIST_BY = "feature.update.notExistBy";

	private static final String FEATURE_NOT_FOUND = "feature.notFound.inDb";;

	@Override
	public ResponseEntity<Object> createFeature(Feature feature) throws ServiceException {
		LOGGER.info("Create Feature...");
		if (featureRepository.existsByEmail(feature.getEmail())) {
			String errorMessage = String.format("Feature already exist for email : %s", feature.getEmail());
			LOGGER.error(errorMessage);
			throw new ServiceException(HttpStatus.BAD_REQUEST.value(), errorMessage, FEATURE_ALREADY_EXIST);
		}
		try {
			LOGGER.info("Going to create feature : {}  in db.", feature);
			feature = featureRepository.save(feature);
			return new ResponseEntity<>(feature, HttpStatus.CREATED);
		} catch (Exception ex) {
			LOGGER.error("Error occured while creating feature for email : {} due to : {}", feature.getEmail(),
					ex.getMessage());
			throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
					FEATURE_INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public boolean getFeature(String email, String featureName) throws ServiceException {
		LOGGER.info("Getting Feature by email and featureName...");
		Feature feature = featureRepository.findByEmailAndFeatureName(email, featureName);
		if (feature == null) {
			String errorMessage = String.format("Could not found feature by email : %s .", email);
			LOGGER.info(errorMessage);
			throw new ServiceException(HttpStatus.NOT_FOUND.value(), errorMessage, FEATURE_NOT_FOUND);
		}
		boolean enable = feature.isEnable();
		LOGGER.info("Got feature by email and featureName.");
		return enable;
	}

	@Override
	public Feature updateFeature(Feature feature, Integer id) throws ServiceException {
		LOGGER.info("Update Feature...");
		Feature updateFeature = featureRepository.findById(id).orElseThrow(() -> {
			String errorMessage = String.format("Feature doesn't exist : %s in database.", id);
			LOGGER.info("Get Feature by email and featureName...");
			throw new ServiceException(HttpStatus.NOT_FOUND.value(), errorMessage, FEATURE_SEARCH_FAILED_NOT_EXIST_BY);
		});
		try {
			feature.setId(updateFeature.getId());
			Feature featureUpdate = featureRepository.save(feature);
			LOGGER.debug("Updating Feature by id {}.", id);
			return featureUpdate;
		} catch (Exception exception) {
			LOGGER.error("Error occured while trying to update feature due to : {}", exception.getMessage());
			throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(),
					FEATURE_UPDATE_FAILED_NOT_EXIST_BY);
		}
	}

}
