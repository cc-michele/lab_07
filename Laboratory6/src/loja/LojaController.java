package loja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import easyaccept.EasyAccept;
import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.TipoJogoInvalidoException;
import excecoes.UpgradeInvalidoException;
import excecoes.ValorInvalidoException;
import factory.FactoryDeJogo;
import factory.FactoryDeUsuario;
import jogo.Jogabilidade;
import jogo.Jogo;
import usuario.Usuario;


public class LojaController {
	public static final String FIM_DE_LINHA = System.lineSeparator();
	private List<Usuario> meusUsuarios;
	private HashMap<String, Jogabilidade> mapJogabildades;
	private FactoryDeUsuario factoryUsuario;
	private FactoryDeJogo factoryJogos;

	public LojaController() {
		this.meusUsuarios = new ArrayList<Usuario>();
		this.initializeMap();
		factoryUsuario = new FactoryDeUsuario();
		factoryJogos = new FactoryDeJogo();
	}

	public boolean adicionaUsuario(String nome, String login) throws StringInvalidaException {
		Usuario novoUsuario = criaUsuario(nome, login);
		return meusUsuarios.add(novoUsuario);
	}

	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser) {

		try {
			Usuario buscado = this.buscaUsuario(loginUser);
			Set<Jogabilidade> tiposJogabilidades = this.createJogabilidades(jogabilidades);
			Jogo jogoVendido = this.criaJogo(jogoNome, preco, tiposJogabilidades, estiloJogo);
			buscado.compraJogo(jogoVendido); // Chamada polimorfica
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	}

	public void registraJogada(String login, String nomeJogo, int score, boolean venceu) {
		try {
			Usuario usr = this.buscaUsuario(login);
			usr.recompensar(nomeJogo, score, venceu); // Chamada polimorfica
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void adicionaCredito(String login, double valor) {
		try {
			if (valor < 0) {
				throw new ValorInvalidoException("Credito nao pode ser negativo");
			}
			Usuario user = this.buscaUsuario(login);
			user.setCredito(user.getCredito() + valor);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	}
	public double confereCredito(String login) {
		try {
			Usuario procurado = this.buscaUsuario(login);
			return procurado.getCredito();
		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

	public Usuario buscaUsuario(String login) {
		Usuario buscado = null;

		try {
			for (int i = 0; i < meusUsuarios.size(); i++) {
				if (meusUsuarios.get(i).getLogin().equals(login)) {
					buscado = meusUsuarios.get(i);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return buscado;
	}

	public void upgrade(String login) throws UpgradeInvalidoException {
		
		Usuario buscado = this.buscaUsuario(login);
		buscado.upgrade();

	}



	public String informacaoUsuarios() {
		String myString = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA;
		for (int i = 0; i < meusUsuarios.size(); i++) {
			myString += meusUsuarios.get(i).toString() + FIM_DE_LINHA;
		}
		return myString;
	}

	public int getX2p(String login) {
		Usuario buscado = this.buscaUsuario(login);
		return buscado.getX2p();
	}

	private Usuario criaUsuario(String nome, String login) throws StringInvalidaException {
		return factoryUsuario.criaUsuario(nome, login);
	}

	private Jogo criaJogo(String nome, double preco, Set<Jogabilidade> tiposJogabilidades, String estiloJogo)
			throws StringInvalidaException, PrecoInvalidoException, TipoJogoInvalidoException {
		return (factoryJogos.criaJogo(nome, preco, tiposJogabilidades, estiloJogo));

	}

	private Set<Jogabilidade> createJogabilidades(String names1) {
		Set<Jogabilidade> jogabilidades = new HashSet<Jogabilidade>();

		String[] listofNames = names1.split(",");

		for (int i = 0; i < listofNames.length; i++) {
			String element = listofNames[i].toUpperCase();
			if (element != null) {
				Jogabilidade tipojogabilidade = mapJogabildades.get(element);
				jogabilidades.add(tipojogabilidade);
			}
		}

		return jogabilidades;

	}

	private void initializeMap() {
		this.mapJogabildades = new HashMap<String, Jogabilidade>();
		mapJogabildades.put("ONLINE", Jogabilidade.ONLINE);
		mapJogabildades.put("OFFLINE", Jogabilidade.OFFLINE);
		mapJogabildades.put("COMPETITIVO", Jogabilidade.COMPETITIVO);
		mapJogabildades.put("COOPERATIVO", Jogabilidade.COOPERATIVO);
		mapJogabildades.put("MULTIPLAYER", Jogabilidade.MULTIPLAYER);

	}

}
