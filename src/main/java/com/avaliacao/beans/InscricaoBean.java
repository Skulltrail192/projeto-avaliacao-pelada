package com.avaliacao.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.avaliacao.db.InscricaoDAO;
import com.avaliacao.model.Inscricao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "InscricaoBean")
@ViewScoped
public class InscricaoBean implements Serializable {

    private InscricaoDAO inscricaoDAO;
    private Inscricao inscricao = new Inscricao();
    private List <Inscricao> lista = new ArrayList<Inscricao>();

    public InscricaoBean() {
        inscricaoDAO = new InscricaoDAO();
    }

    public void fazerInscricao(int peladaId) throws Exception {
        HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        inscricao.setUsuarioId( ((Integer) httpSession.getAttribute("usuarioId")).intValue());
        inscricao.setPeladaId(peladaId);
        inscricaoDAO.inscrever(inscricao);
        //listar();
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Manutenção de inscrição: ",
                        "Inscrição feita com sucesso!"));
    }

    public boolean checaExisteInscricao(int peladaId){
        HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if(inscricaoDAO.getInscricao(((Integer) httpSession.getAttribute("usuarioId")).intValue(), peladaId)!=null){
            return true;
        }
        return false;
    }

    public void listar() {
        setLista(inscricaoDAO.listar());
    }

    public List<Inscricao> getLista() {
        return lista;
    }

    public void setLista(List<Inscricao> lista) {
        this.lista = lista;
    }
}