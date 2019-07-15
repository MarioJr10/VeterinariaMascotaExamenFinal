/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.MascotaDao;
import entidades.Mascota;
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
public class MascotaBean {

    private Mascota mascota;
    private boolean banderaSelect;

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public MascotaBean() {
        this.mascota = new Mascota();
    }

    public String guardarMascota() {

        MascotaDao dao = new MascotaDao();
        boolean respuesta = dao.guardarMascota(mascota);

        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Se Registro Crrectamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo registrar"));
        }
        return "/index.xhtml";
    }

    public String actualizarMascota() {
        MascotaDao dao = new MascotaDao();
        boolean respuesta = dao.actualizarMascota(mascota);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro actualizo con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo actualizar"));
        }

        return "/index.xhtml";
    }

    public ArrayList<Mascota> listarMascotas() {
        ArrayList<Mascota> milista = new ArrayList<>();
        MascotaDao dao = new MascotaDao();
        milista = dao.listarMascotas();

        return milista;
    }

    public String limpiar() {
        return "/index.xhtml";
    }

    public String eliminarMascota() {
        MascotaDao dao = new MascotaDao();
        boolean respuesta = dao.eliminarMascota(mascota);
        System.out.println(respuesta);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro Borrado con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo eliminar"));
        }

        return "/index.xhtml";
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
