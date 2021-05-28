package br.edu.ifpb.jaas.alunota.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.jaas.alunota.business.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/insert")
	public ModelAndView insertBD(ModelAndView mav) {
		usuarioService.insertBD();
		mav.setViewName("redirect:/login/out");
		return mav;
	}
	
}
