package it.polimi.ingsw.LM22.network.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.List;

import it.polimi.ingsw.LM22.model.DoubleChangeEffect;
import it.polimi.ingsw.LM22.model.Game;
import it.polimi.ingsw.LM22.model.Resource;
import it.polimi.ingsw.LM22.model.VentureCard;
import it.polimi.ingsw.LM22.model.leader.LeaderCard;
import it.polimi.ingsw.LM22.network.client.IClient;

public class SocketPlayer implements IPlayer {
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String name;
	private String password;

	// inizializzo gli stream di comunicazione
	public SocketPlayer(Socket socket) throws IOException {
		this.socket = socket;
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		// leggo il nome del client
		name = in.readUTF();
		password = in.readUTF();
	}

	// indica al client quando è il suo turno e restituisce la mossa da lui
	// effettuata
	@Override
	public String yourTurn() throws IOException {
		out.writeUTF("start");
		out.flush();

		return in.readUTF();
	}

	// inivia al client il model per poter visualizzare la board
	@Override
	public void showBoard(Game game) throws IOException {

		out.writeUTF("board");
		out.flush();

		// fondamentale, pulisce la cache e permette di inviare il nuovo oggetto
		// modficato
		out.reset();

		out.writeObject(game);
		out.flush();

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void login(IClient client) throws RemoteException {
		// login eseguito alla connessione
	}

	@Override
	public String councilRequest(Integer number) throws IOException {
		out.writeUTF("council@" + number);
		out.flush();
		return in.readUTF();
	}

	@Override
	public String servantsRequest() throws IOException {
		out.writeUTF("servants");
		out.flush();
		return in.readUTF();
	}

	@Override
	public String towerRequest() throws IOException {
		out.writeUTF("tower");
		out.flush();
		return in.readUTF();
	}

	@Override
	public String floorRequest() throws IOException {
		out.writeUTF("floor");
		out.flush();
		return in.readUTF();
	}

	@Override
	public void showMsg(String msg) throws IOException {
		out.writeUTF("msg@" + msg);
		out.flush();
	}

	@Override
	public boolean supportRequest() throws IOException {
		out.writeUTF("support");
		out.flush();
		return in.readBoolean();
	}

	@Override
	public String colorRequest() throws IOException {
		out.writeUTF("color");
		out.flush();
		return in.readUTF();
	}

	@Override
	public Integer ventureCostRequest(VentureCard vc) throws IOException {
		out.writeUTF("ventureCost");
		out.flush();

		out.reset();

		out.writeObject(vc);
		out.flush();

		return in.readInt();
	}

	@Override 
	public boolean changeRequest(Resource exchange, Integer privileges) throws IOException {
		out.writeUTF("changePriv");
		out.flush();

		out.reset();

		out.writeObject(exchange);
		out.flush();
		out.writeInt(privileges);
		out.flush();

		return in.readBoolean();
	}
	
	@Override
	public boolean changeRequest(Resource[] exchange) throws IOException {
		out.writeUTF("change");
		out.flush();

		out.reset();

		out.writeObject(exchange);
		out.flush();

		return in.readBoolean();
	}

	@Override
	public Integer doubleChangeRequest(DoubleChangeEffect effect) throws IOException {
		out.writeUTF("doubleChange");
		out.flush();

		out.reset();

		out.writeObject(effect);
		out.flush();

		return in.readInt();
	}

	@Override
	public String askToPlayerForEffectToCopy(List<LeaderCard> lcards) throws IOException {
		out.writeUTF("askCopy");
		out.flush();

		// invio lunghezza lista
		out.writeInt(lcards.size());
		out.flush();

		// inivio tutti gli elementi della lista
		for (int i = 0; i < lcards.size(); i++) {
			out.reset();
			out.writeObject(lcards.get(i));
			out.flush();
		}

		return in.readUTF();
	}

	@Override
	public Integer selectPersonalTile(Game game) throws IOException {
		out.writeUTF("personalTile");
		out.flush();

		out.reset();

		out.writeObject(game);
		out.flush();

		return in.readInt();
	}

	@Override
	public void selectLeaderCard(Game game) throws IOException {
		out.writeUTF("leader");
		out.flush();

		out.reset();

		out.writeObject(game);
		out.flush();
	}

	@Override
	public String getLeaderCard() throws IOException {
		out.writeUTF("getLeader");
		out.flush();

		return in.readUTF();
	}

	@Override
	public void close() throws IOException {
		socket.close();
	}

}
