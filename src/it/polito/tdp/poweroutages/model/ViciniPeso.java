package it.polito.tdp.poweroutages.model;

public class ViciniPeso implements Comparable<ViciniPeso>{
	
	private Nerc nerc;
	private int peso;
	
	public ViciniPeso(Nerc nerc, int peso) {
		super();
		this.nerc = nerc;
		this.peso = peso;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public int getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ViciniPeso [nerc=");
		builder.append(nerc);
		builder.append(", peso=");
		builder.append(peso);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(ViciniPeso arg0) {
		return -(getPeso()-arg0.getPeso());
	}

	
}
