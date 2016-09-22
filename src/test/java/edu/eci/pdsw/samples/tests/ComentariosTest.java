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
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.EntradaForo;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionServiciosForos;
import edu.eci.pdsw.samples.services.ServiciosForoStub;
import java.sql.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 * --- add new answer method (agregarRespuestaForo) ---
 * CLASE DE EQUIVALENCIA 1: idforo no esta en los foros
 * RETURN: Error
 * 
 * CLASE DE EQUIVALENCIA 2: idforo esta en los foros y el comentario tiene asociado un usuario
 * RETURN: Accepted
 * 
 * CLASE DE EQUIVALENCIA 3: idforo esta en los foros y el comentario NO tiene asociado un usuario
 * RETURN: Error
 */
public class ComentariosTest {
    
    public ComentariosTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    /**
     * Clase de equivalencia 3
     */
    @Test
    public void registroPacienteTest(){
        ServiciosForoStub sfs = new ServiciosForoStub();
        try{
            EntradaForo ef = sfs.consultarEntradaForo(0);
            sfs.agregarRespuestaForo(0, new Comentario(null, "Porque no seca.", new Date(20,12,3)));
            fail("Tiene que mostrar un error.");
        } catch(ExcepcionServiciosForos e){}
    }
    
    
}
