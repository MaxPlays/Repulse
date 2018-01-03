# Repulse
Repulsing shield for players on a Minecraft Bukkit server

## Introduction ##
This Bukkit plugin was developed and tested using the Spigot API for Minecraft 1.8.9. It ads a per-player shield that knocks back all players in a certain radius around the player that has an activated shield. You can configure the plugin in the config.yml file that will be generated in the plugin's working directory (plugins/Repulse).

## Installation ##
Download the latest release of the plugin [here](https://github.com/MaxPlays/Repulse/releases/latest) and drop it into your plugins folder. Restart your server and the plugin should be up and running.

## Commands and permissions ##
/repulse ....... Toggle your shield. Permission: Repulsor.Toggle

## Config ##
The above-mentioned config file is easy to use. It consists of three major parts: General settings, shield-related settings and strings. I will go through each configurable feature explaining its effects on the plugin.  

**general.saveOnReload**: true by default, enables or disables the storage of the toggle states of the player's shields  
**general.updateCheckerEnabled**: true by default, enables or disables the automatic update checker  

**shield.strength**: 1.5 by default, change the force a player is knocked back with  
**shield.radius**: 5 by default, change the shield's radius  
**shield.ignoreOps**: false by default, If true, the shield will ignore all players with the permission repulse.toggle  
**shield.yStrength**: 0.7 by default, changes the force a player is launched into the air with when entering another player's shield area

**strings.prefix**: The prefix of all plugin-related messages  
**strings.noperm**: The message that is sent to a player when they execute an illegal command  
**strings.activate**: The message that is sent to a player when they activate their shield  
**strings.deactivate**: The message that is sent to a player when they deactivate their shield  
