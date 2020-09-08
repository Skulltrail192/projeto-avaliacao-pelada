package com.avaliacao.db;

import com.avaliacao.model.Convite;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.avaliacao.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ConviteDAO {
    private static ConviteDAO instance;

    public static ConviteDAO getInstance(){
        if (instance == null){
            instance = new ConviteDAO();
        }

        return instance;
    }

    public Convite getConvite(Integer usuarioId, Integer peladaId) {
        Convite convite = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("SELECT i from Convite i where i.usuarioId = :usuarioId and i.peladaId = :peladaId")
                    .setParameter("usuarioId", usuarioId)
                    .setParameter("peladaId", peladaId);

            if(query.list().size()>0){
                convite = (Convite)query.list().get(0);
            }
        } finally {
            session.flush();
            session.close();
        }

        return convite;
    }

    public void salvarConvite(Convite convite) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(convite);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            throw new Exception("Error ao salvar convite");
        } finally {
            session.flush();
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Convite> listar() {
        List<Convite> lista = new ArrayList<Convite>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            lista = (List<Convite>) session.createCriteria(Convite.class).list();
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
