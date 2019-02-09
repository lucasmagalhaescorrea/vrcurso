package ws.dao;

import ws.exception.ValidacaoException;

public interface IDao {
    
    public void validar(Object o) throws ValidacaoException;
    
}
