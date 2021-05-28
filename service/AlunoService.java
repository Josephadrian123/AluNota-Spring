package br.edu.ifpb.jaas.alunota.business.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.jaas.alunota.business.exception.AlunotaException;
import br.edu.ifpb.jaas.alunota.business.model.Aluno;
import br.edu.ifpb.jaas.alunota.business.repository.AlunoDAO;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoDAO alunoDAO;

	@Transactional
	public void saveAluno(Aluno aluno) {
		alunoDAO.save(aluno);
	}
	
	public Aluno findById(Integer id) {
		Optional<Aluno> aluno =  alunoDAO.findById(id);
		return aluno.isPresent() ? aluno.get() : null;
	}
	

	public List<Aluno> findAll() throws AlunotaException {
		return alunoDAO.findAll();
	}

	@Transactional
	public void deleteById(Integer id) {
		alunoDAO.deleteById(id);
	}


}
