package vrcurso.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import vrcurso.modelo.Professor;
import vrcurso.vo.ProfessorFiltroVO;

public class ProfessorService extends HttpService {

    public List<Professor> consultar(ProfessorFiltroVO i_professorFiltro) throws Exception {

        Type professorFiltroType = new TypeToken<ProfessorFiltroVO>() {
        }.getType();

        Type professorType = new TypeToken<List<Professor>>() {
        }.getType();

        List<Professor> vProfessor = new Gson().fromJson(consumirWebService(EndPoints.PROFESSOR_CONSULTAR, new Gson().toJson(i_professorFiltro, professorFiltroType)), professorType);

        return vProfessor;
    }

    public void remover(Professor i_professor) throws Exception {
        Type professorType = new TypeToken<Professor>() {
        }.getType();

        consumirWebService(EndPoints.PROFESSOR_REMOVER, new Gson().toJson(i_professor, professorType));

    }

    public Professor salvar(Professor i_professor) throws Exception {

        Type professorType = new TypeToken<Professor>() {
        }.getType();

        i_professor = new Gson().fromJson(consumirWebService(EndPoints.PROFESSOR_SALVAR, new Gson().toJson(i_professor, professorType)), professorType);
        return i_professor;
    }

}
