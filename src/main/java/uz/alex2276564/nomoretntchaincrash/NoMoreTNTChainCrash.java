package uz.alex2276564.nomoretntchaincrash;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import uz.alex2276564.nomoretntchaincrash.listeners.EntityExplosionListener;
import uz.alex2276564.nomoretntchaincrash.utils.runner.FoliaRunner;
import uz.alex2276564.nomoretntchaincrash.utils.runner.Runner;
import uz.alex2276564.nomoretntchaincrash.utils.UpdateChecker;

public final class NoMoreTNTChainCrash extends JavaPlugin {

    @Getter
    private Runner runner;

    @Override
    public void onEnable() {
        try {
            setupRunner();
            registerListeners();
            checkUpdates();
//            showWarning();

            getLogger().info("NoMoreTNTChainCrash has been enabled successfully!");
        } catch (Exception e) {
            getLogger().severe("Failed to enable NoMoreTNTChainCrash: " + e.getMessage());
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    private void setupRunner() {
        runner = new FoliaRunner(this);
        getLogger().info("Initialized " + runner.getPlatformName() + " scheduler support");

        if (runner.isFolia()) {
            getLogger().info("Folia detected - using RegionScheduler and EntityScheduler for optimal performance");
        }
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new EntityExplosionListener(), this);
    }

    private void checkUpdates() {
        UpdateChecker updateChecker = new UpdateChecker(this, "alex2276564/NoMoreTNTChainCrash", runner);
        updateChecker.checkForUpdates();
    }

//    private void showWarning() {
//        getLogger().info( "");
//        getLogger().info("NoMoreTNTChainCrash activated successfully!");
//        getLogger().info( "");
//        getLogger().warning("WARNING! This plugin removes all tnt's that");
//        getLogger().warning("fell within the explosion radius of another tnt!");
//        getLogger().info( "");
//    }

    @Override
    public void onDisable() {
        if (runner != null) {
            runner.cancelAllTasks();
        }
    }
}
