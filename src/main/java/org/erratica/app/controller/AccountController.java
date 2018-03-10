package org.erratica.app.controller;

//import org.erratica.app.model.Account;
import org.erratica.app.model.Champion;
import org.erratica.app.model.Progress;
//import org.erratica.app.service.IAccount;
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
	private SessionController idSesion;
	
	private int idChampion_Session = 0;
	private int idProgress_Session = 0;
	
	/*@Autowired
	private IAccount accountImpl;*/
	@Autowired
	private IChampion championImpl;
	@Autowired
	private IProgress progressImpl;
		
	//Método que carga el perfil cuando se mapea la URL con profile.
	@GetMapping(value = "/profile")
	public String profile(Model model) {
		model.addAttribute("user",idSesion.getAccount());
		model.addAttribute("champions",championImpl.findByidAccountQuery(idSesion.getIdSesion()));
		model.addAttribute("nChampions",championImpl.countByIdAccQuery(idSesion.getIdSesion()));
		return "account/profile";
	}
	
	//*********************************************************************************
	//****************************** REDIRECTS ****************************************
	//MÉTODO PARA CREAR UN NUEVO PERSONAJE
	@PostMapping(value="/newChamp")
	public String champCreate(String championName, Model model) {
		Champion champion = new Champion();
		champion.setChampionName(championName);
		champion.setIdAccount(idSesion.getIdSesion());
		championImpl.insertQuery(champion);		
		return "redirect:/account/profile";
	}
	
	//MÉTODO PARA CREAR UNA NUEVA PARTIDA
	@GetMapping(value="/newGame")
	public String champSelect(@RequestParam("idChamp") int idChamp_Param, Model model) {
		Progress progress = new Progress();
		progress.setLevelMap("Nivel 1");
		progress.setMap("Ghost Town");
		progress.setTime("00:00:00");
		progress.setIdChamp(idChamp_Param);
		progressImpl.insertQuery(progress);
		model.addAttribute("idChamp", idChamp_Param);
		return "redirect:/account/championProgress";
	}
	
	//MÉTODO PARA CREAR UNA NUEVA PARTIDA
	@GetMapping(value="/deleteGame")
	public String deleteProgress(@RequestParam("idProg") int idProgres_Param, Model model) {
		progressImpl.deleteQuery(idProgres_Param);
		model.addAttribute("idChamp",idChampion_Session);
		return "redirect:/account/championProgress";
	}
	
	//MÉTODO QUE BORRA UN PERSONAJE AL MAPPEAR /BORRARPERSONAJE
	@GetMapping(value="/deleteChamp")
	public String deleteChampion(@RequestParam("idChamp") int idChampion_Param, Model model) {
		championImpl.deleteByIdQuery(idChampion_Param);
		return "redirect:/account/profile";
	}
	//*********************************************************************************
	//*********************************************************************************
	
	@GetMapping(value="/championProgress")
	public String championProgress(@RequestParam("idChamp") int idChampion_param, Model model) {
		idChampion_Session = idChampion_param;
		model.addAttribute("champion",championImpl.findByIdQuery(idChampion_param));
		model.addAttribute("progressList",progressImpl.findAllByIdChampQuery(idChampion_param));
		model.addAttribute("user",idSesion.getAccount());
		model.addAttribute("champions",championImpl.findByidAccountQuery(idSesion.getIdSesion()));
		model.addAttribute("nChampions",championImpl.countByIdAccQuery(idSesion.getIdSesion()));
		return "account/profile";
	}
	
	@GetMapping(value="/play")
	public String game(@RequestParam("idProg") int idProgres_Param, Model model) {
		idProgress_Session = idProgres_Param;
		model.addAttribute("user",idSesion.getAccount());
		return "account/game";
	}
		
	 @GetMapping(value ="/jsonData")
	 @ResponseBody
	 public Progress generateJSONPostsingle() {
		 Progress progress = progressImpl.findByIdQuery(idProgress_Session);
		 return progress;
	 }
	
}
