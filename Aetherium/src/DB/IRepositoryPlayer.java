package DB;
import models.*;
import java.sql.SQLException;
import java.util.List;

public interface IRepositoryPlayer {

    void open() throws SQLException;

    void close() throws SQLException;

    // Nach einem Bestimmten Spieler, über die ID suchen
    register getPlayerById(String playerId) throws SQLException;
    // Alle Spieler Ausgeben
    List<register> getAllPlayers() throws SQLException;
    // Alle Spieler ausgeben, die eine Bestimmte Klasse haben
    List<register> getPlayerByClass(String Class) throws SQLException;
    // Alle Spieler ausgeben, die eine Bestimmte Rasse haben
    List<register> getPlayerByRace(String race) throws SQLException;

    // Neuen Spieler hinzufügen
    boolean createNewPlayer(register p) throws SQLException;
    // Einloggen
    boolean login(register p) throws SQLException;

}