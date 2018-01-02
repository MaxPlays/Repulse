/**
 *    Created by MaxPlays on 02.01.2018.
 *
 *   Copyright 2018 Maximilian Negedly
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package me.MaxPlays.Repulsor.main;

import me.MaxPlays.Repulsor.commands.CommandRepulsor;
import me.MaxPlays.Repulsor.listeners.JoinListener;
import me.MaxPlays.Repulsor.listeners.ShieldManager;
import me.MaxPlays.Repulsor.util.ConfigLoader;
import me.MaxPlays.Repulsor.util.SQL;
import me.MaxPlays.Repulsor.util.SaveManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;


public class Repulsor extends JavaPlugin{

    public static String prefix, noperm, activate, deactivate;
    public static double strength, radius;
    public static boolean ignoreOps, save;

    public static HashMap<UUID, Integer> enabled = new HashMap<>();
    public static SQL sql;

    public static Repulsor instance;

    public void onEnable(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);

        getCommand("repulsor").setExecutor(new CommandRepulsor());

        instance = this;

        new ConfigLoader().load();

        if(save) {
            sql = new SQL("saves", this);
            sql.connect();
            sql.update("CREATE TABLE IF NOT EXISTS players(uuid VARCHAR(64), enabled INT);");
        }

        for(Player pl: Bukkit.getOnlinePlayers())
            SaveManager.load(pl);

        ShieldManager.run.runTaskTimer(this, 0, 5);
    }

    public void onDisable(){
        if(save) {
            SaveManager.save();
            sql.disconnect();
        }
    }

}
