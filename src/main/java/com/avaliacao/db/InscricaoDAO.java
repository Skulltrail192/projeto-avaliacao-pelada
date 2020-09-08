package com.avaliacao.db;

import com.avaliacao.model.Inscricao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.avaliacao.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class InscricaoDAO {
    private static InscricaoDAO instance;

    public static InscricaoDAO getInstance(){
        if (instance == null){
            instance = new InscricaoDAO();
        }

        return instance;
    }

    public Inscricao getInscricao(Integer usuarioId, Integer peladaId) {
        Inscricao inscricao = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("SELECT i from Inscricao i where i.usuarioId = :usuarioId and i.peladaId = :peladaId")
                    .setParameter("usuarioId", usuarioId)
                    .setParameter("peladaId", peladaId);

            if(query.list().size()>0){
                inscricao = (Inscricao)query.list().get(0);
            }
        } finally {
            session.flush();
            session.close();
        }

        return inscricao;
    }

    public void inscrever(Inscricao inscricao) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(inscricao);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            throw new Exception("Error ao fazer inscricao");
        } finally {
            session.flush();
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Inscricao> listar() {
        List<Inscricao> lista = new ArrayList<Inscricao>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            lista = (List<Inscricao>) session.createCriteria(Inscricao.class).list();
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
