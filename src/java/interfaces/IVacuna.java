/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidades.Vacuna;
import java.util.ArrayList;
import org.hibernate.Session;

/**
 *
 * @author LeguiA
 */
public interface IVacuna {

    public abstract boolean guardarVacuna(Vacuna vacuna);

    public abstract ArrayList<Vacuna> listarVacunas();

    public abstract boolean actualizarVacuna(Vacuna vacuna);

    public abstract boolean eliminarVacuna(Vacuna vacuna);

    public abstract ArrayList<Vacuna> listPastor(Session session);

    public abstract ArrayList<Vacuna> listSANDOR(Session session);

    public abstract Integer listCount(Session sesion);
}
