package uz.alex2276564;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import uz.alex2276564.listeners.EntityExplosionListener;
import uz.alex2276564.utils.UpdateChecker;

public final class NoMoreTNTChainCrash extends JavaPlugin {

    @Override
    public void onEnable() {
        showInfo();
        registerListeners();
        checkUpdates();
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new EntityExplosionListener(), this);
    }

    private void checkUpdates() {
        UpdateChecker updateChecker = new UpdateChecker(this, "alex2276564/NoMoreTNTChainCrash");
        updateChecker.checkForUpdates();
    }

    private void showInfo() {
        getLogger().info( "");
        getLogger().info("NoMoreTNTChainCrash activated successfully!");
        getLogger().info( "");
        getLogger().warning("WARNING! This plugin removes all tnt's that");
        getLogger().warning("fell within the explosion radius of another tnt!");
        getLogger().info( "");
    }
}
