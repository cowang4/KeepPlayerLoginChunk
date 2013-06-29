/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.cowang4.keepplayerloginchunk;

import org.bukkit.Bukkit;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Greg
 */
public class KeepPlayerLoginChunk extends JavaPlugin{

    LoginListener listener;
    
    @Override
    public void onEnable()
    {
        listener = new LoginListener();
        listener.setMain(this);
        Bukkit.getServer().getPluginManager().registerEvents(listener, this);
        Bukkit.getServer().setSpawnRadius(1);
        Bukkit.getServer().useExactLoginLocation();
        Bukkit.getServer().getWorlds().get(0).setKeepSpawnInMemory(true);
    }
    
    @Override
    public void onDisable()
    {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
