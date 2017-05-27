	package it.polimi.ingsw.LM22.controller;

import it.polimi.ingsw.LM22.model.Game;
import it.polimi.ingsw.LM22.model.Player;

public class VaticanReportManager {

	private final Integer PERIOD_END_DEFINER = 2;
	
	/*
	 * metodo che controlla se un giocatore ha raggiunto i requisiti
	 * del periodo corrente per la Chiesa
	 * */
	public boolean canGiveSupport(Player player){
		//TO-DO
	}
	
	/*
	 * metodo che conferisce al giocatore la scomunica e viene invocato:
	 * - automaticamente se il giocatore non ha i requisiti (giveSupport()-->false)
	 * - su richiesta del giocatore se askPlayer mi dice che il giocatore
	 * 		decide autonomamente di non dare il sostegno alla Chiesa
	 * */
	public void exCommunicate(Player player){
		//TO-DO
	}
	
	/*
	 * metodo invocato se giveSupport() restituisce true e chiede al giocatore
	 * se desidera dare o no il sostegno alla Chiesa
	 * */
	public void askPlayer(Player player){
		//TO-DO
	}
	
	/*
	 * metodo che si occupa dettagliatamente del recupero della scomunica
	 * e della copia nei vari giocatori scomunicati
	 * */
	public void giveExTile(){
		//TO-DO
	}
	
	/*
	 * metodo che gestisce tutta la fase di VaticanReport controllando volta 
	 * per volta tutti i giocatori
	 * */
	public void manageVaticanReport(){

	}
	
	
}
