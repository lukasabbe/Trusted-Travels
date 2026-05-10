# [Download](https://modrinth.com/plugin/trusted-travels)

# What is it?
This is a mod/plugin made for jumping between your servers. It's orginally made for a survival/creative combo. Use this if you don't want to set up a proxy server.

# Config
![A config](https://cdn.modrinth.com/data/cached_images/496ba7e9ac415214de1f9f514ccf398632b46088.png)
_Ex. photo_


```
servers:
  #add as many servers as needed
  <name of server>:
    address: <ip for server>
    port: <port for server, Minecraft default port is 25565>
```

On Paper servers the config file is located under `plugins/TrustedTravelPlugin/`.

On Fabric servers the config file is located under `config/`.

# Commands

```
/server <name> - The name is the one you put in the config
/tt reload - Reload to current config
```
