
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import vrcurso.modelo.Professor;
import vrcurso.service.HttpService;
import vrcurso.vo.ProfessorFiltroVO;


public class Main {
    
    public static void main(String[] args) {
        
        ProfessorFiltroVO p = new ProfessorFiltroVO();
        p.setNome("");
        p.setCpf("");
        p.setTitulo("0");
        
        
    }
    
}


