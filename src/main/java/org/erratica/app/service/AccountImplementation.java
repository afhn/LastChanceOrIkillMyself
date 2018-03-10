package org.erratica.app.service;

import java.util.Optional;

import org.erratica.app.model.Account;
import org.erratica.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountImplementation implements IAccount {
	
	@Autowired
	AccountRepository accRepo;
	
	@Override
	public void insertQuery(Account acc) {
		accRepo.save(acc);
	}

	@Override
	public int deleteQuery(int idAcc) {
		if(existsById(idAcc)) {
			accRepo.deleteById(idAcc);
			return 1;
		}else {
			return 0;
		}		
	}

	@Override
	public boolean existsById(int idAcc) {
		return (accRepo.existsById(idAcc))?true:false;
	}

	@Override
	public Account findById(int idAcc) {
		Optional<Account> accObj = accRepo.findById(idAcc);
		return (accObj.isPresent())?accObj.get():null;
	}

	@Override
	public Account findBynameAccount(String accName) {
		Optional<Account> accObj = accRepo.findBynameAccount(accName);
		return (accObj.isPresent())?accObj.get():null;
	}

}
