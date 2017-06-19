package it.polimi.ingsw.LM22.model.excommunication;

import java.io.Serializable;

public class DiceCardMalusEx extends DiceMalusEx implements Serializable {

	private static final long serialVersionUID = -7803859850348672641L;
	private Integer cardType;

	public Integer getCardType() {
		return cardType;
	}

	public String getInfo() {
		String info = "";
		info = info + "You get a malus of " + malus + " for the " + (cardType + 1) + " tower";
		return info;
	}
}
