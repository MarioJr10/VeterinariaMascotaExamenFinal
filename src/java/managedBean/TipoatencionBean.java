/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.TipoatencionDao;
import entidades.Tipoatencion;
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
public class TipoatencionBean {

    private Tipoatencion tipoatencion;
    private boolean banderaSelect;

    public Tipoatencion getTipoatencion() {
        return tipoatencion;
    }

    public void setTipoatencion(Tipoatencion tipoatencion) {
        this.tipoatencion = tipoatencion;
    }

    public TipoatencionBean() {
        this.tipoatencion = new Tipoatencion();
    }

    public String guardarTipoatencion() {

        TipoatencionDao dao = new TipoatencionDao();
        boolean respuesta = dao.guardarTipoatencion(tipoatencion);

        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Se Registro Crrectamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo registrar"));
        }
        return "/RegistroTipoatencion.xhtml";
    }

    public String actualizarTipoatencion() {
        TipoatencionDao dao = new TipoatencionDao();
        boolean respuesta = dao.actualizarTipoatencion(tipoatencion);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro actualizo con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo actualizar"));
        }
        return "/RegistroTipoatencion.xhtml";
    }

    public ArrayList<Tipoatencion> listarTipoatencion() {
        ArrayList<Tipoatencion> milista = new ArrayList<>();
        TipoatencionDao dao = new TipoatencionDao();
        milista = dao.listarTipoatencion();

        return milista;
    }

    public String limpiar() {
        return "/TipoAtencion.xhtml";
    }

    public String eliminarTipoatencion() {
        TipoatencionDao dao = new TipoatencionDao();
        boolean respuesta = dao.eliminarTipoatencion(tipoatencion);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro Borrado con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo eliminar"));
        }
        return "/RegistroTipoatencion.xhtml";
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

}
