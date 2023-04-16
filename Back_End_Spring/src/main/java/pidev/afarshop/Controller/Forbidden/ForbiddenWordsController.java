package pidev.afarshop.Controller.Forbidden;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import pidev.afarshop.Entity.Forbidden;
import pidev.afarshop.Service.Forbidden.IForbiddenService;

@CrossOrigin
@RestController
@RequestMapping("/ForbiddenWords")
public class ForbiddenWordsController {


    @Autowired
    private IForbiddenService iForbiddenService ;

    @GetMapping("/retrieve-forbiden/{id}")
    @ResponseBody
    public Forbidden getforbiden (@PathVariable("id") Long id)
    {
        return iForbiddenService.getforbidenword(id);
    }

    @GetMapping("/retrieve-all")
    @ResponseBody
    public List<Forbidden> getforbidens()
    {
        List<Forbidden> forbidens = iForbiddenService.getall();
        return forbidens;
    }

    @PostMapping("/add-forbiden")
    @ResponseBody
    public void addad(@RequestBody Forbidden forbiden )
    {
        iForbiddenService.addforbidenword(forbiden);
    }

    @DeleteMapping("/remove-forbiden/{id}")

    @ResponseBody
    public void removeforbiden(@PathVariable("id") Long id )
    {
        iForbiddenService.deleteforbidenword(id);
    }

    @PutMapping("/modify-forbiden")

    @ResponseBody
    public void modifyAd(@RequestBody Forbidden forbiden)
    {
        iForbiddenService.updateforbidenword(forbiden);

    }

}