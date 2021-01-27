package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessagePrinter {
    private MessageService ms;
    @Autowired
    public void setMs(MessageService ms) {
        this.ms = ms;
    }
    public MessagePrinter() {
        System.out.println("MessagePrinter");
    }
    public void printMessage(){
        System.out.println(this.ms.getMessage());
    }
}
