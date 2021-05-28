package br.edu.ifpb.jaas.alunota.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.jaas.alunota.business.model.Usuario;
import br.edu.ifpb.jaas.alunota.business.repository.UsuarioDAO;
import br.edu.ifpb.jaas.alunota.util.PasswordUtil;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Transactional
	public void insertBD() {
		usuarioDAO.deleteAll();
		
		Usuario joseph = new Usuario();
		joseph.setEmail("joseph@email.com");
		joseph.setNome("Joseph Adrian");
		joseph.setSenha(PasswordUtil.hashPassword("123"));
		usuarioDAO.save(joseph);
		
		Usuario admin = new Usuario();
		admin.setEmail("admin@email.com");
		admin.setNome("Administrador do Sistema");
		admin.setSenha(PasswordUtil.hashPassword("123"));
		admin.setAdmin(true);
		usuarioDAO.save(admin);
		
	}

	public List<Usuario>findAll() {
		return usuarioDAO.findAll();
	}

	public Usuario findById(Integer usuarioId) {
		Optional<Usuario> u =  usuarioDAO.findById(usuarioId);
		return u.isPresent() ? u.get() : null;
	}
	

}
