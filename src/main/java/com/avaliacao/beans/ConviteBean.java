package com.avaliacao.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.avaliacao.db.ConviteDAO;
import com.avaliacao.model.Convite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "ConviteBean")
@ViewScoped
public class ConviteBean implements Serializable {

    private ConviteDAO conviteDAO;
    private Convite convite = new Convite();
    private List <Convite> lista = new ArrayList<Convite>();

    public ConviteBean() {
        conviteDAO = new ConviteDAO();
    }

    public void enviaConvite(int usuarioId,int peladaId) throws Exception {
        convite.setUsuarioId(usuarioId);
        convite.setPeladaId(peladaId);
        conviteDAO.salvarConvite(convite);
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Manutenção de convite: ",
                        "Convite enviado com sucesso!"));
    }

    public void hello(){
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Teste",
                        "Testando!"));
    }

    public boolean checaExisteConvite(int usuarioId, int peladaId){
        if(conviteDAO.getConvite(usuarioId, peladaId)!=null){
            return true;
        }
        return false;
    }

    public void listar() {
        setLista(conviteDAO.listar());
    }

    public List<Convite> getLista() {
        return lista;
    }

    public void setLista(List<Convite> lista) {
        this.lista = lista;
    }
}