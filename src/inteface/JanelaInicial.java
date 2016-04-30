package inteface;

import inteface.painel.Painel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controlador.Controlador;

public class JanelaInicial extends JFrame implements ActionListener {

	protected Controlador controlador;
	protected Painel painel;
	protected Menu menu;
	
	public JanelaInicial(Controlador controlador){
		super("Pucca");
		menu = new Menu(this);
		setJMenuBar(menu);
		this.controlador = controlador;
		painel = new Painel(this);
		setContentPane(painel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 550));
		setResizable(false); 
		pack();
		setLocationRelativeTo(null);
	}

	public void interaja() {
		setVisible(true);
    }

	public void conectou() {
		painel = new Painel(this);
		setContentPane(painel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		// Verifica qual a opcaoo do menu foi clicada
		case "Conectar":
			solicitaConexao();
			break;

		case "Jogar":
			solicitaInicio();
			break;

		case "Desconectar":
			solicitarDesconexao();
			break;

		case "Sair":
			controlador.desconectar();
			this.dispose();
			break;

		}
	}

	public void solicitarDesconexao() {
		controlador.desconectar();
		
	}
	
	private void solicitaInicio(){
		controlador.iniciarJogo();
	}

	public void mostraMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	private void solicitaConexao() {
		String nome = solicitaNome();
		if (nome != null) {
			if (!(nome.trim().equals(""))) {

				controlador.conectar(nome);
			} else {
				mostraMensagem("Coloque um nome");
			}
		}

	}

	private String solicitaNome() {
		return JOptionPane.showInputDialog("Digite seu nome");
	}

	public void aguardandoInicio() {
		painel.aguardandoInicio();
		
		
	}

	public void setDadosJogadores(String jogadorUm, String jogadorDois, Icon iconJogadorUmTabuleiro, Icon iconJogadorDoisTabuleiro,Icon iconJogadorUmMiniatura, Icon iconJogadorDoisMiniatura) {
		painel.setDadosJogador(jogadorUm, jogadorDois, iconJogadorUmTabuleiro, iconJogadorDoisTabuleiro, iconJogadorUmMiniatura, iconJogadorDoisMiniatura);		
	}
	
	public void atualizaPontuacao( int pontuacaoJogadorUm, int pontuacaoJogadorDois){
        String sPontuacaoJogadorUm = ""+pontuacaoJogadorUm;
        String sPontuacaoJogadorDois = ""+pontuacaoJogadorDois;
		painel.atualizaPontuacao(sPontuacaoJogadorUm, sPontuacaoJogadorDois);
	}

	public void imprimeTabuleiro(int[][] tabuleiro) {
		   painel.imprimeTabuleiro(tabuleiro);
          remove(painel);
          this.setContentPane(painel);
	}

	public void posicaoClicada(int linha, int coluna) {
        controlador.posicaoClicada(linha, coluna);
		
	}

	public void criaTabuleiro() {
		painel.criaTabuleiro();
		
	}

	public void setMensagemPainel(String mensagem) {
		painel.setMensagem(mensagem);
		
	}

	public void imprimeAdjacente(int linha, int coluna) {
       painel.setAdjacente(linha, coluna);
	}

	public void imprimeProximaAdjacente(int linha, int coluna) {
        painel.setProximaAdjacente(linha, coluna);		
	}

	
	
}
