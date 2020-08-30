//CopyRight at Carlos17Kopra | Arvid

package de.carlos17kopra.bwrel.listener;

import de.carlos17kopra.bwrel.BedWars;
import de.carlos17kopra.bwrel.util.ItemBuilder;
import de.carlos17kopra.bwrel.util.game.GameManager;
import de.carlos17kopra.bwrel.util.game.PreStartEvent;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class JoinListener implements Listener {

    private ItemStack choose_team = new ItemBuilder(Material.CHEST).setName("§aTeamauswahl").create();
    private ItemStack back_to_lobby = new ItemBuilder(Material.BED).setName("§czur Lobby").create();
    private ItemStack start_game = new ItemBuilder(Material.SLIME_BALL).setName("§aStarten").create();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        e.setJoinMessage("");

        e.getPlayer().setGameMode(GameMode.ADVENTURE);

        if(!GameManager.inGame){

            if(Bukkit.getServer().getOnlinePlayers().size() >= BedWars.getPlugin().getBW_Maxplayers()){

                if(Bukkit.getServer().getOnlinePlayers().size() == BedWars.getPlugin().getBW_Maxplayers()){
                    MinecraftServer.getServer().setMotd("ingame");

                    new PreStartEvent().start();

                }else{
                    MinecraftServer.getServer().setMotd("waiting");
                }

                Bukkit.broadcastMessage("§7[§a+§7] "+e.getPlayer().getName());
                Player p = e.getPlayer();
                if(p.hasPermission("§bw.start")){

                    p.getInventory().setItem(0, choose_team);
                    p.getInventory().setItem(4, start_game);
                    p.getInventory().setItem(8, back_to_lobby);

                }else{

                    p.getInventory().setItem(4, choose_team);
                    p.getInventory().setItem(8, back_to_lobby);

                }


            }else{

                //spectator setzen

                e.getPlayer().sendMessage("§cDieser Server ist voll!");
                BedWars.sendServer(e.getPlayer(), "Lobby");

            }

        }else{

            //spectator setzen

            e.getPlayer().sendMessage("§cDieser Server ist voll!");
            BedWars.sendServer(e.getPlayer(), "Lobby");

        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){

    }

}
