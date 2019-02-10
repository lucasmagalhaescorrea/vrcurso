package ws.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vrcurso.framework.MensagensPadrao;
import vrcurso.framework.exception.ValidacaoException;
import ws.modelo.Disciplina;
import ws.vo.DisciplinaFiltroVO;

public class DisciplinaDao implements IDao {

    public List<Disciplina> consultar(DisciplinaFiltroVO i_disciplinaFiltro) throws Exception {

        Statement stm = Conexao.getStatement();
        ResultSet rst;
        List<Disciplina> vDisciplina = new ArrayList<>();;

        boolean where = false;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT d.*, p.nome AS professor FROM disciplina d");
        sql.append(" INNER JOIN professor p ON p.id = d.id_professor");

        if (!i_disciplinaFiltro.getId().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" d.id = " + i_disciplinaFiltro.getId());
            where = true;
        }

        if (!i_disciplinaFiltro.getDescricao().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" d.descricao LIKE '%" + i_disciplinaFiltro.getDescricao() + "%'");
            where = true;
        }

        sql.append(" ORDER BY d.descricao");

        rst = stm.executeQuery(sql.toString());

        if (!rst.next()) {
            throw new ValidacaoException(MensagensPadrao.REGISTROS_NAO_ENCONTRADOS);
        }

        do {
            Disciplina oDisciplina = new Disciplina();
            oDisciplina.setId(rst.getInt("id"));
            oDisciplina.setDescricao(rst.getString("descricao"));
            oDisciplina.setEmenta(rst.getString("ementa"));
            oDisciplina.setLimiteVagas(rst.getInt("limitevagas"));
            oDisciplina.setIdProfessor(rst.getInt("id_professor"));
            oDisciplina.setProfessor(rst.getString("professor"));
            oDisciplina.setDiaSemana(rst.getInt("diasemana"));
            oDisciplina.setCargaHoraria(rst.getInt("cargahoraria"));

            vDisciplina.add(oDisciplina);
        } while (rst.next());

        return vDisciplina;
    }

    public void remover(Disciplina i_disciplina) throws Exception {

        Statement stm = Conexao.getStatement();

        stm.executeUpdate("DELETE FROM disciplina WHERE id = " + i_disciplina.getId());
    }

    public Disciplina salvar(Disciplina i_disciplina) throws Exception {
        StringBuilder sql = new StringBuilder();
        Statement stm = Conexao.getStatement();
        ResultSet rst;

        if (i_disciplina.getId() == 0) {

            sql.append("INSERT INTO disciplina(descricao, ementa, limitevagas, id_professor, diasemana, cargahoraria)");
            sql.append(" VALUES ('" + i_disciplina.getDescricao() + "','" + i_disciplina.getEmenta() + "', " + i_disciplina.getLimiteVagas());
            sql.append(", " + i_disciplina.getIdProfessor() + ", " + i_disciplina.getDiaSemana() + ", " + i_disciplina.getCargaHoraria() + ");");

            stm.execute(sql.toString());

            rst = stm.executeQuery("select currval('disciplina_id_seq')");
            rst.next();

            i_disciplina.setId(rst.getInt(1));

        } else {

            sql.append("UPDATE disciplina");
            sql.append(" SET descricao = '" + i_disciplina.getDescricao() + "', ementa = '" + i_disciplina.getEmenta() + "', limitevagas = " + i_disciplina.getLimiteVagas());
            sql.append(" , id_professor = " + i_disciplina.getIdProfessor() + ", diasemana = " + i_disciplina.getDiaSemana());
            sql.append(" , cargahoraria = " + i_disciplina.getCargaHoraria());
            sql.append(" WHERE id = " + i_disciplina.getId());

            stm.executeUpdate(sql.toString());
        }

        return i_disciplina;

    }

    @Override
    public void validar(Object o) throws Exception {
        Disciplina oDisciplina = (Disciplina) o;

    }

    @Override
    public void validarReferencias(Object o) throws Exception {
        Disciplina oDisciplina = (Disciplina) o;

        Statement stm = Conexao.getStatement();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT cd.id_curso, c.descricao FROM cursodisciplina cd");
        sql.append(" INNER JOIN curso c ON c.id = cd.id_curso");
        sql.append(" WHERE cd.id_disciplina = " + oDisciplina.getId() + " GROUP BY cd.id_curso, c.descricao");

        ResultSet rst = stm.executeQuery(sql.toString());

        if (rst.next()) {
            throw new ValidacaoException("Esta disciplina não pode ser excluída, pois pertence à grade de disciplinaa do curso '" + rst.getString("descricao") + "'");
        }
    }

}
