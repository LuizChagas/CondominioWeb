package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AluguelDAO;
import br.edu.ifsul.dao.LocatarioDAO;
import br.edu.ifsul.dao.MensalidadeDAO;
import br.edu.ifsul.dao.UnidadeCondominialDAO;
import br.edu.ifsul.model.Aluguel;
import br.edu.ifsul.model.Locatario;
import br.edu.ifsul.model.Mensalidade;
import br.edu.ifsul.model.UnidadeCondominial;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleAluguel")
@ViewScoped
public class ControleAluguel implements Serializable {

    @EJB
    private AluguelDAO<Aluguel> dao;
    private Aluguel objeto;

    @EJB
    private LocatarioDAO<Locatario> daoLocatario;

    @EJB
    private UnidadeCondominialDAO<UnidadeCondominial> daoUnidadeCondominial;

    @EJB
    private MensalidadeDAO<Mensalidade> daoMensalidade;
    private Mensalidade mensalidade;
    private Boolean novoMensalidade;

    public ControleAluguel() {

    }

    public void novoMensalidade() {
        mensalidade = new Mensalidade();

        if (mensalidade != null) {
            novoMensalidade = true;
        }
    }

    public void alterarMensalidade(int index) {
        mensalidade = objeto.getMensalidades().get(index);
        novoMensalidade = false;
    }

    public void salvarMensalidade() {
        if (novoMensalidade) {
            objeto.adicionarMensalidade(mensalidade);
        }
        Util.mensagemInformacao("Mensalidade adicionada ou alterado com sucesso!");
    }

    public void removerMensalidade(int index) {
        objeto.removerMensalidade(index);
        Util.mensagemInformacao("Mensalidade removida com sucesso!");
    }

    public String listar() {
        return "/privado/aluguel/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Aluguel();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao salvar objeto: " + Util.getMensagemErro(e));
        }
    }

    public AluguelDAO<Aluguel> getDao() {
        return dao;
    }

    public void setDao(AluguelDAO<Aluguel> dao) {
        this.dao = dao;
    }

    public Aluguel getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluguel objeto) {
        this.objeto = objeto;
    }

    public UnidadeCondominialDAO<UnidadeCondominial> getDaoUnidadeCondominial() {
        return daoUnidadeCondominial;
    }

    public void setDaoUnidadeCondominial(UnidadeCondominialDAO<UnidadeCondominial> daoUnidadeCondominial) {
        this.daoUnidadeCondominial = daoUnidadeCondominial;
    }

    public LocatarioDAO<Locatario> getDaoLocatario() {
        return daoLocatario;
    }

    public void setDaoLocatario(LocatarioDAO<Locatario> daoLocatario) {
        this.daoLocatario = daoLocatario;
    }

    public MensalidadeDAO<Mensalidade> getDaoMensalidade() {
        return daoMensalidade;
    }

    public void setDaoMensalidade(MensalidadeDAO<Mensalidade> daoMensalidade) {
        this.daoMensalidade = daoMensalidade;
    }

    public Mensalidade getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(Mensalidade mensalidade) {
        this.mensalidade = mensalidade;
    }

    public Boolean getNovoMensalidade() {
        return novoMensalidade;
    }

    public void setNovoMensalidade(Boolean novoMensalidade) {
        this.novoMensalidade = novoMensalidade;
    }
}
