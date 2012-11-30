package com.deloeste.deliveries.data;

import java.util.ArrayList;

import com.deloeste.deliveries.dto.Delivery;

/**
 * Interface for real data
 */
public interface IRealData
{
    // TODO Method that converts data from a file to DTO instances
    public ArrayList<Delivery> loadDeliveries( );
}
