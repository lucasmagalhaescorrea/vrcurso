package ws.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vrcurso.framework.MensagensPadrao;
import vrcurso.framework.exception.ValidacaoException;
import ws.modelo.Matricula;
import ws.modelo.MatriculaDisciplina;
import ws.vo.MatriculaFiltroVO;

public class MatriculaDao implements IDao {

    public List<Matricula> consultar(MatriculaFiltroVO i_matriculaFiltro) throws Exception {

        Statement stm = Conexao.getStatement();
        ResultSet rst;
        List<Matricula> vMatricula = new ArrayList<>();;

        boolean where = false;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT m.id, m.id_aluno, m.id_curso");
        sql.append(", a.matricula, a.nome AS aluno, c.descricao AS curso, COUNT(md.id) AS qtddisciplinas FROM matricula m");
        sql.append(" INNER JOIN aluno a ON a.id = m.id_aluno");
        sql.append(" INNER JOIN curso c ON c.id = m.id_curso");
        sql.append(" LEFT JOIN matriculadisciplina md ON md.id_matricula = m.id");

        if (!i_matriculaFiltro.getIdAluno().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" a.id = " + i_matriculaFiltro.getIdAluno());
            where = true;
        }

        if (!i_matriculaFiltro.getIdCurso().isEmpty()) {
            sql.append(!where ? " WHERE" : " AND");
            sql.append(" c.id = " + i_matriculaFiltro.getIdCurso());
            where = true;
        }

        sql.append(" GROUP BY m.id, m.id_aluno, m.id_curso, a.matricula, a.nome, c.descricao");
        sql.append(" ORDER BY a.nome, c.descricao");

        rst = stm.executeQuery(sql.toString());

        if (!rst.next()) {
            throw new ValidacaoException(MensagensPadrao.REGISTROS_NAO_ENCONTRADOS);
        }

        do {
            Matricula oMatricula = new Matricula();
            oMatricula.setId(rst.getInt("id"));
            oMatricula.setIdAluno(rst.getInt("id_aluno"));
            oMatricula.setIdCurso(rst.getInt("id_curso"));
            oMatricula.setMatricula(rst.getInt("matricula"));
            oMatricula.setAluno(rst.getString("aluno"));
            oMatricula.setCurso(rst.getString("curso"));
            oMatricula.setQtdDisciplinas(rst.getInt("qtddisciplinas"));

            vMatricula.add(oMatricula);
        } while (rst.next());

