package com.imd.telemaco.entity;

/**
 * Class that represents the integration between User and Episode.
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 23 de abr de 2018 | 11:06:22
 */
public class UserEpisode {
	private int idUser;
	private int idEpisode;
	
	public UserEpisode (int idUser, int idEpisode) {
		this.idUser = idUser;
		this.idEpisode = idEpisode;
	}

	public int getIdUser() {
		return idUser;
	}
	
	public int getIdEpisode() {
		return idEpisode;
	}
}