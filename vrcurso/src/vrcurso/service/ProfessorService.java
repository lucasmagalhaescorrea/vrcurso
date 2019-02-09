package vrcurso.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import vrcurso.modelo.Professor;
import vrcurso.vo.ProfessorFiltroVO;

public class ProfessorService extends HttpService {

    public List<ProfessorFiltroVO> consultar(ProfessorFiltroVO i_professorFiltro) throws Exception {

        Type professorType = new TypeToken<ProfessorFiltroVO>() {
        }.getType();

        String response = sendPost("http://localhost:8080/vrcursoWS/webresources/professor/consultar", new Gson().toJson(i_professorFiltro, professorType));
        
        validarRetorno(response);
        
        List<ProfessorFiltroVO> vProfessor = new Gson().fromJson(response, List.class);

        return vProfessor;
    }
    
    
    public void salvar(Professor i_professor) throws Exception {

        Type professorType = new TypeToken<Professor>() {
        }.getType();

        String response = sendPost("http://localhost:8080/vrcursoWS/webresources/professor/consultar", new Gson().toJson(i_professor, professorType));
        
        validarRetorno(response);
        
        List<ProfessorFiltroVO> vProfessor = new Gson().fromJson(response, List.class);
    }

}
