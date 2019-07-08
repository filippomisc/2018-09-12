package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.poweroutages.db.NercIdMap;
import it.polito.tdp.poweroutages.db.PowerOutagesDAO;

public class Model {

	PowerOutagesDAO dao;
	SimpleWeightedGraph<Nerc, DefaultWeightedEdge> graph;
	
	List<Nerc> nercs;
	NercIdMap nercIdMap; 
	
	public Model() {
		
		this.dao=new PowerOutagesDAO();
		this.graph= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		nercIdMap= new NercIdMap();
		this.nercs= dao.loadAllNercs(nercIdMap);
		System.out.println("nercIdMap: "+nercs.size());
		
	}
	
	
	public void creaGrafo() {
		
		Graphs.addAllVertices(this.graph, dao.loadAllNercs(nercIdMap));
		System.out.println("vertici creati: "+this.graph.vertexSet().size());
		
		for (Adiacenza adiacenza : dao.loadAdiacenze(nercIdMap)) {
			
			Graphs.addEdge(this.graph, adiacenza.getN1(), adiacenza.getN2(), adiacenza.getPeso());
		}
		System.out.println("archi creati: "+this.graph.edgeSet().size());
	}
	
	public String displayNeighbohor(Nerc Vnerc) {
		if(this.graph==null)
			creaGrafo();
		
		List<ViciniPeso> vPesos = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		

		List<Nerc> vicini = Graphs.neighborListOf(this.graph, Vnerc);
		
		for (Nerc nercT : vicini) {
			int peso = (int) this.graph.getEdgeWeight(this.graph.getEdge(Vnerc, nercT));
			
			vPesos.add(new ViciniPeso(nercT, peso));

			
		}
		
	Collections.sort(vPesos);
	
	builder.append("vertice selezionato: "+Vnerc.getValue()+"\n");
	for (ViciniPeso viciniPeso : vPesos) {
		builder.append(viciniPeso.getNerc().getValue());
		builder.append(" peso: ");
		builder.append(viciniPeso.getPeso()+"\n");
		
	}
	
		
		
		return builder.toString();
	}


	public Set<Nerc> getVertex() {
		return this.graph.vertexSet();
	}

}
