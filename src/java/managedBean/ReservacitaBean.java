/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.AtencionDao;
import dao.ClienteDao;
import dao.ReservacitaDao;
import dao.TiporeservaDao;
import dao.UsuarioDao;
import entidades.Cliente;
import entidades.Reservacita;
import entidades.Tiporeserva;
import entidades.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
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
public class ReservacitaBean implements Serializable {

    private Reservacita reservacita;
    private boolean banderaSelect;
    private Cliente cliente;
    private Tiporeserva tiporeserva;
    private Usuario usuario;
    private int idCliente;
    private int idTipoReserva;
    private int idUsuario;
    private ArrayList listarCliente;
    private ArrayList listarTipoReserva;
    private ArrayList listarUsuario;

    public ReservacitaBean() {
        listarCliente = new ArrayList();
        listarTipoReserva = new ArrayList();
        listarUsuario = new ArrayList();
        cliente = new Cliente();
        tiporeserva = new Tiporeserva();
        usuario = new Usuario();
        this.reservacita = new Reservacita();
        listasCombos();
    }

    public void listasCombos() {
        ClienteDao clientedao = new ClienteDao();
        TiporeservaDao tiporeservadao = new TiporeservaDao();
        UsuarioDao usuariodao = new UsuarioDao();

        listarCliente = clientedao.listarClientes();
        listarTipoReserva = tiporeservadao.listarTiporeserva();
        listarUsuario = usuariodao.listarUsuario();
    }

    public String guardar() {
        ReservacitaDao dao = new ReservacitaDao();

        cliente.setIdCliente(idCliente);
        tiporeserva.setIdTipoReserva(idTipoReserva);
        usuario.setIdUsuario(idUsuario);

        reservacita.setCliente(cliente);
        reservacita.setTiporeserva(tiporeserva);
        reservacita.setUsuario(usuario);
        dao.guardarReservacita(reservacita);
        return "/RegistroReservaCita";

    }

    public Reservacita getReservacita() {
        return reservacita;
    }

    public void setReservacita(Reservacita reservacita) {
        this.reservacita = reservacita;
    }

    public String guardarReservacita() {

        ReservacitaDao dao = new ReservacitaDao();
        boolean respuesta = dao.guardarReservacita(reservacita);

        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Se Registro Crrectamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo registrar"));
        }
        return "/RegistroReservaCita.xhtml";
    }

    public String actualizarReservacita() {
        ReservacitaDao dao = new ReservacitaDao();
        boolean respuesta = dao.actualizarReservacita(reservacita);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro actualizo con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo actualizar"));
        }
        return "/RegistroReservaCita.xhtml";
    }

    public ArrayList<Reservacita> listarReservacita() {
        ArrayList<Reservacita> milista = new ArrayList<>();
        ReservacitaDao dao = new ReservacitaDao();
        milista = dao.listarReservacita();

        return milista;
    }

    public String limpiar() {
        return "/RegistroReservaCita.xhtml";
    }

    public String eliminarReservacita() {
        ReservacitaDao dao = new ReservacitaDao();
        boolean respuesta = dao.eliminarReservacita(reservacita);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro Borrado con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo eliminar"));
        }
        return "/RegistroReservaCita.xhtml";
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tiporeserva getTiporeserva() {
        return tiporeserva;
    }

    public void setTiporeserva(Tiporeserva tiporeserva) {
        this.tiporeserva = tiporeserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(int idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList getListarCliente() {
        return listarCliente;
    }

    public void setListarCliente(ArrayList listarCliente) {
        this.listarCliente = listarCliente;
    }

    public ArrayList getListarTipoReserva() {
        return listarTipoReserva;
    }

    public void setListarTipoReserva(ArrayList listarTipoReserva) {
        this.listarTipoReserva = listarTipoReserva;
    }

    public ArrayList getListarUsuario() {
        return listarUsuario;
    }

    public void setListarUsuario(ArrayList listarUsuario) {
        this.listarUsuario = listarUsuario;
    }

}
