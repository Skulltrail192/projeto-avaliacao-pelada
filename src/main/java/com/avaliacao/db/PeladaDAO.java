package com.avaliacao.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

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

public class PeladaDAO {
    private static PeladaDAO instance;

    public static PeladaDAO getInstance(){
        if (instance == null){
            instance = new PeladaDAO();
        }
        return instance;
    }

    public Pelada getPelada(String nomeEventoEsportivo) {
        Pelada pelada = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria crit = session.createCriteria(Pelada.class);
        try {
            crit.add(Restrictions.eq("nome_evento", nomeEventoEsportivo));
//            Query query = session.createQuery("SELECT u from Pelada u where u.nomeEvento = :nomeEventoEsportivo")
//                        .setParameter("nome_evento", nomeEventoEsportivo);
            List results = crit.list();
            if(results.size()>0){
                pelada = (Pelada)results.get(0);
            }
            //pelada = (Pelada)query.list().get(0);
        } finally {
            session.flush();
            session.close();
        }

        return pelada;
    }


    public void inserirPelada(Pelada pelada) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(pelada);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            throw new Exception("Error ao criar pelada");
        } finally {
            session.flush();
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Pelada> listar() {
        List<Pelada> lista = new ArrayList<Pelada>();

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria crit = session.createCriteria(Pelada.class);
        try {
            session.beginTransaction();
            crit.addOrder(Order.desc("nome_evento"));
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
