package dmytrok.dkteleportpointsplugin.teleportPoints;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;

public class TPPointEntity {

    public static void setTPPoint(String name, int x, int y, int z, Player player) {

        Location location = player.getLocation();
        World world = player.getWorld();



        String fullName = name.replace("/", " ");

        ArmorStand tpPoint = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
        tpPoint.setVisible(false);
        tpPoint.setGravity(false);
        tpPoint.setInvulnerable(true);
        tpPoint.setCustomName(fullName);
        tpPoint.setCustomNameVisible(true);
        tpPoint.setArms(true);
        EulerAngle angle = new EulerAngle(56, 78, 24);
        tpPoint.setRightArmPose(angle);

        ArmorStand coordinates = (ArmorStand) world.spawnEntity(location.add(0, -0.5, 0), EntityType.ARMOR_STAND);
        coordinates.setVisible(false);
        coordinates.setGravity(false);
        coordinates.setInvulnerable(true);
        String coordinatesName = x + "/" + y + "/" + z;
        player.sendMessage("Coordinates: " + coordinatesName);
        coordinates.setCustomName(coordinatesName);
        coordinates.setCustomNameVisible(false);
        coordinates.setArms(true);
        EulerAngle angleCoordinates = new EulerAngle(59, 92, 28);
        coordinates.setRightArmPose(angleCoordinates);
    }
}

