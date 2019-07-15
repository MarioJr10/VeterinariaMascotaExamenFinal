/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.VacunaDao;
import entidades.Vacuna;
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
public class VacunaBean {

    private Vacuna vacuna;
    private boolean banderaSelect;

    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }

    public VacunaBean() {
        this.vacuna = new Vacuna();
    }

    public String guardarVacuna() {

        VacunaDao dao = new VacunaDao();
        boolean respuesta = dao.guardarVacuna(vacuna);

        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Se Registro Crrectamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo registrar"));
        }
        return "/RegistroVacuna.xhtml";
    }

    public String actualizarVacuna() {
        VacunaDao dao = new VacunaDao();
        boolean respuesta = dao.actualizarVacuna(vacuna);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro actualizo con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo actualizar"));
        }
        return "/RegistroVacuna.xhtml";
    }

    public ArrayList<Vacuna> listarVacunas() {
        ArrayList<Vacuna> milista = new ArrayList<>();
        VacunaDao dao = new VacunaDao();
        milista = dao.listarVacunas();

        return milista;
    }

    public String limpiar() {
        return "/RegistroVacuna.xhtml";
    }

    public String eliminarVacuna() {
        VacunaDao dao = new VacunaDao();
        boolean respuesta = dao.eliminarVacuna(vacuna);
        if (respuesta) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correcto", "Registro Borrado con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo eliminar"));
        }
        return "/RegistroVacuna.xhtml";
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
