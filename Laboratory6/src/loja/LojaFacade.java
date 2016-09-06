package loja;

import easyaccept.EasyAccept;
import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;
import usuario.Usuario;

public class LojaFacade {
	private LojaController lojaController;

	public LojaFacade() {
		lojaController = new LojaController();
	}

	public void adicionaUsuario(String nome, String login) throws StringInvalidaException {
		 lojaController.adicionaUsuario(nome, login);

	}

	public void adicionaCredito(String login, double valor) {
		lojaController.adicionaCredito(login, valor);
	}

	public double confereCredito(String login) {
		return lojaController.confereCredito(login);
	}

	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser) {
		lojaController.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser);
	}

	public String imprimiInforção() throws Exception {
		return lojaController.informacaoUsuarios();
	}

	public void registraJogada(String login, String nomeJogo, int score, boolean venceu) {
		lojaController.registraJogada(login, nomeJogo, score, venceu);
	}

	public Usuario buscaUsuario(String login) {
		return lojaController.buscaUsuario(login);
	}

	public int getX2p(String login) {
		return lojaController.getX2p(login);
	}

	public void upgrade(String login) throws UpgradeInvalidoException {
		lojaController.upgrade(login);
	}

	public static void main(String[] args) {
		args = new String[] { "loja.LojaFacade", "acceptance_test/us1.txt", "acceptance_test/us2.txt",
				"acceptance_test/us3.txt" };
		EasyAccept.main(args);

	}

}
