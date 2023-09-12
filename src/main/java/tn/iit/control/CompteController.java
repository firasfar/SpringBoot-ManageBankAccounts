package tn.iit.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tn.iit.entity.Client;
import tn.iit.entity.Compte;
import tn.iit.service.ClientService;
import tn.iit.service.CompteService;

@Controller
@RequestMapping("/comptes")
public class CompteController {

	public final CompteService compteService;
	public final ClientService clientService;

	@Autowired
	public CompteController(CompteService compteService, ClientService clientService) {
		super();
		this.compteService = compteService;
		this.clientService = clientService;
	}
	

	@PostMapping("/save")
	public String save(@RequestParam(name = "cin") String cin, @RequestParam(name = "solde") float solde) {
		Client client = clientService.findById(cin) ;
		Compte compte  = new Compte(solde ,client );
		compteService.saveOrUpdate(compte);
		return "redirect:/comptes/all";
	}

	@GetMapping("/all")
	public ModelAndView getAll() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("comptes", compteService.findAll());
		modelAndView.setViewName("comptes");// go to comptes.html (sans extension)

		return modelAndView;
	}

	@GetMapping("/edit/{rib}")
	public ModelAndView edit(@PathVariable(name = "rib") Long rib) {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("compte", compteService.findById(rib));

		modelAndView.setViewName("edit-compte");// go to hello.html (sans extension)

		return modelAndView;
	}

	@GetMapping("/delete/{rib}")
	public String delete(@PathVariable(name = "rib") Long rib) {
		compteService.delete(rib);
		return "redirect:/comptes/all";
	}

	@PostMapping("/delete-ajax")
	@ResponseBody
	public void deleteAjax(@RequestParam(name = "rib") Long rib) {
		compteService.delete(rib);
	}

	@PostMapping(value = "/update")
	public String update(@ModelAttribute Compte compte) {
		compteService.saveOrUpdate(compte);
		return "redirect:/comptes/all";
	}

	@GetMapping("/all-json")

	@ResponseBody // le retour est json (api jackson)
	public List<Compte> getAllJson() {
		return compteService.findAll();
	}

}
