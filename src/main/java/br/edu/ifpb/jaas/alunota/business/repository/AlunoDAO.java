package br.edu.ifpb.jaas.alunota.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.jaas.alunota.business.model.Aluno;

public interface AlunoDAO extends JpaRepository<Aluno, Integer>{

}
