package ws.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vrcurso.framework.MensagensPadrao;
import vrcurso.framework.exception.ValidacaoException;
import ws.modelo.Curso;
import ws.modelo.CursoDisciplina;
import ws.vo.CursoFiltroVO;

public class CursoDao implements IDao {

    public List<Curso> consultar(CursoFiltroVO i_cursoFiltro) throws Exception {

        Statement stm = Conexao.getStatement();
        ResultSet rst;
        List<Curso> vCurso = new ArrayList<>();;

        boolean where = false;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT c.* FROM curso c");

        if (!i_cursoFiltro.getId().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" c.id = " + i_cursoFiltro.getId());
            where = true;
        }

        if (!i_cursoFiltro.getDescricao().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" c.descricao LIKE '%" + i_cursoFiltro.getDescricao() + "%'");
            where = true;
        }

        sql.append(" ORDER BY c.descricao");

        rst = stm.executeQuery(sql.toString());

        if (!rst.next()) {
            throw new ValidacaoException(MensagensPadrao.REGISTROS_NAO_ENCONTRADOS);
        }

        do {
            Curso oCurso = new Curso();
            oCurso.setId(rst.getInt("id"));
            oCurso.setDescricao(rst.getString("descricao"));
            oCurso.setDuracaoMeses(rst.getInt("duracaomeses"));
            oCurso.setPeriodo(rst.getInt("periodo"));
            oCurso.setQtdAlunos(rst.getInt("qtdalunos"));
            oCurso.setCargaHoraria(rst.getInt("cargahoraria"));

            vCurso.add(oCurso);
        } while (rst.next());

        return vCurso;
    }

    public Curso carregar(CursoFiltroVO i_cursoFiltro) throws Exception {
        Statement stm = Conexao.getStatement();
        ResultSet rst;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT c.* FROM curso c WHERE c.id = " + i_cursoFiltro.getId());

        rst = stm.executeQuery(sql.toString());

        if (!rst.next()) {
            throw new ValidacaoException(MensagensPadrao.REGISTROS_NAO_ENCONTRADOS);
        }

        Curso oCurso = new Curso();
        oCurso.setId(rst.getInt("id"));
        oCurso.setDescricao(rst.getString("descricao"));
        oCurso.setDuracaoMeses(rst.getInt("duracaomeses"));
        oCurso.setPeriodo(rst.getInt("periodo"));
        oCurso.setQtdAlunos(rst.getInt("qtdalunos"));
        oCurso.setCargaHoraria(rst.getInt("cargahoraria"));

        sql = new StringBuilder();
        sql.append("SELECT cd.id, cd.id_curso, cd.id_disciplina, d.descricao AS disciplina");
        sql.append(", d.diasemana, p.nome AS professor");
        sql.append(" FROM cursodisciplina cd");
        sql.append(" INNER JOIN disciplina d ON d.id = cd.id_disciplina");
        sql.append(" INNER JOIN professor p ON p.id = d.id_preofessor");
        sql.append(" WHERE cd.id_curso = " + i_cursoFiltro.getId());

        rst = stm.executeQuery(sql.toString());

        while (rst.next()) {
            CursoDisciplina oCursoDisciplina = new CursoDisciplina();
            oCursoDisciplina.setId(rst.getInt("id"));
            oCursoDisciplina.setIdCurso(rst.getInt("id_curso"));
            oCursoDisciplina.setIdDisciplina(rst.getInt("id_disciplina"));
            oCursoDisciplina.setDisciplina(rst.getString("disciplina"));
            oCursoDisciplina.setDiaSemana(rst.getInt("diasemana"));
            oCursoDisciplina.setProfessor(rst.getString("professor"));

            oCurso.getvCursoDisciplina().add(oCursoDisciplina);
        }

        return oCurso;
    }

    public void remover(Curso i_curso) throws Exception {

        Statement stm = Conexao.getStatement();

        stm.executeUpdate("DELETE FROM cursodisciplina WHERE id_curso = " + i_curso.getId());
        stm.executeUpdate("DELETE FROM curso WHERE id = " + i_curso.getId());
    }

    public Curso salvar(Curso i_curso) throws Exception {
        StringBuilder sql = new StringBuilder();
        Statement stm = Conexao.getStatement();
        ResultSet rst;

        if (i_curso.getId() == 0) {

            sql.append("INSERT INTO curso(descricao, duracaomeses, periodo, qtdalunos, cargahoraria)");
            sql.append(" VALUES ('" + i_curso.getDescricao() + "'," + i_curso.getDuracaoMeses() + ", " + i_curso.getPeriodo());
            sql.append(", " + i_curso.getQtdAlunos() + ", " + ", " + i_curso.getCargaHoraria() + ");");

            stm.execute(sql.toString());

            rst = stm.executeQuery("select currval('curso_id_seq')");
            rst.next();

            i_curso.setId(rst.getInt(1));

            for (CursoDisciplina oCursoDisciplina : i_curso.getvCursoDisciplina()) {
                sql = new StringBuilder();
                sql.append("INSERT INTO cursodisciplina(id_curso, id_disciplina)");
                sql.append(" VALUES(" + i_curso.getId() + ", " + oCursoDisciplina.getIdDisciplina() + ")");

                stm.execute(sql.toString());

                rst = stm.executeQuery("select currval('cursodisciplina_id_seq')");
                rst.next();

                oCursoDisciplina.setId(rst.getInt(1));
                oCursoDisciplina.setIdCurso(i_curso.getId());
            }

        } else {

            sql.append("UPDATE curso");
            sql.append(" SET descricao = '" + i_curso.getDescricao() + "', duracaomeses = " + i_curso.getDuracaoMeses() + ", periodo = " + i_curso.getPeriodo());
            sql.append(" , qtdalunos = " + i_curso.getQtdAlunos() + ", cargahoraria = " + i_curso.getCargaHoraria());
            sql.append(" WHERE id = " + i_curso.getId());

            stm.executeUpdate(sql.toString());

            for (CursoDisciplina oCursoDisciplina : i_curso.getvCursoDisciplina()) {
                sql = new StringBuilder();
                sql.append("UPDATE cursodisciplina SET id_curso = " + i_curso.getId());
                sql.append(", id_disciplina = " + oCursoDisciplina.getIdDisciplina());
                sql.append(" WHERE id = " + oCursoDisciplina.getId());

                stm.executeUpdate(sql.toString());
            }
            
            for (CursoDisciplina oCursoDisciplinaEx : i_curso.getvCursoDisciplinaExclusao()) {
                sql = new StringBuilder();
                sql.append("DELETE FROM cursodisciplina WHERE id = " + oCursoDisciplinaEx.getId());

                stm.executeUpdate(sql.toString());
            }
            
            i_curso.setvCursoDisciplinaExclusao(new ArrayList<>());
        }

        return i_curso;
    }

    @Override
    public void validar(Object o) throws Exception {
        Curso oCurso = (Curso) o;

    }

    @Override
    public void validarReferencias(Object o) throws Exception {
        Curso oCurso = (Curso) o;

        Statement stm = Conexao.getStatement();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT m.id FROM matricula m");
        sql.append(" WHERE m.id_curso = " + oCurso.getId());

        ResultSet rst = stm.executeQuery(sql.toString());

        if (rst.next()) {
            throw new ValidacaoException("Este curso não pode ser excluído, pois possui matrícula(s) ativa(s)!");
        }
    }

}
