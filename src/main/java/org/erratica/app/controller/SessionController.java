package org.erratica.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.erratica.app.model.Account;
import org.erratica.app.service.IAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class SessionController {
	
	@InitBinder
	//InitBinder para formatear la fecha que se define automáticamente cada vez que se crea una cuenta.
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	//Implementación de la clase Account y sus métodos.
	@Autowired
	private IAccount accImpl;
	
	//Variable de clase de sesión para mantener el login.
	private int idSesion;
	
	//Carga de la página principal, la de LogIn.
	@GetMapping(value = "/")
	public String login() {
		idSesion = 0;
		return "login";
	}
	
	//Carga de la página de registro a través de un href que hay en la página de LogIn.
	@GetMapping(value = "/register")
	public String registro() {
		idSesion = 0;
		return "register";
	}
	
	//Método get para volver al home cuando se quiera volver.
	@GetMapping(value="/home")
	public String home(Model model) {
		Account account = accImpl.findById(idSesion);
		//Se formatea el nombre de cuenta ya que se va a utilizar en el home para que no salga en mayus totalmente.
		int nameLength = account.getNameAccount().length();
		String name = account.getNameAccount().substring(0,1).toUpperCase()+account.getNameAccount().substring(1,nameLength).toLowerCase();
		account.setNameAccount(name);
		model.addAttribute("user",account);
		return "home";
	}
	
	//Método post que guarda una cuenta al darle al botón de registrar en la página de registro.
	@PostMapping(value = "/save")
	public String save(Account ac, Model model, BindingResult errors) {
		/* 
		 * Manipulación de errores, aún a falta de configuración.
		 * if(errors.hasErrors()) {
		 * }else {}
		 */
		//Primero se mira si existe una cuenta que haya con el mismo nombre, todas las cuentas se guardan en mayúsculas.
		if(accImpl.findBynameAccount(ac.getNameAccount().toUpperCase())==null) {
			//Si no existe ninguna cuenta con el mismo nombre, se formatea el nombre que se ha introducido en el formulario a mayus.
			ac.setNameAccount(ac.getNameAccount().toUpperCase());
			//Se inserta en la BBDD.
			accImpl.insertQuery(ac);
			//Se busca la cuenta que se acaba de insertar para coger su id posteriormente.
			Account account = accImpl.findBynameAccount(ac.getNameAccount().toUpperCase());
			//Se añade la id a una variable de sesión.
			idSesion = account.getIdAcc();
			//Se formatea el nombre de cuenta ya que se va a utilizar en el home para que no salga en mayus totalmente.
			int nameLength = account.getNameAccount().length();
			String name = account.getNameAccount().substring(0,1).toUpperCase()+account.getNameAccount().substring(1,nameLength).toLowerCase();
			account.setNameAccount(name);
			//Se pasa la cuenta a través de un model al home y se carga el home.
			model.addAttribute("user",account);
			return "home";
		}else
			return "redirect:/register";
	}
	
	
	//Método post que carga la página de home a través del login.
	@PostMapping(value = "/home")
	public String home(Model model, Account ac, BindingResult errors) {
		//Se busca una cuenta en la BBDD a través del nombre de cuenta que se formatea a mayúsculas.
		Account account = accImpl.findBynameAccount(ac.getNameAccount().toUpperCase());
		/* 
		 * Manipulación de errores, aún a falta de configuración.
		 * if(errors.hasErrors()) {
		 * }else {}
		 */
		//Se comprueba que no nos devuelve null
		if(account!=null) {
			//Si no nos devuelve null, se compara la contraseña que se haya intrudicido con la contraseña que se haya asociada a la cuenta. Tiene que ser igual.
			if(ac.getPassword().equals(account.getPassword())) {
				//Si no hay fallo se añade la id a una variable de sesión.
				idSesion =  account.getIdAcc();
				//Se formatea el nombre de cuenta ya que se va a utilizar en el home para que no salga en mayus totalmente.
				int nameLength = account.getNameAccount().length();
				String name = account.getNameAccount().substring(0,1).toUpperCase()+account.getNameAccount().substring(1,nameLength).toLowerCase();
				account.setNameAccount(name);
				//Se pasa la cuenta a un model y se carga el home.
				model.addAttribute("user",account);
				return "home";
			}else 
				return "redirect:/";
		}else 
			return "redirect:/";
		//Si el nombre o la contraseña no coinciden se redirecciona al login.
	}

	//Método get de la Id de sesión que se guarda en esta clase cada vez que se hace LogIn para poder usarla en otros controladores.
	public int getIdSesion() {return idSesion;}

}
