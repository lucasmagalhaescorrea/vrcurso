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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ws.dao.ProfessorDao;
import ws.modelo.Professor;

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

    /**
     * Retrieves representation of an instance of ws.ProfessorWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        return "";
    }

    /**
     * PUT method for updating or creating an instance of ProfessorWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    public String salvar(String content) {
        try {

            ProfessorDao oProfessorDao = new ProfessorDao();

            Professor oProfessor = new Gson().fromJson(content, Professor.class);

            oProfessorDao.validar(oProfessor);
            
            oProfessorDao.inserir(oProfessor);
        } catch (Exception e) {
            e.printStackTrace();
            return "|ERRO|" + e.getMessage();
        }
        
        return "";
    }
    
    @GET
    @Path("/consultar")
    @Consumes(MediaType.APPLICATION_JSON)
    public String consultar() {
        
        List<Professor> vProfessor = new ProfessorDao().consultar();
        
        return new Gson().toJson(vProfessor);
    }
}
