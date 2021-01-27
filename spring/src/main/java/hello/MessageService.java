package hello;

import org.springframework.stereotype.Repository;

@Repository
public class MessageService {
    public MessageService() {
        System.out.println("MessageService ...");
    }
    public String getMessage(){
        return "Hello World";
    }
}
