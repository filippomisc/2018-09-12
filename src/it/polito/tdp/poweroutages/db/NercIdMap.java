package it.polito.tdp.poweroutages.db;

import java.util.HashMap;
import java.util.Map;

import it.polito.tdp.poweroutages.model.Nerc;


public class NercIdMap {
	
private Map<Integer, Nerc> map;
	
public NercIdMap() {	
		map = new HashMap<>();
	}
	
	public Nerc get(int nercIdId) {
		return map.get(nercIdId);
	}
	
	public Nerc get(Nerc nerc) {
		Nerc old = map.get(nerc.getId());
		if (old == null) {
			map.put(nerc.getId(), nerc);
			return nerc;
		}
		return old;
	}
	
	public void put(Nerc nerc, int nercId) {
		map.put(nercId, nerc);
	}

}
