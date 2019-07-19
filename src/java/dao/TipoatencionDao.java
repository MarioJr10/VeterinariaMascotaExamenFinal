/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Tipoatencion;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilitarios.HibernateUtil;
import interfaces.ITipoatencion;

/**
 *
 * @author LeguiA
 */
public class TipoatencionDao implements ITipoatencion {

    @Override
    public boolean guardarTipoatencion(Tipoatencion tipoatencion) {
        //Construir una nueva session y una nueva transaccion
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();

        //Registrar en la base de datos la tipoatencion
        try {
            sesion.save(tipoatencion);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;

    }

    @Override
    public ArrayList<Tipoatencion> listarTipoatencion() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<Tipoatencion> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = session.createQuery("FROM Tipoatencion");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Tipoatencion>) query.list();
        session.close();
        return milista;

    }

    @Override
    public boolean actualizarTipoatencion(Tipoatencion tipoatencion) {
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.update(tipoatencion);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }

    @Override
    public Integer listCount(Session sesion) {
        String sql = "select count(*) From Tipoatencion";
        Query query = sesion.createQuery(sql);
        Long long1 = (Long) query.uniqueResult();
        Integer count = long1.intValue();
        return count;
    }

    @Override
    public boolean eliminarTipoatencion(Tipoatencion tipoatencion) {

        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.delete(tipoatencion);
            transaccion.commit();
            respuesta = true;
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }

}
