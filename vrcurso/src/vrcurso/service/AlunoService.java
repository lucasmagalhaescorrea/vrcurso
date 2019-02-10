package vrcurso.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import vrcurso.modelo.Aluno;
import vrcurso.vo.AlunoFiltroVO;

public class AlunoService extends HttpService {

    public List<Aluno> consultar(AlunoFiltroVO i_alunoFiltro) throws Exception {

        Type alunoFiltroType = new TypeToken<AlunoFiltroVO>() {
        }.getType();

        Type alunoType = new TypeToken<List<Aluno>>() {
        }.getType();

        List<Aluno> vAluno = new Gson().fromJson(consumirWebService(EndPoints.ALUNO_CONSULTAR, new Gson().toJson(i_alunoFiltro, alunoFiltroType)), alunoType);

        return vAluno;
    }

    public void remover(Aluno i_aluno) throws Exception {
        Type alunoType = new TypeToken<Aluno>() {
        }.getType();

        consumirWebService(EndPoints.ALUNO_REMOVER, new Gson().toJson(i_aluno, alunoType));

    }

    public Aluno salvar(Aluno i_aluno) throws Exception {

        Type alunoType = new TypeToken<Aluno>() {
        }.getType();

        i_aluno = new Gson().fromJson(consumirWebService(EndPoints.ALUNO_SALVAR, new Gson().toJson(i_aluno, alunoType)), alunoType);
        return i_aluno;
    }

}
