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
import tn.iit.service.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientController {

	public final ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@PostMapping("/save")
	public String save(@RequestParam(name = "cin") String cin, @RequestParam(name = "nom") String nom,
			@RequestParam(name = "prenom") String prenom) {
		// FIXME
		Client client = new Client(cin,prenom,nom); // = new Client(nomClient, solde);
		clientService.saveOrUpdate(client);
		return "redirect:/clients/all";
	}

	@GetMapping("/all")
	public ModelAndView getAll() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("clients", clientService.findAll());
		modelAndView.setViewName("clients");// go to clients.html (sans extension)

		return modelAndView;
	}

	@GetMapping("/edit/{cin}")
	public ModelAndView edit(@PathVariable(name = "cin") String cin) {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("client", clientService.findById(cin));

		modelAndView.setViewName("edit-client");// go to hello.html (sans extension)

		return modelAndView;
	}

	@GetMapping("/delete/{cin}")
	public String delete(@PathVariable(name = "cin") String cin) {
		clientService.delete(cin);
		return "redirect:/clients/all";
	}

	@PostMapping("/delete-ajax")
	@ResponseBody
	public void deleteAjax(@RequestParam(name = "cin") String cin) {
		clientService.delete(cin);
	}

	@PostMapping(value = "/update")
	public String update(@ModelAttribute Client client) {
		clientService.saveOrUpdate(client);
		return "redirect:/clients/all";
	}

	@GetMapping("/all-json")

	@ResponseBody // le retour est json (api jackson)
	public List<Client> getAllJson() {
		return clientService.findAll();
	}

}
