//CopyRight at Carlos17Kopra | Arvid

package de.carlos17kopra.bwrel.scoreboard;

import de.carlos17kopra.bwrel.BedWars;
import de.carlos17kopra.bwrel.util.TeamManager;
import de.carlos17kopra.bwrel.util.Teams;
import de.carlos17kopra.bwrel.util.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardCreator {

    public void create(Player p){

        if(!GameManager.inGame) {

            Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective obj = board.registerNewObjective("str1", "str2");

            obj.setDisplayName("§cBedwars");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);

            obj.getScore("§m§0").setScore(0);
            int count = 1;
            for(Teams team : TeamManager.teams){

                Team t = board.registerNewTeam(team.getName());
                t.addEntry(team.getColor());

                if(GameManager.existing.contains(team)){

                    t.setPrefix(team.getColor()+"✓ "+team.getName());

                }else{

                    t.setPrefix(team.getColor()+"✕ "+team.getName());

                }
                obj.getScore(team.getColor()).setScore(count);
                count++;

            }
            obj.getScore("§k§0").setScore(count);

        }

    }

}
