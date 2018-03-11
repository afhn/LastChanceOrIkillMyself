package org.erratica.app.service;

import org.erratica.app.model.Account;

public interface IAccount {

	public void insertQuery(Account account);
	public int deleteQuery(int idAccount);
	public boolean existsByIdQuery(int idAccount);
	public Account findByIdQuery(int idAccount);
	public Account findBynameAccountQuery(String accountName);
	public boolean passwordAndAccountVerifyQuery(Account account);
	
}
