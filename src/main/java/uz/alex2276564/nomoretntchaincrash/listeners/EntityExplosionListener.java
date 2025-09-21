package uz.alex2276564.nomoretntchaincrash.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import uz.alex2276564.nomoretntchaincrash.events.EntityExplosionEvent;

public class EntityExplosionListener implements Listener {

    // Phase 1: fire PRE event as high as possible, give others a chance to veto/flag
    @EventHandler(
            priority = EventPriority.HIGHEST,
            ignoreCancelled = true
    )
    public void on(EntityExplodeEvent event) {
        Entity entity = event.getEntity();

        /*
          Returns true if the entity is a known, safe-to-handle explosion source.
          Prevents issues with certain entities (e.g., EnderDragon) that can crash the server.
         */
        if (!isSupportedExplosiveEntity(entity)) return;

        EntityExplosionEvent pre = new EntityExplosionEvent(event);
        Bukkit.getPluginManager().callEvent(pre);

        // Respect explosion cancellation asked by API listeners
        if (pre.isCancelExplosion()) {
            event.setCancelled(true);
        }
        // Do not remove TNT here — this is done in phase 2 (APPLY)
    }

    // Phase 2 (APPLY): run after other plugins had a chance to set flags
    @EventHandler(
            priority = EventPriority.MONITOR,
            ignoreCancelled = true
    )
    public void applyAntiChain(EntityExplosionEvent event) {
        // If explosion was cancelled by PRE listeners → nothing to apply
        if (event.isCancelExplosion()) return;

        // If someone asked to skip TNT removal → do nothing
        if (event.isSkipTntRemoval()) return;

        // Apply anti-chain: strip TNT from the explosion block list
        event.getEntityExplodeEvent().blockList().removeIf(b -> b.getType() == Material.TNT);

        // TODO - add the config with configurable delete method
//        // Important: remove TNT from the world and from the blockList
//        event.getEntityExplodeEvent().blockList().removeIf(block -> {
//            if (block.getType() == Material.TNT) {
//                // Set to air without physics (no drops, no chain)
//                block.setType(Material.AIR, false);
//                return true; // remove from explosion processing
//            }
//            return false;
//        });
    }

    private boolean isSupportedExplosiveEntity(Entity entity) {
        return switch (entity.getType()) {
            case PRIMED_TNT, CREEPER, WITHER, ENDER_CRYSTAL, MINECART_TNT, FIREBALL, SMALL_FIREBALL, WITHER_SKULL ->
                    true;
            // DRAGON_FIREBALL intentionally excluded to avoid Ender Dragon interactions
            default -> false;
        };
    }
}