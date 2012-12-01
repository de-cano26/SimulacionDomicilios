package com.deloeste.deliveries.dto;

public class GsonDelivery
{
    private int day;
    private int time;
    private int sector;
    
    public GsonDelivery( int day, int time, int sector )
    {
        super( );
        this.day = day;
        this.time = time;
        this.sector = sector;
    }

}
