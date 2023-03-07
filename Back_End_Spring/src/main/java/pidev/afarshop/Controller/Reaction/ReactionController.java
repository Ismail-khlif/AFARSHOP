package pidev.afarshop.Controller.Reaction;


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


import pidev.afarshop.Entity.Reaction;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Repository.UserRepository;
//import pidev.afarshop.Service.ReactionService;


@RestController
@RequestMapping("/Reaction")
@CrossOrigin

public class ReactionController {
    /*
    @Autowired
    private ReactionService reactionSer;
    @Autowired
    private UserRepository UserRep ;

    @GetMapping("/retrieve-all-reactss")
    @ResponseBody
    public List<Reaction> getReacts()
    {
        List<Reaction> reacts = reactionSer.retrieveAll();
        return reacts;
    }

    @GetMapping("/retrieve-reacts/{id}")
    @ResponseBody
    public Reaction getReact (@PathVariable("id") Long id)
    {
        return reactionSer.retrievebyID(id);
    }


    @PostMapping("/add-affectreaction/{idPublication}")
    @ResponseBody
    public void ajouterEtAffceterreactionPub( @RequestBody Reaction reaction ,@PathVariable("idPublication") Long idPublication)
    {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        User u = UserRep.findByLogin(auth.getName());
        int id = u.getIduser() ;
        reactionSer.addReaction(reaction, idPublication ,id);
    }


    @DeleteMapping("/remove-react/{id}")
    @ResponseBody
    public void removeReact(@PathVariable("id") Long id )
    {
        reactionSer.deleteReaction(id);
    }

    @PutMapping("/modify-react")
    @ResponseBody
    public Reaction modifyReact(@RequestBody Reaction r)
    {
        return reactionSer.updatereReaction(r);
    }*/
}