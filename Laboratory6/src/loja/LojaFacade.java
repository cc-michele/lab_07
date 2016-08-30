package loja;

import excecoes.StringInvalidaException;

public class LojaFacade {
	private LojaController lojaController;
	
	public LojaFacade(){
		lojaController = new LojaController();
	}

	public boolean adicionaUsuario(String nome, String login) throws StringInvalidaException{
		return lojaController.adicionaUsuario(nome, login);
		
	}
	
	public boolean adicionaCredito(String login, double valor){
		return lojaController.adicionaCredito(login, valor);
	}
	
	public boolean vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser){
		return lojaController.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser);
		
	}
	/*	
	

	public String imprimiInforção uma entidade tem um membro seu (atributmacoes(String nomeLogin) throws Exception{
		try{
			loja.imprimiInformacoes(nomeLogin);
		}catch (Exception e ){
			System.out.println(e.getMessage());
		}
		return null;
	}*/
	

}
