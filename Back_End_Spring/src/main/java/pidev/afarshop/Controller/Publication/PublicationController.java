package pidev.afarshop.Controller.Publication;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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


//import pidev.afarshop.Entity.Ad;
import pidev.afarshop.Entity.Publication;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Repository.UserRepository;
import pidev.afarshop.Service.Publication.IPublicationService;

@CrossOrigin

@RestController
@RequestMapping("/Publication")
public class PublicationController {

    @Autowired
    private IPublicationService PubSer ;
    @Autowired
    private UserRepository UserRep ;



    @GetMapping("/retrieve-all-pubs")
    @ResponseBody
    public List<Publication> getPubs()
    {
        List<Publication> pubs = PubSer.retrieveallPubs();
        return pubs;
    }

    @GetMapping("/retrieve-pub/{idPublication}")
    @ResponseBody
    public Publication getPublication (@PathVariable("idPublication") Long idpub)
    {
        return PubSer.retrievebyId(idpub);
    }

    @PostMapping("/add-pub")
    @ResponseBody
    public String addPublication(@RequestBody Publication pub )
    {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        User u = UserRep.findByLogin(auth.getName());
        Long id = u.getUserId() ;
        return  PubSer.addPub(pub, id );


    }
    @DeleteMapping("/remove-pub/{idPublication}")
    @ResponseBody
    public void removePub(@PathVariable("idPublication") Long idpub )
    {
        PubSer.deletepubById(idpub);
    }

    @PutMapping("/modify-pub")
    @ResponseBody
    public String modifyPublication(@RequestBody Publication pub)
    {
        return  PubSer.updatePub(pub);

    }
    @GetMapping("/retrieve-pubtendency")
    @ResponseBody
    public List<Publication> getPublicationtendency ( )
    {
        return PubSer.tendency();
    }

    @GetMapping("/retrieve-pubreact")
    @ResponseBody
    public List<Publication> getPublicationmostreact ( )
    {
        return PubSer.MostReacted();
    }


    @GetMapping("/retrieve-3react/{idPublication}")
    @ResponseBody
    public List<String> getPublication3react ( @PathVariable("idPublication") Long idPublication)
    {
        return PubSer.reacts(idPublication);
    }



}