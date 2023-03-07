package pidev.afarshop.Controller.Comment;

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



import pidev.afarshop.Entity.CommentD;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Repository.UserRepository;
//import pidev.afarshop.Service.C.CommentDService;

@CrossOrigin
@RestController
@RequestMapping("/CommentD")
public class CommentDController {
/*
    @Autowired
    private CommentDService commentDSer ;
    @Autowired
    private UserRepository UserRep ;

    @GetMapping("/retrieve-all-cmntsd")
    @ResponseBody
    public List<CommentD> getCommentsd()
    {
        List<CommentD> cmnts = commentDSer.retrieveAll();
        return cmnts;
    }

    @GetMapping("/retrieve-commentd/{idCom}")
    @ResponseBody
    public CommentD getComment (@PathVariable("idCom") Long idCom)
    {
        return commentDSer.retrievebyId(idCom);
    }

    @PostMapping("/add-affectcomment/{idPublication}")
    @ResponseBody
    public String ajouterEtAffceterCommentairePub( @RequestBody CommentD commentD ,@PathVariable("idPublication") Long idPublication)
    {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        User u = UserRep.findByLogin(auth.getName());
        Long id = u.getUserId() ;
        return commentDSer.AddCommentPub(commentD, idPublication,id);
    }

    @PostMapping("/add-cmntd")
    @ResponseBody
    public CommentD addComd(@RequestBody CommentD c )
    {

        return commentDSer.addComment(c) ;
    }

    @DeleteMapping("/remove-cmntd/{idCom}")
    @ResponseBody
    public void removeCmnt(@PathVariable("idCom") Long idCom )
    {
        commentDSer.DeleteComment(idCom);
    }

    @PutMapping("/modify-cmntd")
    @ResponseBody
    public String modifyCmnt(@RequestBody CommentD c)
    {

        return commentDSer.updateComment(c);
    }


*/
}