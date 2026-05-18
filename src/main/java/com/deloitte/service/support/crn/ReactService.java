package com.deloitte.service.support.crn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactService {

	@Autowired
	private ReactRepository reactRepository;

	// GET by ID
	public ReactEntity getValuesById(Long id) {
		return reactRepository.findById(id).orElseThrow(() -> new RuntimeException("Data not found"));
	}

	// GET all
	public List<ReactEntity> getAll() {
		return reactRepository.findAll();
	}

	// CREATE
	public ReactEntity create(ReactEntity entity) {
		return reactRepository.save(entity);
	}

	// UPDATE
	public ReactEntity update(Long id, ReactEntity entity) {
		ReactEntity existing = reactRepository.findById(id).orElseThrow(() -> new RuntimeException("Data not found"));

		existing.setDescription(entity.getDescription());
		existing.setAssignedTo(entity.getAssignedTo());

		return reactRepository.save(existing);
	}

	// DELETE
	public void delete(Long id) {
		reactRepository.deleteById(id);
	}
}
