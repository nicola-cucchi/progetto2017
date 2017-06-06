package it.polimi.ingsw.LM22.model;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import it.polimi.ingsw.LM22.model.leader.CardRequest;
import it.polimi.ingsw.LM22.model.leader.ChurchSubstainEffect;
import it.polimi.ingsw.LM22.model.leader.CoinsDiscountEffect;
import it.polimi.ingsw.LM22.model.leader.CopyEffect;
import it.polimi.ingsw.LM22.model.leader.DoubleResourceEffect;
import it.polimi.ingsw.LM22.model.leader.InOccupiedSpaceEffect;
import it.polimi.ingsw.LM22.model.leader.LeaderCard;
import it.polimi.ingsw.LM22.model.leader.LeaderCardRequest;
import it.polimi.ingsw.LM22.model.leader.LeaderEffect;
import it.polimi.ingsw.LM22.model.leader.LeaderResourceEffect;
import it.polimi.ingsw.LM22.model.leader.MemberBonusEffect;
import it.polimi.ingsw.LM22.model.leader.MemberChangeEffect;
import it.polimi.ingsw.LM22.model.leader.NoMilitaryRequestEffect;
import it.polimi.ingsw.LM22.model.leader.NoOccupiedTowerEffect;
import it.polimi.ingsw.LM22.model.leader.ResourceCardRequest;
import it.polimi.ingsw.LM22.model.leader.ResourceRequest;
import it.polimi.ingsw.LM22.model.leader.WorkAction;

public class FileParser {
	private final String JSONpath = System.getProperty("user.dir") + "\\JSON\\";

	public void getDevCards(Game game) throws IOException {
		FileParser f = new FileParser();
		// definisco i sotto tipi per gli effetti immediati e parmanenti
		RuntimeTypeAdapterFactory<ImmediateEffect> AdapterImm = RuntimeTypeAdapterFactory
				.of(ImmediateEffect.class, "type").registerSubtype(ResourcePrivilegeEffect.class)
				.registerSubtype(WorkActionEffect.class).registerSubtype(ResourceToResourceEffect.class)
				.registerSubtype(CardToResourceEffect.class).registerSubtype(NoEffect.class)
				.registerSubtype(ChangeEffect.class).registerSubtype(CardActionEffect.class)
				.registerSubtype(DoubleChangeEffect.class).registerSubtype(ChangeToPrivilegeEffect.class);
		RuntimeTypeAdapterFactory<PermanentEffect> AdapterPerm = RuntimeTypeAdapterFactory
				.of(PermanentEffect.class, "type").registerSubtype(NoPermanentEffect.class)
				.registerSubtype(WorkBonusEffect.class).registerSubtype(ColorCardBonusEffect.class)
				.registerSubtype(NoCardSpaceBonusEffect.class);

		// tipi di carte per l'oggetto GSON
		Type bType = new TypeToken<ArrayList<BuildingCard>>() {
		}.getType();
		Type cType = new TypeToken<ArrayList<CharacterCard>>() {
		}.getType();
		Type tType = new TypeToken<ArrayList<TerritoryCard>>() {
		}.getType();
		Type vType = new TypeToken<ArrayList<VentureCard>>() {
		}.getType();

		// chiamo la funzione loadCards che restituisce
		ArrayList<BuildingCard> bCards = f.<ArrayList<BuildingCard>>loadDevCards("BuildingCard", AdapterImm,
				AdapterPerm, bType);
		ArrayList<CharacterCard> cCards = f.<ArrayList<CharacterCard>>loadDevCards("CharacterCard", AdapterImm,
				AdapterPerm, cType);
		ArrayList<TerritoryCard> tCards = f.<ArrayList<TerritoryCard>>loadDevCards("TerritoryCard", AdapterImm,
				AdapterPerm, tType);
		ArrayList<VentureCard> vCards = f.<ArrayList<VentureCard>>loadDevCards("VentureCard", AdapterImm, AdapterPerm,
				vType);
		// importo le carte nell'oggetto game
		game.setBuildingCards(bCards);
		game.setCharacterCards(cCards);
		game.setTerritoryCards(tCards);
		game.setVentureCards(vCards);

		// try {
		// String text = new
		// String(Files.readAllBytes(Paths.get(path)),StandardCharsets.UTF_8);
		// Type tType = new TypeToken<Collection<TerritoryCard>>() {
		// }.getType();
		// System.out.println(tType);
		// Gson tGson = new
		// GsonBuilder().registerTypeAdapterFactory(AdapterImm).create();
		// System.out.println(tGson);
		// Collection<TerritoryCard> tCards = tGson.fromJson(text, tType);
		// System.out.println(tCards);
		// } catch (IOException e) {
		// System.err.println("Errore nell'apertura del file JSON
		// TerritoryCard.json");
		// }

		// for (TerritoryCard c : tCards) {
		// try {
		// System.out.println(
		// c.getName() + " " + ((ResourcePrivilegeEffect)
		// c.getImmediateEffect()).getResource().getStone()
		// + " " + c.getPermanentEffect().getResource().getStone());
		// } catch (Exception e) {
		// }
		// }
	}

