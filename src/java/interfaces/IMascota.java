/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidades.Mascota;
import entidades.Mascotaporcliente;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author LeguiA
 */
public interface IMascota {

    public abstract boolean guardarMascota(Mascota mascota);

    public abstract ArrayList<Mascota> listarMascotas();

    public abstract boolean actualizarMascota(Mascota mascota);

    public abstract boolean eliminarMascota(Mascota mascota);

    public abstract ArrayList<Mascota> listPastor(Session session);

    public abstract ArrayList<Mascota> listSANDOR(Session session);

    public abstract Integer listCount(Session sesion);
}
