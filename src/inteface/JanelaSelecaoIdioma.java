package inteface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import controlador.Controlador;

public class JanelaSelecaoIdioma extends JFrame {

	Controlador controlador;
	JRadioButton rdbtnPortugus;
	JRadioButton rdbtnIngls;
	JRadioButton rdbtnFrancs;
	private ButtonGroup grupo;

	public JanelaSelecaoIdioma(Controlador controlador) {
		grupo = new ButtonGroup();
		setBounds(100, 100, 455, 300);
		this.controlador = controlador;
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		JLabel lblSelecioneOIdioma = new JLabel("Selecione o Idioma:");
		lblSelecioneOIdioma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelecioneOIdioma.setBounds(149, 11, 179, 23);
		getContentPane().add(lblSelecioneOIdioma);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY, 2, true));
		panel.setBounds(62, 45, 324, 86);
		getContentPane().add(panel);

		rdbtnFrancs = new JRadioButton("Franc\u00EAs");
		panel.add(rdbtnFrancs);

		rdbtnIngls = new JRadioButton("Ingl\u00EAs");
		panel.add(rdbtnIngls);

		rdbtnPortugus = new JRadioButton("Portugu\u00EAs");
		panel.add(rdbtnPortugus);
		rdbtnPortugus.setSelected(true);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(297, 180, 89, 23);
		getContentPane().add(btnEntrar);

		grupo.add(rdbtnFrancs);
		grupo.add(rdbtnIngls);
		grupo.add(rdbtnPortugus);

		btnEntrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadLocale();

			}
		});
	}

	protected void loadLocale() {
		String locale = null;
		for (Enumeration<AbstractButton> buttons = grupo.getElements(); buttons
				.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				locale = button.getText();
			}
		}
		controlador.inicie(locale);
		dispose();
	}
}
