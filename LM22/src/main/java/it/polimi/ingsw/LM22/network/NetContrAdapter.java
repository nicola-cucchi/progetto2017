package it.polimi.ingsw.LM22.network;

import it.polimi.ingsw.LM22.controller.AbstractMove;
import it.polimi.ingsw.LM22.controller.CardMove;
import it.polimi.ingsw.LM22.controller.CouncilMove;
import it.polimi.ingsw.LM22.controller.EndMove;
import it.polimi.ingsw.LM22.controller.LeaderCardActivation;
import it.polimi.ingsw.LM22.controller.LeaderCardSelling;
import it.polimi.ingsw.LM22.controller.MarketMove;
import it.polimi.ingsw.LM22.controller.WorkMove;
import it.polimi.ingsw.LM22.model.FamilyMember;
import it.polimi.ingsw.LM22.model.Player;
import it.polimi.ingsw.LM22.model.Resource;
import it.polimi.ingsw.LM22.model.leader.LeaderCard;

public class NetContrAdapter {
	Player player;

	/**
	 * riceve una mossa in formato stringa (inviata dal client) e la trasforma
	 * nel giusto oggetto
	 */
	public AbstractMove moveParser(Player p, String sMove) {
		AbstractMove objMove = null;
		player = p;
		// divide la stringa ricevuta in più parametri
		String[] param = sMove.split("@");
		// seleziona il giusto tipo di mossa e assegna all' oggetto mossa il
		// corretto tipo dinamico
		switch (param[0]) {
		case "LeaderAct":
			objMove = new LeaderCardActivation(p, getLeaderCard(param[1]));
			break;
		case "LeaderSell":
			objMove = new LeaderCardSelling(p, getLeaderCard(param[1]));
			break;
		case "Market":
			objMove = new MarketMove(p, getFamilyMember(param[1]), getServantsAdded(param[2]),
					Integer.parseInt(param[3]));
			break;
		case "Work":
			objMove = new WorkMove(p, getFamilyMember(param[1]), getServantsAdded(param[2]), param[3]);
			break;
		case "Card":
			objMove = new CardMove(p, getFamilyMember(param[1]), getServantsAdded(param[2]), Integer.parseInt(param[3]),
					Integer.parseInt(param[4]));
			break;
		case "Council":
			objMove = new CouncilMove(p, getFamilyMember(param[1]), getServantsAdded(param[2]));
			break;
		case "End":
			if (param.length == 2)
				objMove = new EndMove(p, param[1]);
			else
				objMove = new EndMove(p, "noError");
			break;
		default:
			break;
		}
		return objMove;
	}

	/**
	 * ottiene una carta leader cercandola per nome
	 */
	private LeaderCard getLeaderCard(String param) {
		for (LeaderCard ld : player.getHandLeaderCards())
			if (ld.getName().equals(param))
				return ld;
		for (LeaderCard ld : player.getLeaderCards())
			if (ld.getName().equals(param))
				return ld;
		return null;
	}

	/**
	 * ottiene ottiene un familiare cercandolo per colore
	 */
	private FamilyMember getFamilyMember(String param) {
		for (FamilyMember fm : player.getMembers()) {
			if (fm.getColor().equals(param)) {
				return fm;
			}
		}
		return null;
	}

	/**
	 * genera un oggetto risorsa servitori con il valore passato come parametro
	 */
	private Resource getServantsAdded(String param) {
		Integer serv = Integer.parseInt(param);
		return new Resource(0, 0, serv, 0, 0, 0, 0);
	}
}
