/* 115111882 - Michele Santos Memoria: LAB 7 - Turma 3 */
package jogo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JogoTest {
	private Jogo meuJogo;
	private String nomeJogo = "Mario Kart";
	private double precoJogo = 30.00;
	
	@Before
	public void setup() throws Exception{
		this.meuJogo = new Plataforma(nomeJogo, precoJogo);
	}
	@Test
	public void testGetNome(){
		Assert.assertEquals(nomeJogo, meuJogo.getNome());
	}
	@Test 
	public void testGetPreco(){
		Assert.assertEquals(precoJogo, meuJogo.getPreco(), 2);
	}
}
