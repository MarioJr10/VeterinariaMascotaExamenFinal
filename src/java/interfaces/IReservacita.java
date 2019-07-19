/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidades.Reservacita;
import entidades.Reservacita;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author LeguiA
 */
public interface IReservacita {

    public abstract boolean guardarReservacita(Reservacita reservacita);

    public abstract ArrayList<Reservacita> listarReservacita();

    public abstract boolean actualizarReservacita(Reservacita reservacita);

    public abstract boolean eliminarReservacita(Reservacita reservacita);

    public abstract Integer listCount(Session sesion);
}