	private <T> T loadDevCards(String fileName, RuntimeTypeAdapterFactory<ImmediateEffect> AdapterImm,
			RuntimeTypeAdapterFactory<PermanentEffect> AdapterPerm, Type type) throws IOException {
		// ottengo il contenuto del file
		String text = new String(Files.readAllBytes(Paths.get(JSONpath + fileName + ".json")), StandardCharsets.UTF_8);
		// genero l'oggetto deserializzatore GSON
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(AdapterImm).registerTypeAdapterFactory(AdapterPerm)
				.create();
		// ritorno le carte con il tipo generico T (specificato alla chiamata)
		T cards = gson.fromJson(text, type);
		return cards;
	}

	public void getLeaderCards(Game game) throws IOException {
		RuntimeTypeAdapterFactory<LeaderCardRequest> req = RuntimeTypeAdapterFactory.of(LeaderCardRequest.class, "type")
				.registerSubtype(CardRequest.class).registerSubtype(ResourceRequest.class)
				.registerSubtype(ResourceCardRequest.class);
		RuntimeTypeAdapterFactory<LeaderEffect> effect = RuntimeTypeAdapterFactory.of(LeaderEffect.class, "type")
				.registerSubtype(WorkAction.class).registerSubtype(LeaderResourceEffect.class)
				.registerSubtype(CopyEffect.class).registerSubtype(NoOccupiedTowerEffect.class)
				.registerSubtype(InOccupiedSpaceEffect.class).registerSubtype(NoMilitaryRequestEffect.class)
				.registerSubtype(CoinsDiscountEffect.class).registerSubtype(DoubleResourceEffect.class)
				.registerSubtype(ChurchSubstainEffect.class).registerSubtype(MemberChangeEffect.class)
				.registerSubtype(MemberBonusEffect.class);
		Type lType = new TypeToken<ArrayList<LeaderCard>>() {
		}.getType();
		// ottengo il contenuto del file
		String text = new String(Files.readAllBytes(Paths.get(JSONpath + "LeaderCard.json")), StandardCharsets.UTF_8);
		// genero l'oggetto deserializzatore GSON
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(req).registerTypeAdapterFactory(effect).create();
		ArrayList<LeaderCard> lCards = gson.fromJson(text, lType);
		game.setLeaderCards(lCards);
	}

	public void getCouncilSpace(Game game) throws IOException {
		Type type = new TypeToken<CouncilSpace>() {
		}.getType();
		// ottengo il contenuto del file
		String text = new String(Files.readAllBytes(Paths.get(JSONpath + "CouncilSpace.json")), StandardCharsets.UTF_8);
		// genero l'oggetto deserializzatore GSON
		Gson gson = new GsonBuilder().create();
		CouncilSpace councilPalace = gson.fromJson(text, type);
		game.getBoardgame().setCouncilPalace(councilPalace);
	}

	public void getMarketSpace(Game game) throws IOException {
		Type type = new TypeToken<MarketSpace[]>() {
		}.getType();
		// ottengo il contenuto del file
		String text = new String(Files.readAllBytes(Paths.get(JSONpath + "MarketSpace.json")), StandardCharsets.UTF_8);
		// genero l'oggetto deserializzatore GSON
		Gson gson = new GsonBuilder().create();
		MarketSpace[] marketSpace = gson.fromJson(text, type);
		game.getBoardgame().setMarket(marketSpace);
	}

	public void getFaithGrid(Game game) throws IOException {
		Type type = new TypeToken<Resource[]>() {
		}.getType();
		// ottengo il contenuto del file
		String text = new String(Files.readAllBytes(Paths.get(JSONpath + "FaithGridReward.json")),
				StandardCharsets.UTF_8);
		// genero l'oggetto deserializzatore GSON
		Gson gson = new GsonBuilder().create();
		Resource[] resources = gson.fromJson(text, type);
		FaithGrid faithGrid = new FaithGrid();
		faithGrid.setRewards(resources);
		game.getBoardgame().setFaithGrid(faithGrid);
	}
}