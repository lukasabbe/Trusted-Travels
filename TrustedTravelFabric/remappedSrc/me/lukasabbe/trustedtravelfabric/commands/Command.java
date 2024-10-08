package me.lukasabbe.trustedtravelfabric.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;

public interface Command {
    LiteralArgumentBuilder<ServerCommandSource> createCommand();
    int runCommand(CommandContext<ServerCommandSource> ctx);
}
