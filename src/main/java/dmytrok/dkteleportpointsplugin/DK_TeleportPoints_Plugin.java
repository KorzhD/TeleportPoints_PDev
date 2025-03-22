package dmytrok.dkteleportpointsplugin;

import dmytrok.dkteleportpointsplugin.teleportPoints.TPPointCommand;
import dmytrok.dkteleportpointsplugin.teleportPoints.TPPointEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DK_TeleportPoints_Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("\u001B[32m!---------------TeleportPoints Plugin enabled---------------!\u001B[0m");

        getServer().getPluginManager().registerEvents(new TPPointEvent(), this);


        if (getCommand("settppoint") != null) {
            getCommand("settppoint").setExecutor(new TPPointCommand());
        } else {
            getLogger().info("Set TP Point ERROR");
        }

    }

    @Override
    public void onDisable() {
        getLogger().info("!---------------TeleportPoints Plugin disabled---------------!");
    }
}
