package be.ehb.dierentuin.controllers;

import be.ehb.dierentuin.model.Beest;
import be.ehb.dierentuin.model.BeestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/biest")
public class BeestController {

    @Autowired
    BeestDAO dao;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity maakBeest(@RequestParam(value = "naam") String naam,
                                    @RequestParam(value = "soort") String soort){
        Beest nieuwBeest = new Beest();
        nieuwBeest.setNaam(naam);
        nieuwBeest.setSoort(soort);

        dao.save(nieuwBeest);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{naam}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Beest> zoekBeestOpNaam(@PathVariable(value = "naam") String naam){
        return dao.zoekBijNaam(naam);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Beest> toonBeesten(){
        return dao.findAll();
    }

}
