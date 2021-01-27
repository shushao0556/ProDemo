package auto;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class CDPlayer {
    public CDPlayer() {
        System.out.println("CDPlayer ...");
    }
    public void play(){
        System.out.println("正在播放音乐");
    }
}
