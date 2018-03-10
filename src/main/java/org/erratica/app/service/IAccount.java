package org.erratica.app.service;

import org.erratica.app.model.Account;

public interface IAccount {

	public void insertQuery(Account acc);
	public int deleteQuery(int idAcc);
	public boolean existsById(int idAcc);
	public Account findById(int idAcc);
	public Account findBynameAccount(String accName);
	
}
