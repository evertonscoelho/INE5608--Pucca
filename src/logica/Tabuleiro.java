package logica;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;

public class Tabuleiro {

	protected Posicao posicoes[][] = new Posicao[7][7];
	protected boolean pecaSelecionada;
	protected Posicao posicaoSelecionada;
	protected Peca pecaJogadorUm;
	protected Peca pecaJogadorDois;
	protected boolean partidaEmAndamento;

	public Tabuleiro() {
		for (int linha = 0; linha < 7; linha++) {
			for (int coluna = 0; coluna < 7; coluna++) {
				posicoes[linha][coluna] = new Posicao(coluna, coluna);
			}
		}
	}

	public void setPecaJogadorUm(Peca peca) {
		this.pecaJogadorUm = peca;
	}

	public void setPecaJogadorDois(Peca peca) {
		this.pecaJogadorDois = peca;
	}

	public boolean emAndamento() {
		return partidaEmAndamento;
	}

	public void setAndamento(boolean partidaEmAndamento) {
		this.partidaEmAndamento = partidaEmAndamento;
	}

	public boolean getPecaSelecionada() {
		return pecaSelecionada;
	}

	public Peca getPecaJogadorUm() {
		return pecaJogadorUm;
	}

	public void setPosicaoSelecionada(int linha, int coluna) {
		pecaSelecionada = true;
		posicaoSelecionada = new Posicao(linha, coluna);
	}

	public Posicao getPosicaoSelecionada() {
		return posicaoSelecionada;
	}

	public void retiraPosicaoSelecionada() {
		posicaoSelecionada = null;
		pecaSelecionada = false;
	}

	public void colocaPeca(int linha, int coluna, Peca peca) {
		if (pecaJogadorUm == peca) {
			posicoes[linha][coluna].setPeca(pecaJogadorUm);
		} else{
			posicoes[linha][coluna].setPeca(pecaJogadorDois);
		}
		Jogador jogador = peca.getJogador();
		int aux = jogador.getQuantidadePecas();
		jogador.setQuantidadePecas(++aux);
	}

	public void removePeca(Peca peca, int linha, int coluna) {
		Jogador jogador = peca.getJogador();
		int aux = jogador.getQuantidadePecas();
		jogador.setQuantidadePecas(--aux);
		posicoes[linha][coluna].setPeca(null);
	}

	public void converterPeca(int linha, int coluna, Peca peca) {
		removePeca(posicoes[linha][coluna].getPeca(), linha, coluna);
		colocaPeca(linha, coluna, peca);
	}

	public void posicionePecasIniciais() {
		colocaPeca(0, 0, pecaJogadorUm);
		colocaPeca(0, 6, pecaJogadorUm);
		colocaPeca(6, 0, pecaJogadorDois);
		colocaPeca(6, 6, pecaJogadorDois);

	}

	public int[][] preparaTabuleiroParaInterface() {
		int[][] retorno = new int[7][7];
		for (int linha = 0; linha < 7; linha++) {
			for (int coluna = 0; coluna < 7; coluna++) {
				if (posicoes[linha][coluna].getPeca() == null) {
					retorno[linha][coluna] = 0;
				} else if (posicoes[linha][coluna].getPeca() == pecaJogadorUm) {
					retorno[linha][coluna] = 1;
				} else {
					retorno[linha][coluna] = 2;
				}
			}
		}
		return retorno;
	}

	public String getNomeJogadorUm() {
		return pecaJogadorUm.getJogador().getNome();
	}

	public String getNomeJogadorDois() {
		return pecaJogadorDois.getJogador().getNome();
	}

	public Icon getIconJogadorUmTabuleiro() {
		return pecaJogadorUm.getIconTabuleiro();
	}

	public Icon getIconJogadorDoisTabuleiro() {
		return pecaJogadorDois.getIconTabuleiro();
	}

	public Icon getIconJogadorUmMiniatura() {
		return pecaJogadorUm.getIconMiniatura();
	}

	public Icon getIconJogadorDoisMiniatura() {
		return pecaJogadorDois.getIconMiniatura();
	}

