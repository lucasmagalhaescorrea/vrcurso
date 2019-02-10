package ws.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vrcurso.framework.MensagensPadrao;
import vrcurso.framework.exception.ValidacaoException;
import ws.modelo.Curso;
import ws.modelo.CursoDisciplina;
import ws.modelo.enuns.Titulo;
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
        sql.append(", d.diasemana, p.nome AS professor, d.cargahoraria, p.id as id_professor");
        sql.append(" FROM cursodisciplina cd");
        sql.append(" INNER JOIN disciplina d ON d.id = cd.id_disciplina");
        sql.append(" INNER JOIN professor p ON p.id = d.id_professor");
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
            oCursoDisciplina.setCargahoraria(rst.getInt("cargahoraria"));
            oCursoDisciplina.setIdProfessor(rst.getInt("id_professor"));

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
            sql.append(", " + i_curso.getQtdAlunos() + ", " + i_curso.getCargaHoraria() + ");");

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

            for (CursoDisciplina oCursoDisciplinaEx : i_curso.getvCursoDisciplinaExclusao()) {
                sql = new StringBuilder();
                sql.append("DELETE FROM cursodisciplina WHERE id = " + oCursoDisciplinaEx.getId());

                stm.executeUpdate(sql.toString());
            }

            for (CursoDisciplina oCursoDisciplina : i_curso.getvCursoDisciplina()) {
                if (oCursoDisciplina.getId() == 0) {

                    sql = new StringBuilder();
                    sql.append("INSERT INTO cursodisciplina(id_curso, id_disciplina)");
                    sql.append(" VALUES(" + i_curso.getId() + ", " + oCursoDisciplina.getIdDisciplina() + ")");

                    stm.execute(sql.toString());

                    rst = stm.executeQuery("select currval('cursodisciplina_id_seq')");
                    rst.next();

                    oCursoDisciplina.setId(rst.getInt(1));
                    oCursoDisciplina.setIdCurso(i_curso.getId());
                } else {
                    sql = new StringBuilder();
                    sql.append("UPDATE cursodisciplina SET id_curso = " + i_curso.getId());
                    sql.append(", id_disciplina = " + oCursoDisciplina.getIdDisciplina());
                    sql.append(" WHERE id = " + oCursoDisciplina.getId());

                    stm.executeUpdate(sql.toString());
                }
            }

            i_curso.setvCursoDisciplinaExclusao(new ArrayList<>());
        }

        return i_curso;
    }

    @Override
    public void validar(Object o) throws Exception {
        Curso oCurso = (Curso) o;
        ResultSet rst;
        Statement stm = Conexao.getStatement();
        StringBuilder sql;

        // O curso deverá ter uma carga horária de no mínimo 20 e no máximo 40 horas;
        if (oCurso.getCargaHoraria() < 20 || oCurso.getCargaHoraria() > 40) {
            throw new ValidacaoException("Cursos devem conter uma carga horária entre 20 e 40 horas");
        }

        // O cadastro de disciplinas deverá respeitar a carga horária do curso;
        int cargaTotal = 0;
        for (CursoDisciplina oCursoDisciplina : oCurso.getvCursoDisciplina()) {
            cargaTotal += oCursoDisciplina.getCargahoraria();
        }
        if (oCurso.getCargaHoraria() != cargaTotal) {
            throw new ValidacaoException("Carga horária do curso diferente da soma das disciplinas!");
        }

        //O dia da semana das disciplinas não pode colidir
        List<Integer> vDias = new ArrayList<>();
        for (CursoDisciplina oCursoDisciplina : oCurso.getvCursoDisciplina()) {
            Integer dia = Integer.valueOf(oCursoDisciplina.getDiaSemana());

            if (vDias.contains(dia)) {
                throw new ValidacaoException("Não é possível vincular disciplinas ministradas no mesmo dia dentro do mesmo curso!");
            }

            vDias.add(dia);
        }

        boolean encontrouMestreDoutor = false;
        //Cada curso poderá ter somente um professor com o título de Mestre ou Doutor;
        for (CursoDisciplina oCursoDisciplina : oCurso.getvCursoDisciplina()) {
            rst = stm.executeQuery("SELECT titulo FROM professor WHERE id = " + oCursoDisciplina.getIdProfessor());
            rst.next();

            if ((rst.getInt("titulo") == Titulo.DOUTOR.getId() || rst.getInt("titulo") == Titulo.MESTRE.getId())) {
                if (!encontrouMestreDoutor) {
                    encontrouMestreDoutor = true;
                } else {
                    throw new ValidacaoException("Não permitido mais de um professor MESTRE ou DOUTOR por curso!");
                }
            }
        }

        //Um professor poderá ser o instrutor de várias disciplinas desde que o dia da semana e horário não coincidam
        //O curso deverá ter uma cadastro de disciplinas onde o dia da semana e período não coincidam
        for (CursoDisciplina oCursoDisciplina : oCurso.getvCursoDisciplina()) {
            sql = sql = new StringBuilder();
            sql.append("SELECT cd.id, c.descricao AS curso FROM cursodisciplina cd");
            sql.append(" INNER JOIN curso c ON c.id = cd.id_curso");
            sql.append(" INNER JOIN disciplina d ON d.id = cd.id_disciplina");
            sql.append(" WHERE cd.id_curso != " + oCurso.getId());
            sql.append(" AND d.id = " + oCursoDisciplina.getIdDisciplina());
            sql.append(" AND c.periodo = " + oCurso.getPeriodo());

            rst = stm.executeQuery(sql.toString());
            if (rst.next()) {
                throw new ValidacaoException("Disciplina " + oCursoDisciplina.getDisciplina() + " já ministrada neste mesmo período e dia no curso " + rst.getString("curso"));
            }

        }
        //validarexclusao
        for (CursoDisciplina ocCursoDisciplinaEx : oCurso.getvCursoDisciplinaExclusao()) {
            sql = new StringBuilder();
            sql.append("SELECT md.id FROM matriculadisciplina md");
            sql.append(" INNER JOIN matricula m ON m.id = md.id_disciplina");
            sql.append(" WHERE m.id_curso = "+ oCurso.getId());
            sql.append(" AND md.id_disciplina = "+ ocCursoDisciplinaEx.getIdDisciplina());
            
            rst = stm.executeQuery(sql.toString());
            if(rst.next()){
                throw new ValidacaoException("Não é possível excluir a disciplina " + ocCursoDisciplinaEx.getDisciplina() + ", pois ela possui matrículas ativas!");
            }
        }
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
