package org.erratica.app.service;

import java.util.List;
import java.util.Optional;

import org.erratica.app.model.Progress;
import org.erratica.app.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressImplementation implements IProgress{
	
	@Autowired
	ProgressRepository progRepo;
	
	@Override
	public void insertQuery(Progress prog) {
		progRepo.save(prog);		
	}
	
	@Override
	public Progress findById(int idProgress) {
		Optional <Progress> progress = progRepo.findById(idProgress);
		Progress prog;
		if(progress.isPresent()) {
			prog = progress.get();
			return prog;
		}else
			return null;
	}

	@Override
	public List<Progress> findAllByIdChamp(int idChampion) {
		return progRepo.findByidChamp(idChampion);
	}

	@Override
	public void deleteQuery(int idProgress) {
		progRepo.deleteById(idProgress);		
	}	

}
