/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Mascota;
import interfaces.IMascota;
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
public class MascotaDao implements IMascota {

    @Override
    public boolean guardarMascota(Mascota mascota) {
        //Construir una nueva session y una nueva transaccion
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();

        //Registrar en la base de datos la mascota
        try {
            sesion.save(mascota);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;

    }

    @Override
    public ArrayList<Mascota> listarMascotas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<Mascota> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = session.createQuery("FROM Mascota");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Mascota>) query.list();
        session.close();
        return milista;

    }

    @Override
    public boolean actualizarMascota(Mascota mascota) {
        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.update(mascota);
            transaccion.commit();
        } catch (Exception e) {
            respuesta = false;
        }
        sesion.close();
        return respuesta;
    }

    @Override
    public ArrayList<Mascota> listPastor(Session sesion) {
        ArrayList<Mascota> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = sesion.createQuery("FROM Mascota where raza ='pastor aleman'");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Mascota>) query.list();
        sesion.close();

        return milista;
    }

    @Override
    public ArrayList<Mascota> listSANDOR(Session sesion) {
        ArrayList<Mascota> milista = new ArrayList<>();
        //Crear la consulta hacia la base de datos
        Query query = sesion.createQuery("FROM Mascota where nombreMascota ='sandor'");

        //Ejecutar la consulta y obtener la lista
        milista = (ArrayList<Mascota>) query.list();

        return milista;
    }

    @Override
    public Integer listCount(Session sesion) {
        String sql = "select count(*) From Mascota";
        Query query = sesion.createQuery(sql);
        Long long1 = (Long) query.uniqueResult();
        Integer count = long1.intValue();
        return count;
    }

    @Override
    public boolean eliminarMascota(Mascota mascota) {

        boolean respuesta = true;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        try {
            sesion.delete(mascota);
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
