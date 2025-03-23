package dmytrok.dkteleportpointsplugin;

import dmytrok.dkteleportpointsplugin.standdeleter.ArmorStandDeleter;
import dmytrok.dkteleportpointsplugin.teleportPoints.TPPointCommand;
import dmytrok.dkteleportpointsplugin.teleportPoints.TPPointEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DK_TeleportPoints_Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("\u001B[32m!---------------TeleportPoints Plugin enabled---------------!\u001B[0m");

        getServer().getPluginManager().registerEvents(new TPPointEvent(), this);
        getServer().getPluginManager().registerEvents(new ArmorStandDeleter(), this);


        if (getCommand("settppoint") != null) {
            getCommand("settppoint").setExecutor(new TPPointCommand());
        } else {
            getLogger().info("Set TP Point ERROR");
        }

    }

    @Override
    public void onDisable() {
        getLogger().info("\u001B[32m!---------------TeleportPoints Plugin disabled---------------!\u001B[0m");
    }
}
