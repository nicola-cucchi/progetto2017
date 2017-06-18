package it.polimi.ingsw.LM22.network.client;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.LM22.model.DoubleChangeEffect;
import it.polimi.ingsw.LM22.model.Game;
import it.polimi.ingsw.LM22.model.Player;
import it.polimi.ingsw.LM22.model.Resource;
import it.polimi.ingsw.LM22.model.VentureCard;
import it.polimi.ingsw.LM22.model.leader.LeaderCard;
import it.polimi.ingsw.LM22.network.server.IPlayer;

public class RMIClient extends UnicastRemoteObject implements IClient {
	private final transient Logger LOGGER = Logger.getLogger(RMIClient.class.getClass().getSimpleName());
	private static final long serialVersionUID = 5918010069011921777L;
	private String move;
	private String name;
	private transient AbstractUI UI;

	public RMIClient(AbstractUI UI) throws RemoteException {
		this.UI = UI;
	}

	public String getName() throws RemoteException {
		return name;
	}

	public String getMove() throws RemoteException {
		return move;
	}
	/*
	 * metodo che permettee la connessione con il server RMI
	 */

	public void connect(String name, String ip) throws RemoteException {
		try {
			this.name = name;
			// ottendo l'oggetto renoto del server
			IPlayer server = (IPlayer) Naming.lookup("rmi://" + ip + "/MSG");
			// mando il mio oggetto al server
			server.login(this);
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			LOGGER.log(Level.SEVERE, "RMI connection error!", e);
		}
		UI.connectionOK();
	}

	/*
	 * metodo invocato da remoto quando è il proprio turno
	 */

	public void play() throws RemoteException {
		UI.printMoveMenu();
		move = UI.getMove();
	}

	/*
	 * metodo invocato da remoto per visualizzre la board
	 */

	@Override
	public void showBoard(Game game) throws RemoteException {
		UI.showBoard(game);
	}

	@Override
	public String councilRequest(Integer number) throws RemoteException {
		return UI.councilRequest(number);
	}

	@Override
	public String servantsRequest() throws RemoteException {
		return UI.printServantsAddictionMenu();
	}

	@Override
	public String towerRequest() throws RemoteException {
		return UI.printTowersMenu();
	}

	@Override
	public String floorRequest() throws RemoteException {
		return UI.printLevelsMenu();
	}

	@Override
	public void showMsg(String msg) throws RemoteException {
		UI.showMsg(msg);
	}

	@Override
	public boolean supportRequest() throws RemoteException {
		return UI.printSupportMenu();
	}

	@Override
	public String colorRequest() throws RemoteException {
		return UI.printColorMenu();
	}

	@Override
	public Integer ventureCostRequest(VentureCard vc) throws RemoteException {
		return UI.printVentureCostMenu(vc);
	}

	@Override
	public boolean changeRequest(Resource[] exchange) throws RemoteException {
		return UI.printChangeMenu(exchange);
	}

	@Override
	public Integer doubleChangeRequest(DoubleChangeEffect effect) throws RemoteException {
		return UI.printDoubleChangeMenu(effect);
	}

	@Override
	public String askToPlayerForEffectToCopy(List<LeaderCard> lcards) throws RemoteException {
		return UI.askToPlayerForEffectToCopy(lcards);
	}

	@Override
	public Integer selectPersonalTile(Game game) throws RemoteException {
		return UI.selectPersonalTile(game);
	}
}