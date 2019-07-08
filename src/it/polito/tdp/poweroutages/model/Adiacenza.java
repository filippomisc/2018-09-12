package it.polito.tdp.poweroutages.model;

public class Adiacenza {
	
	private Nerc n1;
	private Nerc n2;
	private int peso;
	
	public Adiacenza(Nerc n1, Nerc n2, int peso) {
		super();
		this.n1 = n1;
		this.n2 = n2;
		this.peso = peso;
	}

	public Nerc getN1() {
		return n1;
	}

	public Nerc getN2() {
		return n2;
	}

	public int getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Adiacenza [n1=");
		builder.append(n1);
		builder.append(", n2=");
		builder.append(n2);
		builder.append(", peso=");
		builder.append(peso);
		builder.append("]");
		return builder.toString();
	}

	
	
}
