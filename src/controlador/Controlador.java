package controlador;

import inteface.JanelaInicial;
import inteface.JanelaSelecaoIdioma;
import internacionalizacao.Internacionalizacao;

import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import logica.JogadaValida;
import logica.Jogador;
import logica.Lance;
import logica.Peca;
import logica.Posicao;
import logica.Tabuleiro;
import netGames.AtorNetGames;

public class Controlador {

	protected JanelaInicial janelaInicial;
	protected Jogador jogador;
	protected AtorNetGames atorNetGames;
	protected Tabuleiro tabuleiro;
	protected Lance lance;
	private Internacionalizacao internacionalizacao;

	public void inicie(String localeFile) {
		String locale = "";
		switch (localeFile) {
		case "Franc\u00EAs":
			locale = "fr_fr";
			break;
		case "Ingl\u00EAs":
			locale = "en_es";
			break;
		case "Portugu\u00EAs":
			locale = "pt_br";
			break;
		default:
			locale = "pt_br";
			break;
		}
		internacionalizacao = new Internacionalizacao(locale);
		janelaInicial = new JanelaInicial(this);
		tabuleiro = new Tabuleiro();
		janelaInicial.interaja();
		jogador = new Jogador();
	}

	public String getValue(String key) {
		try {
			return internacionalizacao.getTranslate(key);
		} catch (Exception e) {
			System.out.println("Não localizou a String no arquivo");
		}
		return key;
	}

	public void initIdioma() {
		JanelaSelecaoIdioma janelaSelecaoIdioma = new JanelaSelecaoIdioma(this);
		janelaSelecaoIdioma.setVisible(true);
	}

	public void iniciarJogo() {

		boolean conexao = jogador.verificaConexao();
		if (conexao) {
			boolean andamento = tabuleiro.emAndamento();
			if (!andamento) {
				atorNetGames.iniciarNovaPartidaServidor();
				tabuleiro.setAndamento(true);
				janelaInicial.aguardandoInicio();
			} else {
				janelaInicial
						.mostraMensagem(getValue("Ja possui uma partida em andamento"));
			}

		} else {
			janelaInicial
					.mostraMensagem(getValue("Ainda nao esta conectado com o servidor"));
		}

	}

	public void conectar(String nome) {
		jogador.setNome(nome);
		atorNetGames = new AtorNetGames(this);
		String problema = atorNetGames.conectar(nome, "venus.inf.ufsc.br");
		// String problema = atorNetGames.conectar(nome, "localhost");
		if (problema != null) {
			janelaInicial.mostraMensagem(problema);
		} else {
			jogador.setConectado(true);
			janelaInicial.mostraMensagem(getValue("Conectado com sucesso"));
		}

	}

	public void receberJogada(JogadaValida jogada) {
		int linhaInicial = 6 - jogada.getLinhaInicial();
		int linhaFinal = 6 - jogada.getLinhaFinal();
		int colunaInicial = 6 - jogada.getColunaInicial();
		int colunaFinal = 6 - jogada.getColunaFinal();
		Posicao posicaoSelecionada = new Posicao(linhaInicial, colunaInicial);
		Peca pecaJogadorDois = tabuleiro.getPecaJogadorDois();
		lance = new Lance(posicaoSelecionada, linhaFinal, colunaFinal,
				pecaJogadorDois, tabuleiro);
		lance.realizaJogada();
		int[][] tabuleiroInterface = tabuleiro.preparaTabuleiroParaInterface();
		janelaInicial.imprimeTabuleiro(tabuleiroInterface);
		tabuleiro.retiraPosicaoSelecionada();
		int pontuacaoJogadorUm = tabuleiro.getPontuacaoJogadorUm();
		int pontuacaoJogadorDois = tabuleiro.getPontuacaoJogadorDois();
		janelaInicial.atualizaPontuacao(pontuacaoJogadorUm,
				pontuacaoJogadorDois);

		boolean fimDaPartida = verificaFimDaPartida();
		if (fimDaPartida) {
			finalizaPartida();
			verificaVencedor();
		} else {
			jogador.habilita();
			janelaInicial.setMensagemPainel(getValue("Sua vez"));
		}
	}

