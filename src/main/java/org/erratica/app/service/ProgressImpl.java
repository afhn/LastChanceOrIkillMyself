package org.erratica.app.service;

import java.util.List;

import org.erratica.app.model.Progress;
import org.erratica.app.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressImpl implements IProgress{
	
	@Autowired
	ProgressRepository progressRepository;
	
	@Override
	public void insertQuery(Progress progress) {
		progressRepository.save(progress);		
	}
	
	@Override
	public Progress findByIdQuery(int idProgress) {
		return (progressRepository.findById(idProgress).isPresent())?progressRepository.findById(idProgress).get():null;
	}

	@Override
	public List<Progress> findAllByIdChampQuery(int idChampion) {
		return progressRepository.findByidChamp(idChampion);
	}

	@Override
	public void deleteQuery(int idProgress) {
		progressRepository.deleteById(idProgress);		
	}	

}
