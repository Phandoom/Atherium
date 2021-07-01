package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Enums.classes;
import models.Enums.races;
import models.register;

public class RepositoryPlayerDB implements IRepositoryPlayer {

    private Connection _connection;
    private String user = "root";
    private String pwd = "Buraksamet68";

    // ctor
    public RepositoryPlayerDB() throws ClassNotFoundException {
        Class<?> c = Class.forName("com.mysql.cj.jdbc.Driver");
        if (c!=null){
            System.out.println("MySQL Treiber wurde geladen");
        }
    }


    @Override
    public void open() throws SQLException {
        String url = "jdbc:mysql://localhost/Aetherium";
        this._connection = DriverManager.getConnection(url, user, pwd);

    } //Done
    @Override
    public void close() throws SQLException {
        if((this._connection != null) && (!this._connection.isClosed())){
            this._connection.close();
            System.out.println("Verbindung wurde geschlossen");
        }
    } //Done

    @Override
    public register getPlayerById(String playerId) throws SQLException {
        PreparedStatement pStmt = this._connection.prepareStatement("select * from players where player_id = ?");
        pStmt.setString(1, playerId);

        ResultSet result = pStmt.executeQuery();

        if (result.next()){
            register p = new register();
            p.setName(result.getString("player_name"));
            p.setPassword(result.getString("player_password"));
            p.setUserId();
            p.setClasses(classes.valueOf(result.getString("player_class")));
            p.setRaces(races.valueOf(result.getString("player_race")));
            /*  int classes = result.getInt("player_class");
            if (classes == 0){
                p.setClasses(models.Enums.classes.archer);
            }
            else if (classes == 1){
                p.setClasses(models.Enums.classes.knight);
            }
            else if (classes == 2) {
                p.setClasses(models.Enums.classes.mage);
            }
            else if (classes == 3){
                p.setClasses(models.Enums.classes.priest);
            }
         */
            return p;
        }
        else {
            return null;
        }
    } //Done
    @Override
    public List<register> getAllPlayers() throws SQLException {
        List<register> players = new ArrayList<>();
        PreparedStatement pStmt = this._connection.prepareStatement("select * from Players");

        ResultSet result = pStmt.executeQuery();

        while (result.next()){
            register p = new register();
            p.setName(result.getString("player_name"));
            p.setPassword(result.getString("player_password"));
            p.setUserId();
            p.setClasses(classes.valueOf(result.getString("player_class")));
            p.setRaces(races.valueOf(result.getString("player_race")));

            players.add(p);
        }
        if (players.size() >= 1){
            return players;
        }
        else {
            return null;
        }
    } //Done
    @Override
    public List<register> getPlayerByClass(String Class) throws SQLException {
        List<register> players = new ArrayList<>();
        PreparedStatement pStmt = this._connection.prepareStatement("select * from players where player_class = ?");
        pStmt.setString(1, Class);
        ResultSet result = pStmt.executeQuery();
        while (result.next()){
            register p = new register();
            p.setName(result.getString("player_name"));
            p.setPassword(result.getString("player_password"));
            p.setUserId();
            p.setClasses(classes.valueOf(result.getString("player_class")));
            p.setRaces(races.valueOf(result.getString("player_race")));

            players.add(p);
        }
        if (players.size() >= 1){
            return players;
        }
        else{
            return null;
        }
    } //Done
    @Override
    public List<register> getPlayerByRace(String race) throws SQLException {
        List<register> players = new ArrayList<>();
        PreparedStatement pStmt = this._connection.prepareStatement("select * from players where player_race = ?");
        pStmt.setString(1, race);
        ResultSet result = pStmt.executeQuery();
        while (result.next()){
            register p = new register();
            p.setName(result.getString("player_name"));
            p.setPassword(result.getString("player_password"));
            p.setUserId();
            p.setClasses(classes.valueOf(result.getString("player_class")));
            p.setRaces(races.valueOf(result.getString("player_race")));

            players.add(p);
        }
        if (players.size() >= 1){
            return players;
        }
        else{
            return null;
        }
    } //Done

    @Override
    public boolean createNewPlayer(register p) throws SQLException {
        PreparedStatement pStmt = this._connection.prepareStatement("insert into players values (?, ?, ?, ?, ?)");

        pStmt.setString(1, p.getUserId());
        pStmt.setString(2, p.getName());
        pStmt.setString(3, p.getPassword());
        pStmt.setString(4, p.getClasses().toString());
        pStmt.setString(5, p.getRaces().toString());

        return pStmt.executeUpdate() == 1;
    } //Done
    @Override
    public boolean login(register p) throws SQLException {
        PreparedStatement pStmt = this._connection.prepareStatement("select * from players where player_name = ? and player_password = ?");
        pStmt.setString(1, p.getName());
        pStmt.setString(2, p.getPassword());

        ResultSet result = pStmt.executeQuery();
        if (result.next()){
            p.setName(result.getString("player_name"));
            p.setPassword(result.getString("player_password"));
            p.setUserId();
            p.setClasses(classes.valueOf(result.getString("player_class")));
            p.setRaces(races.valueOf(result.getString("player_race")));
            return true;
        }
        else {
            return false;
        }
    } //Done


}
