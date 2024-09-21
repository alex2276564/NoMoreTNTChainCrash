package uz.alex2276564;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import uz.alex2276564.listeners.TntExplosionListener;

public final class NoMoreTNTChainCrash extends JavaPlugin {

    @Override
    public void onEnable() {
        showInfo();
        Bukkit.getPluginManager().registerEvents(new TntExplosionListener(), this);

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
