import com.evshou.admin.util.ESClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

public class Demo {
    @Test
    public void testConnect(){
        RestHighLevelClient client = ESClient.getClient();
        System.out.println("OK!");
    }
}
