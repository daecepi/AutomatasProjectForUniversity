package logica.analizadorLexico;

import java.util.List;

/**
 *
 * @author Pedro
 */
public interface IAL {

    /**
     *
     * @param codigo
     * @return
     */
    public List<IDefaultToken> getTokens(List<String> codigo);

}
