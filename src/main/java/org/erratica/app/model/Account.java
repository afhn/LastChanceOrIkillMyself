package org.erratica.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAccount;
	private String nameAccount;
	private String password;
	private String respuestaSecreta;
	private Date fechaCreacion;
	
	public Account() {
		this.fechaCreacion = new Date();
	}
	
	public String getRespuestaSecreta() {
		return respuestaSecreta;
	}

	public void setRespuestaSecreta(String respuestaSecreta) {
		this.respuestaSecreta = respuestaSecreta;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public String getNameAccount() {
		return nameAccount;
	}

	public void setNameAccount(String nameAccount) {
		this.nameAccount = nameAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//No recuerdo cómo funcionaba, pero mola.
	public Account setFormatNameAccount() {
		int nameLength = nameAccount.length();
		String name = nameAccount.substring(0,1).toUpperCase()+nameAccount.substring(1,nameLength).toLowerCase();
		setNameAccount(name);
		return this;
	}
	
}
