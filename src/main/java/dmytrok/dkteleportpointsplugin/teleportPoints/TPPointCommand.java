package dmytrok.dkteleportpointsplugin.teleportPoints;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPPointCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player");
            return false;
        }
        Player player = (Player) commandSender;

        if(!(player.hasPermission("tppoint.admin"))) {
            player.sendMessage("You must be an admin");
            return false;
        }
        Location location = player.getLocation();
        if(location == null || location.getWorld() == null) {
            player.sendMessage("Error: Tp Point can't be spawned in this place!");
            return false;
        }

        if(args == null || args.length < 3) {
            player.sendMessage("§4/settppoint [teleport/point/name] [x] [y] [z]");
            return false;
        }

        for (int i = 1; i <= 3; i++) {
            if(args[i] == null) {
                return false;
            }
            if(!isNumeric(args[i], player)) {
                player.sendMessage("§4/settppoint [teleport/point/name] [x] [y] [z]");
                return false;
            }
        }

        String name = args[0];

        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        int z = Integer.parseInt(args[3]);

        TPPointEntity.setTPPoint(name, x, y, z, player);
        return true;
    }

    private boolean isNumeric(String s, Player player) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            player.sendMessage("§4/settppoint [teleport/point/name] [x] [y] [z]");
            return false;
        }

    }

}
