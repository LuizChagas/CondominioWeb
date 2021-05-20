/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.conversores.ConverterOrdem;
import br.edu.ifsul.model.Aluguel;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Pichau
 */
@Stateful
public class AluguelDAO<TIPO> extends DAOGenerico<Aluguel> implements Serializable {

    public AluguelDAO() {
        super();
        classePersistente = Aluguel.class;

        // definir as ordens possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        // difinir a ordem inicial
        ordemAtual = listaOrdem.get(0);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }

    public Aluguel getObjectById(Object id) throws Exception {
        Aluguel obj = em.find(Aluguel.class, id);
        obj.getMensalidades().size();
        return (Aluguel) em.find(classePersistente, id);
    }

}