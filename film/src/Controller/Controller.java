/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author LENOVO
 */

import View.NewJFrame;
import java.util.List;
import DaoData.DaoData;
import DaoImplements.DataDaoImplements;
import Model.DataFilm;
import Model.TabelDataFilm;



public class Controller {
    NewJFrame frame;
    DataDaoImplements impldatafilm;
    List<DataFilm> dp;
    
    public Controller (NewJFrame frame){
        this.frame = frame;
        impldatafilm = new DaoData();
        dp = impldatafilm.getAll();
    }
    public void isitabel(){
        dp = impldatafilm.getAll();
        TabelDataFilm mp = new TabelDataFilm(dp);
        frame.getTableData().setModel(mp);
    }
    
    public void insert(){
        DataFilm dp = new DataFilm(frame.getJudul().getText(), 
        Double.parseDouble(frame.getAlur().getText()), 
        Double.parseDouble(frame.getPenokohan().getText()), 
        Double.parseDouble(frame.getAkting().getText()));
        impldatafilm.insert(dp);
    }
     public void update(){
        DataFilm dp = new DataFilm(frame.getJudul().getText(), 
        Double.parseDouble(frame.getAlur().getText()), 
        Double.parseDouble(frame.getPenokohan().getText()), 
        Double.parseDouble(frame.getAkting().getText()));
        impldatafilm.update(dp);
    }
     public void delete(){
        String judul = (frame.getJudul().getText());
        impldatafilm.delete(judul);
    }
    
}