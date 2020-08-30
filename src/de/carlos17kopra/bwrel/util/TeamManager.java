//CopyRight at Carlos17Kopra | Arvid

package de.carlos17kopra.bwrel.util;

import de.carlos17kopra.bwrel.BedWars;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamManager {

    public static List<Teams> teams = new ArrayList<>();

    public static HashMap<String, Teams>PlayerTeam = new HashMap<>();

    public static void addPlayer(Player p){

        for(Teams team : Teams.values()){

            if(!teams.contains(team)){

                PlayerTeam.put(p.getName(), team);
                if(getCurrentTeamSize(team) == BedWars.getPlugin().getBW_Team_Size()){
                    teams.add(team);
                }
                return;

            }

        }

    }
    public static void addPlayer(Player p, Teams Team){

        if(!teams.contains(Team)){

            if(!isPlayerInTeam(p)){

                PlayerTeam.put(p.getName(), Team);
                if(getCurrentTeamSize(Team) == BedWars.getPlugin().getBW_Team_Size()){
                    teams.add(Team);
                }
                return;
            }

        }

    }

    public static boolean isPlayerInSpecTeam(Player p, Teams Team){
        return PlayerTeam.get(p.getName()) != Team;
    }

    public static boolean isPlayerInTeam(Player p){
        return PlayerTeam.get(p.getName()) != Teams.NONE;
    }

    public static void removePlayer(Player p){

        if(PlayerTeam.containsKey(p.getName())){

            Teams team = PlayerTeam.get(p.getName());
            if(teams.contains(team)){
                teams.remove(team);
            }
            PlayerTeam.put(p.getName(), Teams.NONE);

        }

    }

    public static int getCurrentTeamSize(Teams team){

        int size = 0;

        for(String player : PlayerTeam.keySet()){

            if(PlayerTeam.get(player) == team){
                size++;
            }

        }
        return size;

    }

}
