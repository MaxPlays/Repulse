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

package me.MaxPlays.Repulse.updater;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.MaxPlays.Repulse.main.Repulse;
import org.bukkit.scheduler.BukkitRunnable;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker {

    private boolean enabled;
    private static String url = "https://api.github.com/repos/MaxPlays/Repulse/releases/latest";

    public UpdateChecker(boolean enabled){
        this.enabled = enabled;
    }

    public void check(UpdateCallback c){
        if(!enabled)
            return;
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();
                    BufferedReader read = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String s = "";
                    while((s = read.readLine()) != null)
                        sb.append(s).append(" ");
                    con.disconnect();
                    JsonObject o = new JsonParser().parse(sb.toString()).getAsJsonObject();
                    String version = o.get("tag_name").getAsString();
                    if(!version.equalsIgnoreCase(Repulse.instance.getDescription().getVersion())) {
                        c.onUpdateFound(Repulse.instance.getDescription().getVersion(), version, "https://dev.bukkit.org/projects/repulse");
                    }
                    else {
                        c.onUpToDate(Repulse.instance.getDescription().getVersion());
                    }
                } catch (IOException e) {
                    printMessage("Failed to check for update! Error message:" + e.getMessage());
                }
            }
        }.runTaskAsynchronously(Repulse.instance);
    }

    public interface UpdateCallback{
        void onUpdateFound(String current, String latest, String url);
        void onUpToDate(String current);
    }

    public static void printMessage(String msg){
        System.out.println("------------- [Repulse] -------------");
        String[] a = msg.split("::");
        for(String s: a)
            System.out.println(s);
    }

}
