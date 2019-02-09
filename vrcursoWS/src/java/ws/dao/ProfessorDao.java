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
import ws.framework.exception.ValidacaoException;
import ws.framework.MensagensPadrao;
import ws.modelo.Professor;
import ws.vo.ProfessorFiltroVO;

/**
 *
 * @author Lucas
 */
public class ProfessorDao implements IDao {

    public List<Professor> consultar(ProfessorFiltroVO i_professorFiltro) throws Exception {

        Statement ps = Conexao.getStatement();

        boolean where = false;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM professor");

        if (!i_professorFiltro.getId().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" id = " + i_professorFiltro.getId());
        }

        if (!i_professorFiltro.getNome().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" nome = LIKE'%" + i_professorFiltro.getNome() + "%'");
        }

        if (!i_professorFiltro.getCpf().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" cpf = " + i_professorFiltro.getCpf());
        }

        if (!i_professorFiltro.getTitulo().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" titulo = " + i_professorFiltro.getTitulo());
        }

        ResultSet rs = ps.executeQuery(sql.toString());

        List<Professor> vProfessor = new ArrayList<>();
        
        if(!rs.next()){
            throw new ValidacaoException(MensagensPadrao.REGISTROS_NAO_ENCONTRADOS);
        }

        do {
            Professor prof = new Professor();
            prof.setId(rs.getInt("id"));
            prof.setNome(rs.getString("nome"));
            prof.setRg(rs.getString("rg"));
            prof.setCpf(rs.getLong("cpf"));
            prof.setTitulo(rs.getInt("titulo"));

            vProfessor.add(prof);
        } while (rs.next());

        return vProfessor;
    }

    public void salvar(Professor i_professor) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO public.professor(nome, rg, cpf, titulo)");
        sql.append(" VALUES ('" + i_professor.getNome() + "', '" + i_professor.getRg() + "', " + i_professor.getCpf() + ", " + i_professor.getTitulo() + ");");

        Conexao.getStatement().execute(sql.toString());

    }

    @Override
    public void validar(Object o) throws ValidacaoException {
        Professor oProfessor = (Professor) o;

        if (oProfessor.getNome().startsWith("A")) {
            throw new ValidacaoException("Não aceito pessoas com inicial A");
        }
    }

}
