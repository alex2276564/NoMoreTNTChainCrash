package uz.alex2276564.nomoretntchaincrash.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import uz.alex2276564.nomoretntchaincrash.events.EntityExplosionEvent;

public class EntityExplosionListener implements Listener {

    @EventHandler
    public void on(EntityExplodeEvent event) {
        Entity entity = event.getEntity();

        /*
          Returns true if the entity is a known, safe-to-handle explosion source.
          Prevents issues with certain entities (e.g., EnderDragon) that can crash the server.
         */
        if (isSupportedExplosiveEntity(entity)) {

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

    private boolean isSupportedExplosiveEntity(Entity entity) {
        return entity instanceof TNTPrimed ||
                entity instanceof Creeper ||
                entity instanceof Wither ||
                entity instanceof EnderCrystal ||
                entity instanceof Fireball ||
                entity instanceof ExplosiveMinecart;
    }
}
