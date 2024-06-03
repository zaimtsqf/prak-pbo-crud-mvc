/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoData;

/**
 *
 * @author LENOVO
 */

import java.sql.*;
import java.util.*;
import Connection.Koneksi;
import DaoImplements.DataDaoImplements;
import Model.DataFilm;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoData implements DataDaoImplements {
    Connection con;
    
    final String select = "select * from film;";
    final String insert = "INSERT INTO film (judul,alur,penokohan,akting,nilai) VALUES (?,?,?,?,?);";
    final String update = "update film set alur=?, penokohan=?, akting=? where judul=?;";
    final String delete = "delete from film where judul=?;";
    
    public DaoData() {
        con = Koneksi.connection();
    }
    
    @Override
    public void insert(DataFilm Film) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, Film.getJudul());
            statement.setDouble(2, Film.getAlur());
            statement.setDouble(3, Film.getPenokohan());
            statement.setDouble(4, Film.getAkting());
            statement.setDouble(5, Film.getRating());
            statement.executeUpdate();
        } catch ( SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex ) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void update(DataFilm Film) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(update);
            statement.setDouble(1, Film.getAlur());
            statement.setDouble(2, Film.getPenokohan());
            statement.setDouble(3, Film.getAkting());
            statement.setString(4, Film.getJudul());
            statement.executeUpdate();
        } catch (SQLException ex ) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void delete(String judul) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(delete);
            statement.setString(1, judul);
            statement.executeUpdate();
         } catch (SQLException ex) {
            try (FileWriter writer = new FileWriter("error.log", true)) {
                writer.write("Error updating film: " + ex.getMessage() + "\n");
                writer.write(Arrays.toString(ex.getStackTrace()) + "\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public List<DataFilm> getAll() {
        List<DataFilm> dm = null;
        try {
            dm = new ArrayList<>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                DataFilm md = new DataFilm(rs.getString("judul"), rs.getDouble("alur"), rs.getDouble("penokohan"), rs.getDouble("akting"));
                dm.add(md);
            }
        } catch(SQLException ex){
            Logger.getLogger(DaoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dm;
    }
}