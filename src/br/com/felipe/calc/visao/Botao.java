package br.com.felipe.calc.visao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class Botao extends JButton{
	
	public Botao(String texto, Color cor) {
		setText(texto);
		setFont(new Font("coirier",Font.PLAIN,25));
		setOpaque(true);
		setBackground(cor);
		setForeground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
	}
}