	public void posicaoClicada(int linha, int coluna) {
		boolean andamento = tabuleiro.emAndamento();
		boolean temPecaNaPosicao = tabuleiro.temPecaNaPosicao(linha, coluna);
		boolean posicaoDoJogador = tabuleiro.posicaoDoJogador(jogador, linha,
				coluna);
		boolean possuiPecaSelecionada = tabuleiro.getPecaSelecionada();
		if (andamento) {
			boolean habilitado = jogador.habilitado();
			// Se jogador esta habilitado para jogar
			if (habilitado) {
				// Se jogador ja possui posicao selecionada
				if (!temPecaNaPosicao) {
					if (possuiPecaSelecionada) {
						Posicao posicaoSelecionada = tabuleiro
								.getPosicaoSelecionada();
						Peca pecaJogadorUm = tabuleiro.getPecaJogadorUm();
						lance = new Lance(posicaoSelecionada, linha, coluna,
								pecaJogadorUm, tabuleiro);
						boolean realizaJogada = realizaJogada();
						if (realizaJogada) {
							JogadaValida jogada = new JogadaValida(linha,
									coluna, tabuleiro.getPosicaoSelecionada()
											.getLinha(), tabuleiro
											.getPosicaoSelecionada()
											.getColuna());
							passarAVez(jogada);
						} else {
							janelaInicial
									.mostraMensagem(getValue("Atencao, selecione uma posicao valida"));
						}

					} else {
						janelaInicial
								.mostraMensagem(getValue("Atencao, selecione uma peca"));
					}
					// Se a posicao selecionada for uma posicao do jogador,
					// troca de
					// peca selecionada

				} else if (posicaoDoJogador) {
					selecionarPeca(linha, coluna);
				} // Se a posicao Selecionada nao for do jogaodr
				else {
					janelaInicial.mostraMensagem(getValue("Peca do adversario"));
				}
			} else {
				janelaInicial.mostraMensagem(getValue("Vez do adversario"));
			}
			// Se partida nao esta em andamento
		} else {
			janelaInicial.mostraMensagem(getValue("Partida nao esta em andamento"));
		}

	}

	private boolean realizaJogada() {
		return lance.realizaJogada();
	}

	private void selecionarPeca(int linha, int coluna) {
		tabuleiro.setPosicaoSelecionada(linha, coluna);
		int[][] tabuleiroInterface = tabuleiro.preparaTabuleiroParaInterface();
		janelaInicial.imprimeTabuleiro(tabuleiroInterface);
		List<Posicao> adjacentes = tabuleiro.getAdjacente(linha, coluna);
		imprimeAdjacentes(adjacentes);
		imprimeProximaAdjacente(tabuleiro.getProximaAdjacente(linha, coluna));

	}

	private void passarAVez(JogadaValida jogada) {
		jogador.desabilita();
		atorNetGames.enviarJogada(jogada);
		janelaInicial.setMensagemPainel(getValue("Aguardando adversario"));
		janelaInicial.imprimeTabuleiro(tabuleiro
				.preparaTabuleiroParaInterface());
		janelaInicial.atualizaPontuacao(tabuleiro.getPontuacaoJogadorUm(),
				tabuleiro.getPontuacaoJogadorDois());
		tabuleiro.retiraPosicaoSelecionada();
		boolean fim = verificaFimDaPartida();
		if (fim) {
			finalizaPartida();
			verificaVencedor();
		}

	}

	public void iniciarPartida(int posicaoDeJogada) {
		// Metodo para iniciar uma nova partida, onde recebe um int posicao
		// informando quem comeca o jogo
		// esvaziar();
		String nomeAdversario;
		if (posicaoDeJogada == 1) {
			jogador.habilita();
			nomeAdversario = atorNetGames.obterNomeAdversario(2);
			janelaInicial.setMensagemPainel(getValue("Sua vez!"));
		} else {
			jogador.desabilita();
			nomeAdversario = atorNetGames.obterNomeAdversario(1);
			janelaInicial.setMensagemPainel(getValue("Aguardando oponente!"));
		}

		inicializaJogadores(nomeAdversario);
		janelaInicial.criaTabuleiro();
		tabuleiro.posicionePecasIniciais();
		janelaInicial.setDadosJogadores(tabuleiro.getNomeJogadorUm(),
				tabuleiro.getNomeJogadorDois(),
				tabuleiro.getIconJogadorUmTabuleiro(),
				tabuleiro.getIconJogadorDoisTabuleiro(),
				tabuleiro.getIconJogadorUmMiniatura(),
				tabuleiro.getIconJogadorDoisMiniatura());
		janelaInicial.atualizaPontuacao(tabuleiro.getPontuacaoJogadorUm(),
				tabuleiro.getPontuacaoJogadorDois());
		int[][] tabuleiroInterface = tabuleiro.preparaTabuleiroParaInterface();
		janelaInicial.imprimeTabuleiro(tabuleiroInterface);

	}

