package com.tech.example.tech_assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.example.tech_assessment.model.Feature;

public interface FeatureRepository extends JpaRepository<Feature, Integer>{

	public Feature findByEmailAndFeatureName(String email,String featureName);
	
	boolean existsByEmail(String email);
}
