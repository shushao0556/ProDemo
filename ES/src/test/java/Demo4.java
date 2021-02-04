import com.evshou.admin.entity.Person;
import com.evshou.admin.util.ESClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Demo4 {
    ObjectMapper mapper=new ObjectMapper();
    RestHighLevelClient client = ESClient.getClient();
    @Test
    public void createDoc() throws Exception {
        // 1.准备 json 数据
        Person person=new Person(1,"张三",23,new Date());
        String json = mapper.writeValueAsString(person);
        // 2. 准备一个 request 对象 (手动指定id)
        IndexRequest request=new IndexRequest("person","man",person.getId().toString());
        request.source(json, XContentType.JSON);
        // 3. 通过 client 对象执行添加
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult().toString());
    }
    @Test
    public void updateDoc() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        //1. 创建Map集合，指定需要修改的内容
        Map<String,Object> doc=new HashMap<>();
        doc.put("name","李四");
        String docId="1";
        // 2. 创建 request 对象，封装数据
        UpdateRequest request=new UpdateRequest("person","man",docId);
        request.doc(doc);
        // 3. 通过 client 对象执行
        UpdateResponse update = client.update(request, RequestOptions.DEFAULT);
        // 4. 输出结果
        System.out.println(update.getResult().toString());
    }
    @Test
    public void deleteDoc() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        DeleteRequest request=new DeleteRequest("person","man","1");
        DeleteResponse delete = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.getResult().toString());
    }
}
