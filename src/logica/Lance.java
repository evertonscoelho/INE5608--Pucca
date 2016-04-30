package logica;

import java.util.List;



public class Lance {

	protected Posicao posicaoSelecionada;
	protected int linha;
	protected int coluna;
	protected Peca peca;
	protected Tabuleiro tabuleiro;

	public Lance(Posicao posicaoSelecionada, int linha, int coluna, Peca peca,
			Tabuleiro tabuleiro) {
		this.posicaoSelecionada = posicaoSelecionada;
		this.coluna = coluna;
		this.linha = linha;
		this.peca = peca;
		this.tabuleiro = tabuleiro;

	}

	public boolean realizaJogada() {
	   return tratarLance();
	}

	private boolean tratarLance() {
		boolean retorno = false;
		boolean realizouJogada = false;
		boolean posicaoEhAdjacente = posicaoClicadaAdjacente();
		boolean posicaoEhProximaAdjacente = posicaoClicadaProximaAdjacente();
		if (posicaoEhAdjacente) {
			realizouJogada = true;
			retorno = true;
		} else if (posicaoEhProximaAdjacente) {
			tabuleiro.removePeca(peca, posicaoSelecionada.linha,
					posicaoSelecionada.coluna);
			realizouJogada = true;
			retorno = true;
		}
		if(realizouJogada){
			tabuleiro.colocaPeca(linha, coluna, peca);
			verificaConverter();
		}
		return retorno;
	}

	private void verificaConverter() {
		List<Posicao> pecasAdjacentes = tabuleiro.getPecasAdjacentes(linha, coluna);
		for (Posicao posicaoPecasAdjacente : pecasAdjacentes) {
			tabuleiro.converterPeca(posicaoPecasAdjacente.getLinha(), posicaoPecasAdjacente.getColuna(), peca);
		}
	}

	private boolean posicaoClicadaProximaAdjacente() {
		boolean retorno = false;
		List<Posicao> proximaAdjacentes = tabuleiro.getProximaAdjacente(posicaoSelecionada.getLinha(), posicaoSelecionada.getColuna());
		for (Posicao posicaoProximaAdjacente : proximaAdjacentes) {
			if (posicaoProximaAdjacente.getLinha() == linha
					&& posicaoProximaAdjacente.getColuna() == coluna) {
				retorno = true;
			}
		}
		return retorno;
	}

	private boolean posicaoClicadaAdjacente() {
		boolean retorno = false;
		List<Posicao> adjacentes = tabuleiro.getAdjacente(posicaoSelecionada.getLinha(), posicaoSelecionada.getColuna());
		for (Posicao posicaoAdjacente : adjacentes) {
			if (posicaoAdjacente.getLinha() == linha
					&& posicaoAdjacente.getColuna() == coluna) {
				retorno = true;
			}
		}
		return retorno;

	}

}
