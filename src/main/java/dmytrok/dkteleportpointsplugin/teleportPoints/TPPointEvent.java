package dmytrok.dkteleportpointsplugin.teleportPoints;

import dmytrok.dkteleportpointsplugin.DK_TeleportPoints_Plugin;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TPPointEvent implements Listener {

    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 4000;

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
        if (isOnCooldown(player)) {
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

        String[] coordinatesName = coordinates.getCustomName().split("/");
        int x2 = Integer.parseInt(coordinatesName[0]);
        int y2 = Integer.parseInt(coordinatesName[1]);
        int z2 = Integer.parseInt(coordinatesName[2]);

        Location tpLocation = new Location(player.getWorld(), x2, y2, z2);

        String color = armorStand.getCustomName().substring(0, 2);

        setCooldown(player);

        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 100));

        //door
        if(color.equals("§a") || color.equals("§c")) {
            player.playSound(player.getLocation(), "custom.door_custom", SoundCategory.MASTER, 1 ,1);
            playSound(player, "custom.door_close_custom", tpLocation);
        }
        //gate
        else if(color.equals("§2") || color.equals("§4")) {
            player.playSound(player.getLocation(), "custom.gate_custom", SoundCategory.MASTER, 1 ,1);
            playSound(player, "custom.gate_close_custom", tpLocation);

        } else {
            player.teleport(tpLocation);
        }

    }

    private void playSound(Player player, String soundName, Location tpLocation) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(tpLocation);
                player.playSound(player.getLocation(), soundName, SoundCategory.MASTER, 1, 1);
            }
        }.runTaskLater(DK_TeleportPoints_Plugin.getInstance(), 40);
    }
    private boolean isOnCooldown(Player player) {
        if (!cooldowns.containsKey(player)) {
            return false;
        }
        long lastUsed = cooldowns.get(player);
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastUsed) < cooldownTime;
    }

    private void setCooldown(Player player) {
        cooldowns.put(player, System.currentTimeMillis());
    }
}
