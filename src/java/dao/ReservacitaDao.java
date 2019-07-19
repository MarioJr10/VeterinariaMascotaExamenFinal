/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Reservacita;
import interfaces.IReservacita;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilitarios.HibernateUtil;

/**
 *
 * @author LeguiA
 */
public class ReservacitaDao implements IReservacita {

    @Override
    public boolean guardarReservacita(Reservacita reservacita) {
        //Construir una nueva session y una nueva transaccion
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();

        //Registrar en la base de datos la reservacita
        try {
            sesion.save(reservacita);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;

    }

    @Override
    public ArrayList<Reservacita> listarReservacita() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<Reservacita> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = session.createQuery("FROM Reservacita");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Reservacita>) query.list();
        session.close();
        return milista;

    }

    @Override
    public boolean actualizarReservacita(Reservacita reservacita) {
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.update(reservacita);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }

    @Override
    public Integer listCount(Session sesion) {
        String sql = "select count(*) From Reservacita";
        Query query = sesion.createQuery(sql);
        Long long1 = (Long) query.uniqueResult();
        Integer count = long1.intValue();
        return count;
    }

    @Override
    public boolean eliminarReservacita(Reservacita reservacita) {

        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.delete(reservacita);
            transaccion.commit();
            respuesta = true;
        } catch (Exception e) {
            respuesta = false;
            System.out.println(e.getMessage());
        }
        sesion.close();
        return respuesta;
    }

}
