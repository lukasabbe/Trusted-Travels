package me.lukasabbe.trustedtravelfabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Arrays;
import java.util.List;

public class Commands {
    public static List<Command> commands = List.of(new ServerCommand());
    public static void createCommands(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess, net.minecraft.server.command.CommandManager.RegistrationEnvironment registrationEnvironment) {
        commands.forEach(command -> serverCommandSourceCommandDispatcher.register(command.createCommand()));
    }
}
