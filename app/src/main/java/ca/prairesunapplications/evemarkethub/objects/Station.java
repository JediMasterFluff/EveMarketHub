package ca.prairesunapplications.evemarkethub.objects;

import java.util.Map;

/**
 * Created by fluffy on 16/12/17.
 */

public class Station {

	private String name;
	private double price;
	private int station_id;
	private Map<String, Double> items;

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
}
