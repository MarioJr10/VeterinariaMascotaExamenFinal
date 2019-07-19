/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.ClienteDao;
import dao.PersonalDao;
import dao.UsuarioDao;
import entidades.Cliente;
import entidades.Personal;
import entidades.Usuario;
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
public class UsuarioBean {

    private Usuario usuario;
    private boolean banderaSelect;

    private Cliente cliente;
    private Personal personal;
    private int idPersonal;
    private int idCliente;
    private ArrayList listarPersonal;
    private ArrayList listarCliente;

    public UsuarioBean() {
        listarCliente = new ArrayList();
        listarPersonal = new ArrayList();
        this.usuario = new Usuario();
        cliente = new Cliente();
        personal = new Personal();
        listasCombos();
    }

    public void listasCombos() {
        ClienteDao clientedao = new ClienteDao();
        PersonalDao personaldao = new PersonalDao();

        listarCliente = clientedao.listarClientes();
        listarPersonal = personaldao.listarPersonales();
    }

    public String guardar() {
        UsuarioDao dao = new UsuarioDao();
        cliente.setIdCliente(idCliente);
        personal.setIdPersonal(idPersonal);
        usuario.setCliente(cliente);
        usuario.setPersonal(personal);
        dao.guardarUsuario(usuario);
        return "/RegistroUsuario";

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String guardarUsuario() {

        UsuarioDao dao = new UsuarioDao();
        boolean respuesta = dao.guardarUsuario(usuario);

        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Se Registro Correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo registrar"));
        }
        return "/RegistroUsuario.xhtml";
    }

    public String actualizarUsuario() {
        UsuarioDao dao = new UsuarioDao();
        boolean respuesta = dao.actualizarUsuario(usuario);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro actualizo con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo actualizar"));
        }
        return "/RegistroUsuario.xhtml";
    }

    public ArrayList<Usuario> listarUsuario() {
        ArrayList<Usuario> milista = new ArrayList<>();
        UsuarioDao dao = new UsuarioDao();
        milista = dao.listarUsuario();

        return milista;
    }

    public String limpiar() {
        return "/RegistroUsuario.xhtml";
    }

    public String eliminarUsuario() {
        UsuarioDao dao = new UsuarioDao();
        boolean respuesta = dao.eliminarUsuario(usuario);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro Borrado con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo eliminar"));
        }
        return "/RegistroUsuario.xhtml";
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

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public ArrayList getListarPersonal() {
        return listarPersonal;
    }

    public void setListarPersonal(ArrayList listarPersonal) {
        this.listarPersonal = listarPersonal;
    }

    public ArrayList getListarCliente() {
        return listarCliente;
    }

    public void setListarCliente(ArrayList listarCliente) {
        this.listarCliente = listarCliente;
    }

}
