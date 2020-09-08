package com.avaliacao.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.avaliacao.db.PeladaDAO;
import com.avaliacao.db.UsuarioDAO;
import com.avaliacao.model.Pelada;
import com.avaliacao.model.Usuario;
import com.avaliacao.util.ReportUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "PeladaBean")
@ViewScoped
public class PeladaBean implements Serializable {

    private PeladaDAO peladaDAO;
    private Pelada pelada = new Pelada();
    private ReportUtil reportUtil = new ReportUtil();
    private List <Pelada> lista = new ArrayList<Pelada>();
    private int currentId;

    public PeladaBean() {
        peladaDAO = new PeladaDAO();
    }

    public void incluir() throws Exception {
        System.out.println("PeladaBean: incluir");
        peladaDAO.inserirPelada(pelada);
        //listar();
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Manutenção de usuário: ",
                        "Pelada incluido com sucesso!"));
    }

    public void exibeUsuarioId(){
        HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Id do Usuario",
                        "O Id do usuário é:"+ httpSession.getAttribute("usuarioId")));
    }

    public void listar() {
        setLista(peladaDAO.listar());
    }

    public Pelada getPelada() {
        return pelada;
    }

    public void setPelada(Pelada pelada) {
        this.pelada = pelada;
    }

    public List<Pelada> getLista() {
        return lista;
    }

    public void setLista(List<Pelada> lista) {
        this.lista = lista;
    }

    public ReportUtil getReportUtil() {
        return reportUtil;
    }

    public void setReportUtil(ReportUtil reportUtil) {
        this.reportUtil = reportUtil;
    }

    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public void updateCurrentId(int id){
        this.setCurrentId(id);
    }
}