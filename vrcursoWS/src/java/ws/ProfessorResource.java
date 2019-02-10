/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import vrcurso.framework.exception.ValidacaoException;
import ws.dao.ProfessorDao;
import ws.modelo.Professor;
import ws.vo.ProfessorFiltroVO;

/**
 * REST Web Service
 *
 * @author Lucas
 */
@Path("professor")
public class ProfessorResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProfessorWS
     */
    public ProfessorResource() {
    }

    @POST
    @Path("/remover")
    @Consumes(MediaType.APPLICATION_JSON)
    public String remover(String content) {
        try {

            ProfessorDao oProfessorDao = new ProfessorDao();

            Professor oProfessor = new Gson().fromJson(content, Professor.class);

            oProfessorDao.validarReferencias(oProfessor);
            
            oProfessorDao.remover(oProfessor);
            
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

            ProfessorDao oProfessorDao = new ProfessorDao();

            Professor oProfessor = new Gson().fromJson(content, Professor.class);

            oProfessorDao.validar(oProfessor);

            oProfessorDao.salvar(oProfessor);
        } catch (ValidacaoException e) {
            return "|ALERTA|" + e.getMessage();
        } catch (Exception e) {
            return "|ERRO|" + e.getMessage();
        }

        return "";
    }

    @POST
    @Path("/consultar")
    @Consumes(MediaType.APPLICATION_JSON)
    public String consultar(String content) {

        try {
            ProfessorDao oProfessorDao = new ProfessorDao();

            ProfessorFiltroVO oProfessorFiltro = new Gson().fromJson(content, ProfessorFiltroVO.class);

            List<Professor> vProfessor = oProfessorDao.consultar(oProfessorFiltro);

            return new Gson().toJson(vProfessor);
        } catch (ValidacaoException e) {
            return "|ALERTA|" + e.getMessage();
        } catch (Exception e) {
            return "|ERRO|" + e.getMessage();
        }
    }
}
