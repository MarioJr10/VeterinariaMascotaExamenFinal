/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Atencion;
import interfaces.IAtencion;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilitarios.HibernateUtil;

/**
 *
 * @author LeguiA
 */
public class AtencionDao implements IAtencion {

    @Override
    public boolean guardarAtencion(Atencion atencion) {
        //Construir una nueva session y una nueva transaccion
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();

        //Registrar en la base de datos la atencion
        try {
            sesion.save(atencion);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }

    @Override
    public ArrayList<Atencion> listarAtencion() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<Atencion> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = session.createQuery("FROM Atencion");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Atencion>) query.list();
        session.close();
        return milista;

    }

    @Override
    public boolean actualizarAtencion(Atencion atencion) {
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.update(atencion);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }

    @Override
    public Integer listCount(Session sesion) {
        String sql = "select count(*) From Atencion";
        Query query = sesion.createQuery(sql);
        Long long1 = (Long) query.uniqueResult();
        Integer count = long1.intValue();
        return count;

    }

    @Override
    public boolean eliminarAtencion(Atencion atencion) {

        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.delete(atencion);
            transaccion.commit();
            respuesta = true;
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }
}
