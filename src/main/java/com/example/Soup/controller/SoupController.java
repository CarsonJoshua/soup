package com.example.Soup.controller;

import com.example.Soup.model.Soup;
import com.example.Soup.service.SoupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

@Controller
public class SoupController {
    public SoupService soupService;

    public SoupController(SoupService soupService) {
        this.soupService = soupService;
    }

    @GetMapping("/")
    public String index(Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("soupList", soupService.getSoups());
        return "home";
    }

    @PostMapping("/create")
    public String addSoup(Model model, @RequestParam String soupName, @RequestParam String soupDesc, @RequestParam String imageAddress) throws InterruptedException, ExecutionException {
        String errors = soupService.validateFormSubmit(soupName, soupDesc, imageAddress);
        if (errors==null){
            model.addAttribute("success", "Database changes saved");
            soupService.addSoup(soupName, soupDesc, imageAddress);
        }else{
            model.addAttribute("errors", errors);
            model.addAttribute("soupName", soupName);
            model.addAttribute("soupDesc", soupDesc);
            model.addAttribute("imageAddress", imageAddress);
        }
        model.addAttribute("soupList", soupService.getSoups());
        return "redirect:/";
    }

//    @GetMapping("/read/{soupId}")//replace with search?
//    public Soup getSoup(@PathVariable String soupId) throws ExecutionException, InterruptedException {
//        return soupService.getSoupById(soupId);
//    }
    @GetMapping("/search")
    public String search(Model model, @RequestParam String search) throws ExecutionException, InterruptedException {
        String upSearch = search.toUpperCase();
        var f = soupService.getSoups().stream()
                .filter(s->
                        s.getSoupId().contains(search)
                        || s.getSoupName().toUpperCase().contains(upSearch)
                        || s.getSoupDesc().toUpperCase().contains(upSearch)
                ).toList();
        model.addAttribute("soupList", f);
        return "home";//TODO make a redirect work here
    }

    @GetMapping("/update/{soupId}")
    public String updateSoupPage(Model model, @PathVariable String soupId) throws ExecutionException, InterruptedException {
        Soup s = soupService.getSoupById(soupId);
        model.addAttribute("soupName", s.getSoupName());
        model.addAttribute("soupDesc", s.getSoupDesc());
        model.addAttribute("imageAddress", s.getImageAddress());
        model.addAttribute("soupId", soupId);
        return "edit";
    }
    @PostMapping("/update-soup")
    public String updateSoupSubmit(Model model, @RequestParam String soupName, @RequestParam String soupDesc, @RequestParam String imageAddress, @RequestParam String soupId) throws ExecutionException, InterruptedException {
        String errors = soupService.validateFormSubmit(soupName, soupDesc, imageAddress);
        if(errors==null){
            model.addAttribute("success", "Database changes saved");
            soupService.editSoup(soupId, soupName, soupDesc, imageAddress);
        }else{
            model.addAttribute("errors", errors);
            model.addAttribute("soupName", soupName);
            model.addAttribute("soupDesc", soupDesc);
            model.addAttribute("imageAddress", imageAddress);
        }
        model.addAttribute("soupList", soupService.getSoups());
        return "redirect:/";
    }

    @GetMapping("/delete/{soupId}")
    public String deleteSoup(Model model, @PathVariable String soupId) throws ExecutionException, InterruptedException {
        soupService.deleteSoup(soupId);
        model.addAttribute("soupList", soupService.getSoups());
        return "redirect:/";
    }
}
