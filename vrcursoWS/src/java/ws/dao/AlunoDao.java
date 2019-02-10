package ws.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vrcurso.framework.MensagensPadrao;
import vrcurso.framework.exception.ValidacaoException;
import ws.modelo.Aluno;
import ws.vo.AlunoFiltroVO;

public class AlunoDao implements IDao {

    public List<Aluno> consultar(AlunoFiltroVO i_alunoFiltro) throws Exception {

        Statement stm = Conexao.getStatement();
        ResultSet rst;
        List<Aluno> vAluno = new ArrayList<>();;

        boolean where = false;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM aluno");

        if (!i_alunoFiltro.getId().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" id = " + i_alunoFiltro.getId());
            where = true;
        }

        if (!i_alunoFiltro.getMatricula().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" matricula = " + i_alunoFiltro.getMatricula());
            where = true;
        }

        if (!i_alunoFiltro.getNome().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" nome LIKE '%" + i_alunoFiltro.getNome() + "%'");
            where = true;
        }

        if (!i_alunoFiltro.getCpf().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" cpf = " + i_alunoFiltro.getCpf());
            where = true;
        }

        sql.append(" ORDER BY nome");

        rst = stm.executeQuery(sql.toString());

        if (!rst.next()) {
            throw new ValidacaoException(MensagensPadrao.REGISTROS_NAO_ENCONTRADOS);
        }

        do {
            Aluno oAluno = new Aluno();
            oAluno.setId(rst.getInt("id"));
            oAluno.setMatricula(rst.getLong("matricula"));
            oAluno.setNome(rst.getString("nome"));
            oAluno.setRg(rst.getString("rg"));
            oAluno.setCpf(rst.getLong("cpf"));

            vAluno.add(oAluno);
        } while (rst.next());

        return vAluno;
    }

    public void remover(Aluno i_aluno) throws Exception {

        Statement stm = Conexao.getStatement();

        stm.executeUpdate("DELETE FROM aluno WHERE id = " + i_aluno.getId());
    }

    public Aluno salvar(Aluno i_aluno) throws Exception {
        StringBuilder sql = new StringBuilder();
        Statement stm = Conexao.getStatement();
        ResultSet rst;

        if (i_aluno.getId() == 0) {

            sql.append("INSERT INTO aluno(matricula, nome, rg, cpf)");
            sql.append(" VALUES (" + i_aluno.getMatricula() + ",'" + i_aluno.getNome() + "', '" + i_aluno.getRg() + "', " + i_aluno.getCpf() + ");");

            stm.execute(sql.toString());

            rst = stm.executeQuery("select currval('aluno_id_seq')");
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
