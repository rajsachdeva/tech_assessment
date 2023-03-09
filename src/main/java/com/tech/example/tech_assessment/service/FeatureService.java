package com.tech.example.tech_assessment.service;

import org.springframework.http.ResponseEntity;

import com.tech.example.tech_assessment.exception.ServiceException;
import com.tech.example.tech_assessment.model.Feature;

public interface FeatureService {

	public ResponseEntity<Object> createFeature(Feature feature) throws ServiceException;
	
	public boolean getFeature(String email, String featureName)throws ServiceException;
	
	public Feature updateFeature(Feature feature,Integer id) throws ServiceException;
	
}
