package uz.alex2276564.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class TntExplosionListener implements Listener {

    @EventHandler
    private void on(EntityExplodeEvent event) {

        //Check entity need because server crash if explosion caused by Ender Dragon
        if (event.getEntity() instanceof TNTPrimed ||
                event.getEntity() instanceof Creeper ||
                event.getEntity() instanceof Wither ||
                event.getEntity() instanceof EnderCrystal) {

            //Remove TNT blocks from the explosion to prevent chain reactions and server crashes
            event.blockList().removeIf(block -> block.getType() == Material.TNT);
        }
    }
}
