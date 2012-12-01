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
import com.deloeste.deliveries.dto.GsonDelivery;


public class DataManager {

	/**
	 * Minimum deliveries on Fri, Sat, Sun.
	 */
	private static final int MIN_DELS_WEEKEND = 17;

	/**
	 * Maximum deliveries on Fri, Sat, Sun.
	 */
	private static final int MAX_DELS_WEEKEND = 28;

	/**
	 * Minimum deliveries on Mon, Tus, Wed, Thu.
	 */
	private static final int MIN_DELS_WEEK = 4;

	/**
	 * Maximum deliveries on Mon, Tus, Wed, Thu.
	 */
	private static final int MAX_DELS_WEEK = 7;

	/**
	 * Main de pruebas.
	 * @param args ...
	 * @throws IOException En caso de algún error con los archivos.
	 */
	public static void main( String[] args ) throws IOException {
		// Input Analyzer files
		DataManager dm = new DataManager( );
		List<Delivery> deliveries = dm.readData( new File( "./docs/Deliveries.txt" ) );
		dm.createTimesFile( dm.getDayDeliveries( deliveries, 5 ), new File( "./docs/Friday.dst" ) );
		dm.createTimesFile( dm.getDayDeliveries( deliveries, 6 ), new File( "./docs/Saturday.dst" ) );
		dm.createTimesFile( dm.getDayDeliveries( deliveries, 0 ), new File( "./docs/Sunday.dst" ) );
		// Generated data
		List<GsonDelivery> gDeliveries = dm.generateDeliveries( );
		for( GsonDelivery delivery : gDeliveries ) {
			System.out.println( delivery.toString( ) );
		}
	}

	/**
	 * Carga los domicilios de un archivo de texto.
	 * @param textFile Archivo de texto con un domicilio por línea.
	 * @return La lista de los domicilios.
	 * @throws IOException En caso de error con el archivo.
	 */
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

	/**
	 * Crea un archivo de texto con los tiempos en minutos de los domicilios.
	 * @param deliveries Domicilios.
	 * @param textFile Archivo a escribir.
	 * @throws IOException En caso de error con el archivo.
	 */
	@SuppressWarnings( "deprecation" )
	public void createTimesFile( List<Delivery> deliveries, File textFile ) throws IOException {
		BufferedWriter bw = new BufferedWriter( new FileWriter( textFile ) );
		for( Delivery delivery : deliveries ) {
			Date datetime = delivery.getDatetime( );
			bw.write( ( datetime.getHours( ) * 60 ) + datetime.getMinutes( ) + "\n" );
		}
		bw.close( );
	}

	/**
	 * Retorna los domicilios de un día de la semana de una lista más grande de domicilios.
	 * @param deliveries Domicilios a filtrar.
	 * @param day Día de la semana.
	 * @return Los domicilios de un día de la semana de una lista más grande de domicilios.
	 */
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

	/**
	 * Genera datos de 4 semanas, siguiendo las distribución beta en el tiempo, y probabilidad en
	 * los sectores, para cada día de la semana.
	 * @return Lista de domicilios generados.
	 */
	public List<GsonDelivery> generateDeliveries( ) {
		List<GsonDelivery> deliveries = new ArrayList<GsonDelivery>( );

		for( int week = 0; week < 4; week++ ) {
			// Monday-Thursday
			for( int day = 0; day < 4; day++ ) {
				int number = (int) Math.random( ) * ( MAX_DELS_WEEK - MIN_DELS_WEEK + 1 ) + MIN_DELS_WEEK;
				for( int d = 0; d < number; d++ ) {
					double mins_beta = 737 + 433 * RandomGenerator.beta( 1.04, 1.15 );
					int time = 60000 * (int) mins_beta;
					double r = Math.random( );
					int sector;
					if( r < 0.5 ) {
						sector = 1;
					} else if( r < 0.8 ) {
						sector = 2;
					} else if( r < 0.95 ) {
						sector = 3;
					} else {
						sector = 4;
					}
					deliveries.add( new GsonDelivery( 1, time, sector ) );
				}
			}
			// WEEKEND
			int number = (int) Math.random( ) * ( MAX_DELS_WEEKEND - MIN_DELS_WEEKEND + 1 ) + MIN_DELS_WEEKEND;
			// Friday
			for( int d = 0; d < number; d++ ) {
				double mins_beta = 723 + 472 * RandomGenerator.beta( 1.19, 0.959 );
				int time = 60000 * (int) mins_beta;
				double r = Math.random( );
				int sector;
				if( r < 0.35 ) {
					sector = 1;
				} else if( r < 0.55 ) {
					sector = 2;
				} else if( r < 0.9 ) {
					sector = 3;
				} else {
					sector = 4;
				}
				deliveries.add( new GsonDelivery( 2, time, sector ) );
			}
			// Saturday
			for( int d = 0; d < number; d++ ) {
				double mins_beta = 723 + 477 * RandomGenerator.beta( 0.854, 0.993 );
				int time = 60000 * (int) mins_beta;
				double r = Math.random( );
				int sector;
				if( r < 0.5 ) {
					sector = 1;
				} else if( r < 0.8 ) {
					sector = 2;
				} else if( r < 0.95 ) {
					sector = 3;
				} else {
					sector = 4;
				}
				deliveries.add( new GsonDelivery( 3, time, sector ) );
			}
			// Sunday
			for( int d = 0; d < number; d++ ) {
				double mins_beta = 723 + 472 * RandomGenerator.beta( 0.831, 0.957 );
				int time = 60000 * (int) mins_beta;
				double r = Math.random( );
				int sector;
				if( r < 0.4 ) {
					sector = 1;
				} else if( r < .8 ) {
					sector = 2;
				} else if( r < 0.1 ) {
					sector = 3;
				} else {
					sector = 4;
				}
				deliveries.add( new GsonDelivery( 4, time, sector ) );
			}
		}
		return deliveries;
	}
}