/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author mehakksingla
 */
public interface DvdLibraryDao {
    
	    
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;
    
    Dvd addDvd(String dvdTitle, Dvd dvd) throws DvdLibraryDaoException;

    Dvd removeDvd(String dvdTitle) throws DvdLibraryDaoException;
    
    Dvd getDvd(String dvdTitle) throws DvdLibraryDaoException;
    
}

