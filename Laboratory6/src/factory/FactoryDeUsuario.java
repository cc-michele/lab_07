/* 115111882 - Michele Santos Memoria: LAB 7 - Turma 3 */
package factory;

import excecoes.StringInvalidaException;
import usuario.Noob;
import usuario.Usuario;

public class FactoryDeUsuario {

	public Usuario criaUsuario(String nome, String login) throws StringInvalidaException {
		Usuario novoUsuario = new Usuario(nome, login);
		return novoUsuario;

	}
}
