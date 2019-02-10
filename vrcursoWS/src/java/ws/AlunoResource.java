package ws;

import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import vrcurso.framework.exception.ValidacaoException;
import ws.dao.AlunoDao;
import ws.modelo.Aluno;
import ws.vo.AlunoFiltroVO;


@Path("aluno")
public class AlunoResource {

    @Context
    private UriInfo context;
    
    public AlunoResource() {
    }

    @POST
    @Path("/remover")
    @Consumes(MediaType.APPLICATION_JSON)
    public String remover(String content) {
        try {

            AlunoDao oAlunoDao = new AlunoDao();

            Aluno oAluno = new Gson().fromJson(content, Aluno.class);

            oAlunoDao.validarReferencias(oAluno);
            
            oAlunoDao.remover(oAluno);
            
        } catch (ValidacaoException e) {
            return "|ALERTA|" + e.getMessage();
        } catch (Exception e) {
            return "|ERRO|" + e.getMessage();
        }
        
        return "";
    }

    @POST
    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    public String salvar(String content) {
        try {

            AlunoDao oAlunoDao = new AlunoDao();

            Aluno oAluno = new Gson().fromJson(content, Aluno.class);

            oAlunoDao.validar(oAluno);

            oAlunoDao.salvar(oAluno);
            
            return new Gson().toJson(oAluno);
        } catch (ValidacaoException e) {
            return "|ALERTA|" + e.getMessage();
        } catch (Exception e) {
            return "|ERRO|" + e.getMessage();
        }
    }

    @POST
    @Path("/consultar")
    @Consumes(MediaType.APPLICATION_JSON)
    public String consultar(String content) {

        try {
            AlunoDao oAlunoDao= new AlunoDao();

            AlunoFiltroVO oAlunoFiltro = new Gson().fromJson(content, AlunoFiltroVO.class);

            List<Aluno> vAluno = oAlunoDao.consultar(oAlunoFiltro);

            return new Gson().toJson(vAluno);
        } catch (ValidacaoException e) {
            return "|ALERTA|" + e.getMessage();
        } catch (Exception e) {
            return "|ERRO|" + e.getMessage();
        }
    }
}
