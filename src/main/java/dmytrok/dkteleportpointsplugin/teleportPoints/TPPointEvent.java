package dmytrok.dkteleportpointsplugin.teleportPoints;

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
        if(event.getPlayer() == null) {
            return;
        }
        Player player = event.getPlayer();
        if(event.getRightClicked() == null) {
            player.sendMessage("1NULL");
            return;
        }
        if(!(event.getRightClicked() instanceof ArmorStand)) {
            player.sendMessage("NOT A AS");
            return;
        }
        ArmorStand armorStand = (ArmorStand) event.getRightClicked();

        if(armorStand.getCustomName() == null) {
            player.sendMessage("2NULL");
            return;
        }
        if(!armorStand.hasArms()) {
            player.sendMessage("NO ARMS");
            return;
        }
        double x = armorStand.getRightArmPose().getX();
        double y = armorStand.getRightArmPose().getY();
        double z = armorStand.getRightArmPose().getZ();
        player.sendMessage("X: " + Math.floor(x) + ", Y: " + Math.floor(y) + ", Z: " + Math.floor(z));

        if(Math.floor(x) != 5 || Math.floor(y) != 2 || Math.floor(z) != 5) {
            player.sendMessage("NOT ANGLE");
            return;
        }
        if(armorStand.getNearbyEntities(0.5, 1, 0.5) == null) {
            player.sendMessage("3NULL");
            return;
        }
        List<Entity> entities = armorStand.getNearbyEntities(0.5, 1, 0.5);
        entities.removeIf(entity -> !(entity instanceof ArmorStand));

        ArmorStand coordinates = null;
        for(Entity entity : entities) {
            if(entity == null || !(entity instanceof ArmorStand)) {
                player.sendMessage("4NULL");
                return;
            }
            ArmorStand stand = (ArmorStand) entity;
            if(stand.getCustomName() == null) {
                player.sendMessage("5NULL");
                continue;
            }
            if(!stand.hasArms()) {
                player.sendMessage("NO ARMS");
                continue;
            }
            double x1 = stand.getRightArmPose().getX();
            double y1 = stand.getRightArmPose().getY();
            double z1 = stand.getRightArmPose().getZ();
            player.sendMessage("X: " + Math.floor(x1) + ", Y: " + Math.floor(y1) + ", Z: " + Math.floor(z1));
            if(Math.floor(x1) != 2 || Math.floor(y1) != 4 || Math.floor(z1) != 2) {
                player.sendMessage("NOT ANGLE111");
                continue;
            } else {
                coordinates = stand;
            }
        }
            if(coordinates == null) {
                player.sendMessage("6NULL");
                return;
            }

            String coordinatesName = coordinates.getCustomName();
            player.sendMessage(coordinatesName);



            
    }
}
