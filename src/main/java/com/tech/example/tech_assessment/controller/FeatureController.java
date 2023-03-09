package com.tech.example.tech_assessment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.example.tech_assessment.model.Feature;
import com.tech.example.tech_assessment.model.Response;
import com.tech.example.tech_assessment.service.FeatureService;

@RestController
@RequestMapping("/feature")
public class FeatureController {

	@Autowired
	private FeatureService featureService;

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(FeatureController.class);

	/* GET /feature?email=XXX&featureName=XXX */
	@GetMapping
	public ResponseEntity<?> getFeature(@RequestParam String email, @RequestParam String featureName) {
		LOGGER.info("Get Feature by email and featureName...");
		boolean feature = featureService.getFeature(email, featureName);
		Response response = new Response();
		response.setCanAccess(feature);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}

	@PostMapping
	public ResponseEntity<Object> createFeature(@RequestBody Feature feature) {
		LOGGER.info("createFeature...");
		return featureService.createFeature(feature);
	}

	@PutMapping("/{id}")
	public Feature updateFeature(@RequestBody Feature feature, @PathVariable Integer id) {
		LOGGER.info("updateFeature...");
		return featureService.updateFeature(feature, id);
	}
}
