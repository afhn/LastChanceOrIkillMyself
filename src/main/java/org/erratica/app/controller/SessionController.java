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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
public class SessionController {
	
	@Autowired
	private IAccount accountImpl;
	
	private int accountIdSesion; //Variable de sesión para mantener el LogIn

	@GetMapping(value = "/")
	public String login() {
		accountIdSesion = 0; //Reseteo de la variable de sesión al cerrar LogIn
		return "login";
	}

	@GetMapping(value = "/register")
	public String registro() {
		accountIdSesion = 0; //Reseteo de la variable por si queda algún rastro.
		return "register";
	}
	
	@GetMapping(value="/home")
	public String home(Model model) {
		model.addAttribute("user",accountImpl.findByIdQuery(accountIdSesion).setFormatNameAccount());
		return "home";
	}
	
	//Método post que guarda una cuenta al darle al botón de registrar en la página de registro.
	@PostMapping(value = "/save")
	public String save(Account account_BindingData, Model model, BindingResult errors) {
		//Formateo a Mayus del nombre del acc de Binding Data ya que en la BBDD se guarda en mayus.
		String nameAccountFormatUpper = account_BindingData.getNameAccount().toUpperCase();
		if(accountImpl.findBynameAccountQuery(nameAccountFormatUpper)==null) {
			//Antes de guardar la cuenta en la BBDD se formatea para guardarla en mayus.
			account_BindingData.setNameAccount(nameAccountFormatUpper);
			accountImpl.insertQuery(account_BindingData);
			//Se guarda la id como variable de sesión.
			accountIdSesion = accountImpl.findBynameAccountQuery(nameAccountFormatUpper).getIdAccount();
			//Se pasa la cuenta al modelo.
			model.addAttribute("user", accountImpl.findBynameAccountQuery(nameAccountFormatUpper).setFormatNameAccount());
			return "redirect:/home";
		}else
			return "redirect:/register";
	}
	
	//Método post que carga la página de home a través del login.
	@PostMapping(value = "/home")
	public String home(Model model, Account account_BindingData) {
		//Formateo a Mayus del nombre del acc de Binding Data ya que en la BBDD se guarda en mayus.
		String nameAccountFormatUpper = account_BindingData.getNameAccount().toUpperCase();
		if(accountImpl.passwordAndAccountVerifyQuery(account_BindingData)) {
			//Se guarda la id como variable de sesión.
			accountIdSesion = accountImpl.findBynameAccountQuery(nameAccountFormatUpper).getIdAccount();
			//Se pasa la cuenta de registro al modelo.
			model.addAttribute("user",accountImpl.findBynameAccountQuery(nameAccountFormatUpper).setFormatNameAccount());
			return "home";
		}else 
			return "redirect:/";
	}
	
	//MÉTODO PARA BORRAR CUENTA Y REDIRIGIR AL LOGIN
	@GetMapping(value="/account/deleteAccount")
	public String deleteAccount(@RequestParam("idAccount") int idAccount) {
		accountImpl.deleteQuery(idAccount);
		return "redirect:/";
	}

	//Método get para acceder a la id de sesión desde cualquier clase.
	public int getIdSesion() {
		return accountIdSesion;
	}

}
