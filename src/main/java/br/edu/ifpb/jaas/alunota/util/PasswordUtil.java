package br.edu.ifpb.jaas.alunota.util;

import org.mindrot.jbcrypt.BCrypt;

import br.edu.ifpb.jaas.alunota.util.PasswordUtil;

public abstract class PasswordUtil {

	public static String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}
	
	public static boolean checkPass(String plainPassword, String hashedPassword) {
		if (BCrypt.checkpw(plainPassword, hashedPassword))
			return true;
		else
			return false;
	}
	
	//Exemplo de uso
	public static void main(String[] args) {
		System.out.println(PasswordUtil.hashPassword("Nabucodonosor"));
		System.out.println(PasswordUtil.checkPass("Nabucodonosor", "$2a$10$ERNm3h05JniqNPZhVZjQoeNvEimJffDJFHs1OVW2XqCUfzHRUxFM2"));
	}
}
