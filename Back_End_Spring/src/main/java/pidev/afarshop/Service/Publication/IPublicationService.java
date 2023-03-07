package pidev.afarshop.Service.Publication;



import java.time.LocalTime;
        import java.util.Date;
        import java.util.List;

        import org.springframework.stereotype.Service;
        import pidev.afarshop.Entity.Publication;

@Service
public interface IPublicationService {

    public String addPub( Publication pub , Long id  );
    public String updatePub ( Publication pub );
    public void deletepubById ( Long id );
    public Publication retrievebyId ( Long id );
    public List<Publication> retrieveallPubs ();
    public List<Publication> tendency ( );
    public List<Publication> MostReacted();
    public List<String> reacts( Long idpublication);
}