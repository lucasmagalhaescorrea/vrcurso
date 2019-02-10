package vrcurso.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import vrcurso.modelo.Professor;
import vrcurso.vo.ProfessorFiltroVO;

public class ProfessorService extends HttpService {

    public List<Professor> consultar(ProfessorFiltroVO i_professorFiltro) throws Exception {

        Type professorType = new TypeToken<ProfessorFiltroVO>() {
        }.getType();
        
        List<Professor> vProfessor = new Gson().fromJson(consumirWebService(EndPoints.PROFESSOR_CONSULTAR, new Gson().toJson(i_professorFiltro, professorType)), List.class);

        return vProfessor;
    }
    
    public void remover(Professor i_professor) throws Exception{
         Type professorType = new TypeToken<Professor>() {
        }.getType();
        
        consumirWebService(EndPoints.PROFESSOR_REMOVER, new Gson().toJson(i_professor, professorType));
    
    }
    
    
    public void salvar(Professor i_professor) throws Exception {

        Type professorType = new TypeToken<Professor>() {
        }.getType();
        
        consumirWebService(EndPoints.PROFESSOR_SALVAR, new Gson().toJson(i_professor, professorType));
    }

}
