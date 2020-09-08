package com.avaliacao.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.avaliacao.db.UsuarioDAO;
import com.avaliacao.model.Usuario;
import com.avaliacao.util.ReportUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "UsuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

    private UsuarioDAO usuarioDAO;
    private Usuario usuario = new Usuario();
    private ReportUtil reportUtil = new ReportUtil();
    private List <Usuario> lista = new ArrayList<Usuario>();

    public UsuarioBean() {
        usuarioDAO = new UsuarioDAO();
    }

    public void incluir() throws Exception {
        System.out.println("UsuarioBean: incluir");
        usuarioDAO.inserirUsuario(usuario);
        //listar();
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Manutenção de usuário: ",
                        "Usuario incluido com sucesso!"));
    }

    public void listar() {
        setLista(usuarioDAO.listar());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    public ReportUtil getReportUtil() {
        return reportUtil;
    }

    public void setReportUtil(ReportUtil reportUtil) {
        this.reportUtil = reportUtil;
    }
}