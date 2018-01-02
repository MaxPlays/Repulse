# Repulsor
Repulsive shield for players on a Minecraft Bukkit server

## Introduction ##
This Bukkit plugin was developed and tested using the Spigot API for Minecraft 1.8.9. It ads a per-player shield that knocks back all players in a certain radius around the player that has an activated shield. You can configure the plugin in the config.yml file that will be generated in the plugin's working directory (plugins/Repulsor).

## Installation ##
Download the latest release of the plugin [here](https://github.com/MaxPlays/Repulsor/releases/latest) and drop it into your plugins folder. Restart your server and the plugin should be up and running.

## Commands and permissions ##
/repulsor ....... Toggle your shield. Permission: Repulsor.Toggle

## Config ##
The above-mentioned config file is easy to use. It consists of three major parts: General settings, shield-related settings and strings. I will go through each configurable feature explaining its effects on the plugin.

general.saveOnReload: false by default, enables or disables the storage of the toggle states of the player's shields

shield.strength: 1.5 by default, change the force a player is knocked back with
shield.radius: 5 by default, change the shield's radius
shield.ignoreOps: false by default, If true, the shield will ignore all players with the permission repulsor.toggle

strings.prefix: The prefix of all plugin-related messages
strings.noperm: The message that is sent to a player when they execute an illegal command
strings.activate: The message that is sent to a player when they activate their shield
strings.deactivate: The message that is sent to a player when they deactivate their shield
