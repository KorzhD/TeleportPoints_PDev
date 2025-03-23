package dmytrok.dkteleportpointsplugin.standdeleter;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ArmorStandDeleter implements Listener {
    @EventHandler
    public void onPlayerHitArmorStand(EntityDamageByEntityEvent event) {
        if (event.getDamager() == null || !(event.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getDamager();
        if(!player.hasPermission("op")) {
            return;
        }

        if (player.getInventory().getItemInMainHand() == null) {
            return;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.getType().equals(Material.STICK)) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().hasDisplayName()) {
            return;
        }
        if (!item.getItemMeta().getDisplayName().equals("TP Point Deleter")) {
            return;
        }

        if (event.getEntity() == null || !(event.getEntity() instanceof ArmorStand)) {
            return;
        }
        ArmorStand armorStand = (ArmorStand) event.getEntity();


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
            if (Math.floor(x1) != 2 || Math.floor(y1) != 4 || Math.floor(z1) != 2) {
                continue;
            } else {
                coordinates = stand;
            }
        }
        if (coordinates == null) {
            return;
        }

        armorStand.remove();
        coordinates.remove();

    }
}
