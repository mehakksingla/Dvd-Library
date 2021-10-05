/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import java.util.List;

/**
 *
 * @author mehakksingla
 */
public class DvdLibraryController {
    DvdLibraryView view;
    DvdLibraryDao dao;
    
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();
                
                switch (menuSelection) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        addDvd();
                        break;
                    case 3:
                        removeDvd();
                        break;
                    case 4:
                        editDvd(); 
                        break;
                    case 5:
                        searchAndViewDvd();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
	    
            } catch (DvdLibraryDaoException e) {
	        view.displayErrorMessage(e.getMessage());
	    }
        exitMessage();
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void listDvds() throws DvdLibraryDaoException{
        view.displayListAllDvdsBanner();
        List<Dvd> dvdList = dao.getAllDvds(); 
        view.displayDvdList(dvdList);
    }
    
    private void addDvd() throws DvdLibraryDaoException{
        view.displayAddDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd); 
        view.displayAddDvdSuccessBanner();
    }
    
    private void removeDvd() throws DvdLibraryDaoException{
        view.displayRemoveDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        dao.removeDvd(dvdTitle);
        view.displayRemoveDvdSuccessBanner();
    }
    
    private void editDvd() throws DvdLibraryDaoException{
        view.displayEditDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvd(dvdTitle);
        view.getEditDvdInfo(dvd);
        dao.addDvd(dvdTitle, dvd);
        view.displayEditDvdSuccessBanner();
    }
    
    private void searchAndViewDvd() throws DvdLibraryDaoException{
        view.displaySearchDvdByNameBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvd(dvdTitle);
        view.displayDvd(dvd);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    
}
