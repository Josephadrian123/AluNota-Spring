package br.edu.ifpb.jaas.alunota.spring.controller;

import java.math.BigDecimal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.jaas.alunota.business.model.Aluno;
import br.edu.ifpb.jaas.alunota.business.service.AlunoService;
import br.edu.ifpb.jaas.alunota.business.exception.AlunotaException;


@Controller
@RequestMapping("/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@RequestMapping("/form")
	public ModelAndView getForm(ModelAndView modelAndView) {
		modelAndView.setViewName("alunos/form");
		modelAndView.addObject("aluno", new Aluno());
		return modelAndView;
	}
	
	
	
	@RequestMapping("/notas")
	public ModelAndView getNotas(ModelAndView modelAndView) {
		modelAndView.setViewName("alunos/notas");
		try {
			List<Aluno> alunos = alunoService.findAll();
			modelAndView.addObject("alunos", alunos);
		} catch (AlunotaException e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}
		return modelAndView;
	}
	
	@RequestMapping("/relatorio")
	public ModelAndView getRelatorio(ModelAndView modelAndView) {
		modelAndView.setViewName("alunos/relatorio");
		try {
			List<Aluno> alunos = alunoService.findAll();
			modelAndView.addObject("alunos", alunos);
		} catch (AlunotaException e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView adicioneAluno(Aluno aluno, ModelAndView modelAndView, RedirectAttributes attr) {
	
		alunoService.saveAluno(aluno);
		modelAndView.setViewName("redirect:/alunos");
		attr.addFlashAttribute("mensagem", "Aluno salvo com sucesso!");
		return modelAndView;
	}
	
	@RequestMapping(value = "/{id}/edit")
	public ModelAndView atualizeAluno(@PathVariable("id") Integer id, Aluno alunoNovo, ModelAndView modelAndView, RedirectAttributes attr) {
		
		Aluno aluno = alunoService.findById(id);
		if(alunoNovo.getNome() != null) {
			aluno.setNome(alunoNovo.getNome());
		}
		if(alunoNovo.getDataNasc() != null) {
			aluno.setDataNasc(alunoNovo.getDataNasc());
		}
		if(alunoNovo.getFaltas() != null) {
			aluno.setFaltas(alunoNovo.getFaltas());
		}
		if(alunoNovo.getNotaUm() != null) {
			aluno.setNotaUm(alunoNovo.getNotaUm());
		}
		if(alunoNovo.getNotaDois() != null) {
			aluno.setNotaDois(alunoNovo.getNotaDois());
		}
		if(alunoNovo.getNotaTres() != null) {
			aluno.setNotaTres(alunoNovo.getNotaTres());
		}
		if(alunoNovo.getNotaFinal() != null) {
			aluno.setNotaFinal(alunoNovo.getNotaFinal());
		}
		alunoService.saveAluno(aluno);
		modelAndView.setViewName("redirect:/alunos");
		attr.addFlashAttribute("mensagem", "Aluno atualizado com sucesso!");
		return modelAndView;
	}
	
	@RequestMapping(value = "/{id}/salvarNotas")
	public ModelAndView cadastrarNotas(@PathVariable("id") Integer id, Aluno alunoNovo, ModelAndView modelAndView, RedirectAttributes attr) {
		int contador = 0;
		String proxPag;
		Aluno aluno = alunoService.findById(id);
		if(alunoNovo.getNotaUm() != null) {
			aluno.setNotaUm(alunoNovo.getNotaUm());
		}else contador +=1;
		
		if(alunoNovo.getNotaDois() != null) {
			aluno.setNotaDois(alunoNovo.getNotaDois());
		}else contador +=1;
		
		if(alunoNovo.getNotaTres() != null) {
			aluno.setNotaTres(alunoNovo.getNotaTres());
		}else contador +=1;
		
		if(alunoNovo.getNotaFinal() != null) {		
			if(aluno.getNotaUm() != null && aluno.getNotaDois() != null && aluno.getNotaTres() != null && aluno.getFaltas() != null) {
				if(aluno.mediaNotas().compareTo(new BigDecimal(40.00)) > 0 && aluno.mediaNotas().compareTo(new BigDecimal(70.00)) < 0 && aluno.getFaltas() <= 25) {
					aluno.setNotaFinal(alunoNovo.getNotaFinal());	
				}else {
					attr.addFlashAttribute("mensagem", "Aluno Aprovado ou inelegível a prova final!");
					proxPag = "redirect:/alunos/" + id + "/notas";
					modelAndView.setViewName(proxPag);
					return modelAndView;
				}
			}else {
				attr.addFlashAttribute("mensagem", "Aluno Aprovado ou inelegível a prova final!");
				proxPag = "redirect:/alunos/" + id + "/notas";
				modelAndView.setViewName(proxPag);
				return modelAndView;
			}
			
			
		}else contador +=1;
		
		if(alunoNovo.getFaltas() != null) {
			aluno.setFaltas(alunoNovo.getFaltas());
		}else contador +=1;
		
		if(contador == 5) {
			attr.addFlashAttribute("mensagem", "Nenhum campo preenchido!");
			proxPag = "redirect:/alunos/" + id + "/notas";
			modelAndView.setViewName(proxPag);
		}else {
		alunoService.saveAluno(aluno);
		
	
		attr.addFlashAttribute("mensagem", "Notas cadastradas com sucesso!");
		modelAndView.setViewName("redirect:/alunos/relatorio");
		}
		modelAndView.addObject("aluno", aluno);
		return modelAndView;
	}


	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listeAlunos(ModelAndView modelAndView) {
		modelAndView.setViewName("/alunos/list");
		try {
			List<Aluno> alunos = alunoService.findAll();
			modelAndView.addObject("alunos", alunos);
		} catch (AlunotaException e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}
		return modelAndView;
	}

	@RequestMapping("/{id}")
	public String busquePorId(@PathVariable("id") Integer id, Model model, RedirectAttributes attr) {
		Aluno aluno = alunoService.findById(id);
		if (aluno != null) {
			model.addAttribute("aluno", aluno);
		} else {
			attr.addFlashAttribute("mensagem", "Aluno não encontrado!");
			model.addAttribute("aluno", new Aluno());
		}
		return "redirect:/alunos/form";
	}

	@RequestMapping(value = "/{id}/delete")
	public ModelAndView deletePorId(@PathVariable("id") Integer id, ModelAndView modelAndView,
			RedirectAttributes attr) {
		alunoService.deleteById(id);
		modelAndView.setViewName("redirect:/alunos");
		attr.addFlashAttribute("mensagem", "Aluno excluído!");
		return modelAndView;
	}
	
	@RequestMapping(value = "/{id}/notas")
	public String insereNotas(@PathVariable("id") Integer id, Model model, RedirectAttributes attr) {
		Aluno aluno = alunoService.findById(id);
		
		model.addAttribute("aluno", aluno);
		
		
		return "alunos/form-notas";
	}
	
	@RequestMapping(value = "/{id}/editForm")
	public String editaAluno(@PathVariable("id") Integer id, Model model) {
		Aluno aluno = alunoService.findById(id);
		
		model.addAttribute("aluno", aluno);
		
		
		return "alunos/edit-aluno";
	}
	
	@RequestMapping(value = "/form-notas")
	public ModelAndView getFormNotas(ModelAndView modelAndView, RedirectAttributes attr) {
		
		modelAndView.setViewName("alunos/form-notas");
		
		
		return modelAndView;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/edit-aluno")
	public ModelAndView editAluno(ModelAndView modelAndView, RedirectAttributes attr) {
		
		modelAndView.setViewName("redirect:/alunos/edit-aluno");
		
		
		return modelAndView;
	}



}