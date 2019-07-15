/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidades.Mascotaporcliente;
import entidades.Mascotaporcliente;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author LeguiA
 */
public interface IMascotaporcliente {

    public abstract boolean guardarMascotaporcliente(Mascotaporcliente mascota);

    public abstract ArrayList<Mascotaporcliente> listarMascotaporcliente();

    public abstract boolean actualizarMascotaporcliente(Mascotaporcliente mascotaPorCliente);

    public abstract boolean eliminarMascotaporcliente(Mascotaporcliente mascotaPorCliente);

    public abstract ArrayList<Mascotaporcliente> listPastor(Session session);

    public abstract ArrayList<Mascotaporcliente> listSANDOR(Session session);

    public abstract Integer listCount(Session sesion);
}