        return vMatricula;
    }

    public Matricula carregar(MatriculaFiltroVO i_matriculaFiltro) throws Exception {
        Statement stm = Conexao.getStatement();
        ResultSet rst;
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT m.id, m.id_aluno, m.id_curso");
        sql.append(", a.matricula, a.nome AS aluno, c.descricao AS curso, c.periodo FROM matricula m");
        sql.append(" INNER JOIN aluno a ON a.id = m.id_aluno");
        sql.append(" INNER JOIN curso c ON c.id = m.id_curso");
        sql.append(" WHERE m.id = " + i_matriculaFiltro.getId());

        rst = stm.executeQuery(sql.toString());

        if (!rst.next()) {
            throw new ValidacaoException(MensagensPadrao.REGISTROS_NAO_ENCONTRADOS);
        }

        Matricula oMatricula = new Matricula();
        oMatricula.setId(rst.getInt("id"));
        oMatricula.setIdAluno(rst.getInt("id_aluno"));
        oMatricula.setIdCurso(rst.getInt("id_curso"));
        oMatricula.setMatricula(rst.getInt("matricula"));
        oMatricula.setAluno(rst.getString("aluno"));
        oMatricula.setCurso(rst.getString("curso"));
        oMatricula.setPeriodo(rst.getInt("periodo"));
        oMatricula.setQtdDisciplinas(rst.getInt("qtddisciplinas"));
        
        sql = new StringBuilder();
        sql.append("SELECT md.id, md.id_disciplina, md.id_matricula");
        sql.append(", d.descricao AS disciplina, d.diasemana, p.nome AS professor");
        sql.append(" FROM matriculadisciplina md");
        sql.append(" INNER JOIN disciplina d ON d.id = md.id_disciplina");
        sql.append(" INNER JOIN professor p ON p.id = d.id_professor");
        sql.append(" WHERE md.id_matricula = " + i_matriculaFiltro.getId());
        
        rst = stm.executeQuery(sql.toString());
        
        while(rst.next()){
            MatriculaDisciplina oMatriculaDisciplina = new MatriculaDisciplina();
            oMatriculaDisciplina.setId(rst.getInt("id"));
            oMatriculaDisciplina.setIdDisciplina(rst.getInt("id_disciplina"));
            oMatriculaDisciplina.setIdMatricula(rst.getInt("id_matricula"));
            oMatriculaDisciplina.setDisciplina(rst.getString("disciplina"));
            oMatriculaDisciplina.setDiaSemana(rst.getInt("diasemana"));
            oMatriculaDisciplina.setDisciplina(rst.getString("disciplina"));
            
            oMatricula.getvMatriculaDisciplina().add(oMatriculaDisciplina);
        }
        

        return oMatricula;
    }

    public void remover(Matricula i_matricula) throws Exception {

        Statement stm = Conexao.getStatement();

        stm.executeUpdate("DELETE FROM matriculadisciplina WHERE id_matricula = " + i_matricula.getId());
        stm.executeUpdate("DELETE FROM matricula WHERE id = " + i_matricula.getId());
    }

    public Matricula salvar(Matricula i_matricula) throws Exception {
        StringBuilder sql = new StringBuilder();
        Statement stm = Conexao.getStatement();
        ResultSet rst;

        if (i_matricula.getId() == 0) {

            sql.append("INSERT INTO matricula(id_aluno, id_curso)");
            sql.append(" VALUES (" + i_matricula.getIdAluno()+ "," + i_matricula.getIdCurso() + ");");

            stm.execute(sql.toString());

            rst = stm.executeQuery("select currval('matricula_id_seq')");
            rst.next();

            i_matricula.setId(rst.getInt(1));

            for (MatriculaDisciplina oMatriculaDisciplina : i_matricula.getvMatriculaDisciplina()) {
                sql = new StringBuilder();
                sql.append("INSERT INTO matriculadisciplina(id_matricula, id_disciplina)");
                sql.append(" VALUES(" + i_matricula.getId() + ", " + oMatriculaDisciplina.getIdDisciplina() + ")");

                stm.execute(sql.toString());

                rst = stm.executeQuery("select currval('cursodisciplina_id_seq')");
                rst.next();

                oMatriculaDisciplina.setId(rst.getInt(1));
                oMatriculaDisciplina.setIdMatricula(i_matricula.getId());
            }

        } else {

            sql.append("UPDATE matricula");
            sql.append(" SET id_aluno = " + i_matricula.getIdAluno()+ ", id_curso = " + i_matricula.getIdCurso());
            sql.append(" WHERE id = " + i_matricula.getId());

            stm.executeUpdate(sql.toString());

            for (MatriculaDisciplina oMatriculaDisciplina : i_matricula.getvMatriculaDisciplina()) {
                sql = new StringBuilder();
                sql.append("UPDATE matriculadisciplina SET id_matricula = " + i_matricula.getId());
                sql.append(", id_disciplina = " + oMatriculaDisciplina.getIdDisciplina());
                sql.append(" WHERE id = " + oMatriculaDisciplina.getId());

                stm.executeUpdate(sql.toString());
            }

            for (MatriculaDisciplina oMatriculaDisciplinaEx : i_matricula.getvMatriculaDisciplinaExclusao()) {
                sql = new StringBuilder();
                sql.append("DELETE FROM matriculadisciplina WHERE id = " + oMatriculaDisciplinaEx.getId());

                stm.executeUpdate(sql.toString());
            }
            
            i_matricula.setvMatriculaDisciplinaExclusao(new ArrayList<>());
        }

        return i_matricula;
    }

    @Override
    public void validar(Object o) throws Exception {
        Matricula oMatricula = (Matricula) o;

    }

    @Override
    public void validarReferencias(Object o) throws Exception {
        Matricula oMatricula = (Matricula) o;
    }

}
