/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.ClienteDao;
import dao.MascotaDao;
import dao.MascotaporclienteDao;
import entidades.Mascota;
import entidades.Mascotaporcliente;
import entidades.MascotaporclienteId;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;

/**
 *
 * @author LeguiA
 */
@ManagedBean
@ViewScoped
public class MascotaPorClienteBean {

    private ArrayList listaclientes;
    private ArrayList listamascotas;

    private int idCliente;
    private int idMascota;
    /*Estanciando las clases*/
    private Mascotaporcliente mascotaporcliente;
    MascotaporclienteId mascotaporclienteid;

    public MascotaPorClienteBean() {
        listaclientes = new ArrayList();
        mascotaporcliente = new Mascotaporcliente();
        mascotaporclienteid = new MascotaporclienteId();

        listasCombos();
    }

    public void listasCombos() {
        ClienteDao clientedao = new ClienteDao();
        MascotaDao mascotadao = new MascotaDao();

        listaclientes = clientedao.listarClientes();
        listamascotas = mascotadao.listarMascotas();
    }

    public String guardar() {
        MascotaporclienteDao dao = new MascotaporclienteDao();

        mascotaporclienteid.setIdCliente(idCliente);
        mascotaporclienteid.setIdMascota(idMascota);

        mascotaporcliente.setId(mascotaporclienteid);
        dao.guardarMascotaporcliente(mascotaporcliente);
        return "/RegistroMascotaPorCliente";

    }

    public ArrayList getListaclientes() {
        return listaclientes;
    }

    public void setListaclientes(ArrayList listaclientes) {
        this.listaclientes = listaclientes;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public ArrayList getListamascotas() {
        return listamascotas;
    }

    public void setListamascotas(ArrayList listamascotas) {
        this.listamascotas = listamascotas;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public Mascotaporcliente getMascotaporcliente() {
        return mascotaporcliente;
    }

    public void setMascotaporcliente(Mascotaporcliente mascotaporcliente) {
        this.mascotaporcliente = mascotaporcliente;
    }

}
