package br.edu.ifsul.conversores;

import br.edu.ifsul.model.Recurso;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value = "converterRecurso")
@RequestScoped
public class ConverterRecurso implements Serializable, Converter {

    @PersistenceContext(unitName = "CondominioWebPU")
    protected EntityManager em;

    // converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")) {
            return null;
        }
        return em.find(Recurso.class, Integer.parseInt(string));
    }

    // converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null) {
            return null;
        }
        
        Recurso obj = (Recurso) t;
        return obj.getId().toString();
        /*
        Recurso obj = (Recurso) t;
        return obj.getDescricao();
        */
    }

}
