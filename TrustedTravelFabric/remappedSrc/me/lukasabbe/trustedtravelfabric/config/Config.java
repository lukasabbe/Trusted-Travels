package me.lukasabbe.trustedtravelfabric.config;

import net.fabricmc.loader.api.FabricLoader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.yaml.snakeyaml.Yaml;

public class Config {

    public List<ServerObj> servers = new ArrayList<>();

    public Config(){
        try {
            createAndLoadConfig();
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    private void createAndLoadConfig() throws FileNotFoundException {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("servers.yml");
        if(!Files.exists(configPath)) createConfigFile(configPath);

        Yaml yaml = new Yaml();
        Map<String, Object> configMap = yaml.load(new FileReader(configPath.toFile()));

        for (var server : ((Map<String, Map<String, Object>>) configMap.get("servers")).entrySet()){
            servers.add(new ServerObj(
                    server.getKey(),
                    (String) server.getValue().get("address"),
                    (int) server.getValue().get("port")
            ));
        }
    }

    public void createConfigFile(Path configPath){
        FabricLoader.getInstance().getModContainer("trustedtravelfabric").ifPresent(modContainer -> {
            Path path = modContainer.findPath("config.yml").orElseThrow();
            try {
                Files.copy(path, configPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void reloadConfig(){
        servers.clear();
        try{
            createAndLoadConfig();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
