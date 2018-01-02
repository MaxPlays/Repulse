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

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.*;


public class SQL {

    private String filename = "";

    private JavaPlugin plugin;

    private Connection con;

    public SQL(String filename, JavaPlugin plugin){
        this.filename = filename;
        this.plugin = plugin;
    }

    public void connect(){
        try{
            Class.forName("org.sqlite.JDBC");

            File dir = new File("plugins/" + plugin.getDescription().getName());
            if(!dir.exists()){
                dir.mkdir();
            }
            con = DriverManager.getConnection("jdbc:sqlite:plugins/" + plugin.getDescription().getName() + "/" + this.filename + ".db");
        }catch(Exception e){
            System.out.println("[SQL] Connection failed! Error: " + e.getMessage());
        }
    }

    public void disconnect(){
        try{
            if(connected())
                con.close();
        }catch(SQLException e){
            System.out.println("[SQL] Error while disconnecting: " + e.getMessage());
        }
    }
    public boolean connected(){
        return con == null ? false : true;
    }
    public void update(String qry){
        try {
            Statement st = con.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public ResultSet query(String qry){
        try {
            Statement st = con.createStatement();
            return st.executeQuery(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}