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
            return true;
        }
        Player player = (Player) commandSender;

        if (!(player.hasPermission("tppoint.admin"))) {
            player.sendMessage("You must be an admin");
            return true;
        }
        Location location = player.getLocation();
        if (location == null || location.getWorld() == null) {
            player.sendMessage("Error: Tp Point can't be spawned in this place!");
            return true;
        }

        if (args == null || args.length < 4) {
            player.sendMessage("§4/settppoint [teleport/point/name] [x] [y] [z] [color]");
            return true;
        }

        for (int i = 1; i <= 3; i++) {
            if (args[i] == null) {
                return true;
            }
            if (!isNumeric(args[i], player)) {
                player.sendMessage("§4/settppoint [teleport/point/name] [x] [y] [z] [color]");
                return true;
            }
        }

        String name = args[0];

        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        int z = Integer.parseInt(args[3]);

        if(args[4] == null) {
            player.sendMessage("§4/settppoint [teleport/point/name] [x] [y] [z] [color]");
            return true;
        }

        String color = args[4].toLowerCase();
        String colorCode = "§0";
        switch (color) {
            case ("dark_red"):
                colorCode = "§4";
                break;
            case ("red"):
                colorCode = "§c";
                break;
            case ("gold"):
                colorCode = "§6";
                break;
            case ("yellow"):
                colorCode = "§e";
                break;
            case ("dark_green"):
                colorCode = "§2";
                break;
            case ("green"):
                colorCode = "§a";
                break;
            case ("aqua"):
                colorCode = "§b";
                break;
            case ("dark_aqua"):
                colorCode = "§3";
                break;
            case ("dark_blue"):
                colorCode = "§1";
                break;
            case ("blue"):
                colorCode = "§9";
                break;
            case ("light_purple"):
                colorCode = "§d";
                break;
            case ("dark_purple"):
                colorCode = "§5";
                break;
            case ("white"):
                colorCode = "§f";
                break;
            case ("gray"):
                colorCode = "§7";
                break;
            case ("dark_gray"):
                colorCode = "§8";
                break;
            case ("black"):
                colorCode = "§0";
                break;
        }


        TPPointEntity.setTPPoint(name, x, y, z, colorCode, player);
        return true;
    }

    private boolean isNumeric(String s, Player player) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            player.sendMessage("§4/settppoint [teleport/point/name] [x] [y] [z] [color]");
            return false;
        }

    }

}
