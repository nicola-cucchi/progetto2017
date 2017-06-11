package it.polimi.ingsw.LM22.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.LM22.model.CardActionEffect;
import it.polimi.ingsw.LM22.model.CardToResourceEffect;
import it.polimi.ingsw.LM22.model.ChangeEffect;
import it.polimi.ingsw.LM22.model.ChangeToPrivilegeEffect;
import it.polimi.ingsw.LM22.model.DoubleChangeEffect;
import it.polimi.ingsw.LM22.model.Effect;
import it.polimi.ingsw.LM22.model.FamilyMember;
import it.polimi.ingsw.LM22.model.NoEffect;
import it.polimi.ingsw.LM22.model.Player;
import it.polimi.ingsw.LM22.model.Resource;
import it.polimi.ingsw.LM22.model.ResourcePrivilegeEffect;
import it.polimi.ingsw.LM22.model.ResourceToResourceEffect;
import it.polimi.ingsw.LM22.model.WorkActionEffect;
import it.polimi.ingsw.LM22.model.leader.LeaderResourceEffect;
import it.polimi.ingsw.LM22.model.leader.MemberBonusEffect;
import it.polimi.ingsw.LM22.model.leader.MemberChangeEffect;

public class EffectManager {
	/*
	 * NEEDED represents the first value of the array of dimension 2, 
	 * representing the needed resource to make the change possible
	 */
	private final Integer NEEDED = 0;
	private final Resource NOTHING = new Resource(0, 0, 0, 0, 0, 0, 0);
	private final String UNCOLORED = "Uncolored";
	private Player player;
	private MainGameController mainGC;
	private ResourceHandler r = new ResourceHandler();
	private static final Logger LOGGER = Logger.getLogger(EffectManager.class.getClass().getSimpleName());

	public void manageEffect(Effect effect, Player player, MainGameController mainGC) {
		this.player = player;
		this.mainGC = mainGC;
		try {
			String name = effect.getClass().getSimpleName().toLowerCase() + "Manage";
			Method metodo = this.getClass().getMethod(name, new Class[] { effect.getClass() });
			if (metodo != null)
				metodo.invoke(this, new Object[] { effect });
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public void changetoprivilegeeffectManage(ChangeToPrivilegeEffect effect) {

	}
	
	/*
	 * metodo che gestisce l'effetto in ingresso come effetto immediato di una carta
	 */
	public void resourceprivilegeeffectManage(ResourcePrivilegeEffect effect) throws IOException {
		// rivedere ResourceHandler
		r.addResource(player.getPersonalBoard().getResources(), effect.getResource());
		r.addResource(player.getPersonalBoard().getResources(),
				mainGC.selectCouncilPrivilege(effect.getCouncilPrivilege(), player));
	}

	/*
	 * metodo che gestisce la produzione per una singola carta e
	 * che in base all'effetto che si vuole attivare invoca il metodo giusto
	 */
	public void productionHandle(Effect effect, Resource sum){
		
	}
	
	/*
	 * metodo che gestisce il raccolto per ogni singola carta
	 * e che in base all'effetto che si vuole attivare invoca il metodo giusto
	 */
	public void harvestHandle(ResourcePrivilegeEffect effect, Resource resource) throws IOException {
		/*
		 * deve poter anche considerare il ResourceToPrivilegeEffect
		 */
		r.addResource(resource, effect.getResource());
		r.addResource(resource, mainGC.selectCouncilPrivilege(effect.getCouncilPrivilege(), player));
	}

	/*
	 * gestisce l'effetto del Generale (carta Character)
	 */
	public void resourcetoresourceeffectManage(ResourceToResourceEffect effect) {
		Integer points = player.getPersonalBoard().getResources().getMilitary()/effect.getRequirement().getMilitary();
		Resource bonus = r.resourceMultiplication(effect.getReward(), points);
		r.addResource(player.getPersonalBoard().getResources(), bonus);
	}

	public void cardactioneffectManage(CardActionEffect effect) {

	}

	public void workactioneffectManage(WorkActionEffect effect) {

	}

	/*
	 * metodo che gestisce tale effetto PROBLEMA 
	 * - se è un effetto immediato allora posso subito aggiungere il reward alle risorse del player 
	 * - se invece è un effetto permanente devo poterlo sommare alla risorsa sommatrice della produzione
	 */
	public void cardtoresourceeffectManage(CardToResourceEffect effect, Resource resource) {
		Resource bonus = NOTHING;
		switch (effect.getCardRequired()) {
		case "TERRITORY":
			bonus = r.resourceMultiplication(effect.getReward(),
					player.getPersonalBoard().getTerritoriesCards().size());
		case "CHARACTER":
			bonus = r.resourceMultiplication(effect.getReward(), player.getPersonalBoard().getCharactersCards().size());
		case "BUILDING":
			bonus = r.resourceMultiplication(effect.getReward(), player.getPersonalBoard().getBuildingsCards().size());
		case "VENTURE":
			bonus = r.resourceMultiplication(effect.getReward(), player.getPersonalBoard().getVenturesCards().size());
		}
		r.addResource(resource, bonus);
	}

	/*
	 * metodo che gestisce l'effetto rappresentante uno scambio di risorse
	 * (non Privilegi del Consiglio)
	 */
	public void changeeffectManage(ChangeEffect effect, Resource sum) {
		if (r.enoughResources(player.getPersonalBoard().getResources(), effect.getExchangeEffect1()[NEEDED]) && askChangeToPlayer());
			r.addResource(sum, effect.getExchangeEffect1()[NEEDED+1]);
			r.subResource(player.getPersonalBoard().getResources(), effect.getExchangeEffect1()[NEEDED]);
		return;
	}

	/*
	 * metodo che chiama il player giocante e chiede se vuole effettuare questo scambio
	 * (nel caso abbia effettivamente le risorse disponibili)
	 */
	private boolean askChangeToPlayer() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * metodo che gestisce l'effetto con due possibili scambi
	 * --> controlla se almeno uno dei due è fattibile (altrimenti esce)
	 * --> se solo uno è fattibile usa askChangeToPlayer()
	 * --> atrimenti usa un altro metodo (ancora da implementare)
	 */
	public void doublechangeeffectManage(DoubleChangeEffect effect) {

	}

	public void noeffectManage(NoEffect effect) {
		return;
	}

	public void leaderresourceeffectManage(LeaderResourceEffect effect) throws IOException {
		r.addResource(player.getPersonalBoard().getResources(), effect.getResource());
		r.addResource(player.getPersonalBoard().getResources(),
				mainGC.selectCouncilPrivilege(effect.getCouncilPrivilege(), player));
	}
	
	public void memberchangeeffectManage(MemberChangeEffect e, Player p){
		String color = e.getTypeOfMember();
		switch (color) {
		case "ALL":
			for (FamilyMember m : p.getMembers()) {
				if (m.getColor() != UNCOLORED)
					m.setValue(((MemberChangeEffect) e).getNewValueOfMember());
			}
			break;
		case "UNCOLORED":
			for (FamilyMember m : p.getMembers())
				if (m.getColor() == UNCOLORED) {
					m.setValue(e.getNewValueOfMember());
					break;
				}
		}
	}

	/*
	 * metodo che gestisce tale effetto
	 */
	public void memberbonuseffectManage(MemberBonusEffect e, Player p) {
		String color = e.getTypeOfMember();
		switch (color) {
		case "ALL": {
			for (FamilyMember f : p.getMembers()) {
				f.setValue(f.getValue() + e.getValueOfBonus());
			}
		}
		}
	}
	
}
