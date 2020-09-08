package com.avaliacao.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Mara
 */
public class HibernateUtil {
    // declare um objeto do tipo SessionFactory
    /* Mas pq o atributo é static?
     * declare como static para que vc possa chamar esse método mesmo sem ter uma instância
     * da classe HibernateUtil, conceito básico de encapsulamento.
     */
    // ops não esqueça de importar do pacote correto heim
    // deve ser o pacote org.hibernate

    public static SessionFactory sessionFactory;

    public HibernateUtil() {
    }

    /*vamos criar um método que retorne a nossa sessionFactory aberta
     *esse método tb deve ser static, pois um atributo static só pode ser visto
     * por um método tb static
     */
    public static SessionFactory getSessionFactory() {
        // verificar se nossa session é null, se for passar a configuração e abrir
        if (sessionFactory == null) {
            // por favor dentro de try e catch para tratarmos o erro se ocorrer
            try {
                // instanciar o objeto para receber a configuração
                AnnotationConfiguration annotation = new AnnotationConfiguration();
                // vamos pedir para ler a configuração e abrir a sessão
                sessionFactory = annotation.configure().buildSessionFactory();

            } catch (Throwable ex) {
                /* Throwable é o pai de todas as excessões então qualquer
                 * excessão que ocorrer será tratada
                 */
                System.out.println("Erro ao inicar o Hibernte " + ex);
                throw new ExceptionInInitializerError(ex);
            }
            // se td der certo retorna a sessao aberta
            return sessionFactory;

        } else {
            return sessionFactory;
        }
    }
}