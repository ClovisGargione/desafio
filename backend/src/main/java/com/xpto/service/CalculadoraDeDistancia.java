package com.xpto.service;

public class CalculadoraDeDistancia {

	
	/**
	 * Calcula distância entre dois pontos utilizando a latitude e longitude
	 * @param lat1 - latitude primeiro ponto
	 * @param lon1 - longitude primeiro ponto
	 * @param lat2 - latitude segundo ponto
	 * @param lon2 - longitude segundo ponto
	 * @param unit - unidade de medida da distância M=Milhas K=Kilômetros N=Milhas náuticas
	 * @return - distância 
	 */
	public static double distancia(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit.equals("K")) {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	Esta função converte graus para radianos						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	Esta função converte radianos para graus						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

}
