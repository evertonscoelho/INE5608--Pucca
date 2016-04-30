package logica;


public class Jogador {
	 
	
	protected String nome;
	protected int quantidadePecas;
	protected boolean habitadoParaJogar;
	protected boolean conectado;
	protected boolean vencedor;
	
	
	

	public Jogador(String nome) {
        this.nome = nome;
	}

	public Jogador() {

	}

	public String getNome() {
		return nome;
	}
	
	public int getQuantidadePecas(){
		return quantidadePecas;
	}
	
	public void setQuantidadePecas(int quantidadePecas){
		this.quantidadePecas = quantidadePecas; 
	}
	
	public boolean getVencedor(){
		return vencedor;
	}
	
	public void setVencedor(boolean vencedor){
		this.vencedor = vencedor; 
	}
	
	public void setNome(String nome){
		this.nome = nome; 
	}

	public void habilita() {
	   habitadoParaJogar = true;
	}

	public void desabilita() {
		habitadoParaJogar = false;		
	}

	public boolean habilitado() {
		return habitadoParaJogar;
	}
	
	public boolean verificaConexao(){
        return conectado;		
	}
	
	public void setConectado(boolean conectado){
		this.conectado = conectado;
	}



}
