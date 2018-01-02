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

package me.MaxPlays.Repulse.listeners;

import me.MaxPlays.Repulse.main.Repulse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class ShieldManager {

    public static BukkitRunnable run = new BukkitRunnable() {
        @Override
        public void run() {
            ArrayList<UUID> list = new ArrayList<>();
            for(Map.Entry<UUID, Integer> e: Repulse.enabled.entrySet()){
                if(Bukkit.getPlayer(e.getKey()) != null){
                    Player p = Bukkit.getPlayer(e.getKey());
                    if(p.hasPermission("repulse.toggle")){
                        if(e.getValue() == 1){
                            for(Player pl: Bukkit.getOnlinePlayers()){
                                if(Repulse.ignoreOps && pl.hasPermission("repulse.toggle"))
                                    continue;
                                if(!p.getName().equals(pl.getName()) && p.getLocation().distance(pl.getLocation()) <= Repulse.radius){
                                    Vector v = pl.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(Repulse.strength).setY(1);
                                    pl.setVelocity(v);
                                }
                            }
                        }
                    }else{
                        if(!list.contains(e.getKey()))
                            list.add(e.getKey());
                    }
                }
            }
            for(UUID id: list)
                Repulse.enabled.remove(id);
        }
    };

}
