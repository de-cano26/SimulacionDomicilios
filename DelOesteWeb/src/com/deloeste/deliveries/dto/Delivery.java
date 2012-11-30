package com.deloeste.deliveries.dto;

import java.util.Date;


/**
 * Class that represents an order
 */
public class Delivery {

	public static final int ACTIVE = 1;

	private Date datetime;

	private int sector;

	private int state;

	public Delivery( Date datetime, int sector ) {
		this.datetime = datetime;
		this.sector = sector;
		this.state = ACTIVE;
	}

	@Override
	public String toString( ) {
		return datetime.toString( ) + " - " + sector + " - " + state;
	}

	public int getState( ) {
		return state;
	}

	public void setState( int state ) {
		this.state = state;
	}

	public Date getDatetime( ) {
		return datetime;
	}

	public int getSector( ) {
		return sector;
	}
}