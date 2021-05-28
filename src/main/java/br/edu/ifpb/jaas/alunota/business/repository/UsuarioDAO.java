package br.edu.ifpb.jaas.alunota.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.jaas.alunota.business.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer>{
	
	public Usuario findByEmail(String email);

}
