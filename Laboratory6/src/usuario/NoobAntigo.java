package usuario;

import java.util.Iterator;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;
import usuario.Usuario;

public class NoobAntigo extends Usuario {
	public static final double DESCONTO_NOOB = 0.9;

	public NoobAntigo(String nome, String login) throws StringInvalidaException {
		super(nome, login);
		setX2p(0);
	}

	@Override
	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou) {
		Jogo jogo = this.buscaJogo(nomeJogo);
		if (jogo.getJogabilidades().contains(Jogabilidade.OFFLINE)) {
			setX2p((getXp2() + 30)  + jogo.registraJogada(scoreObtido, zerou)); 
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.MULTIPLAYER)) {
			setX2p( (getXp2() + (10))  + jogo.registraJogada(scoreObtido, zerou));
		}	
	}
	
	public void punir(String nomeJogo, int scoreObtido, boolean zerou){
		Jogo jogo = buscaJogo(nomeJogo);
		if (jogo.getJogabilidades().contains(Jogabilidade.ONLINE)) {
			setX2p( (getXp2() - 10) + jogo.registraJogada(scoreObtido, zerou)); 
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.COMPETITIVO)) {
			setX2p( (getXp2() - 20) + jogo.registraJogada(scoreObtido, zerou));
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.COOPERATIVO)) {
			setX2p( (getXp2() - 50)  + jogo.registraJogada(scoreObtido, zerou));
		}

	}

	@Override
	public void compraJogo(Jogo jogo) throws Exception {
		double custo = jogo.getPreco() * DESCONTO_NOOB;
		if (custo > this.getCredito()) {
			throw new ValorInvalidoException("Credito insuficiente para realizar a compra.");
		} else {
			int parteInteira = (int) (jogo.getPreco() - (jogo.getPreco() % 1));
			int bonusXp = parteInteira * 10;
			setX2p(getXp2() + bonusXp);
			setCredito(getCredito() - custo);
			this.cadastraJogo(jogo);

		}

	}

	@Override
	public String toString() {
		String myString = this.getLogin() + FIM_DE_LINHA;
		myString += this.getNome() + " - Jogador Noob" + FIM_DE_LINHA;
		myString += "Lista de Jogos:" + FIM_DE_LINHA;

		Iterator itr = getMeusJogos().iterator();
		while (itr.hasNext()) {
			Jogo j = (Jogo) itr.next();
			myString += j.toString();
		}
		myString += FIM_DE_LINHA;
		myString += "Total de pre�o dos jogos: R$ " + this.calculaPrecoTotal() + FIM_DE_LINHA;
		myString += "--------------------------------------------";
		return myString;
	}

}