package netGames;

import javax.swing.JOptionPane;

import logica.JogadaValida;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import controlador.Controlador;

public class AtorNetGames implements OuvidorProxy {

	protected Proxy proxy;
	protected Controlador controlador;
	
	public AtorNetGames(Controlador controlador){
		super();
		proxy = Proxy.getInstance();
		this.controlador = controlador;
		proxy.addOuvinte(this);
		
	}
	
	public String conectar(String nome, String servidor){
		String problema = null;
		try {
			proxy.conectar(servidor, nome);
		} catch (JahConectadoException e) {
			problema = e.getLocalizedMessage();
		} catch (NaoPossivelConectarException e) {
			problema = e.getLocalizedMessage();
		} catch (ArquivoMultiplayerException e) {
			problema = e.getLocalizedMessage();
		}
		return problema;
	}
	

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		controlador.iniciarPartida(posicao);
	}
	
	public void enviarJogada(JogadaValida jogada){
		try {
			proxy.enviaJogada(jogada);
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void receberJogada(Jogada jogada) {
		JogadaValida jogadaValida = (JogadaValida)jogada;
		controlador.receberJogada(jogadaValida);
	}
	
	public void desconectar(){
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	

	@Override
	public void finalizarPartidaComErro(String message) {
        controlador.finalizaPartidaComErro();

	}


	@Override
	public void tratarConexaoPerdida() {


	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub

	}

	public String obterNomeAdversario(int posicao) {
		return proxy.obterNomeAdversario(posicao);
		
	}

	public void iniciarNovaPartidaServidor(){
		try {
			proxy.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub
		
	}

}
