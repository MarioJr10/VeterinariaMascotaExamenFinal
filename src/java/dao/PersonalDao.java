/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Personal;
import interfaces.IPersonal;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilitarios.HibernateUtil;

/**
 *
 * @author LeguiA
 */
public class PersonalDao implements IPersonal {

    @Override
    public boolean guardarPersonal(Personal personal) {
        //Construir una nueva session y una nueva transaccion
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();

        //Registrar en la base de datos la personal
        try {
            sesion.save(personal);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;

    }

    @Override
    public ArrayList<Personal> listarPersonales() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<Personal> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = session.createQuery("FROM Personal");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Personal>) query.list();
        session.close();
        return milista;

    }

    @Override
    public boolean actualizarPersonal(Personal personal) {
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.update(personal);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }

    @Override
    public ArrayList<Personal> listPastor(Session sesion) {
        ArrayList<Personal> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = sesion.createQuery("FROM Personal where raza ='pastor aleman'");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Personal>) query.list();
        sesion.close();

        return milista;
    }

    @Override
    public ArrayList<Personal> listSANDOR(Session sesion) {
        ArrayList<Personal> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = sesion.createQuery("FROM Personal where nombrePersonal ='sandor'");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Personal>) query.list();

        return milista;
    }

    @Override
    public Integer listCount(Session sesion) {
        String sql = "select count(*) From Personal";
        Query query = sesion.createQuery(sql);
        Long long1 = (Long) query.uniqueResult();
        Integer count = long1.intValue();
        return count;
    }

    @Override
    public boolean eliminarPersonal(Personal personal) {

        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.delete(personal);
            transaccion.commit();
            respuesta = true;
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }

}
