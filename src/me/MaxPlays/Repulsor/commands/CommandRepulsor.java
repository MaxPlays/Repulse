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

package me.MaxPlays.Repulsor.commands;

import me.MaxPlays.Repulsor.main.Repulsor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRepulsor implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("repulsor") && sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("repulsor.toggle")){
                if(Repulsor.enabled.containsKey(p.getUniqueId())){
                    if(Repulsor.enabled.get(p.getUniqueId()) == 0){
                        Repulsor.enabled.remove(p.getUniqueId());
                        Repulsor.enabled.put(p.getUniqueId(), 1);
                        p.sendMessage(Repulsor.activate);
                    }else{
                        Repulsor.enabled.remove(p.getUniqueId());
                        Repulsor.enabled.put(p.getUniqueId(), 0);
                        p.sendMessage(Repulsor.deactivate);
                    }
                }else{
                    Repulsor.enabled.put(p.getUniqueId(), 1);
                    p.sendMessage(Repulsor.activate);
                }
            }else{
                p.sendMessage(Repulsor.noperm);
            }
        }

        return true;
    }
}
