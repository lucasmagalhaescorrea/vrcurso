package ws.dao;

import ws.framework.exception.ValidacaoException;

public interface IDao {
    
    public void validar(Object o) throws ValidacaoException;
    
}
