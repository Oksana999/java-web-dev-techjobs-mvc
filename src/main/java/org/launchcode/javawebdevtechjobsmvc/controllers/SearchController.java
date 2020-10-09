package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController extends TechJobController {
   private static ArrayList<Job> jobs;


   @RequestMapping(value = "")
   public String search(Model model) {
      return "search";
   }


   /**
    * Displays the result of the search by given params.
    *
    * @param searchType the name of the search column
    * @param searchTerm the search value
    * @return page with the search data.
    */
   @RequestMapping(value = "results")
   public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

      ArrayList<Job> jobs;
      if (searchType.toLowerCase().equals("all")) {
         jobs = JobData.findAll();
         model.addAttribute("title", "All Jobs");
      } else {
         jobs = JobData.findByColumnAndValue(searchType, searchTerm);
         model.addAttribute("title", searchType + " and " + searchTerm);
      }
      model.addAttribute("jobs", jobs);
      model.addAttribute("searchType", searchType);
      model.addAttribute("searchTerm", searchTerm);

      return "search";
   }
}
