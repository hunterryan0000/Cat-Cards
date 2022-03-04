package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatCardNotFoundException;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/cards")
public class CatController {

    @Autowired
    private CatCardDao cardDao;

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    @RequestMapping( path = "", method = RequestMethod.GET )
    public List<CatCard> list() {
        return cardDao.list();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public CatCard get(@PathVariable int id) throws CatCardNotFoundException {
        return cardDao.get(id);
    }

    @RequestMapping( path = "/random", method = RequestMethod.GET )
    public CatCard random() {
        return cardDao.list().get(new Random().nextInt(cardDao.list().size()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping( path = "", method = RequestMethod.POST)
    public Boolean saves (@Valid @RequestBody CatCard cardToSave) {
       return cardDao.save(cardToSave);
    } //TODO comeback


    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Boolean update(@Valid @PathVariable int id, @RequestBody CatCard card)
            throws CatCardNotFoundException {
        return cardDao.update(id, card);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) throws CatCardNotFoundException {
        cardDao.delete(id);
    }


}
