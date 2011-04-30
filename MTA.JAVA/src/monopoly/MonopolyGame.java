/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import monopoly.results.EventArrayResult;
import monopoly.results.GameDetailsResult;
import monopoly.results.IDResult;
import monopoly.results.MonopolyResult;
import monopoly.results.PlayerDetailsResult;


/**
 *
 * @author blecherl
 */
public class MonopolyGame {

    private String activeGameName;
    private boolean isGameActive;

    private Map<Integer, Event> events;

    public MonopolyGame() {
        events = new HashMap<Integer, Event>();
    }

    public String getGameBoardSchema() {
        return null;
    }

    public String getGameBoardXML() throws IOException {
        File xmlFile = new File("d:\\Workspace\\MTA.JAVA\\res\\config\\monopoly_config.xml");
        File blhblah = new File("myNewFile.txt");
        blhblah.createNewFile();
        String xmlString = "";
        try {
            Scanner sc = new Scanner(xmlFile);
            while (sc.hasNextLine())
            {
                xmlString+=sc.nextLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MonopolyGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return xmlString;
    }

    public MonopolyResult startGame (String gameName, int humanPlayers, int computerizedPlayers, boolean useAutomaticDiceRoll) {
        activeGameName = gameName;
        //validate players nummbers
        return new MonopolyResult();
    }

    public GameDetailsResult getGameDetails (String gameName) {
        return new GameDetailsResult("ACTIVE", 0, 0, 0, true);
    }

    public String[] getWaitingGames() {
        return !isGameActive && activeGameName != null ? new String[] {activeGameName} : new String[0];
    }
    
    public String[] getActiveGames() {
        return isGameActive && activeGameName != null ? new String[] {activeGameName} : new String[0];
    }

    public IDResult joinGame (String gameName, String playerName) {
        //check that game name exists and not active
        //validate player name
        return new IDResult(UUID.randomUUID().clockSequence());
    }

    public PlayerDetailsResult getPlayersDetails(String gameName) {
        return new PlayerDetailsResult(null);
    }

    public EventArrayResult getAllEvents (int eventID) {
        //validate evendID against last eventID
        return new EventArrayResult();
    }

    public MonopolyResult setDiceRollResults (int playerID, int eventID, int dice1, int dice2) {
        //validate evendID against last eventID
        //validate playerID against games players and players state
        //validate game mode
        return new MonopolyResult();
    }

    public MonopolyResult resign (int playerID) {
        //validate playerID against games players and players state
        return new MonopolyResult();
    }

    public MonopolyResult buy (int playerID, int eventID, boolean buy) {
        //validate evendID against last eventID
        //validate playerID against games players and players state
        //validate asset from eventID
        return new MonopolyResult();
    }
}