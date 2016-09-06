/* 115111882 - Michele Santos Memoria: LAB 7 - Turma 3 */
package usuario;

import jogo.Jogabilidade;
import jogo.Jogo;

public class Noob implements TipoDeUsuarioIF {

	public static final double DESCONTO_NOOB = 0.9;
	public static final int BONUS_NOOB = 10; 
	
	@Override
	public double calculaDesconto(Jogo jogo) {
		return jogo.getPreco() * DESCONTO_NOOB;
	}
	
	@Override
	public int calculaX2P(Jogo jogo){
		int parteInteira =(int)( jogo.getPreco() - (jogo.getPreco() % 1));
		int bonusXp =  parteInteira * BONUS_NOOB;
		return bonusXp;
	}

	@Override
	public int recompensar(Jogo jogo) {
		int recompensar = 0;
		if (jogo.getJogabilidades().contains(Jogabilidade.OFFLINE)) {
			recompensar += 30; 
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.MULTIPLAYER)) {
			recompensar += 10;
		}	
		return recompensar;
	}

	@Override
	public int punir(Jogo jogo) {
		int punir = 0;
		if (jogo.getJogabilidades().contains(Jogabilidade.ONLINE)) {
			punir -= 10; 
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.COMPETITIVO)) {
			punir -= 20;
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.COOPERATIVO)) {
			punir -= 50;
		}
		return punir;
	}



}
