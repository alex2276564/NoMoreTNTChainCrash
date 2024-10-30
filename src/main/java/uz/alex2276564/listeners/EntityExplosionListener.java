package uz.alex2276564.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import uz.alex2276564.events.EntityExplosionEvent;

public class EntityExplosionListener implements Listener {

    @EventHandler
    public void on(EntityExplodeEvent event) {
        Entity entity = event.getEntity();

        // Check entity need because server crash if explosion caused by Ender Dragon
        if (entity instanceof TNTPrimed ||
                entity instanceof Creeper ||
                entity instanceof Wither ||
                entity instanceof EnderCrystal) {

            final EntityExplosionEvent e = new EntityExplosionEvent(event);
            Bukkit.getPluginManager().callEvent(e);
            if(e.isCancelled()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public  void on(EntityExplosionEvent event) {

        // Remove TNT blocks from the explosion to prevent chain reactions and server crashes
        event.getEntityExplodeEvent().blockList().removeIf(block -> block.getType() == Material.TNT);
    }
}
