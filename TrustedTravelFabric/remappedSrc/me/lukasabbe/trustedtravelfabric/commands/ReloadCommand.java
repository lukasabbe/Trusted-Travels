package me.lukasabbe.trustedtravelfabric.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import me.lukasabbe.trustedtravelfabric.TrustedTravelFabric;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ReloadCommand implements Command{
    @Override
    public LiteralArgumentBuilder<ServerCommandSource> createCommand() {
        return CommandManager
                .literal("tt")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(1))
                .then(CommandManager
                        .literal("reload")
                        .executes(this::runCommand));
    }

    @Override
    public int runCommand(CommandContext<ServerCommandSource> ctx) {
        TrustedTravelFabric.serverConfig.reloadConfig();
        ctx.getSource().sendFeedback(()-> Text.literal("Reloaded config"),false);
        return 1;
    }
}
