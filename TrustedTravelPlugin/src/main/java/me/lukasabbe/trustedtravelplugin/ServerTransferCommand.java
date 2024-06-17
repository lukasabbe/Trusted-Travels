package me.lukasabbe.trustedtravelplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.List;

public class ServerTransferCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length == 0 || !(commandSender instanceof Player)) return false;
        List<ServerObj> servers = TrustedTravelPlugin.instance.servers;
        Player player = (Player) commandSender;
        for(ServerObj server : servers){
            if(server.name.equals(args[0])){
                player.transfer(server.address, server.port);
                return true;
            }
        }
        return false;
    }
}
