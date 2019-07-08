package it.polito.tdp.poweroutages.db;

public class TestDAO {

	public static void main(String[] args) {

		PowerOutagesDAO dao = new PowerOutagesDAO();

		NercIdMap  nercIdMap= new NercIdMap();
		
		System.out.println(dao.loadAllNercs(nercIdMap).size());
		
		System.out.println(dao.loadAdiacenze(nercIdMap).size());
	}

}
