package br.edu.ifpb.jaas.alunota.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.jaas.alunota.business.model.Usuario;
import br.edu.ifpb.jaas.alunota.business.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getForm(ModelAndView modelAndView) {
		modelAndView.setViewName("login/login");
		modelAndView.addObject("usuario", new Usuario());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView valide(Usuario usuario, HttpSession session, ModelAndView modelAndView, RedirectAttributes redirectAttts) {
		if ((usuario = loginService.isValido(usuario)) != null) {
			session.setAttribute("usuario", usuario);
			modelAndView.setViewName("redirect:/home");			
		} else {
			redirectAttts.addFlashAttribute("mensagem", "Login e/ou senha inválidos!");
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}
	
	@RequestMapping("/out")
	public ModelAndView logout(ModelAndView mav, HttpSession session) {
		session.invalidate();
		mav.setViewName("redirect:/login");
		return mav;
	}

}
