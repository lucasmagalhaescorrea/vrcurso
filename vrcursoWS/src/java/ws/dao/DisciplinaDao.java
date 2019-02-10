package ws.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vrcurso.framework.MensagensPadrao;
import vrcurso.framework.exception.ValidacaoException;
import ws.modelo.Aluno;
import ws.modelo.Disciplina;
import ws.vo.AlunoFiltroVO;
import ws.vo.DisciplinaFiltroVO;

public class DisciplinaDao implements IDao {

    public List<Disciplina> consultar(DisciplinaFiltroVO i_disciplinaFiltro) throws Exception {

        Statement stm = Conexao.getStatement();
        ResultSet rst;
        List<Disciplina> vDisciplina = new ArrayList<>();;

        boolean where = false;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM disciplina");

        if (!i_disciplinaFiltro.getId().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" id = " + i_disciplinaFiltro.getId());
            where = true;
        }

        if (!i_disciplinaFiltro.getDescricao().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" descricao LIKE '%" + i_disciplinaFiltro.getDescricao()+ "%'");
            where = true;
        }

        if (!i_disciplinaFiltro.getDiaSemana().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" diasemana = " + i_disciplinaFiltro.getDiaSemana());
            where = true;
        }

        if (!i_disciplinaFiltro.getPeriodo().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" periodo = " + i_disciplinaFiltro.getPeriodo());
            where = true;
        }

        sql.append(" ORDER BY descricao");

        rst = stm.executeQuery(sql.toString());

        if (!rst.next()) {
            throw new ValidacaoException(MensagensPadrao.REGISTROS_NAO_ENCONTRADOS);
        }

        do {
            Disciplina oDisciplina = new Disciplina();
            oDisciplina.setId(rst.getInt("id"));
            //continuas

            vDisciplina.add(oDisciplina);
        } while (rst.next());

        return vDisciplina;
    }

    public void remover(Aluno i_aluno) throws Exception {

        Statement stm = Conexao.getStatement();

        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM aluno WHERE id = " + i_aluno.getId());

        stm.executeUpdate(sql.toString());
    }

    public Aluno salvar(Aluno i_aluno) throws Exception {
        StringBuilder sql = new StringBuilder();
        Statement stm = Conexao.getStatement();
        ResultSet rst;

        if (i_aluno.getId() == 0) {

            sql.append("INSERT INTO aluno(matricula, nome, rg, cpf)");
            sql.append(" VALUES (" + i_aluno.getMatricula() + ",'" + i_aluno.getNome() + "', '" + i_aluno.getRg() + "', " + i_aluno.getCpf() + ");");

            stm.execute(sql.toString());

            rst = Conexao.getStatement().executeQuery("select currval('aluno_id_seq')");
            rst.next();

            i_aluno.setId(rst.getInt(1));

        } else {

            sql.append("UPDATE aluno");
            sql.append(" SET matricula = " + i_aluno.getMatricula() + ", nome = '" + i_aluno.getNome() + "', rg = '" + i_aluno.getRg() + "', cpf = " + i_aluno.getCpf());
            sql.append(" WHERE id = " + i_aluno.getId());

            stm.executeUpdate(sql.toString());
        }

        return i_aluno;

    }

    @Override
    public void validar(Object o) throws Exception {
        Aluno oAluno = (Aluno) o;

        
    }

    @Override
    public void validarReferencias(Object o) throws Exception {
        Aluno oAluno = (Aluno) o;

        Statement stm = Conexao.getStatement();

        ResultSet rst = stm.executeQuery("SELECT id FROM matricula WHERE id_aluno = " + oAluno.getId());

        if (rst.next()) {
            throw new ValidacaoException("Este aluno não pode ser excluído, pois está matriculado em curso(s) ");
        }
    }

}
