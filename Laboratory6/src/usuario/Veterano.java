package usuario;

import jogo.Jogabilidade;
import jogo.Jogo;

public class Veterano implements TipoDeUsuarioIF {
	public static final double DESCONTO_VETERANO = 0.8;	
	public static final int BONUS_VETERANO = 15;


	@Override
	public double calculaDesconto(Jogo jogo) {
		return jogo.getPreco() * DESCONTO_VETERANO;
	}
	@Override
	public int calculaX2P(Jogo jogo) {
		int parteInteira =(int)( jogo.getPreco() - (jogo.getPreco() % 1));
		int bonusXp =  parteInteira * BONUS_VETERANO;
		return bonusXp;
	}

	
	@Override
	public int recompensar(Jogo jogo) {
		int recompensar = 0;
		if (jogo.getJogabilidades().contains(Jogabilidade.ONLINE)) {
			recompensar += 20; 
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.COOPERATIVO)) {
			recompensar += 20; 
		}	
		return recompensar;
	}
	
	public int punir(Jogo jogo){
		int punir = 0;
		
		if (jogo.getJogabilidades().contains(Jogabilidade.OFFLINE)) {
			punir -= 20;
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.COMPETITIVO)) {
			punir -= 20; 
		}
		return punir;

	}

}
