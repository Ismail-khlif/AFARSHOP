package pidev.afarshop.Controller.Comment;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pidev.afarshop.Entity.Comment;
import pidev.afarshop.Entity.Forbidden;
import pidev.afarshop.Service.Product.ProductServices;

//import pidev.afarshop.Service.ICommentService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/coment")
public class CommentController {


    ProductServices productServices;
/*
    @Autowired
    ICommentService its;


    @PostMapping ("/add-comment")
    public Comment add(@RequestBody  Comment c) {
        Comment t = its.add(c);
        return t;
    }

    @GetMapping("/retrieve-comment/{comment-id}")
    public Comment retrieve(@PathVariable("comment-id") Long Id) {
        return its.retrieve(Id);
    }
    @DeleteMapping("/remove-comment/{comment-id}")
    public void remove(@PathVariable("comment-id") Long Id) {
        its.delete(Id);
    }
    @PutMapping("/modify-comment")
    public Comment modify(@RequestBody Comment t) {
        return its.update(t);
    }
*/
    @PostMapping("/add-Bad-word")
    @ResponseBody
    public Forbidden addPost_affectedto_User(@RequestBody Forbidden b) {

        return productServices.addForbidden(b);
}

}