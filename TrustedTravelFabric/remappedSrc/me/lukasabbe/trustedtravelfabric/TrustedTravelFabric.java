package me.lukasabbe.trustedtravelfabric;

import me.lukasabbe.trustedtravelfabric.commands.Commands;
import me.lukasabbe.trustedtravelfabric.config.Config;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class TrustedTravelFabric implements DedicatedServerModInitializer {
    public static Config serverConfig;
    @Override
    public void onInitializeServer() {
        serverConfig = new Config();
        CommandRegistrationCallback.EVENT.register(Commands::createCommands);
    }
}
