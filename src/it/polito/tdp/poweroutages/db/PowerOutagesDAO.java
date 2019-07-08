package it.polito.tdp.poweroutages.db;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.poweroutages.model.Adiacenza;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutagesDAO {
	
	public List<Nerc> loadAllNercs(NercIdMap nercIdMap) {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(nercIdMap.get(n));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public List<Adiacenza> loadAdiacenze(NercIdMap nercIdMap) {
		
		String sql = "select count(distinct po1.nerc_id, year(po1.date_event_began), month(po1.date_event_began), month(po2.date_event_began), year(po2.date_event_began), po2.nerc_id) as peso, po1.nerc_id as n1, po2.nerc_id as n2 " + 
				"from nercrelations nr, poweroutages po1, poweroutages po2 " + 
				"where nr.nerc_one=po1.nerc_id and nr.nerc_two=po2.nerc_id " + 
				"and year(po1.date_event_began)=year(po2.date_event_began) " + 
				"and month(po1.date_event_began)=month(po2.date_event_began) " + 
				"group by po1.nerc_id, po2.nerc_id";
		
		
		List<Adiacenza> list = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Adiacenza adiacenza = new Adiacenza(nercIdMap.get(res.getInt("n1")), nercIdMap.get(res.getInt("n2")), res.getInt("peso"));
				
				list.add(adiacenza);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return list;
	}
}
