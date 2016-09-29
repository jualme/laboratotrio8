/*
 * Copyright (C) 2015 hcadavid
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
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.EntradaForo;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ServiciosForoStub;
import java.sql.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 * 
 *  Clases de equivalencia:
 * 
 * Clase 1: La entrada foro tiene asociado un usuario 
 *      la prueba es valida
 * 
 * calse 2: La entrada foro no tiene asociado un usuario 
 *      la prueba no es valida
 */
public class EntradasForoTest {
    
    public EntradasForoTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void EntradaForoConUsuario(){
        ServiciosForoStub sf = new ServiciosForoStub();
        Usuario us = new Usuario("emaildeejempo@hotmail.com","Usuario de ejemplo");
        Date dt = new Date(1,2,3);
        EntradaForo ef = new EntradaForo(1,us,"Comentario de prueba","Esto es el titulo de una prueba",dt);
        try{
            sf.registrarNuevaEntradaForo(ef);
        }
        catch (Exception e){
            fail("Ha retornado error con usuario creado");
        }
    }
    
    @Test
    public void EntradaForoSinUsuario(){
        ServiciosForoStub sf = new ServiciosForoStub();
        EntradaForo ef = new EntradaForo();
        try{
            sf.registrarNuevaEntradaForo(ef);
            fail("No Ha retornado error sin usuario creado");
        } catch (Exception e){
            assertTrue("No ha generado el error correcto", "No se tiene asosiado un usuario".equals(e.getMessage()));
        }
    }   
}
