package auto;

import org.springframework.stereotype.Component;

@Component
public class CompactDisc {
    public CompactDisc() {
        System.out.println("CompactDisc ...");
    }
    public void play(){
        System.out.println("正在播放音乐");
    }
}
