package usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

public class Usuario {

	public static final String FIM_DE_LINHA = System.lineSeparator();

	private String nome;
	private String login;
	private Set<Jogo> meusJogos;
	private double credito;
	private int x2p;
	private TipoDeUsuarioIF statusDoUsuario;

	public Usuario(String nome, String login) throws StringInvalidaException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (login == null || login.trim().isEmpty()) {
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}

		this.nome = nome;
		this.login = login;
		meusJogos = new HashSet<Jogo>();
		this.credito = 0;
		this.statusDoUsuario = new Noob();
	}

	public void compraJogo(Jogo jogo) throws Exception {
		double custo = statusDoUsuario.calculaDesconto(jogo);
		if (custo > this.getCredito()) {
			throw new ValorInvalidoException("Credito insuficiente para realizar a compra.");
		} else {
			setX2p(getXp2() + statusDoUsuario.calculaX2P(jogo));
			setCredito(getCredito() - custo);
			this.cadastraJogo(jogo);

		}

	}

	public TipoDeUsuarioIF getStatusDoUsuario() {
		return statusDoUsuario;
	}

	public void setStatusDoUsuario(TipoDeUsuarioIF statusDoUsuario) {
		this.statusDoUsuario = statusDoUsuario;
	}

	public void setX2p(int novoValor) {
		this.x2p = novoValor;
	}

	public int getXp2() {
		return this.x2p;
	}

	public void cadastraJogo(Jogo jogo) {
		this.meusJogos.add(jogo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setCredito(double novoValor) {
		this.credito = novoValor;
	}

	public double getCredito() {
		return this.credito;
	}

	public Set<Jogo> getMeusJogos() {
		return meusJogos;
	}

	public void setMeusJogos(Set<Jogo> meusJogos) {
		this.meusJogos = meusJogos;
	}

	/*
	 * public void registradaJogada(String nomeJogo, int score, boolean venceu)
	 * throws Exception { Jogo jogo = this.buscaJogo(nomeJogo); if (jogo ==
	 * null) { throw new Exception(); } setXp2(getXp2() +
	 * jogo.registraJogada(score, venceu)); }
	 */

	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou) {
		Jogo jogo = this.buscaJogo(nomeJogo);
		int x2p = getXp2() + (statusDoUsuario.calculaX2P(jogo)) + jogo.registraJogada(scoreObtido, zerou);
		setX2p(x2p);
	}

	public void punir(String nomeJogo, int scoreObtido, boolean zerou) {
		Jogo jogo = this.buscaJogo(nomeJogo);
		int x2p = getXp2() + (statusDoUsuario.punir(jogo) + jogo.registraJogada(scoreObtido, zerou));
		setX2p(x2p);
	}

	public Jogo buscaJogo(String nomeJogo) {
		Jogo buscado = null;
		Iterator itr = meusJogos.iterator();
		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			if (achado.getNome().equals(nomeJogo)) {
				buscado = achado;
			}
		}
		return buscado;
	}

	public double calculaPrecoTotal() {
		double total = 0;
		Iterator itr = meusJogos.iterator();
		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			total += achado.getPreco();
		}
		return total;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario temp = (Usuario) obj;
			return this.getNome().equals(temp.getNome()) && this.getLogin().equals(temp.getLogin()) && this.getStatusDoUsuario().equals(temp.getStatusDoUsuario());
		} else {
			return false;
		}
	}

	
	public void upgrade() throws UpgradeInvalidoException {
		
		if (statusDoUsuario.getClass() == Veterano.class) {
			throw new UpgradeInvalidoException("Upgrade impossivel de ser realizado, usuario ja eh veterano");
		} else if (getXp2() < 1000) {
			throw new UpgradeInvalidoException("Impossivel realizar upgrade, quantidade de x2p insuficiente!");
		}
		setStatusDoUsuario(new Veterano());

	}
}
