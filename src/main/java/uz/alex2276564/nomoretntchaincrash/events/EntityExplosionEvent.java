package uz.alex2276564.nomoretntchaincrash.events;

import lombok.Getter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.jetbrains.annotations.NotNull;

public class EntityExplosionEvent extends EntityEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private boolean cancel = false;
    @Getter
    private final EntityExplodeEvent entityExplodeEvent;

    public EntityExplosionEvent(EntityExplodeEvent entityExplodeEvent) {
        super(entityExplodeEvent.getEntity());
        this.entityExplodeEvent = entityExplodeEvent;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
