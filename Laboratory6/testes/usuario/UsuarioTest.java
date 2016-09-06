package usuario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;
import jogo.Jogo;
import jogo.Rpg;

public class UsuarioTest {

	private Usuario meuUsuario;
	private String nomeUsuario = "Alice";
	private String login = "alice.s";
	private double credito = 100;
	private Jogo jogo;

	@Before
	public void setup() throws Exception {
		this.meuUsuario = new Usuario(nomeUsuario, login);
		this.meuUsuario.setCredito(credito);
		this.jogo = new Rpg("Mario", 10);

	}

	@Test
	public void testcompraJogo() throws Exception {
		meuUsuario.compraJogo(jogo);
		Assert.assertTrue(meuUsuario.getMeusJogos().contains(jogo));
		Assert.assertEquals(meuUsuario.getCredito(), 90, 2);
	}

	@Test(expected = Exception.class)
	public void testUpgradeInvalidoException() throws UpgradeInvalidoException {
		meuUsuario.upgrade();
	}
	
	@Test
	public void testUsuarioIguas() throws StringInvalidaException{
		Usuario user = new Usuario("Alice", "alice.s");
		Assert.assertEquals(meuUsuario, user);
	}

}
