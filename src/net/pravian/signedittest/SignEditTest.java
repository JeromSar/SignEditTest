package net.pravian.signedittest;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SignEditTest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        super.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (!event.hasItem() || event.getItem().getType() != Material.STICK) {
            return;
        }
        
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            event.setCancelled(true);
            event.getPlayer().openSign();
            return;
        }
        
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        
        final Block block = event.getClickedBlock();
        
        if (block.getType() != Material.SIGN_POST && block.getType() != Material.WALL_SIGN) {
            return; 
        }
     
        event.setCancelled(true);
        final Sign sign = (Sign) block.getState();
        
        if (block.getType() == Material.SIGN_POST) {       
            event.getPlayer().openSign(sign, true);
        }

        if (block.getType() == Material.WALL_SIGN) {       
            event.getPlayer().openSign(sign, false);
        }
    }
    
    @EventHandler
    public void onSignEdit(SignChangeEvent event) {
        super.getLogger().info("SignChangeEvent - Cancelled: " + event.isCancelled() + ", Line 0:" + event.getLine(0));
    }
    
}
