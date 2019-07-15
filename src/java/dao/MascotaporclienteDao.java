/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Mascotaporcliente;
import interfaces.IMascotaporcliente;
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
public class MascotaporclienteDao implements IMascotaporcliente {

    @Override
    public boolean guardarMascotaporcliente(Mascotaporcliente mascotaporcliente) {
        //Construir una nueva session y una nueva transaccion
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();

        //Registrar en la base de datos la mascotaporcliente
        try {
            sesion.save(mascotaporcliente);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;

    }

    @Override
    public ArrayList<Mascotaporcliente> listarMascotaporcliente() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<Mascotaporcliente> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = session.createQuery("FROM Mascotaporcliente");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Mascotaporcliente>) query.list();
        session.close();
        return milista;

    }

    @Override
    public boolean actualizarMascotaporcliente(Mascotaporcliente mascotaporcliente) {
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.update(mascotaporcliente);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }

    @Override
    public ArrayList<Mascotaporcliente> listPastor(Session sesion) {
        ArrayList<Mascotaporcliente> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = sesion.createQuery("FROM Mascotaporcliente where raza ='pastor aleman'");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Mascotaporcliente>) query.list();
        sesion.close();

        return milista;
    }

    @Override
    public ArrayList<Mascotaporcliente> listSANDOR(Session sesion) {
        ArrayList<Mascotaporcliente> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = sesion.createQuery("FROM Mascotaporcliente where nombreMascotaporcliente ='sandor'");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Mascotaporcliente>) query.list();

        return milista;
    }

    @Override
    public Integer listCount(Session sesion) {
        String sql = "select count(*) From Mascotaporcliente";
        Query query = sesion.createQuery(sql);
        Long long1 = (Long) query.uniqueResult();
        Integer count = long1.intValue();
        return count;
    }

    @Override
    public boolean eliminarMascotaporcliente(Mascotaporcliente mascotaporcliente) {

        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.delete(mascotaporcliente);
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
