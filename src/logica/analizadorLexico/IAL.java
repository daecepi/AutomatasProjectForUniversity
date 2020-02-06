package logica.analizadorLexico;

import java.util.List;

/**
 *
 * @author David E Cermeño Pinzon
 */
public interface IAL {

    /**
     *
     * @param codigo
     * @return
     */
    public List<IDefaultToken> getTokens(List<String> codigo);

}
