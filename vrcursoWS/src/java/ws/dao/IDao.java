package ws.dao;

import vrcurso.framework.exception.ValidacaoException;

public interface IDao {
    
    public void validar(Object o) throws Exception;
    
    public void validarReferencias(Object o) throws Exception;
    
}
