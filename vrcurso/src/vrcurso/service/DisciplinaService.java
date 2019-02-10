package vrcurso.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import vrcurso.modelo.Disciplina;
import vrcurso.vo.DisciplinaFiltroVO;

public class DisciplinaService extends HttpService {

    public List<Disciplina> consultar(DisciplinaFiltroVO i_disciplinaFiltro) throws Exception {

        Type disciplinaFiltroType = new TypeToken<DisciplinaFiltroVO>() {
        }.getType();

        Type disciplinaType = new TypeToken<List<Disciplina>>() {
        }.getType();

        List<Disciplina> vDisciplina = new Gson().fromJson(consumirWebService(EndPoints.DISCIPLINA_CONSULTAR, new Gson().toJson(i_disciplinaFiltro, disciplinaFiltroType)), disciplinaType);

        return vDisciplina;
    }

    public void remover(Disciplina i_disciplina) throws Exception {
        Type disciplinaType = new TypeToken<Disciplina>() {
        }.getType();

        consumirWebService(EndPoints.DISCIPLINA_REMOVER, new Gson().toJson(i_disciplina, disciplinaType));

    }

    public Disciplina salvar(Disciplina i_disciplina) throws Exception {

        Type disciplinaType = new TypeToken<Disciplina>() {
        }.getType();

        i_disciplina = new Gson().fromJson(consumirWebService(EndPoints.DISCIPLINA_SALVAR, new Gson().toJson(i_disciplina, disciplinaType)), disciplinaType);
        return i_disciplina;
    }

}
