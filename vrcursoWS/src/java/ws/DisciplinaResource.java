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
import ws.dao.DisciplinaDao;
import ws.modelo.Disciplina;
import ws.vo.DisciplinaFiltroVO;


@Path("disciplina")
public class DisciplinaResource {

    @Context
    private UriInfo context;

    @POST
    @Path("/remover")
    @Consumes(MediaType.APPLICATION_JSON)
    public String remover(String content) {
        try {

            DisciplinaDao oDisciplinaDao = new DisciplinaDao();

            Disciplina oDisciplina = new Gson().fromJson(content, Disciplina.class);

            oDisciplinaDao.validarReferencias(oDisciplina);
            
            oDisciplinaDao.remover(oDisciplina);
            
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

            DisciplinaDao oDisciplinaDao = new DisciplinaDao();

            Disciplina oDisciplina = new Gson().fromJson(content, Disciplina.class);

            oDisciplinaDao.validar(oDisciplina);

            oDisciplinaDao.salvar(oDisciplina);
            
            return new Gson().toJson(oDisciplina);
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
            DisciplinaDao oDisciplinaDao= new DisciplinaDao();

            DisciplinaFiltroVO oDisciplinaFiltro = new Gson().fromJson(content, DisciplinaFiltroVO.class);

            List<Disciplina> vDisciplina = oDisciplinaDao.consultar(oDisciplinaFiltro);

            return new Gson().toJson(vDisciplina);
        } catch (ValidacaoException e) {
            return "|ALERTA|" + e.getMessage();
        } catch (Exception e) {
            return "|ERRO|" + e.getMessage();
        }
    }
}
