package com.deloeste.deliveries.data;

public class RandomGenerator {

	public static double beta( double a, double b ) {
		// Input Analyzer Results:
		// Friday: 723 + 477 * beta( 1.19, 0.959 )
		// Saturday: 723 + 477 * BETA(0.854, 0.993)
		// Sunday: 721 + 473 * BETA(0.831, 0.957)
		double Ga, Gb;
		if( ( a <= 1 ) && ( b <= 1 ) ) {
			double U, V, X, Y;

			while( true ) {
				U = Math.random( );
				V = Math.random( );
				X = Math.pow( U, 1 / a );
				Y = Math.pow( V, 1 / b );

				if( ( X + Y ) <= 1 ) {
					return X / ( X + Y );
				}
			}
		} else {
			Ga = gamma( a );
			Gb = gamma( b );
			return Ga / ( Ga + Gb );
		}
	}

	public static double gamma( double alfa ) {
		double a = 1d / Math.sqrt( 2 * alfa - 1 );
		double b = alfa - Math.log( 4 );
		double q = alfa + 1d / a;
		double theta = 4.5;
		double d = 1 + Math.log( theta );
		double u1 = Math.random( );
		double u2 = Math.random( );
		double v = a * Math.log( u1 / ( 1 - u1 ) );
		double y = alfa * Math.exp( v );
		double z = u1 * u1 * u2;
		double w = b + q * v - y;
		while( w + d - theta * z < 0 ) {
			if( w >= Math.log( z ) ) {
				return y;
			} else {
				u1 = Math.random( );
				u2 = Math.random( );
				v = a * Math.log( u1 / ( 1 - u1 ) );
				y = alfa * Math.exp( v );
				z = u1 * u1 * u2;
				w = b + q * v - y;
			}
		}
		return y;
	}

	public static double exponential( ) {
		return -Math.log( 1 - Math.random( ) );
	}
}
