package it.polimi.ingsw.LM22.network.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import it.polimi.ingsw.LM22.model.DoubleChangeEffect;
import it.polimi.ingsw.LM22.model.Game;
import it.polimi.ingsw.LM22.model.Resource;
import it.polimi.ingsw.LM22.model.VentureCard;
import it.polimi.ingsw.LM22.model.leader.LeaderCard;

/**
 * Interfaccia estesa da RMIClient necessaria per la gestione della logica di
 * gioco
 */
public interface IClient extends Remote {

	public void play() throws RemoteException;

	public String getMove() throws RemoteException;

	public void showBoard(Game game) throws RemoteException;

	public String getName() throws RemoteException;

	public String councilRequest(Integer number) throws RemoteException;

	public String servantsRequest() throws RemoteException;

	public String towerRequest() throws RemoteException;

	public String floorRequest() throws RemoteException;

	public boolean supportRequest() throws RemoteException;

	public String colorRequest() throws RemoteException;

	public Integer ventureCostRequest(VentureCard vc) throws RemoteException;

	public boolean changeRequest(Resource[] exchange) throws RemoteException;

	public boolean changeRequest(Resource exchange, Integer privileges) throws RemoteException;

	public Integer doubleChangeRequest(DoubleChangeEffect effect) throws RemoteException;

	public String askToPlayerForEffectToCopy(List<LeaderCard> lcards) throws RemoteException;

	public void showMsg(String msg) throws RemoteException;

	public Integer selectPersonalTile(Game game) throws RemoteException;

	public void selectLeaderCard(Game game) throws RemoteException;

	public String getLeaderCard() throws RemoteException;

	public void close() throws RemoteException;

	public String getPassword() throws RemoteException;
}
