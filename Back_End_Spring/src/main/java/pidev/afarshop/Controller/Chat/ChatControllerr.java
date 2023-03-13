package pidev.afarshop.Controller.Chat;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pidev.afarshop.Entity.User;
import pidev.afarshop.Repository.UserRepository;
import pidev.afarshop.websocketproject.ChatService;
import pidev.afarshop.websocketproject.Chatroom;
import pidev.afarshop.websocketproject.ChatroomRepo;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/chat")
public class ChatControllerr {
    @Autowired
    ChatService cs ;
    @Autowired
    UserRepository ur ;

    @Autowired
    ChatroomRepo cr ;

    @GetMapping("/Chatroom/{Idsender}/{idreciver}")
    @ResponseBody
    public Chatroom chatfind(@PathVariable("Idsender") Long Idsender, @PathVariable("idreciver") Long idreciver) {
        return cs.findchat(Idsender, idreciver);
    }

	/*@PostMapping("/send/{idreciver}")
	@ResponseBody
	public void send(@RequestBody Message m,@PathVariable("idreciver") Long idreciver) {
	 cs.sendmessage(m, idreciver);
	}*/

    @PostMapping("/getc/{idreciver}")
    @ResponseBody
    public Chatroom getcon(@PathVariable("idreciver") Long idreciver) {
        return cs.getConv(idreciver);
    }

    @GetMapping("/ListUser")
    @ResponseBody
    public List<User> getListUser() {
        return ur.findAll();
    }

    @GetMapping("/allchat")
    @ResponseBody
    public List<Chatroom> getChat() {
        return cr.findAll();
    }

    @PostMapping("/color/{id}")
    @ResponseBody
    public void color(@PathVariable("id") Long id ,@RequestBody String c) {
        cs.changecolor(id, c);
    }

}
