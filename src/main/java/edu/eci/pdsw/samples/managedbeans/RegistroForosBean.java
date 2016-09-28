/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.managedbeans;


import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.EntradaForo;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionServiciosForos;
import edu.eci.pdsw.samples.services.ServiciosForo;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author hcadavid
 */
@ManagedBean(name = "registroForo")
@SessionScoped
public class RegistroForosBean implements Serializable{
    
    ServiciosForo sp=ServiciosForo.getInstance();
    Usuario user;
    Comentario coment;
    EntradaForo foro;

    public void setForo(EntradaForo foro) {
        this.foro = foro;
    }

    public EntradaForo getForo() {
        return foro;
    }

    public List<EntradaForo> getForos() throws ExcepcionServiciosForos{
        return sp.consultarEntradasForo();
    }
    
    public void newForo(String email, String nombre, int identificador, Usuario autor, String comentario, String titulo, Date fechayHora) throws ExcepcionServiciosForos{
        sp.registrarNuevaEntradaForo(new EntradaForo(identificador, sp.consultarUsuario(email), comentario, titulo, fechayHora));
    }
    
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Forum Selected", ((EntradaForo) event.getObject()).getTitulo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Forum Unselected", ((EntradaForo) event.getObject()).getTitulo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
