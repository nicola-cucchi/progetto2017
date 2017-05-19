package it.polimi.ingsw.LM22.model;

import java.util.List;

public class PersonalBoard {
	private final Integer MAXNUMCARDS = 6;
	private final PersonalBonusTile bonusBoard;
	private Resource resources;
	private List<TerritoryCard> territoriesCards;
	private List<CharacterCard> charactersCards;
	private List<BuildingCard> buildingsCards;
	private List<VentureCard> venturesCards;
	private List<ExInterface> exCommunications;
	public Resource getResources() {
		return resources;
	}
	public void setResources(Resource resources) {
		this.resources = resources;
	}
	public PersonalBonusTile getBonusBoard() {
		return bonusBoard;
	}
	public void setBonusBoard(PersonalBonusTile bonusBoard) {
		this.bonusBoard = bonusBoard;
	}
	public List<TerritoryCard> getTerritoriesCards() {
		return territoriesCards;
	}
	public void setTerritoriesCards(List<TerritoryCard> territoriesCards) {
		this.territoriesCards = territoriesCards;
	}
	public List<CharacterCard> getCharactersCards() {
		return charactersCards;
	}
	public void setCharactersCards(List<CharacterCard> charactersCards) {
		this.charactersCards = charactersCards;
	}
	public List<BuildingCard> getBuildingsCards() {
		return buildingsCards;
	}
	public void setBuildingsCards(List<BuildingCard> buildingsCards) {
		this.buildingsCards = buildingsCards;
	}
	public List<VentureCard> getVenturesCards() {
		return venturesCards;
	}
	public void setVenturesCards(List<VentureCard> venturesCards) {
		this.venturesCards = venturesCards;
	}
	public List<ExInterface> getExCommunications() {
		return exCommunications;
	}
	public void setExCommunications(List<ExInterface> exCommunications) {
		this.exCommunications = exCommunications;
	}
}
