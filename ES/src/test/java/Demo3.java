import com.evshou.admin.util.ESClient;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

import java.io.IOException;

public class Demo3 {
    @Test
    public void isExists() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        // 1. 准备 request 对象
        GetIndexRequest request = new GetIndexRequest();
        request.indices("person");
        // 2. 通过 client 操作
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    @Test
    public void del() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        DeleteIndexRequest request=new DeleteIndexRequest();
        request.indices("person");
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }
}
