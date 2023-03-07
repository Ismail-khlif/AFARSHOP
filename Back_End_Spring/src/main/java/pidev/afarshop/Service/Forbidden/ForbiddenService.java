package pidev.afarshop.Service.Forbidden;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.afarshop.Entity.Forbidden;
import pidev.afarshop.Repository.ForbiddenRepository;

@Service
public class ForbiddenService implements IForbiddenService{
    @Autowired
    private ForbiddenRepository forbidenRep ;
    @Override
    public void addforbidenword(Forbidden forbiden) {
        forbidenRep.save(forbiden);


    }

    @Override
    public void updateforbidenword(Forbidden forbiden) {
        forbidenRep.save(forbiden);

    }

    @Override
    public Forbidden getforbidenword(Long id) {

        return forbidenRep.findById(id).get();
    }

    @Override
    public void deleteforbidenword(Long id) {
        forbidenRep.deleteById(id);

    }

    @Override
    public List<Forbidden> getall() {

        return (List<Forbidden>)forbidenRep.findAll();




    }
}
