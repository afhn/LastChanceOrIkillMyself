package org.erratica.app.service;

import org.erratica.app.model.Account;
import org.erratica.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountImpl implements IAccount {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public void insertQuery(Account account) {
		accountRepository.save(account);
	}

	@Override
	public int deleteQuery(int idAccount) {
		if(existsByIdQuery(idAccount)) {
			accountRepository.deleteById(idAccount);
			return 1;
		}else {
			return 0;
		}		
	}

	@Override
	public boolean existsByIdQuery(int idAccount) {
		return (accountRepository.existsById(idAccount))?true:false;
	}

	@Override
	public Account findByIdQuery(int idAccount) {
		return (accountRepository.findById(idAccount).isPresent())?accountRepository.findById(idAccount).get():null;
	}

	@Override
	public Account findBynameAccountQuery(String accountName) {
		return (accountRepository.findBynameAccount(accountName).isPresent())?accountRepository.findBynameAccount(accountName).get():null;
	}

}
