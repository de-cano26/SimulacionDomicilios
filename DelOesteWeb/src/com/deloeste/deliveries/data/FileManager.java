package com.deloeste.deliveries.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deloeste.deliveries.dto.Delivery;


public class FileManager {

	public static void main( String[] args ) throws IOException {
		FileManager fm = new FileManager( );
		List<Delivery> deliveries = fm.readData( new File( "./data/Deliveries.txt" ) );
		for( Delivery delivery : deliveries ) {
			System.out.println( delivery.toString( ) );
		}
		fm.createTimesFile( fm.getDayDeliveries( deliveries, 5 ), new File( "./data/Friday.dst" ) );
		fm.createTimesFile( fm.getDayDeliveries( deliveries, 6 ), new File( "./data/Saturday.dst" ) );
		fm.createTimesFile( fm.getDayDeliveries( deliveries, 0 ), new File( "./data/Sunday.dst" ) );
	}

	@SuppressWarnings( "deprecation" )
	public List<Delivery> readData( File textFile ) throws IOException {
		List<Delivery> deliveries = new ArrayList<Delivery>( );
		BufferedReader br = new BufferedReader( new FileReader( textFile ) );
		String line;
		while( ( line = br.readLine( ) ) != null ) {
			String[] lineSplit = line.split( "\t" );
			String[] date = lineSplit[0].split( "/" );
			int year = Integer.parseInt( date[2] ) - 1900;
			int month = Integer.parseInt( date[1] ) - 1;
			int day = Integer.parseInt( date[0] );
			String[] time = lineSplit[1].split( ":" );
			int hour = Integer.parseInt( time[0] );
			int min = Integer.parseInt( time[1] );
			Date datetime = new Date( year, month, day, hour, min );
			int sector = Integer.parseInt( lineSplit[2] );
			Delivery delivery = new Delivery( datetime, sector );
			deliveries.add( delivery );
		}
		br.close( );
		return deliveries;
	}

	@SuppressWarnings( "deprecation" )
	public void createTimesFile( List<Delivery> deliveries, File textFile ) throws IOException {
		BufferedWriter bw = new BufferedWriter( new FileWriter( textFile ) );
		for( Delivery delivery : deliveries ) {
			Date datetime = delivery.getDatetime( );
			bw.write( ( datetime.getHours( ) * 60 ) + datetime.getMinutes( ) + "\n" );
		}
		bw.close( );
	}

	@SuppressWarnings( "deprecation" )
	public List<Delivery> getDayDeliveries( List<Delivery> deliveries, int day ) {
		List<Delivery> dayDeliveries = new ArrayList<Delivery>( );
		for( Delivery delivery : deliveries ) {
			if( delivery.getDatetime( ).getDay( ) == day ) {
				dayDeliveries.add( delivery );
			}
		}
		return dayDeliveries;
	}

	@SuppressWarnings( "deprecation" )
	public List<Delivery> generateDeliveries( ) {
		List<Delivery> deliveries = new ArrayList<Delivery>( );
		for( int i = 0; i < 100; i++ ) {
			double beta = 723 + 477 * RandomGenerator.beta( 1.19, 0.959 );
			int mins = (int) beta;

			Date datetime = new Date( 112, 9, 30, mins, mins % 60 );
			int sector = 0; // FIXME
			deliveries.add( new Delivery( datetime, sector ) );
		}
		return deliveries;
	}
}