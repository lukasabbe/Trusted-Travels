package me.lukasabbe.trustedtravelfabric.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import me.lukasabbe.trustedtravelfabric.TrustedTravelFabric;
import me.lukasabbe.trustedtravelfabric.config.ServerObj;
import net.minecraft.network.packet.s2c.common.ServerTransferS2CPacket;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Optional;

public class ServerCommand implements Command {

    @Override
    public LiteralArgumentBuilder<ServerCommandSource> createCommand() {
        return CommandManager.literal("server")
                .then(CommandManager
                        .argument("servers", StringArgumentType.word())
                        .suggests(new ServerSuggestionProvider())
                        .executes(ctx -> runCommand(ctx)));
    }

    @Override
    public int runCommand(CommandContext<ServerCommandSource> ctx) {
        List<ServerObj> servers = TrustedTravelFabric.serverConfig.servers;
        String server = StringArgumentType.getString(ctx,"servers");
        Optional<ServerObj> OptionalServerObj = servers.stream().filter(args -> args.name.equals(server)).findFirst();
        if(OptionalServerObj.isEmpty()){
            ctx.getSource().sendError(Text.literal("There is no server with that name"));
            return 0;
        }
        ServerObj serverObj = OptionalServerObj.get();
        ServerTransferS2CPacket transferPacket = new ServerTransferS2CPacket(serverObj.address, serverObj.port);
        if(!ctx.getSource().isExecutedByPlayer()) {
            ctx.getSource().sendError(Text.literal("Only players can execute this command"));
            return 0;
        }
        ServerPlayerEntity player = ctx.getSource().getPlayer();
        player.networkHandler.sendPacket(transferPacket);
        return 1;
    }
}
