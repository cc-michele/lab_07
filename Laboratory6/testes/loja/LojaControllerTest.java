package loja;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;
import jogo.Jogo;
import jogo.Plataforma;
import junit.framework.Assert;
import usuario.Noob;
import usuario.Usuario;

public class LojaControllerTest {

	private Jogo meuJogo;
	private String nomeJogo = "Mario Kart";
	private double precoJogo = 30.00;
	private Usuario meuUsuario;
	private String nomeUsuario = "Alice";
	private String login = "alice.s";
	private double credito = 100;
	private LojaController loja;

	@Before
	public void setup() throws Exception {
		this.loja = new LojaController();

	}

	@Test
	public void testAdicionaUsuario() throws StringInvalidaException {
		try {
			loja.adicionaUsuario("", "login");
		} catch (Exception e) {
			Assert.assertEquals("Nome nao pode ser nulo ou vazio.", e.getMessage());
		}
		Assert.assertTrue(loja.adicionaUsuario(nomeUsuario, login));
	}
	
	@Test
	public void testAdicionaCredito() throws StringInvalidaException{
		loja.adicionaUsuario(nomeUsuario, login);
		loja.adicionaCredito(login, credito);
		Assert.assertEquals(100, loja.confereCredito(login), 2);
	}
	@Test
	public void testVendeJogo() throws StringInvalidaException{
		loja.adicionaUsuario(nomeUsuario, login);
		loja.adicionaCredito(login, credito);
		loja.vendeJogo(nomeJogo, precoJogo, "Online", "Rpg", login);
		Assert.assertEquals(73, loja.buscaUsuario(login).getCredito(), 2);
		
	}
	@Test
	public void testGetX2p() throws StringInvalidaException{
		loja.adicionaUsuario(nomeUsuario, login);
		loja.adicionaCredito(login, credito);
		loja.vendeJogo(nomeJogo, precoJogo, "Online", "Rpg", login);
		Assert.assertEquals(300, loja.getX2p(login));
	}
	@Test(expected = Exception.class)
	public void testUpgrade() throws Exception {
		loja.adicionaUsuario(nomeUsuario, login);
		loja.adicionaCredito(login, credito);
		loja.vendeJogo(nomeJogo, precoJogo, "Online", "Plataforma", login);
		loja.upgrade(login);

	}


}
