package inteface.painel;

import inteface.JanelaInicial;

import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Painel extends JPanel{

	protected JanelaInicial janelaInicial;
	protected JLabel lNomeJogadorUm;
	protected JLabel lNomeJogadorDois;
	protected JLabel lPontuacaoJogadorUm;
	protected JLabel lPontuacaoJogadorDois;
	protected JLabel lMensagem;
	protected JLabel lIconeJogadorUm;
	protected JLabel lIconeJogadorDois;
	protected Icon vazia;
	protected Icon adjacente;
	protected Icon proximaAdjacente;
	protected Icon iconJogadorUm;
	protected Icon iconJogadorDois;
	protected JLabel tabuleiro[][] = new JLabel[7][7];

	public Painel(JanelaInicial janelaInicial) {
		setBorder(BorderFactory.createTitledBorder(janelaInicial.loadTranslate("Pucca")));
		setSize(150, 150);
		this.janelaInicial = janelaInicial;
		this.setLayout(null);
		incializaComponentes();
	}

	public void criaTabuleiro() {
		int y = 70;
		
		for (int linha = 0; linha < 7; linha++) {
			int x = 50;
			for (int coluna = 0; coluna < 7; coluna++) {
				final Integer auxLinha = linha;
				final Integer auxColuna = coluna;
				tabuleiro[linha][coluna] = new JLabel();
				tabuleiro[linha][coluna].setBounds(new Rectangle(x, y, 50, 50));
				tabuleiro[linha][coluna].setIcon(vazia);
				x += 50;
				this.add(tabuleiro[linha][coluna]);
				tabuleiro[linha][coluna]
						.addMouseListener(new java.awt.event.MouseAdapter() {
							public void mouseClicked(java.awt.event.MouseEvent e) {
								selecionaPosicao(auxLinha, auxColuna);
							}
						});
			}
			y += 50;
			
		}
	}

	private void selecionaPosicao(int linha, int coluna) {
        janelaInicial.posicaoClicada(linha, coluna); 

	}

	public void setAdjacente(int linha, int coluna) {
		tabuleiro[linha][coluna].setIcon(adjacente);
	}
	
	public void setProximaAdjacente(int linha, int coluna) {
		tabuleiro[linha][coluna].setIcon(proximaAdjacente);
	}

	private void incializaComponentes() {
		ClassLoader cl = this.getClass().getClassLoader();
		vazia = new ImageIcon(cl.getResource("posicaoVazia.png"));
		adjacente = new ImageIcon(cl.getResource("posicaoAdjacente.png"));
		proximaAdjacente = new ImageIcon(
				cl.getResource("posicaoProximaAdjacente.png"));
		
		lMensagem = new JLabel();
		lMensagem.setBounds(new Rectangle(10, 0, 200, 60));
		this.add(lMensagem);
		
		lNomeJogadorUm = new JLabel();
		lNomeJogadorUm.setBounds(new Rectangle(30, 0, 200, 100));
		this.add(lNomeJogadorUm);
		
		lNomeJogadorDois = new JLabel();
		lNomeJogadorDois.setBounds(new Rectangle(320, 420, 200, 60));
		this.add(lNomeJogadorDois);
		
		lPontuacaoJogadorUm = new JLabel();
		lPontuacaoJogadorUm.setBounds(new Rectangle(30, 0, 50, 125));
		this.add(lPontuacaoJogadorUm);
		
		lPontuacaoJogadorDois = new JLabel();
		lPontuacaoJogadorDois.setBounds(new Rectangle(320, 433, 100, 60));
		this.add(lPontuacaoJogadorDois);
		
		lIconeJogadorUm = new JLabel();
		
		lIconeJogadorUm.setBounds(new Rectangle(9, 46, 20, 20));
		this.add(lIconeJogadorUm);

		lIconeJogadorDois = new JLabel();
		lIconeJogadorDois.setBounds(new Rectangle(300, 446, 20, 20));
		this.add(lIconeJogadorDois);
	}

	public void setMensagem(String mensagem) {
		lMensagem.setText(mensagem);
	}

	public void setNomeJogadorUm(String nome) {
		lNomeJogadorUm.setText(nome);
	}

	public void setNomeJogadorDois(String nome) {
		lNomeJogadorDois.setText(nome);
	}

	public void aguardandoInicio() {
		setMensagem(janelaInicial.loadTranslate("Aguardando adversario"));
	}
	

	public void setDadosJogador(String jogadorUm, String jogadorDois,
			Icon iconJogadorUmTabuleiro, Icon iconJogadorDoisTabuleiro, Icon iconJogadorUmMiniatura, Icon iconJogadorDoisMiniatura) {
		lNomeJogadorUm.setText(jogadorUm);
		lNomeJogadorDois.setText(jogadorDois);
		this.iconJogadorUm = iconJogadorUmTabuleiro;
		this.iconJogadorDois = iconJogadorDoisTabuleiro;
		this.lIconeJogadorDois.setIcon(iconJogadorDoisMiniatura);
		this.lIconeJogadorUm.setIcon(iconJogadorUmMiniatura);

	}

	public void imprimeTabuleiro(int[][] tabuleiroInterface) {
		for (int linha = 0; linha < 7; linha++) {
			for (int coluna = 0; coluna < 7; coluna++) {
				if(tabuleiroInterface[linha][coluna] == 0){
					tabuleiro[linha][coluna].setIcon(vazia);
				}else if(tabuleiroInterface[linha][coluna] == 1){
					tabuleiro[linha][coluna].setIcon(iconJogadorUm);
				}else{
					tabuleiro[linha][coluna].setIcon(iconJogadorDois);
				}
			}
		}
	}

	public void atualizaPontuacao(String pontuacaoJogadorUm,
			String pontuacaoJogadorDois) {
		lPontuacaoJogadorUm.setText(pontuacaoJogadorUm);
		lPontuacaoJogadorDois.setText(pontuacaoJogadorDois);
		

	}

		
		

}

