package com.deloeste.deliveries.dto;

import java.util.Date;


public class Delivery {

	private Date datetime;

	private int sector;

	public Delivery( Date datetime, int sector ) {
		this.datetime = datetime;
		this.sector = sector;
	}

	@Override
	public String toString( ) {
		return datetime.toString( ) + " - " + sector;
	}

	public Date getDatetime( ) {
		return datetime;
	}

	public int getSector( ) {
		return sector;
	}
}