/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ws.exception.ValidacaoException;
import ws.modelo.Professor;

/**
 *
 * @author Lucas
 */
public class ProfessorDao implements IDao{
    
    public List<Professor> consultar(){
        
        try {
         
            Statement ps = Conexao.getStatement();
            
            ResultSet rs =  ps.executeQuery("SELECT * FROM professor");
            
            List<Professor> vProfessor = new ArrayList<>();
            
            while (rs.next()) {
                Professor prof = new Professor();
                prof.setId(rs.getInt("id"));
                prof.setNome(rs.getString("nome"));
                prof.setRg(rs.getString("rg"));
                prof.setCpf(rs.getLong("cpf"));
                prof.setTitulo(rs.getInt("titulo"));

                vProfessor.add(prof);
            }
         
             return vProfessor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public void inserir(Professor i_professor) {

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO public.professor(nome, rg, cpf, titulo)");
            sql.append(" VALUES ('" + i_professor.getNome() + "', '" + i_professor.getRg() + "', " + i_professor.getCpf() + ", " + i_professor.getTitulo() + ");");

            Conexao.getStatement().execute(sql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void validar(Object o) throws ValidacaoException {
       Professor oProfessor = (Professor) o;
       
       if(oProfessor.getNome().startsWith("A")){
           throw new ValidacaoException("Não aceito pessoas com inicial A");
       }
    }
    
}
