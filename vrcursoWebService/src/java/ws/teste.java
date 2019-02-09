/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.util.List;
import ws.dao.ProfessorDao;
import ws.modelo.Professor;

/**
 *
 * @author Lucas
 */
public class teste {
    
    public static void main(String[] args) {
        List<Professor> vProfessor = new ProfessorDao().consultar();
        
        
        System.out.println(new Gson().toJson(vProfessor));
    }
}
