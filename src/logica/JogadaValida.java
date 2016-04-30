package logica;

import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaValida implements Jogada{

	
	protected int linhaFinal;
	protected int colunaFinal;
	protected int linhaInicial;
	protected int colunaInicial;
	
	public int getLinhaFinal() {
		return linhaFinal;
	}

	public void setLinhaFinal(int linhaFinal) {
		this.linhaFinal = linhaFinal;
	}

	public int getColunaFinal() {
		return colunaFinal;
	}

	public void setColunaFinal(int colunaFinal) {
		this.colunaFinal = colunaFinal;
	}

	public int getLinhaInicial() {
		return linhaInicial;
	}

	public void setLinhaInicial(int linhaInicial) {
		this.linhaInicial = linhaInicial;
	}

	public int getColunaInicial() {
		return colunaInicial;
	}

	public void setColunaInicial(int colunaInicial) {
		this.colunaInicial = colunaInicial;
	}

	public JogadaValida(int linhaFinal, int colunaFinal, int linhaInicial,
			int colunaInicial) {
		super();
		this.linhaFinal = linhaFinal;
		this.colunaFinal = colunaFinal;
		this.linhaInicial = linhaInicial;
		this.colunaInicial = colunaInicial;
	}
	
	
}
