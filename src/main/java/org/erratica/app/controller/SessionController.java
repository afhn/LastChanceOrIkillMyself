package org.erratica.app.controller;

import org.erratica.app.model.Account;
import org.erratica.app.service.IAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class SessionController {
	
	//Implementación de la clase Account y sus métodos.
	@Autowired
	private IAccount accountImpl;
	
	//Variable de clase de sesión para mantener el login.
	private int accountIdSesion;
	private Account account;
	
	//Carga de la página principal, la de LogIn.
	@GetMapping(value = "/")
	public String login() {
		accountIdSesion = 0;
		return "login";
	}
	
	//Carga de la página de registro a través de un href que hay en la página de LogIn.
	@GetMapping(value = "/register")
	public String registro() {
		accountIdSesion = 0;
		return "register";
	}
	
	//Método get para volver al home cuando se quiera volver.
	@GetMapping(value="/home")
	public String home(Model model) {
		model.addAttribute("user",accountImpl.findByIdQuery(accountIdSesion).setFormatNameAccount());
		return "home";
	}
	
	//Método post que guarda una cuenta al darle al botón de registrar en la página de registro.
	@PostMapping(value = "/save")
	public String save(Account account_BindingData, Model model, BindingResult errors) {
		String nameAccountFormated = account_BindingData.getNameAccount().toUpperCase();
		if(accountImpl.findBynameAccountQuery(nameAccountFormated)==null) {
			account_BindingData.setNameAccount(nameAccountFormated);
			accountImpl.insertQuery(account_BindingData);
			accountIdSesion = accountImpl.findBynameAccountQuery(nameAccountFormated).getIdAccount();
			account = accountImpl.findBynameAccountQuery(nameAccountFormated);
			account.setFormatNameAccount();
			model.addAttribute("user",account);
			return "home";
		}else
			return "redirect:/register";
	}
	
	
	//Método post que carga la página de home a través del login.
	@PostMapping(value = "/home")
	public String home(Model model, Account account_BindingData) {
		String nameAccountFormated = account_BindingData.getNameAccount().toUpperCase();
		if(accountImpl.findBynameAccountQuery(nameAccountFormated) != null) {
			if(account_BindingData.getPassword().equals(accountImpl.findBynameAccountQuery(nameAccountFormated).getPassword())) {
				accountIdSesion = accountImpl.findBynameAccountQuery(nameAccountFormated).getIdAccount();
				account_BindingData.setFormatNameAccount();
				account = accountImpl.findBynameAccountQuery(nameAccountFormated);
				model.addAttribute("user",account);
				return "home";
			}else 
				return "redirect:/";
		}else 
			return "redirect:/";
	}

	public int getIdSesion() {
		return accountIdSesion;
	}
	
	public Account getAccount() {
		return account;
	}

}
