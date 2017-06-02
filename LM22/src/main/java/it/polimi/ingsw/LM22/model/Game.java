package it.polimi.ingsw.LM22.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.LM22.model.leader.LeaderCard;

public class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Integer NUM_BONUS_TILE = 4;
	private final BoardGame boardgame = new BoardGame();
	private Integer period;
	private Integer round;
	private Player[] players;
	private List<Player> playersOrder;
	private ArrayList<TerritoryCard> territoryCards;
	private ArrayList<CharacterCard> characterCards;
	private ArrayList<BuildingCard> buildingCards;
	private ArrayList<VentureCard> ventureCards;
	private ArrayList<LeaderCard> leaderCards;
	private final PersonalBonusTile personalBonusTile[] = new PersonalBonusTile[NUM_BONUS_TILE];

	/*
	 * durante la creazione della Game dovrò inizializzare tutti gli attributi
	 * messi a final (saranno sempre quelli)
	 */
	public BoardGame getBoardgame() {
		return boardgame;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public void setTerritoryCards(ArrayList<TerritoryCard> territoryCards) {
		this.territoryCards = territoryCards;
	}

	public void setCharacterCards(ArrayList<CharacterCard> characterCards) {
		this.characterCards = characterCards;
	}

	public void setBuildingCards(ArrayList<BuildingCard> buildingCards) {
		this.buildingCards = buildingCards;
	}

	public void setVentureCards(ArrayList<VentureCard> ventureCards) {
		this.ventureCards = ventureCards;
	}

	public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
		this.leaderCards = leaderCards;
	}

	public Player[] getPlayers() {
		return players;
	}

	public List<Player> getPlayersOrder() {
		return playersOrder;
	}

	public void setPlayersOrder(List<Player> playersOrder) {
		this.playersOrder = playersOrder;
	}

	public ArrayList<TerritoryCard> getTerritoryCards() {
		return territoryCards;
	}

	public ArrayList<CharacterCard> getCharacterCards() {
		return characterCards;
	}

	public ArrayList<BuildingCard> getBuildingCards() {
		return buildingCards;
	}

	public ArrayList<VentureCard> getVentureCards() {
		return ventureCards;
	}

	public ArrayList<LeaderCard> getLeaderCards() {
		return leaderCards;
	}

	public PersonalBonusTile[] getPersonalBonusTile() {
		return personalBonusTile;
	}

}
