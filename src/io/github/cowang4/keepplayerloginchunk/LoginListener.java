/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.cowang4.keepplayerloginchunk;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author Greg
 */
public class LoginListener implements Listener{
    
    KeepPlayerLoginChunk main;
    HashMap<Player, Location> playerLocs = new HashMap<>();
    
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent ev)
    {
        main.getLogger().info("Player: " + ev.getPlayer().getName() + " at " + ev.getPlayer().getLocation().getWorld().getName());
        Location loc = ev.getPlayer().getLocation();
        Bukkit.getServer().getWorlds().get(0).loadChunk(Bukkit.getServer().getWorlds().get(0).getChunkAt(loc));
        playerLocs.put(ev.getPlayer(), loc);
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerLogin(PlayerLoginEvent ev)
    {
        final Player player = ev.getPlayer();
        ev.getPlayer().teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
        ev.getPlayer().setGameMode(GameMode.CREATIVE);
        try {
            Bukkit.getServer().getWorlds().get(0).loadChunk(Bukkit.getServer().getWorlds().get(0).getChunkAt(playerLocs.get(ev.getPlayer())));
                               ev.getPlayer().teleport(playerLocs.get(ev.getPlayer()));
            Timer timer = new Timer();
            Date login = new Date();
            Date fiveSec = new Date();
            fiveSec.setTime(login.getTime() + 5000);

            TimerTask task = new TimerTask() {

                @Override
                public void run() {
                    player.teleport(playerLocs.get(player));
                }
            };
            timer.schedule(task, fiveSec); 
        }
        catch (Exception e)
        {}
    }

    public void setMain(KeepPlayerLoginChunk mainClass)
    {
        main = mainClass;
    }
    
}
