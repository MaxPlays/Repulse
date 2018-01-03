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

package me.MaxPlays.Repulse.util;

import me.MaxPlays.Repulse.main.Repulse;
import org.bukkit.ChatColor;

public class ConfigLoader {

    public void load() {
        Repulse.instance.getConfig().options().copyDefaults(true);
        Repulse.instance.saveConfig();

        Repulse.save = Repulse.instance.getConfig().getBoolean("general.saveOnReload");
        Repulse.ignoreOps =  Repulse.instance.getConfig().getBoolean("shield.ignoreOps");
        Repulse.checkUpdates = Repulse.instance.getConfig().getBoolean("general.updateCheckerEnabled");

        Repulse.strength =  Repulse.instance.getConfig().getDouble("shield.strength");
        Repulse.radius =  Repulse.instance.getConfig().getDouble("shield.radius");
        Repulse.yStrength = Repulse.instance.getConfig().getDouble("shield.yStrength");

        Repulse.prefix = t( Repulse.instance.getConfig().getString("strings.prefix"));
        Repulse.noperm = Repulse.prefix + " " + t( Repulse.instance.getConfig().getString("strings.noperm"));
        Repulse.activate = Repulse.prefix + " " + t( Repulse.instance.getConfig().getString("strings.activate"));
        Repulse.deactivate = Repulse.prefix + " " + t( Repulse.instance.getConfig().getString("strings.deactivate"));

    }

    private String t(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
