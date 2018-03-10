package org.erratica.app.service;

import java.util.List;
import java.util.Optional;

import org.erratica.app.model.Champion;
import org.erratica.app.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampionImplementation implements IChampion{
	
	@Autowired
	ChampionRepository champRepo;
	
	@Override
	public int countByIdAcc(int idAcc) {
		int count = 0;
		List<Champion> lista = champRepo.findByidAccount(idAcc);
		for(int i=0; i<lista.size(); i++)
			count++;
		return count;
	}

	@Override
	public void insertQuery(Champion champ) {
		champRepo.save(champ);		
	}

	@Override
	public List<Champion> findByidAccount(int idAccount) {
		return champRepo.findByidAccount(idAccount);
	}

	@Override
	public void deleteById(int id) {
		champRepo.deleteById(id);		
	}

	@Override
	public Champion findById(int idChamp) {
		Optional <Champion> champ = champRepo.findById(idChamp);
		Champion cham;
		if(champ.isPresent()) {
			cham = champ.get();
			return cham;
		}else
			return null;
	}

}
