package com.avaliacao.db;

import com.avaliacao.model.Inscricao;
import com.avaliacao.model.Pelada;
import com.avaliacao.model.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.avaliacao.util.HibernateUtil;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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

        Criteria crit = session.createCriteria(Inscricao.class);
        try {
            crit.add(Restrictions.eq("usuario_id", usuarioId));
            crit.add(Restrictions.eq("pelada_id", peladaId));
//            Query query = session.createQuery("SELECT i from Inscricao i where i.usuarioId = :usuarioId and i.peladaId = :peladaId")
//                    .setParameter("usuarioId", usuarioId)
//                    .setParameter("peladaId", peladaId);

//            if(query.list().size()>0){
//                inscricao = (Inscricao)query.list().get(0);
//            }
            List results = crit.list();
            if(results.size()>0){
                inscricao = (Inscricao)results.get(0);
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

        Criteria crit = session.createCriteria(Inscricao.class);
        try {
            session.beginTransaction();
//            lista = (List<Inscricao>) session.createCriteria(Inscricao.class).list();
            lista = crit.list();
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
