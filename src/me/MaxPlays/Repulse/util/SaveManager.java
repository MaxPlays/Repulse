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
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class SaveManager {

    public static void save() {
        for(Map.Entry<UUID, Integer> e: Repulse.enabled.entrySet()){
            Repulse.sql.update("DELETE FROM players WHERE uuid='" + e.getKey().toString() + "';");
            Repulse.sql.update("INSERT INTO players VALUES('" + e.getKey().toString() + "', " + e.getValue() + ");");
        }
    }

    public static void load(Player p){
        if(!Repulse.enabled.containsKey(p.getUniqueId())){
            if(Repulse.save){
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        ResultSet rs = Repulse.sql.query("SELECT * FROM players WHERE uuid='" + p.getUniqueId().toString() + "';");
                        try {
                            if(rs.next()){
                                if(p.hasPermission("repulse.toggle")){
                                    Repulse.enabled.put(p.getUniqueId(), rs.getInt("enabled"));
                                }else {
                                    Repulse.sql.update("DELETE FROM players WHERE uuid='" + p.getUniqueId().toString() + "';");
                                }
                            }else{
                                if(p.hasPermission("repulse.toggle"))
                                    Repulse.enabled.put(p.getUniqueId(), 0);
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }.runTaskAsynchronously(Repulse.instance);
            }else{
                if(p.hasPermission("repulse.toggle"))
                    Repulse.enabled.put(p.getUniqueId(), 0);
            }
        }else if(!p.hasPermission("repulse.toggle")){
            Repulse.enabled.remove(p.getUniqueId());
            Repulse.enabled.put(p.getUniqueId(), 0);
        }
    }

}
