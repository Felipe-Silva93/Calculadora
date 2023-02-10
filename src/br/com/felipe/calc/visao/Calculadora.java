package br.com.felipe.calc.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculadora extends JFrame{

	public Calculadora() {
		organizarLayout();
		//setUndecorated(true); tira a barra do S.O 
		setSize(300, 322);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//pode ser EXIT_ON_CLOSE garante que vai sair da aplicação tambem
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	private void organizarLayout() {

		setLayout(new BorderLayout());
		
		Display display = new Display();
		display.setPreferredSize(new Dimension(233,60));
		add(display, BorderLayout.NORTH);
		
		Teclado teclado = new Teclado();
		add(teclado,BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		new Calculadora();
	}
}
