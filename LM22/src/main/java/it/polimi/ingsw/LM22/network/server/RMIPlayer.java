package it.polimi.ingsw.LM22.network.server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.LM22.model.DoubleChangeEffect;
import it.polimi.ingsw.LM22.model.Game;
import it.polimi.ingsw.LM22.model.Resource;
import it.polimi.ingsw.LM22.model.VentureCard;
import it.polimi.ingsw.LM22.model.leader.LeaderCard;
import it.polimi.ingsw.LM22.network.client.IClient;

/**
 * classe utilizzata da remoto per le azioni sul client RMI
 */

public class RMIPlayer extends UnicastRemoteObject implements IPlayer {
	private static final Logger logger = Logger.getLogger(RMIPlayer.class.getClass().getSimpleName());
	private static final long serialVersionUID = -2036349694420489903L;
	private transient IClient client;
	private String name;
	private String password;

	public RMIPlayer() throws RemoteException {
		// costruttore vuoto
	}

	/**
	 * indica al client quando è il suo turno e restituisce la mossa da lui
	 * effettuata
	 */

	@Override
	public String yourTurn() throws RemoteException {
		client.play();
		return client.getMove();
	}

	// manda al client il model per poter visualizzare la board
	@Override
	public void showBoard(Game game) throws RemoteException {
		client.showBoard(game);
	}

	// riceve l'oggetto remoto del client
	@Override
	public void login(IClient client) throws RemoteException {
		this.name = client.getName();
		this.password = client.getPassword();
		this.client = client;
	}

	// restituisce l'oggetto remoto del client
	public IClient getClient() throws RemoteException {
		return client;
	}

	@Override
	public String getName() throws RemoteException {
		return name;
	}

	@Override
	public String councilRequest(Integer number) throws RemoteException {
		return client.councilRequest(number);
	}

	@Override
	public String servantsRequest() throws IOException {
		return client.servantsRequest();
	}

	@Override
	public String towerRequest() throws IOException {
		return client.towerRequest();
	}

	@Override
	public String floorRequest() throws IOException {
		return client.floorRequest();
	}

	@Override
	public void showMsg(String msg) throws IOException {
		client.showMsg(msg);
	}

	@Override
	public boolean supportRequest() throws IOException {
		return client.supportRequest();
	}

	@Override
	public String colorRequest() throws IOException {
		return client.colorRequest();
	}

	@Override
	public Integer ventureCostRequest(VentureCard vc) throws IOException {
		return client.ventureCostRequest(vc);
	}

	@Override
	public boolean changeRequest(Resource[] exchange) throws IOException {
		return client.changeRequest(exchange);
	}
	
	@Override 
	public boolean changeRequest(Resource exchange, Integer privileges) throws IOException {
		return client.changeRequest(exchange, privileges);
	}

	@Override
	public Integer doubleChangeRequest(DoubleChangeEffect effect) throws IOException {
		return client.doubleChangeRequest(effect);
	}

	@Override
	public String askToPlayerForEffectToCopy(List<LeaderCard> lcards) throws IOException {
		return client.askToPlayerForEffectToCopy(lcards);
	}

	@Override
	public Integer selectPersonalTile(Game game) throws IOException {
		return client.selectPersonalTile(game);
	}

	@Override
	public void selectLeaderCard(Game game) throws IOException {
		new Thread() {
			public void run() {
				try {
					client.selectLeaderCard(game);
				} catch (RemoteException e) {
					logger.log(Level.INFO, "Player disconnected!", e);
				}
			}
		}.start();
	}

	@Override
	public String getLeaderCard() throws IOException {
		return client.getLeaderCard();
	}

	@Override
	public void close() throws IOException {
		client.close();
	}

	@Override
	public String getPassword() throws IOException {
		return password;
	}
}