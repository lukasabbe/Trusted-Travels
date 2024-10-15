package me.lukasabbe.trustedtravelfabric.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import me.lukasabbe.trustedtravelfabric.TrustedTravelFabric;
import me.lukasabbe.trustedtravelfabric.config.ServerObj;
import net.minecraft.server.command.ServerCommandSource;

import java.util.concurrent.CompletableFuture;

public class ServerSuggestionProvider implements SuggestionProvider<ServerCommandSource> {

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) throws CommandSyntaxException {
        for(ServerObj server : TrustedTravelFabric.serverConfig.servers){
            builder.suggest(server.name);
        }
        return builder.buildFuture();
    }
}
