/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Rococo Global Technologies, Inc - All Rights Reserved 2016
 * --------------------------------------------------------------------------- */
 
package midterm.controller;

import java.util.ArrayList;
import java.util.List;

import midterm.dto.ReportCardDto;
import midterm.service.ReportCardService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

/**
* This controller handles the deletion of reportCard model from the datastore.
* @author Lehmar Cabrillos
* @version 0.01
* Version History
* [03/08/2016] 0.01 – Lehmar Cabrillos – initial codes
**/ 
public class DeleteRecordController extends Controller {
    
    /**
     * Service object that will be used to call the delete from datastore function.
     */
    ReportCardService reportCardService = new ReportCardService();
    
    /**
     * The function that will be ran by default when this class is called.
     */
    @Override
    public Navigation run() throws Exception {
        System.out.println("DeleteRecordController " + "start");
        /**
         * dto object that will be used to store the values passed from the request.
         */ 
        ReportCardDto reportCardDto = new ReportCardDto();
        
        try {
            // TODO: Implement this function
            
            reportCardDto.setStudentName(request.getParameter("studentName"));
            reportCardDto.setSectionName(request.getParameter("sectionName"));
            reportCardDto.setGradeLevel(Integer.parseInt(request.getParameter("gradeLevel")));
            reportCardDto.setGradingPeriod(Integer.parseInt(request.getParameter("gradingPeriod")));
            reportCardDto.setPassed(Boolean.parseBoolean(request.getParameter("passFail")));
            reportCardDto.setTotalGrade(Double.parseDouble(request.getParameter("gradeTotal")));
            
            List <Integer> gradeList = new  ArrayList<Integer>();
           
            gradeList.add(Integer.parseInt(request.getParameter("gradeFil")));
            gradeList.add(Integer.parseInt(request.getParameter("gradeSocStud")));
            gradeList.add(Integer.parseInt(request.getParameter("gradeMapeh")));
            gradeList.add(Integer.parseInt(request.getParameter("gradeEnglish")));
            gradeList.add(Integer.parseInt(request.getParameter("gradeScience")));
            gradeList.add(Integer.parseInt(request.getParameter("gradeMath")));
            
            reportCardDto.setGradingList(gradeList);
            reportCardService.deleteRecord(reportCardDto);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        System.out.println("DeleteRecordController " + "end");
        // displaying back to the main web page.
        return forward("/html/midtermExam.html");
    }
}
