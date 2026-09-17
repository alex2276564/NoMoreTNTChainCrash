package uz.alex2276564.nomoretntchaincrash;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import uz.alex2276564.nomoretntchaincrash.listeners.EntityExplosionListener;
import uz.alex2276564.nomoretntchaincrash.utils.HttpUtils;
import uz.alex2276564.nomoretntchaincrash.utils.UpdateChecker;
import uz.alex2276564.nomoretntchaincrash.utils.runner.FoliaRunner;
import uz.alex2276564.nomoretntchaincrash.utils.runner.Runner;

import java.util.logging.Level;

public final class NoMoreTNTChainCrash extends JavaPlugin {

    private Runner runner;
    private HttpUtils httpUtils;

    @Override
    public void onEnable() {
        try {
            setupRunner();
            setupHttpClient();
            setupUpdateChecker();
            registerListeners();

            getLogger().info("NoMoreTNTChainCrash has been enabled successfully!");
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Failed to enable NoMoreTNTChainCrash", e);
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

    private void setupHttpClient() {
        this.httpUtils = new HttpUtils();
    }

    private void setupUpdateChecker() {
        UpdateChecker updateChecker;
        updateChecker = new UpdateChecker(
                getDescription().getName(),
                getDescription().getVersion(),
                "alex2276564/NoMoreTNTChainCrash",
                runner,
                httpUtils,
                getLogger()
        );
        updateChecker.checkForUpdates();
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new EntityExplosionListener(), this);
    }

    @Override
    public void onDisable() {
        if (runner != null) {
            runner.cancelAllTasks();
        }
    }
}
