/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoImplements;

/**
 *
 * @author LENOVO
 */

import Model.DataFilm;
import java.util.List;

public interface DataDaoImplements {
    
    public void insert(DataFilm Film);
    public void update(DataFilm Film);
    public void delete(String judul);
    public List<DataFilm> getAll();
    
}
