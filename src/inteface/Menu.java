package inteface;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	
	protected  JMenu menuJogo;
	protected JMenuItem itemConectar, itemJogar, itemDesconectar, itemSair;

	public Menu(JanelaInicial ji) {
		menuJogo = new JMenu("Jogo");
		
		itemConectar = new JMenuItem("Conectar");
		itemConectar.addActionListener(ji);
		
		itemJogar = new JMenuItem("Jogar");
		itemJogar.addActionListener(ji);
		
		itemDesconectar = new JMenuItem("Desconectar");
		itemDesconectar.addActionListener(ji);
		
		itemSair = new JMenuItem("Sair");
		itemSair.addActionListener(ji);
		
		menuJogo.add(itemConectar);
		menuJogo.add(itemJogar);
		menuJogo.add(itemDesconectar);
		menuJogo.add(itemSair);
		add(menuJogo);
	
     }
}	
	
