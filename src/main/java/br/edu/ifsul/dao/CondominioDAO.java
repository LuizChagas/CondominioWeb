/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.conversores.ConverterOrdem;
import br.edu.ifsul.model.Condominio;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Pichau
 */
@Stateful
public class CondominioDAO<TIPO> extends DAOGenerico<Condominio> implements Serializable {

    public CondominioDAO() {
        super();
        classePersistente = Condominio.class;

        // definir as ordens possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // difinir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }

    public Condominio getObjectById(Object id) throws Exception {
        Condominio obj = em.find(Condominio.class, id);
        obj.getUnidadesCondominiais().size();
        obj.getRecursos().size();
        return (Condominio) em.find(classePersistente, id);
    }
}
