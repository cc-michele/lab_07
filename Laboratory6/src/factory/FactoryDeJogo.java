/* 115111882 - Michele Santos Memoria: LAB 7 - Turma 3 */
package factory;

import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.TipoJogoInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;
import jogo.Luta;
import jogo.Plataforma;
import jogo.Rpg;

public class FactoryDeJogo {
	
	public Jogo criaJogo(String nome, double preco, Set<Jogabilidade> tiposJogabilidades, String estilo)
			throws StringInvalidaException, PrecoInvalidoException, TipoJogoInvalidoException {
		
		//String estilo = estiloJogo.toLowerCase();
		if (estilo.equalsIgnoreCase("rpg")) {
			return new Rpg(nome, preco, tiposJogabilidades);
		} else if (estilo.equalsIgnoreCase("plataforma")) {
			return new Plataforma(nome, preco, tiposJogabilidades);
		} else if (estilo.equalsIgnoreCase("luta")) {
			return new Luta(nome, preco, tiposJogabilidades);
		} else {
			throw new TipoJogoInvalidoException("Tipo de jogo invalido");
		}
		
	}

}
