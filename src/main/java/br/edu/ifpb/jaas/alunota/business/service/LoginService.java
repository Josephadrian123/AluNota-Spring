package br.edu.ifpb.jaas.alunota.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.jaas.alunota.business.model.Usuario;
import br.edu.ifpb.jaas.alunota.business.repository.UsuarioDAO;
import br.edu.ifpb.jaas.alunota.util.PasswordUtil;


@Service
public class LoginService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public Usuario isValido(Usuario usuario) {
		Usuario usuarioBD = usuarioDAO.findByEmail(usuario.getEmail());
		boolean valido = false;
		if (usuarioBD != null) {
			if (PasswordUtil.checkPass(usuario.getSenha(), usuarioBD.getSenha())) {
				valido = true;
			}
		} 
		return valido ? usuarioBD : null;
	}
	
	

}
