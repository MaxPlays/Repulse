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

/**
 * Created by MaxPlays on 03.01.2018.
 */
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
