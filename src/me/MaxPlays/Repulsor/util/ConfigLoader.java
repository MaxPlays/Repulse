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

package me.MaxPlays.Repulsor.util;

import me.MaxPlays.Repulsor.main.Repulsor;
import org.bukkit.ChatColor;

public class ConfigLoader {

    public void load() {
        Repulsor.instance.getConfig().options().copyDefaults(true);
        Repulsor.instance.saveConfig();

        Repulsor.save = Repulsor.instance.getConfig().getBoolean("general.saveOnReload");
        Repulsor.ignoreOps =  Repulsor.instance.getConfig().getBoolean("shield.ignoreOps");

        Repulsor.strength =  Repulsor.instance.getConfig().getDouble("shield.strength");
        Repulsor.radius =  Repulsor.instance.getConfig().getDouble("shield.radius");

        Repulsor.prefix = t( Repulsor.instance.getConfig().getString("strings.prefix"));
        Repulsor.noperm = Repulsor.prefix + " " + t( Repulsor.instance.getConfig().getString("strings.noperm"));
        Repulsor.activate = Repulsor.prefix + " " + t( Repulsor.instance.getConfig().getString("strings.activate"));
        Repulsor.deactivate = Repulsor.prefix + " " + t( Repulsor.instance.getConfig().getString("strings.deactivate"));

    }

    private String t(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
