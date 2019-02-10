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
import ws.dao.CursoDao;
import ws.modelo.Curso;
import ws.vo.CursoFiltroVO;

@Path("matricula")
public class MatriculaResource {

    @Context
    private UriInfo context;

    public MatriculaResource() {
    }

    @POST
    @Path("/remover")
    @Consumes(MediaType.APPLICATION_JSON)
    public String remover(String content) {
        try {
            CursoDao oCursoDao = new CursoDao();

            Curso oCurso = new Gson().fromJson(content, Curso.class);

            oCursoDao.validarReferencias(oCurso);
            
            oCursoDao.remover(oCurso);
            
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

            CursoDao oCursoDao = new CursoDao();

            Curso oCurso = new Gson().fromJson(content, Curso.class);

            oCursoDao.validar(oCurso);

            oCursoDao.salvar(oCurso);
            
            return new Gson().toJson(oCurso);
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
            CursoDao oCursoDao = new CursoDao();

            CursoFiltroVO oCursoFiltro = new Gson().fromJson(content, CursoFiltroVO.class);

            List<Curso> vCurso = oCursoDao.consultar(oCursoFiltro);

            return new Gson().toJson(vCurso);
        } catch (ValidacaoException e) {
            return "|ALERTA|" + e.getMessage();
        } catch (Exception e) {
            return "|ERRO|" + e.getMessage();
        }
    }

    @POST
    @Path("/carregar")
    @Consumes(MediaType.APPLICATION_JSON)
    public String carregar(String content) {

        try {
            CursoDao oCursoDao = new CursoDao();

            CursoFiltroVO oCursoFiltro = new Gson().fromJson(content, CursoFiltroVO.class);

            Curso oCurso = oCursoDao.carregar(oCursoFiltro);

            return new Gson().toJson(oCurso);
        } catch (ValidacaoException e) {
            return "|ALERTA|" + e.getMessage();
        } catch (Exception e) {
            return "|ERRO|" + e.getMessage();
        }
    }
}
