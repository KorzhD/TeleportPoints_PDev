package dmytrok.dkteleportpointsplugin.teleportPoints;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.List;

public class TPPointEvent implements Listener {

    @EventHandler
    public void onPlayerInteractWithArmorStand(PlayerInteractAtEntityEvent event) {
        if (event.getPlayer() == null) {
            return;
        }
        Player player = event.getPlayer();
        if (event.getRightClicked() == null) {
            return;
        }
        if (!(event.getRightClicked() instanceof ArmorStand)) {
            return;
        }
        ArmorStand armorStand = (ArmorStand) event.getRightClicked();

        if (armorStand.getCustomName() == null) {
            return;
        }
        if (!armorStand.hasArms()) {
            return;
        }
        double x = armorStand.getRightArmPose().getX();
        double y = armorStand.getRightArmPose().getY();
        double z = armorStand.getRightArmPose().getZ();

        if (Math.floor(x) != 5 || Math.floor(y) != 2 || Math.floor(z) != 5) {
            return;
        }
        if (armorStand.getNearbyEntities(0.5, 1, 0.5) == null) {
            return;
        }
        List<Entity> entities = armorStand.getNearbyEntities(0.5, 1, 0.5);
        entities.removeIf(entity -> !(entity instanceof ArmorStand));

        ArmorStand coordinates = null;
        for (Entity entity : entities) {
            if (entity == null || !(entity instanceof ArmorStand)) {
                return;
            }
            ArmorStand stand = (ArmorStand) entity;
            if (stand.getCustomName() == null) {
                continue;
            }
            if (!stand.hasArms()) {
                continue;
            }
            double x1 = stand.getRightArmPose().getX();
            double y1 = stand.getRightArmPose().getY();
            double z1 = stand.getRightArmPose().getZ();
            player.sendMessage("X: " + Math.floor(x1) + ", Y: " + Math.floor(y1) + ", Z: " + Math.floor(z1));
            if (Math.floor(x1) != 2 || Math.floor(y1) != 4 || Math.floor(z1) != 2) {
                continue;
            } else {
                coordinates = stand;
            }
        }
        if (coordinates == null) {
            return;
        }

        String[] coordinatesName = coordinates.getCustomName().split("/");
        int x2 = Integer.parseInt(coordinatesName[0]);
        int y2 = Integer.parseInt(coordinatesName[1]);
        int z2 = Integer.parseInt(coordinatesName[2]);

        Location tpLocation = new Location(player.getWorld(), x2, y2, z2);

        player.teleport(tpLocation);


    }
}
