package it.polimi.ingsw.LM22.model;

public class CardActionEffect extends ImmediateEffect{
	private String cardType;
	private Integer diceValue;
	private Resource cardDiscount;
	private Resource resource;
	private Integer councilPrivilege;

	public String getCardType() {
		return cardType;
	}

	public Integer getDiceValue() {
		return diceValue;
	}

	public Resource getCardDiscount() {
		return cardDiscount;
	}

	public Resource getResource() {
		return resource;
	}

	public Integer getCouncilPrivilege() {
		return councilPrivilege;
	}
}
