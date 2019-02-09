
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import vrcurso.modelo.Professor;
import vrcurso.service.HttpService;


public class Main {
    
    public static void main(String[] args) {
        
        Professor p = new Professor();
        p.setNome("LUCAAAS");
        p.setCpf(42720339806L);
        p.setRg("488162890");
        p.setTitulo(0);
        
        Type professorType = new TypeToken<Professor>(){}.getType();
        
        try {
            
            System.out.println(new HttpService().sendPost("http://localhost:8080/vrcursoWebService/webresources/professor/salvar", new Gson().toJson(p, professorType)));
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}


