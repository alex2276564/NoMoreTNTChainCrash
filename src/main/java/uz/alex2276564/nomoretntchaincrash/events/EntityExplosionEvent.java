package uz.alex2276564.nomoretntchaincrash.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.jetbrains.annotations.NotNull;

/**
 * PRE phase for explosions.
 * Listeners can:
 * - cancelExplosion: cancel the underlying Bukkit explosion event;
 * - skipTntRemoval: keep TNT blocks (disable anti-chain removal).
 *
 * Cancellable mapping:
 * - isCancelled()/setCancelled() map to cancelExplosion for compatibility.
 */
public class EntityExplosionEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    @Getter
    private final EntityExplodeEvent entityExplodeEvent;

    @Getter @Setter
    private boolean cancelExplosion = false;

    @Getter @Setter
    private boolean skipTntRemoval = false;

    public EntityExplosionEvent(@NotNull EntityExplodeEvent bukkitEvent) {
        super(bukkitEvent.getEntity());
        this.entityExplodeEvent = bukkitEvent;
    }

    // Cancellable compatibility
    @Override
    public boolean isCancelled() { return cancelExplosion; }

    @Override
    public void setCancelled(boolean cancel) { this.cancelExplosion = cancel; }

    @Override
    public @NotNull HandlerList getHandlers() { return handlers; }

    public static HandlerList getHandlerList() { return handlers; }
}
