package vrcurso.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import vrcurso.modelo.Aluno;
import vrcurso.modelo.Matricula;
import vrcurso.vo.AlunoFiltroVO;
import vrcurso.vo.MatriculaFiltroVO;

public class MatriculaService extends HttpService {

    public List<Matricula> consultar(MatriculaFiltroVO i_alunoFiltro) throws Exception {

        Type matriculaFiltroType = new TypeToken<MatriculaFiltroVO>() {
        }.getType();

        Type matriculaType = new TypeToken<List<Matricula>>() {
        }.getType();

        List<Matricula> vMatricula = new Gson().fromJson(consumirWebService(EndPoints.MATRICULA_CONSULTAR, new Gson().toJson(i_alunoFiltro, matriculaFiltroType)), matriculaType);

        return vMatricula;
    }
    
    public Matricula carregar(MatriculaFiltroVO i_cursoFiltro) throws Exception {

        Type matriculaFiltroType = new TypeToken<MatriculaFiltroVO>() {
        }.getType();

        Type matriculaType = new TypeToken<Matricula>() {
        }.getType();

        Matricula oMatricula = new Gson().fromJson(consumirWebService(EndPoints.MATRICULA_CARREGAR, new Gson().toJson(i_cursoFiltro, matriculaFiltroType)), matriculaType);

        return oMatricula;
    }

    public void remover(Matricula i_matricula) throws Exception {
        Type alunoType = new TypeToken<Matricula>() {
        }.getType();

        consumirWebService(EndPoints.MATRICULA_REMOVER, new Gson().toJson(i_matricula, alunoType));

    }

    public Matricula salvar(Matricula i_aluno) throws Exception {

        Type matriculaType = new TypeToken<Matricula>() {
        }.getType();

        i_aluno = new Gson().fromJson(consumirWebService(EndPoints.MATRICULA_SALVAR, new Gson().toJson(i_aluno, matriculaType)), matriculaType);
        return i_aluno;
    }

}
