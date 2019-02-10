package vrcurso.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import vrcurso.modelo.Curso;
import vrcurso.vo.CursoFiltroVO;

public class CursoService extends HttpService {

    public List<Curso> consultar(CursoFiltroVO i_cursoFiltro) throws Exception {

        Type cursoFiltroType = new TypeToken<CursoFiltroVO>() {
        }.getType();

        Type cursoType = new TypeToken<List<Curso>>() {
        }.getType();

        List<Curso> vCurso = new Gson().fromJson(consumirWebService(EndPoints.CURSO_CONSULTAR, new Gson().toJson(i_cursoFiltro, cursoFiltroType)), cursoType);

        return vCurso;
    }
    
    public Curso carregar(CursoFiltroVO i_cursoFiltro) throws Exception {

        Type cursoFiltroType = new TypeToken<CursoFiltroVO>() {
        }.getType();

        Type cursoType = new TypeToken<Curso>() {
        }.getType();

        Curso oCurso = new Gson().fromJson(consumirWebService(EndPoints.CURSO_CARREGAR, new Gson().toJson(i_cursoFiltro, cursoFiltroType)), cursoType);

        return oCurso;
    }

    public void remover(Curso i_curso) throws Exception {
        Type cursoType = new TypeToken<Curso>() {
        }.getType();

        consumirWebService(EndPoints.CURSO_REMOVER, new Gson().toJson(i_curso, cursoType));

    }

    public Curso salvar(Curso i_curso) throws Exception {

        Type cursoType = new TypeToken<Curso>() {
        }.getType();

        i_curso = new Gson().fromJson(consumirWebService(EndPoints.CURSO_SALVAR, new Gson().toJson(i_curso, cursoType)), cursoType);
        return i_curso;
    }

}
