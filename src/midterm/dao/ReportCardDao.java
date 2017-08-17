/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Rococo Global Technologies, Inc - All Rights Reserved 2016
 * --------------------------------------------------------------------------- */

package midterm.dao;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.Query.FilterOperator;

import midterm.meta.ReportCardModelMeta;
import midterm.model.ReportCardModel;

/**
 * This controller handles the creation of reportCard model to the datastore.
 * @author Lehmar Cabrillos
 * @version 0.01 
 * Version History [03/08/2016] 0.01 – Lehmar Cabrillos – initial
 *          codes
 **/
public class ReportCardDao {

    /**
     * This function is called to insert the reportCardModel to the datastore.
     * 
     * @param inputModel
     *            - model to be inserted to the datastore
     */
    public void insertReportCard(ReportCardModel inputModel) {
        System.out.println("ReportCardDao.insertReportCard " + "start");
        // TODO: Implement this function.
        Transaction trans = Datastore.beginTransaction();
        
        // creating key and ID for the new entity
        Key parentKey = KeyFactory.createKey("ReportCard", inputModel.getStudentName());
        Key key = Datastore.allocateId(parentKey, "ReportCardModel");
        
        // setting the 'key' and 'id' of the model
        inputModel.setKey(key);
        inputModel.setId(key.getId());
        
        Datastore.put(inputModel);
        trans.commit();
        System.out.println("ReportCardDao.insertReportCard " + "end");
    }

    /**
     * This function is used to update a reportCardModel to the datastore.
     * @param inputModel
     *            - model to be updated
     */
    public void updateReportCard(ReportCardModel inputModel) {
        System.out.println("ReportCardDao.updateReportCard " + "start");
        // TODO: Implement this function.
        Transaction trans = Datastore.beginTransaction();
        
        Datastore.put(trans, inputModel);
        
        trans.commit();
        System.out.println("ReportCardDao.updateReportCard " + "end");
    }

    /**
     * This function is used to delete a reportCardModel from the datastore.
     * 
     * @param inputModel
     *            - model to be updated
     */
    public void deleteReportCard(ReportCardModel inputModel) {
        System.out.println("ReportCardDao.deleteReportCard " + "start");
        // TODO: Implement this function.
        Transaction trans = Datastore.beginTransaction();
        
        Key parentKey = KeyFactory.createKey("ReportCard", inputModel.getStudentName());
        Key key = Datastore.allocateId(parentKey, "ReportCardModel");
        
        Datastore.delete(key);
        
        trans.commit();
        System.out.println("ReportCardDao.deleteReportCard " + "end");
    }

    /**
     * This function is used to be a reportCardModel with the same id.
     * 
     * @param inputModel
     *            - contains the id to be checked.
     * @return model that was returned by the query.
     */
    public ReportCardModel getCardById(ReportCardModel inputModel) {
        System.out.println("ReportCardDao.getCardById " + "start");
        ReportCardModel reportCardModel = new ReportCardModel();
        // TODO: Implement this function.
        
        if(ReportCardModelMeta.get().entityToModel(
            Datastore.query("ReportCardModel")
                     .filter("ID/Name", FilterOperator.EQUAL, inputModel.getId())
                     .asSingleEntity()) == null){
            System.out.println("ReportCardDao.getCardById " + "end");
            return null;
        }else{
            System.out.println("ReportCardDao.getCardById " + "end");
            return inputModel;
        }
    }

    /**
     * This function is used to be a reportCardModel with the same name and
     * level.
     * 
     * @param inputModel
     *            - contains the name and level to be checked.
     * @return model that was returned by the query.
     */
    public ReportCardModel getCardByNameLevel(ReportCardModel inputModel) {
        System.out.println("ReportCardDao.getCardByNameLevel " + "start");
        ReportCardModel reportCardModel = new ReportCardModel();
        // TODO: Implement this function.
        if(Datastore.query("ReportCardModel")
                     .filter("studentName", FilterOperator.EQUAL, inputModel.getStudentName())
                     .filter("gradeLevel", FilterOperator.EQUAL, inputModel.getGradeLevel())
                     .asSingleEntity() == null){
            System.out.println("ReportCardDao.getCardByNameLevel " + "end");
            return null;
        }else{
            System.out.println("ReportCardDao.getCardByNameLevel " + "end");
            return inputModel;
        }
    }
}
