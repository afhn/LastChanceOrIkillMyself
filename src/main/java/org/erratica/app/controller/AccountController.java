package org.erratica.app.controller;

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
	
	@Autowired
	private SessionController idSesion;//Se recoge la ID de sesion que se guarda cuando se hace LogIn.
	
	private int idChampion_Session = 0;
	private int idProgress_Session = 0;
	
	@Autowired
	private IAccount accountImpl;
	@Autowired
	private IChampion championImpl;
	@Autowired
	private IProgress progressImpl;
		
	//Método que carga el perfil cuando se mapea la URL con profile.
	@GetMapping(value = "/profile")
	public String profile(Model model) {
		model.addAttribute("user",accountImpl.findByIdQuery(idSesion.getIdSesion()).setFormatNameAccount());
		model.addAttribute("championList",championImpl.findByidAccountQuery(idSesion.getIdSesion()));
		model.addAttribute("nChampions",championImpl.countByIdAccQuery(idSesion.getIdSesion()));
		return "account/profile";
	}
	
	//*********************************************************************************
	//****************************** REDIRECTS ****************************************
	//MÉTODO PARA CREAR UN NUEVO PERSONAJE Y VUELVE AL CHAMPION PROGRESS
	@PostMapping(value="/newChamp")
	public String champCreate(String championName, Model model) {
		championImpl.insertQuery(new Champion(championName,idSesion.getIdSesion()));	
		model.addAttribute("idChamp", championImpl.findBychampionName(championName).getId());
		return "redirect:/account/championProgress";
	}
	
	//MÉTODO PARA CREAR UNA NUEVA PARTIDA Y VUELVE AL CHAMPION PROGRESS
	@GetMapping(value="/newGame")
	public String champSelect(@RequestParam("idChamp") int idChamp_Param, Model model) {
		progressImpl.insertQuery(new Progress("Nivel 1","Ghost Town","00:00:00",idChamp_Param));
		model.addAttribute("idChamp", idChamp_Param);
		return "redirect:/account/championProgress";
	}
	
	//MÉTODO PARA CREAR UNA NUEVA PARTIDA Y VUELVE AL CHAMPION PROGRESS
	@GetMapping(value="/deleteGame")
	public String deleteProgress(@RequestParam("idProg") int idProgres_Param, Model model) {
		progressImpl.deleteQuery(idProgres_Param);
		model.addAttribute("idChamp",idChampion_Session);
		return "redirect:/account/championProgress";
	}
	
	//MÉTODO QUE BORRA UN PERSONAJE Y VUELVE A LA PÁGINA PROFILE
	@GetMapping(value="/deleteChamp")
	public String deleteChampion(@RequestParam("idChamp") int idChampion_Param, Model model) {
		championImpl.deleteByIdQuery(idChampion_Param);
		return "redirect:/account/profile";
	}
	//*********************************************************************************
	//*********************************************************************************
	
	//Método profile v2.0 que permite ver las partidas de cada personaje.
	@GetMapping(value="/championProgress")
	public String championProgress(@RequestParam("idChamp") int idChampion_param, Model model) {
		idChampion_Session = idChampion_param;
		model.addAttribute("champion",championImpl.findByIdQuery(idChampion_param));
		model.addAttribute("progressList",progressImpl.findAllByIdChampQuery(idChampion_param));
		model.addAttribute("user",accountImpl.findByIdQuery(idSesion.getIdSesion()).setFormatNameAccount());
		model.addAttribute("championList",championImpl.findByidAccountQuery(idSesion.getIdSesion()));
		model.addAttribute("nChampions",championImpl.countByIdAccQuery(idSesion.getIdSesion()));
		return "account/profile";
	}
	
	//Método para empezar la partida.
	@GetMapping(value="/play")
	public String game(@RequestParam("idProg") int idProgres_Param, Model model) {
		idProgress_Session = idProgres_Param;
		model.addAttribute("user",accountImpl.findByIdQuery(idSesion.getIdSesion()).setFormatNameAccount());
		return "account/game";
	}
	
	//Método json request que devuelve un objeto cuando unity haga requests al servidor.
	@GetMapping(value ="/jsonData")
	@ResponseBody
	public Progress generateJSONPostsingle() {
	Progress progress = progressImpl.findByIdQuery(idProgress_Session);
		return progress;
	}
	
}
