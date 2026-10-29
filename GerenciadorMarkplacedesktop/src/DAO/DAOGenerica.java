package DAO;
import java.util.List;
/**
 * Classe DAO genérica que contém todas as classe que todas as outras DAO devem possuir
 * @author RodolfoFonseca
 * @param <tipoModelo>
 * @param <tipoIdentificador>
 */
public interface DAOGenerica<tipoModelo, tipoIdentificador> {
    boolean inserir(tipoModelo objeto);
    boolean alterar(tipoModelo objeto);
    tipoModelo bucaPorId(tipoIdentificador ID);
    List<tipoModelo> listar(tipoModelo objeto);
}
