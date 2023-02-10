package br.com.felipe.calc.modelo;

@FunctionalInterface
public interface MemoriaObservador {

	void valorAlterado(String novoValor);//por default é um metodo publico
}
