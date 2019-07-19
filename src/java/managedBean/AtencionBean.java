/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.AtencionDao;
import dao.ClienteDao;
import dao.MascotaDao;
import dao.PersonalDao;
import dao.TipoatencionDao;
import entidades.Atencion;
import entidades.Cliente;
import entidades.Mascota;
import entidades.Mascotaporcliente;
import entidades.MascotaporclienteId;
import entidades.Personal;
import entidades.Tipoatencion;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author LeguiA
 */
@ManagedBean
@ViewScoped
public class AtencionBean {

    private Atencion atencion;

    private boolean banderaSelect;

    private Personal personal;
    private Mascota mascota;
    private Cliente cliente;
    private Tipoatencion tipoatencion;

    private int idPersonal;
    private int idMascota;
    private int idCliente;
    private int idTipoAtencion;

    private ArrayList listarPersonal;
    private ArrayList listarMascota;
    private ArrayList listarCliente;
    private ArrayList listarTipoAtencion;

    private Mascotaporcliente mascotaporcliente;
    MascotaporclienteId mascotaporclienteid;
    Date date;

    public AtencionBean() {
        listarPersonal = new ArrayList();
        listarMascota = new ArrayList();
        listarCliente = new ArrayList();
        listarTipoAtencion = new ArrayList();
        mascotaporcliente = new Mascotaporcliente();
        mascotaporclienteid = new MascotaporclienteId();
        personal = new Personal();
        mascota = new Mascota();
        cliente = new Cliente();
        tipoatencion = new Tipoatencion();
        this.atencion = new Atencion();
        listasCombos();
    }

    public void listasCombos() {
        PersonalDao personaldao = new PersonalDao();
        MascotaDao mascotadao = new MascotaDao();
        ClienteDao clientedao = new ClienteDao();
        TipoatencionDao tipoatenciondao = new TipoatencionDao();

        listarPersonal = personaldao.listarPersonales();
        listarMascota = mascotadao.listarMascotas();
        listarCliente = clientedao.listarClientes();
        listarTipoAtencion = tipoatenciondao.listarTipoatencion();
    }

    public String guardar() {
        try {

            DateFormat formmat = new SimpleDateFormat("HH:mm:ss");
            String hora = "12:00:00";
            date = formmat.parse(hora);
            AtencionDao dao = new AtencionDao();
            personal.setIdPersonal(idPersonal);
            mascotaporclienteid.setIdMascota(idMascota);
            mascotaporclienteid.setIdCliente(idCliente);
            tipoatencion.setIdTipoAtencion(idTipoAtencion);

            atencion.setPersonal(personal);
            mascotaporcliente.setId(mascotaporclienteid);
            atencion.setMascotaporcliente(mascotaporcliente);
            atencion.setTipoatencion(tipoatencion);
            /**
             * HORA *
             */

            DateFormat horaformato = new SimpleDateFormat("HH:mm:ss");
            String horas = horaformato.format(date);
            Date horaex = horaformato.parse(horas);
            atencion.setHoraAtencion(horaex);
            /**
             * HORA *
             */
            atencion.setHoraAtencion(date);
            dao.guardarAtencion(atencion);
        } catch (ParseException e) {
            System.out.println("error" + e);
        }
        return "/RegistroAtencion";
    }

    public Atencion getAtencion() {
        return atencion;
    }

    public void setAtencion(Atencion atencion) {
        this.atencion = atencion;
    }

//    public AtencionBean() {
//        this.atencion = new Atencion();
//    }
    public String guardarAtencion() {

        AtencionDao dao = new AtencionDao();
        boolean respuesta = dao.guardarAtencion(atencion);

        if (respuesta) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Se Registro Correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo registrar"));
        }
        return "/RegistroAtencion.xhtml";
    }

    public String actualizarAtencion() {
        AtencionDao dao = new AtencionDao();
        boolean respuesta = dao.actualizarAtencion(atencion);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro actualizo con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo actualizar"));
        }
        return "/RegistroAtencion.xhtml";
    }

    public ArrayList<Atencion> listarAtencion() {
        ArrayList<Atencion> milista = new ArrayList<>();
        AtencionDao dao = new AtencionDao();
        milista = dao.listarAtencion();

        return milista;
    }

    public String limpiar() {
        return "/RegistroAtencion.xhtml";
    }

    public String eliminarAtencion() {
        AtencionDao dao = new AtencionDao();
        boolean respuesta = dao.eliminarAtencion(atencion);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro Borrado con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo eliminar"));
        }
        return "/RegistroAtencion.xhtml";
    }

    public void selectBandera() {
        banderaSelect = true;
    }

    public boolean isBanderaSelect() {
        return banderaSelect;
    }

    public void setBanderaSelect(boolean banderaSelect) {
        this.banderaSelect = banderaSelect;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tipoatencion getTipoatencion() {
        return tipoatencion;
    }

    public void setTipoatencion(Tipoatencion tipoatencion) {
        this.tipoatencion = tipoatencion;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTipoAtencion() {
        return idTipoAtencion;
    }

    public void setIdTipoAtencion(int idTipoAtencion) {
        this.idTipoAtencion = idTipoAtencion;
    }

    public ArrayList getListarPersonal() {
        return listarPersonal;
    }

    public void setListarPersonal(ArrayList listarPersonal) {
        this.listarPersonal = listarPersonal;
    }

    public ArrayList getListarMascota() {
        return listarMascota;
    }

    public void setListarMascota(ArrayList listarMascota) {
        this.listarMascota = listarMascota;
    }

    public ArrayList getListarCliente() {
        return listarCliente;
    }

    public void setListarCliente(ArrayList listarCliente) {
        this.listarCliente = listarCliente;
    }

    public ArrayList getListarTipoAtencion() {
        return listarTipoAtencion;
    }

    public void setListarTipoAtencion(ArrayList listarTipoAtencion) {
        this.listarTipoAtencion = listarTipoAtencion;
    }

    public Mascotaporcliente getMascotaporcliente() {
        return mascotaporcliente;
    }

    public void setMascotaporcliente(Mascotaporcliente mascotaporcliente) {
        this.mascotaporcliente = mascotaporcliente;
    }

}
