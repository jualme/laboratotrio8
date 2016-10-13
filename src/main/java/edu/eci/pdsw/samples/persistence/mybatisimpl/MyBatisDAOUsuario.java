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
package edu.eci.pdsw.samples.persistence.mybatisimpl;

import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.persistence.DaoUsuario;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.UsuarioMapper;
import static edu.eci.pdsw.samples.textview.MyBatisExample.getSqlSessionFactory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author hcadavid
 */
public class MyBatisDAOUsuario implements DaoUsuario{

    
    private SqlSession currentSession=null;

    public MyBatisDAOUsuario(SqlSession session) {
        this.currentSession=session;
    }
    
    
    
    @Override
    public Usuario load(String email) throws PersistenceException {
       return currentSession.getMapper(UsuarioMapper.class).getUsuario(email);
    }

    @Override
    public void save(Usuario p) throws PersistenceException {
        currentSession.getMapper(UsuarioMapper.class).insertarUsuario(p.getEmail(), p.getNombre());
    }

    @Override
    public void update(Usuario p) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
