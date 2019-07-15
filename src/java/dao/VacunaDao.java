/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Vacuna;
import interfaces.IVacuna;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilitarios.HibernateUtil;

/**
 *
 * @author LeguiA
 */
public class VacunaDao implements IVacuna {

    @Override
    public boolean guardarVacuna(Vacuna vacuna) {
        //Construir una nueva session y una nueva transaccion
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();

        //Registrar en la base de datos la vacuna
        try {
            sesion.save(vacuna);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }

    @Override
    public ArrayList<Vacuna> listarVacunas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<Vacuna> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = session.createQuery("FROM Vacuna");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Vacuna>) query.list();
        session.close();
        return milista;

    }

    @Override
    public boolean actualizarVacuna(Vacuna vacuna) {
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.update(vacuna);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }

    @Override
    public ArrayList<Vacuna> listPastor(Session sesion) {
        ArrayList<Vacuna> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = sesion.createQuery("FROM Vacuna where raza ='pastor aleman'");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Vacuna>) query.list();
        sesion.close();
        return milista;
    }

    @Override
    public ArrayList<Vacuna> listSANDOR(Session sesion) {
        ArrayList<Vacuna> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = sesion.createQuery("FROM Vacuna where nombreVacuna ='sandor'");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Vacuna>) query.list();
        return milista;

    }

    @Override
    public Integer listCount(Session sesion) {
        String sql = "select count(*) From Vacuna";
        Query query = sesion.createQuery(sql);
        Long long1 = (Long) query.uniqueResult();
        Integer count = long1.intValue();
        return count;

    }

    @Override
    public boolean eliminarVacuna(Vacuna vacuna) {

        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.delete(vacuna);
            transaccion.commit();
            respuesta = true;
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }
}
