package factory;

import excecoes.StringInvalidaException;
import usuario.Noob;
import usuario.Usuario;

public class FactoryDeUsuario {

	public Usuario criaUsuario(String nome, String login) throws StringInvalidaException {
		Usuario novoUsuario = new Noob(nome, login);
		return novoUsuario;

	}
}
