package ca.prairesunapplications.evemarkethub.objects;

import java.util.Map;

/**
 * Created by fluffy on 16/12/17.
 */

public class Station {

	private String name;
	private String system_name;
	private double price;
	private int station_id;
	private Map<String, Double> items;
	private int orders;

	public Station() {}

	public Station(String name, String system) {
		this.name = name;
		this.system_name = system;
	}

	public String getItemPrice(int itemId) {
		return "123456.59";
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		return station_id;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		Station station = (Station) o;

		return station_id == station.station_id;
	}

	public void setStation_id(int id) {this.station_id = id;}

	public int getId() {
		return station_id;
	}
}
