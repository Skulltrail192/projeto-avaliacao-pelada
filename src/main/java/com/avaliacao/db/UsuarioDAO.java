package com.avaliacao.db;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.avaliacao.model.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.avaliacao.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static UsuarioDAO instance;

    public static UsuarioDAO getInstance(){
        if (instance == null){
            instance = new UsuarioDAO();
        }

        return instance;
    }

    public Usuario getUsuario(String email, String senha) {
        Usuario usuario = null;
        HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query query = session.createQuery("SELECT u from Usuario u where u.email = :email and u.senha = :senha")
            .setParameter("email", email)
            .setParameter("senha", senha);
            if(query.list().size()>0){
                usuario = (Usuario)query.list().get(0);
                httpSession.setAttribute("usuarioId", usuario.getId());
            }
        } finally {
            session.flush();
            session.close();
        }

        return usuario;
    }

    public void inserirUsuario(Usuario usuario) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(usuario);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            throw new Exception("Error ao criar usuário");
        } finally {
            session.flush();
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<Usuario>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            lista = (List<Usuario>) session.createCriteria(Usuario.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lista;
    }
}
