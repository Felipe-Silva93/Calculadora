package br.com.felipe.calc.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.felipe.calc.modelo.Memoria;
import br.com.felipe.calc.modelo.MemoriaObservador;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObservador{

	private JLabel label;
	
	public Display() {
		//registrar a alteração feita, o display está intereçado de ser notificado
		Memoria.getInstancia().adicionarObservador(this);//dizendo para memoria que está interessado no valor
		
		setBackground(new Color(49,49,49));
		label = new JLabel(Memoria.getInstancia().getTextoAtual());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("courier", Font.PLAIN,30));
		
		setLayout(new FlowLayout(FlowLayout.RIGHT,10,25));
		
		add(label);
	}

	@Override
	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
	}
}
