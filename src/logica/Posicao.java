package logica;

public class Posicao {
	
	protected Peca peca;
	protected int linha;
	protected int coluna;
	
	public Posicao(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
		
	}
	
	public void setPeca(Peca peca){
		this.peca = peca;
	}
	
	public boolean posicaoOcupada(){
		return peca != null;
	}
	
	public int getLinha(){
		return linha;
	}
	
	public int getColuna(){
		return coluna;
	}
	
	public Peca getPeca(){
		return peca;
	}

}
