/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Tiporeserva;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilitarios.HibernateUtil;
import interfaces.ITiporeserva;

/**
 *
 * @author LeguiA
 */
public class TiporeservaDao implements ITiporeserva {

    @Override
    public boolean guardarTiporeserva(Tiporeserva tiporeserva) {
        //Construir una nueva session y una nueva transaccion
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();

        //Registrar en la base de datos la tiporeserva
        try {
            sesion.save(tiporeserva);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;

    }

    @Override
    public ArrayList<Tiporeserva> listarTiporeserva() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<Tiporeserva> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = session.createQuery("FROM Tiporeserva");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Tiporeserva>) query.list();
        session.close();
        return milista;

    }

    @Override
    public boolean actualizarTiporeserva(Tiporeserva tiporeserva) {
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.update(tiporeserva);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }

    @Override
    public Integer listCount(Session sesion) {
        String sql = "select count(*) From Tiporeserva";
        Query query = sesion.createQuery(sql);
        Long long1 = (Long) query.uniqueResult();
        Integer count = long1.intValue();
        return count;
    }

    @Override
    public boolean eliminarTiporeserva(Tiporeserva tiporeserva) {

        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.delete(tiporeserva);
            transaccion.commit();
            respuesta = true;
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }

}
