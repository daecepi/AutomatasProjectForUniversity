/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.codeGenerator;

import java.util.List;
import logica.analizadorLexico.IDefaultToken;

/**
 *
 * @author davidcermeno
 */
public interface CGI {
    public String generateCode(List<IDefaultToken> tokens);
}
