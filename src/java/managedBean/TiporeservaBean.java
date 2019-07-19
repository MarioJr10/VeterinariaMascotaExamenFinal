/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.TiporeservaDao;
import entidades.Tiporeserva;
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
public class TiporeservaBean {

    private Tiporeserva tiporeserva;
    private boolean banderaSelect;

    public Tiporeserva getTiporeserva() {
        return tiporeserva;
    }

    public void setTiporeserva(Tiporeserva tiporeserva) {
        this.tiporeserva = tiporeserva;
    }

    public TiporeservaBean() {
        this.tiporeserva = new Tiporeserva();
    }

    public String guardarTiporeserva() {

        TiporeservaDao dao = new TiporeservaDao();
        boolean respuesta = dao.guardarTiporeserva(tiporeserva);

        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Se Registro Crrectamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo registrar"));
        }
        return "/RegistroTiporeserva.xhtml";
    }

    public String actualizarTiporeserva() {
        TiporeservaDao dao = new TiporeservaDao();
        boolean respuesta = dao.actualizarTiporeserva(tiporeserva);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro actualizo con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo actualizar"));
        }
        return "/RegistroTiporeserva.xhtml";
    }

    public ArrayList<Tiporeserva> listarTiporeserva() {
        ArrayList<Tiporeserva> milista = new ArrayList<>();
        TiporeservaDao dao = new TiporeservaDao();
        milista = dao.listarTiporeserva();

        return milista;
    }

    public String limpiar() {
        return "/TipoAtencion.xhtml";
    }

    public String eliminarTiporeserva() {
        TiporeservaDao dao = new TiporeservaDao();
        boolean respuesta = dao.eliminarTiporeserva(tiporeserva);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro Borrado con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo eliminar"));
        }
        return "/RegistroTiporeserva.xhtml";
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
