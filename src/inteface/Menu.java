package inteface;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	
	protected  JMenu menuJogo;
	protected JMenuItem itemConectar, itemJogar, itemDesconectar, itemSair;

	public Menu(JanelaInicial ji) {
		menuJogo = new JMenu(ji.loadTranslate("Jogo"));
		
		itemConectar = new JMenuItem(ji.loadTranslate("Conectar"));
		itemConectar.addActionListener(ji);
		
		itemJogar = new JMenuItem(ji.loadTranslate("Jogar"));
		itemJogar.addActionListener(ji);
		
		itemDesconectar = new JMenuItem(ji.loadTranslate("Desconectar"));
		itemDesconectar.addActionListener(ji);
		
		itemSair = new JMenuItem(ji.loadTranslate("Sair"));
		itemSair.addActionListener(ji);
		
		menuJogo.add(itemConectar);
		menuJogo.add(itemJogar);
		menuJogo.add(itemDesconectar);
		menuJogo.add(itemSair);
		add(menuJogo);
	
     }
}	
	
