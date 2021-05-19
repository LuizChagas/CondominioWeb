/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.UnidadeCondominialDAO;
import br.edu.ifsul.model.Pessoa;
import br.edu.ifsul.model.Condominio;
import br.edu.ifsul.model.UnidadeCondominial;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Pichau
 */
@Named(value = "controleCondominio")
@ViewScoped
public class ControleCondominio implements Serializable {

    @EJB
    private CondominioDAO<Condominio> dao;
    private Condominio objeto;

    @EJB
    private PessoaDAO<Pessoa> daoPessoa;

    @EJB
    private UnidadeCondominialDAO<UnidadeCondominial> daoUnidadeCondominial;

    private UnidadeCondominial uc;
    private Boolean novoUnidadeCondominial;

    public void novoUnidadeCondominial() {
        uc = new UnidadeCondominial();

        if (uc != null) {
            Pessoa p = new Pessoa();
            p.setId(1);
            uc.setPessoa(p);
            novoUnidadeCondominial = true;
        }
    }

    public void alterarUnidadeCondominial(int index) {
        uc = objeto.getUnidadesCondominiais().get(index);
        novoUnidadeCondominial = false;
    }

    public void salvarUnidadeCondominial() {
        if (novoUnidadeCondominial) {
            objeto.adicionarUnidadeCondominial(uc);
        }
        Util.mensagemInformacao("Unidade Condominial adicionado ou alterado com sucesso!");
    }

    public void removerUnidadeCondominial(int index) {
        objeto.removerUnidadeCondominial(index);
        Util.mensagemInformacao("Unidade Condominial removido com sucesso!");
    }

    public ControleCondominio() {

    }

    public String listar() {
        return "/privado/condominio/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Condominio();
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

    public CondominioDAO<Condominio> getDao() {
        return dao;
    }

    public void setDao(CondominioDAO<Condominio> dao) {
        this.dao = dao;
    }

    public Condominio getObjeto() {
        return objeto;
    }

    public void setObjeto(Condominio objeto) {
        this.objeto = objeto;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public UnidadeCondominialDAO<UnidadeCondominial> getDaoUnidadeCondominial() {
        return daoUnidadeCondominial;
    }

    public void setDaoUnidadeCondominial(UnidadeCondominialDAO<UnidadeCondominial> daoUnidadeCondominial) {
        this.daoUnidadeCondominial = daoUnidadeCondominial;
    }

    public UnidadeCondominial getUc() {
        return uc;
    }

    public void setUc(UnidadeCondominial uc) {
        this.uc = uc;
    }

    public Boolean getNovoUnidadeCondominial() {
        return novoUnidadeCondominial;
    }

    public void setNovoUnidadeCondominial(Boolean novoUnidadeCondominial) {
        this.novoUnidadeCondominial = novoUnidadeCondominial;
    }

}
