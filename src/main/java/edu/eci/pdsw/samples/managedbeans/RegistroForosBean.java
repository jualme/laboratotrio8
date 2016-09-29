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
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
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
    Date fechaHoraUserForum;
    Comentario coment;
    EntradaForo foro;
    String nameUser;
    String emailUser;
    String commentUserForum;
    String tittleUserForum;
    String commentaryForum;

    public void setCommentaryForum(String CommentaryForum) {
        this.commentaryForum = CommentaryForum;
    }

    public String getCommentaryForum() {
        return commentaryForum;
    }

    public void setFechaHoraUserForum(Date fechaHoraUserForum) {
        this.fechaHoraUserForum = fechaHoraUserForum;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public void setCommentUserForum(String commentUserForum) {
        this.commentUserForum = commentUserForum;
    }

    public void setTittleUserForum(String tittleUserForum) {
        this.tittleUserForum = tittleUserForum;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public String getCommentUserForum() {
        return commentUserForum;
    }

    public String getTittleUserForum() {
        return tittleUserForum;
    }

    public Date getFechaHoraUserForum() {
        return fechaHoraUserForum;
    }

    public void setForo(EntradaForo foro) {
        this.foro = foro;
    }

    public EntradaForo getForo() {
        return foro;
    }
    
    public Set<Comentario> getRespuestas() throws ExcepcionServiciosForos {
        return sp.consultarEntradaForo(foro.getIdentificador()).getRespuestas();
    }
    
    public void setRespuestas() throws ExcepcionServiciosForos{
        FacesMessage message = null;
        Date dt = new Date(java.util.Calendar.getInstance().getTime().getTime());
        try{
            Comentario cm = new Comentario(new Usuario(emailUser, nameUser),commentaryForum,dt);
            sp.agregarRespuestaForo(foro.getIdentificador(),cm);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "The commentary has been added");
        }catch(ExcepcionServiciosForos e){
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!!", "The commentary hasn't been added");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<EntradaForo> getForos() throws ExcepcionServiciosForos{
        return sp.consultarEntradasForo();
    }
    
    public void newForo(String email, String nombre, int identificador, String comentario, String titulo, Date fechayHora) throws ExcepcionServiciosForos{
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

    public boolean puedeMostrar(){
        return (foro != null);
    }
        
    public void submit() {
        FacesMessage message = null;
        Date dt = new Date(java.util.Calendar.getInstance().getTime().getTime());
        try{
            sp.registrarNuevaEntradaForo(new EntradaForo(sp.consultarEntradasForo().size(), 
                new Usuario(emailUser, nameUser), commentUserForum, tittleUserForum, dt));
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "The forum has been added");
        } catch(ExcepcionServiciosForos e){
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!!", "The forum hasn't been added");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
