/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.ClienteDao;
import entidades.Cliente;
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
public class ClienteBean {

    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteBean() {
        this.cliente = new Cliente();
    }

    public String guardarCliente() {

        ClienteDao dao = new ClienteDao();
        boolean respuesta = dao.guardarCliente(cliente);

        if (respuesta) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Se Registro Crrectamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo registrar"));
        }
        return "/RegistroCliente.xhtml";
    }

    public String actualizarCliente() {
        ClienteDao dao = new ClienteDao();
        boolean respuesta = dao.actualizarCliente(cliente);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro actualizo con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo actualizar"));
        }
        return "/RegistroCliente.xhtml";
    }

    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> milista = new ArrayList<>();
        ClienteDao dao = new ClienteDao();
        milista = dao.listarClientes();

        return milista;
    }

    public String limpiar() {
        return "/RegistroCliente.xhtml";
    }

    public String eliminarCliente() {
        ClienteDao dao = new ClienteDao();
        boolean respuesta = dao.eliminarCliente(cliente);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro Borrado con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo eliminar"));
        }
        return "/RegistroCliente.xhtml";
    }
}
