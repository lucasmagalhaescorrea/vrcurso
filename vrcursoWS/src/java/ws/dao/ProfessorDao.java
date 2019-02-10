package ws.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vrcurso.framework.MensagensPadrao;
import vrcurso.framework.exception.ValidacaoException;
import ws.modelo.Professor;
import ws.vo.ProfessorFiltroVO;

public class ProfessorDao implements IDao {

    public List<Professor> consultar(ProfessorFiltroVO i_professorFiltro) throws Exception {

        Statement stm = Conexao.getStatement();
        ResultSet rst;
        List<Professor> vProfessor = new ArrayList<>();

        boolean where = false;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM professor");

        if (!i_professorFiltro.getId().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" id = " + i_professorFiltro.getId());
            where = true;
        }

        if (!i_professorFiltro.getNome().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" nome LIKE '%" + i_professorFiltro.getNome() + "%'");
            where = true;
        }

        if (!i_professorFiltro.getCpf().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" cpf = " + i_professorFiltro.getCpf());
            where = true;
        }

        sql.append(" ORDER BY nome");

        rst = stm.executeQuery(sql.toString());

        if (!rst.next()) {
            throw new ValidacaoException(MensagensPadrao.REGISTROS_NAO_ENCONTRADOS);
        }

        do {
            Professor prof = new Professor();
            prof.setId(rst.getInt("id"));
            prof.setNome(rst.getString("nome"));
            prof.setRg(rst.getString("rg"));
            prof.setCpf(rst.getLong("cpf"));
            prof.setTitulo(rst.getInt("titulo"));

            vProfessor.add(prof);
        } while (rst.next());

        return vProfessor;
    }

    public void remover(Professor i_professor) throws Exception {

        Statement stm = Conexao.getStatement();
        
        stm.executeUpdate("DELETE FROM professor WHERE id = " + i_professor.getId());
    }

    public Professor salvar(Professor i_professor) throws Exception {
        Statement stm = Conexao.getStatement();
        StringBuilder sql = new StringBuilder();
        ResultSet rst;

        if (i_professor.getId() == 0) {

            sql.append("INSERT INTO professor(nome, rg, cpf, titulo)");
            sql.append(" VALUES ('" + i_professor.getNome() + "', '" + i_professor.getRg() + "', " + i_professor.getCpf() + ", " + i_professor.getTitulo() + ");");

            stm.execute(sql.toString());

            rst = stm.executeQuery("select currval('professor_id_seq')");
            rst.next();

            i_professor.setId(rst.getInt(1));

        } else {

            sql.append("UPDATE professor");
            sql.append(" SET nome = '" + i_professor.getNome() + "', rg = '" + i_professor.getRg() + "', cpf = " + i_professor.getCpf() + ", titulo =  " + i_professor.getTitulo());
            sql.append(" WHERE id = " + i_professor.getId());

            stm.executeUpdate(sql.toString());
        }
        
        return i_professor;

    }

    @Override
    public void validar(Object o) throws Exception {
        Professor oProfessor = (Professor) o;

        if (oProfessor.getNome().startsWith("A")) {
            throw new ValidacaoException("Não aceito pessoas com inicial A");
        }
    }

    @Override
    public void validarReferencias(Object o) throws Exception {
        Professor oProfessor = (Professor) o;
        
        Statement stm = Conexao.getStatement();
        
        ResultSet rst = stm.executeQuery("SELECT id FROM disciplina WHERE id_professor = " + oProfessor.getId());
        
        if(rst.next()){
            throw new ValidacaoException("Professor não pode ser excluído, pois está vinculado à disciplina " + rst.getInt(1));
        }
    }

}
