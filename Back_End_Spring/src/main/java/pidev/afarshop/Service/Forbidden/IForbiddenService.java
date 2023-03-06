package pidev.afarshop.Service.Forbidden;

import java.util.List;

import org.springframework.stereotype.Service;

import pidev.afarshop.Entity.Forbidden;

@Service
public interface IForbiddenService {

    public void addforbidenword (Forbidden forbiden);
    public void updateforbidenword ( Forbidden forbiden);
    public List<Forbidden> getall( );
    public Forbidden getforbidenword (Long id);
    public void deleteforbidenword ( Long id );

}
