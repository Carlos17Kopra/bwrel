package de.carlos17kopra.bwrel;

import de.carlos17kopra.bwrel.util.sql.SQLConnection;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BedWars extends JavaPlugin {

    public static BedWars plugin;

    
    /*
    *
    * Jonas Stinkt
    *
    */
    
    private String prefix;

    //sql
    private String host;
    private String port;
    private String user;
    private String passwd;
    private String database;

    private SQLConnection con;

    //Settings
    private int BW_Minplayers;
    private int BW_Team_Size;
    private int BW_Team_Amount;
    private int BW_Maxplayers;
    private boolean BW_Team_Friendlyfire;
    private int BW_PreCountDown;

    @Override
    public void onEnable() {

        plugin = this;

        registerConfig();
        init();
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        con.connect();
        System.out.println("Bedwars geladen");

        MinecraftServer.getServer().setMotd("waiting");

    }

    public void init(){
        this.prefix = getConfig().getString("Settings.Prefix");
        this.host = getConfig().getString("Settings.SQL.Host");
        this.port = getConfig().getString("Settings.SQL.Port");
        this.user = getConfig().getString("Settings.SQL.UserName");
        this.passwd = getConfig().getString("Settings.SQL.Passwd");
        this.database = getConfig().getString("Settings.SQL.Database");

        this.BW_Minplayers = getConfig().getInt("Settings.BW.MinPlayers");
        this.BW_Team_Amount = getConfig().getInt("Settings.BW.Team.Amount");
        this.BW_Team_Size = getConfig().getInt("Settings.BW.Team.Size");
        this.BW_Maxplayers = getConfig().getInt("Settings.BW.MaxPlayers");
        this.BW_Team_Friendlyfire = getConfig().getBoolean("Settings.BW.Team.FriendlyFire");
        this.BW_PreCountDown = getConfig().getInt("Settings.BW.Precountdown");

        con = new SQLConnection(host, port, user, passwd, database);

    }

    public void registerConfig(){

        getConfig().options().copyDefaults(true);
        getConfig().addDefault("Settings.Prefix", "&eBedwars &7| ");
        getConfig().addDefault("Settings.SQL.Host","localhost");
        getConfig().addDefault("Settings.SQL.Port","3306");
        getConfig().addDefault("Settings.SQL.UserName","root");
        getConfig().addDefault("Settings.SQL.Passwd","root");
        getConfig().addDefault("Settings.SQL.Database","bw");

        getConfig().addDefault("Settings.BW.MinPlayers", 4);
        getConfig().addDefault("Settings.BW.MaxPlayers", 8);
        getConfig().addDefault("Settings.BW.Team.Size", 2);
        getConfig().addDefault("Settings.BW.Team.Amount", 4);
        getConfig().addDefault("Settings.BW.Team.FriendlyFire", false);
        getConfig().addDefault("Settings.BW.Precountdown", 30);

        saveConfig();

    }

    @Override
    public void onDisable() {
        con.Disconnect();
    }

    public static void sendServer(Player p, String ServerName) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF(ServerName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
        p.sendPluginMessage(BedWars.getPlugin(), "BungeeCord", b.toByteArray());
    }


    public static BedWars getPlugin() {
        return plugin;
    }

    public String getPrefix() {
        return prefix;
    }

    public SQLConnection getCon() {
        return con;
    }

    public String getHost() {
        return host;
    }

    public String getDataBase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPort() {
        return port;
    }

    public String getPasswd() {
        return passwd;
    }

    public int getBW_Minplayers() {
        return BW_Minplayers;
    }

    public int getBW_Team_Amount() {
        return BW_Team_Amount;
    }

    public int getBW_Maxplayers() {
        return BW_Maxplayers;
    }

    public int getBW_Team_Size() {
        return BW_Team_Size;
    }

    public boolean isBW_Team_Friendlyfire() {
        return BW_Team_Friendlyfire;
    }

    public int getBW_PreCountDown() {
        return BW_PreCountDown;
    }
}
