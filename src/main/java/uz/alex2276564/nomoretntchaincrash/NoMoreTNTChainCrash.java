package uz.alex2276564.nomoretntchaincrash;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import uz.alex2276564.nomoretntchaincrash.listeners.EntityExplosionListener;
import uz.alex2276564.nomoretntchaincrash.utils.runner.BukkitRunner;
import uz.alex2276564.nomoretntchaincrash.utils.runner.Runner;
import uz.alex2276564.nomoretntchaincrash.utils.UpdateChecker;

public final class NoMoreTNTChainCrash extends JavaPlugin {

    @Getter
    private Runner runner;

    @Override
    public void onEnable() {
        showInfo();
        setupRunner();
        registerListeners();
        checkUpdates();
    }

    private void setupRunner() {
        runner = new BukkitRunner(this);
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new EntityExplosionListener(), this);
    }

    private void checkUpdates() {
        UpdateChecker updateChecker = new UpdateChecker(this, "alex2276564/NoMoreTNTChainCrash", runner);
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

    @Override
    public void onDisable() {
        runner.cancelTasks();
    }
}
