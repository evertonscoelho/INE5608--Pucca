package logica;

import javax.swing.Icon;

public class Peca {

	protected Icon iconTabuleiro;
	protected Jogador jogador;
	protected Icon iconMiniatura;

	public Peca(Jogador jogador, Icon iconTabuleiro, Icon iconMiniatura) {
      this.iconTabuleiro = iconTabuleiro;
      this.iconMiniatura = iconMiniatura;
      this.jogador = jogador;
	}

	public Icon getIconTabuleiro() {
		return iconTabuleiro;
	}
	
	public Icon getIconMiniatura() {
		return iconMiniatura;
	}

	public Jogador getJogador() {
		return jogador;
	}
	

}