	/*
	 * private void esvaziar() { tabuleiro.retiraPecas();
	 * tabuleiro.setPecaJogadorDois(null); tabuleiro.zeraPontuacao();
	 * 
	 * }
	 */

	private void inicializaJogadores(String nomeAdversario) {
		ClassLoader cl = this.getClass().getClassLoader();
		Icon jogadorUm = new ImageIcon(cl.getResource("jogadorUmTabuleiro.png"));
		Icon jogadorUmMiniatura = new ImageIcon(
				cl.getResource("jogadorUmMiniatura.png"));

		Peca minhaPeca = new Peca(jogador, jogadorUm, jogadorUmMiniatura);

		Jogador adversario = new Jogador(nomeAdversario);
		Icon jogadorDois = new ImageIcon(
				cl.getResource("jogadorDoisTabuleiro.png"));
		Icon jogadorDoisMiniatura = new ImageIcon(
				cl.getResource("jogadorDoisMiniatura.png"));

		Peca pecaAdversario = new Peca(adversario, jogadorDois,
				jogadorDoisMiniatura);

		tabuleiro.setPecaJogadorUm(minhaPeca);
		tabuleiro.setPecaJogadorDois(pecaAdversario);

	}

	public void desconectar() {
		boolean conexao = jogador.verificaConexao();
		if (conexao) {
			boolean andamento = tabuleiro.emAndamento();
			if (andamento) {
				finalizaPartida();
				janelaInicial
						.setMensagemPainel(getValue("Partida finalizada por desconexao"));
			}
			atorNetGames.desconectar();
			jogador.setConectado(false);
			janelaInicial.mostraMensagem(getValue("Desconectado com sucesso"));
		} else {
			janelaInicial
					.mostraMensagem(getValue("Nao esta conectado a nenhum servidor"));
		}

	}

	private void finalizaPartida() {
		tabuleiro.setAndamento(false);
	}

	public void verificaVencedor() {
		int pontuacaoJogadorUm = tabuleiro.getPontuacaoJogadorUm();
		int pontuacaoJogadorDois = tabuleiro.getPontuacaoJogadorDois();
		boolean vencedor = pontuacaoJogadorDois == 0
				|| pontuacaoJogadorDois < pontuacaoJogadorUm;
		if (vencedor) {
			jogador.setVencedor(true);
			janelaInicial.setMensagemPainel(getValue("Voce venceu"));
		} else {
			jogador.setVencedor(false);
			janelaInicial.setMensagemPainel(getValue("Voce perdeu"));
		}
	}

	private boolean verificaFimDaPartida() {
		return tabuleiro.getPontuacaoJogadorDois() == 0
				|| tabuleiro.getPontuacaoJogadorUm() == 0
				|| ((tabuleiro.getPontuacaoJogadorDois() + tabuleiro
						.getPontuacaoJogadorUm()) == 49);
	}

	private void imprimeProximaAdjacente(List<Posicao> proximaAdjacente) {
		for (Posicao posicaoAdjacente : proximaAdjacente) {
			janelaInicial.imprimeProximaAdjacente(posicaoAdjacente.getLinha(),
					posicaoAdjacente.getColuna());
		}

	}

	private void imprimeAdjacentes(List<Posicao> adjacente) {
		for (Posicao posicaoAdjacente : adjacente) {
			janelaInicial.imprimeAdjacente(posicaoAdjacente.getLinha(),
					posicaoAdjacente.getColuna());
		}

	}

	public void finalizaPartidaComErro() {
		finalizaPartida();
		janelaInicial.setMensagemPainel(getValue("Partida finalizada por desconexao"));
	}

	public String getKeyForValue(String value) {
		return internacionalizacao.getKeyForValue(value);
	}

}
