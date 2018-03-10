package org.erratica.app.controller;

import java.util.List;

import org.erratica.app.model.Account;
import org.erratica.app.model.Champion;
import org.erratica.app.model.Progress;
import org.erratica.app.service.IAccount;
import org.erratica.app.service.IChampion;
import org.erratica.app.service.IProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/account")
public class AccountController {
	
	//Se recoge la ID de sesion que se guarda cuando se hace LogIn.
	@Autowired
	public SessionController idSesion;
	
	private int idChampion = 0;
	private int idProgress = 0;
	
	@Autowired
	private IAccount accImpl;
	@Autowired
	private IChampion champImpl;
	@Autowired
	private IProgress progImpl;
		
	//Método que carga el perfil cuando se mapea la URL con profile.
	@GetMapping(value = "/profile")
	public String profile(Model model) {

		Account account = accImpl.findById(idSesion.getIdSesion());
		int nameLength = account.getNameAccount().length();
		String name = account.getNameAccount().substring(0,1).toUpperCase()+account.getNameAccount().substring(1,nameLength).toLowerCase();
		account.setNameAccount(name);
		
		List<Champion> listChampions = champImpl.findByidAccount(account.getIdAcc());
		model.addAttribute("user",account);
		model.addAttribute("champions",listChampions);
		model.addAttribute("nChampions",champImpl.countByIdAcc(account.getIdAcc()));
		return "account/profile";
	}
	
	//*********************************************************************************
	//****************************** REDIRECTS ****************************************
	//MÉTODO PARA CREAR UN NUEVO PERSONAJE
	@PostMapping(value="/newChamp")
	public String champCreate(String championName, Model model) {
		Champion champ = new Champion();
		champ.setChampionName(championName);
		champ.setIdAccount(idSesion.getIdSesion());
		champImpl.insertQuery(champ);		
		return "redirect:/account/profile";
	}
	
	//MÉTODO PARA CREAR UNA NUEVA PARTIDA
	@GetMapping(value="/newGame")
	public String champSelect(@RequestParam("idChamp") int idChamp, Model model) {
		Progress prog = new Progress();
		prog.setLevelMap("Nivel 1");
		prog.setMap("Ghost Town");
		prog.setTime("00:00:00");
		prog.setIdChamp(idChamp);
		progImpl.insertQuery(prog);
		model.addAttribute("idChamp", idChamp);
		return "redirect:/account/championProgress";
	}
	
	//MÉTODO PARA CREAR UNA NUEVA PARTIDA
	@GetMapping(value="/deleteGame")
	public String deleteProgress(@RequestParam("idProg") int idProgr, Model model) {
		progImpl.deleteQuery(idProgr);
		model.addAttribute("idChamp",idChampion);
		return "redirect:/account/championProgress";
	}
	
	//MÉTODO QUE BORRA UN PERSONAJE AL MAPPEAR /BORRARPERSONAJE
	@GetMapping(value="/deleteChamp")
	public String deleteChampion(@RequestParam("idChamp") int idChamp, Model model) {
		champImpl.deleteById(idChamp);
		return "redirect:/account/profile";
	}
	//*********************************************************************************
	//*********************************************************************************
	
	@GetMapping(value="/championProgress")
	public String championProgress(@RequestParam("idChamp") int idChamp, Model model) {

		Account account = accImpl.findById(idSesion.getIdSesion());
		int nameLength = account.getNameAccount().length();
		String name = account.getNameAccount().substring(0,1).toUpperCase()+account.getNameAccount().substring(1,nameLength).toLowerCase();
		account.setNameAccount(name);
		
		List<Champion> listChampions = champImpl.findByidAccount(account.getIdAcc());
		List<Progress> listProgress = progImpl.findAllByIdChamp(idChamp);

		idChampion = idChamp;
		
		model.addAttribute("champion",champImpl.findById(idChamp));
		model.addAttribute("progressList",listProgress);
		model.addAttribute("user",account);
		model.addAttribute("champions",listChampions);
		model.addAttribute("nChampions",champImpl.countByIdAcc(account.getIdAcc()));
		
		return "account/profile";
	}
	
	@GetMapping(value="/play")
	public String game(@RequestParam("idProg") int idProgr, Model model) {
		
		Account account = accImpl.findById(idSesion.getIdSesion());
		int nameLength = account.getNameAccount().length();
		String name = account.getNameAccount().substring(0,1).toUpperCase()+account.getNameAccount().substring(1,nameLength).toLowerCase();
		account.setNameAccount(name);
		
		idProgress = idProgr;
		model.addAttribute("user",account);
		
		return "account/game";
	}
		
	 @GetMapping(value ="/jsonData")
	 @ResponseBody
	 public Progress generateJSONPostsingle() {
		 Progress prog = progImpl.findById(idProgress);
		 return prog;
	 }
	
}