	public int getPontuacaoJogadorUm() {
		return pecaJogadorUm.getJogador().getQuantidadePecas();
	}

	public int getPontuacaoJogadorDois() {
		return pecaJogadorDois.getJogador().getQuantidadePecas();
	}

	public boolean temPecaNaPosicao(int linha, int coluna) {
		return posicoes[linha][coluna].getPeca() != null;
	}

	public boolean posicaoDoJogador(Jogador jogador, int linha, int coluna) {
		boolean retorno = false;
		if (temPecaNaPosicao(linha, coluna)) {
			retorno = posicoes[linha][coluna].getPeca().getJogador() == jogador;
		}
		return retorno;

	}

	public List<Posicao> getAdjacente(int linha, int coluna) {
		List<Posicao> adjacentes = new ArrayList<Posicao>();
		Posicao posicoesAdjacentes;

		for (int linhaAux = linha - 1; linhaAux <= linha + 1; linhaAux++) {
			for (int colunaAux = coluna - 1; colunaAux <= coluna + 1; colunaAux++) {

				if (!(colunaAux == coluna && linhaAux == linha)
						&& (colunaAux > -1 && colunaAux < 7)
						&& (linhaAux > -1 && linhaAux < 7)
						&& (posicoes[linhaAux][colunaAux].getPeca() == null)) {
					posicoesAdjacentes = new Posicao(linhaAux, colunaAux);
					adjacentes.add(posicoesAdjacentes);
				}
			}
		}

		return adjacentes;

	}

	public List<Posicao> getProximaAdjacente(int linha, int coluna) {
		List<Posicao> proximaAdjacente = new ArrayList<Posicao>();
		Posicao posicoesAdjacentes;
		int diferencaColuna;
		int diferencaLinha;

		for (int linhaAux = linha - 2; linhaAux <= linha + 2; linhaAux++) {
			for (int colunaAux = coluna - 2; colunaAux <= coluna + 2; colunaAux++) {
				if (!(colunaAux == coluna && linhaAux == linha)
						&& (colunaAux > -1 && colunaAux < 7)
						&& (linhaAux > -1 && linhaAux < 7)
						&& (posicoes[linhaAux][colunaAux].getPeca() == null)) {
					diferencaLinha = linha - linhaAux;
					diferencaLinha = Math.abs(diferencaLinha);
					diferencaColuna = coluna - colunaAux;
					diferencaColuna = Math.abs(diferencaColuna);
					if (diferencaLinha > 1 || diferencaColuna > 1) {
						posicoesAdjacentes = new Posicao(linhaAux, colunaAux);
						proximaAdjacente.add(posicoesAdjacentes);
					}
				}
			}
		}

		return proximaAdjacente;

	}

	public List<Posicao> getPecasAdjacentes(int linha, int coluna) {
		List<Posicao> pecasAdjacentes = new ArrayList<Posicao>();
		Posicao posicoesAdjacentes;

		for (int linhaAux = linha - 1; linhaAux <= linha + 1; linhaAux++) {
			for (int colunaAux = coluna - 1; colunaAux <= coluna + 1; colunaAux++) {

				if (!(colunaAux == coluna && linhaAux == linha)
						&& (colunaAux > -1 && colunaAux < 7)
						&& (linhaAux > -1 && linhaAux < 7)
						&& (posicoes[linhaAux][colunaAux].getPeca() != null)) {
					posicoesAdjacentes = new Posicao(linhaAux, colunaAux);
					pecasAdjacentes.add(posicoesAdjacentes);
				}
			}
		}

		return pecasAdjacentes;

	}

	public Peca getPecaJogadorDois() {
		return pecaJogadorDois;
	}

/*	public void retiraPecas() {
		for (int linha = 0; linha < 7; linha++) {
			for (int coluna = 0; coluna < 7; coluna++) {
			  posicoes[linha][coluna].setPeca(null) ;
			}
		}		
	}

	public void zeraPontuacao() {
         if(pecaJogadorUm != null){
		   Jogador jogador = pecaJogadorUm.getJogador();
            jogador.setQuantidadePecas(0);
         }   
	}*/
	
	
	
	
	
}
