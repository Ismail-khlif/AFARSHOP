package pidev.afarshop.websocketproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatmessageRepo extends JpaRepository<ChatMessage, Long>{

}
