/* 115111882 - Michele Santos Memoria: LAB 7 - Turma 3 */
package usuario;

import jogo.Jogo;

public interface TipoDeUsuarioIF {
	
	public abstract int calculaX2P (Jogo jogo);

	public abstract int recompensar(Jogo jogo);

	public abstract int punir(Jogo jogo);
	
	public abstract double calculaDesconto(Jogo jogo);


}
