package br.com.felipe.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
	private static final Memoria instancia = new Memoria();
	
	private final List<MemoriaObservador>observadores = new ArrayList<>();
	private boolean substituir = false;
	private TipoComando ultimaOperacao = null;
	private String textoAtual = "";
	private String textoBuffer = "";
	
	private Memoria() {
		
	}

	public static Memoria getInstancia() {
		return instancia;
	}
	
	//metodo que vai registrar todos os observadores
	public void adicionarObservador(MemoriaObservador observador) {
		observadores.add(observador);
	}

	public final String getTextoAtual() {
		return textoAtual.isEmpty() ? "0":textoAtual;
	}
	
	
	//metodo que vai processar um novo caractere de estado permitindo ter uma mudança no texto
	public void processarComando(String texto) {
		
		
		TipoComando tipoComando = detectarTipoComando(texto);
		
		if(tipoComando == null) {
			return;
			
		}else if(tipoComando == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimaOperacao = null;
			
		}else if(tipoComando == TipoComando.MENOSEMAIS && textoAtual.contains("-")) {
				
				textoAtual = textoAtual.substring(1);
			
		
		
		}else if(tipoComando == TipoComando.MENOSEMAIS) {
			
			textoAtual = "-"+textoAtual;
		
		}else if(tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA) {
			textoAtual = substituir ? texto : textoAtual + texto;
			substituir = false;
		
		}else {
			substituir = true;
			textoAtual = obterResultadoOperacao();
			textoBuffer = textoAtual;
			ultimaOperacao = tipoComando;
		}
		
		observadores.forEach(o->o.valorAlterado(getTextoAtual()));
	}

	private String obterResultadoOperacao() {
		if(ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return textoAtual;
		}
		double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));
		
		double resultado = 0;
		if(ultimaOperacao == TipoComando.SOMA) {
			resultado = numeroBuffer + numeroAtual;
		}else if(ultimaOperacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;

		}else if(ultimaOperacao == TipoComando.MULT) {
			resultado = numeroBuffer * numeroAtual;

		}else if(ultimaOperacao == TipoComando.DIV) {
			resultado = numeroBuffer / numeroAtual;

		}

		String resultadoString = Double.toString(resultado).replace(".", ",");
		boolean inteiro = resultadoString.endsWith(",0");
		return inteiro ? resultadoString.replace(",",""): resultadoString;
	}

	private TipoComando detectarTipoComando(String texto) {
		

		if(textoAtual.isEmpty()&& texto =="0") {
			return null;
		}
		
		
		try {
			Integer.parseInt(texto);
			return TipoComando.NUMERO;
		} catch (NumberFormatException e) {
			//quando não for numero
			if("AC".equals(texto)) {
				return TipoComando.ZERAR;
			}else if("/".equals(texto)) {
				return TipoComando.DIV;
			}else if("*".equals(texto)) {
				return TipoComando.MULT;
			}else if("+".equals(texto)) {
				return TipoComando.SOMA;
			}else if("-".equals(texto)) {
				return TipoComando.SUB;
			}else if("=".equals(texto)) {
				return TipoComando.IGUAL;
			}else if("=".equals(texto)) {
				return TipoComando.MENOSEMAIS;
			}else if(",".equals(texto) && !textoAtual.contains(",")) {//se já tiver uma virgula não adicione mais outro virgula
				return TipoComando.VIRGULA;
			}				
			
			e.printStackTrace();
		}
		
		
		return null;
	}
	

	
	

}
