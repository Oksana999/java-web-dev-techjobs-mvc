package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "list")
public class ListController extends TechJobController {

   @RequestMapping(value = "")
   public String list(Model model) {
      model.addAttribute("all", JobData.findAll());
      model.addAttribute("employers", JobData.getAllEmployers());
      model.addAttribute("locations", JobData.getAllLocations());
      model.addAttribute("positions", JobData.getAllPositionTypes());
      model.addAttribute("skills", JobData.getAllCoreCompetency());

      return "list";
   }

   @RequestMapping(value = "jobs")
   public String listJobsByColumnAndValue(Model model, @RequestParam String column,
                                          @RequestParam(required = false) String value) {
      ArrayList<Job> jobs;
      if (column.toLowerCase().equals("all")) {
         jobs = JobData.findAll();
         model.addAttribute("title", "All Jobs");
      } else {
         jobs = JobData.findByColumnAndValue(column, value);
         model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
      }
      model.addAttribute("jobs", jobs);

      return "list-jobs";
   }

   @RequestMapping(value = "jobfield")
   public String listJobsByColumnAndValue(Model model, @RequestParam String value) {
      ArrayList<Job> jobs;

      jobs = JobData.findByValue(value);
      model.addAttribute("title", "Jobs by value");

      model.addAttribute("jobs", jobs);

      return "search";
   }
}
