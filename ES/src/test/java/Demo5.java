import com.evshou.admin.entity.Person;
import com.evshou.admin.util.ESClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.util.Date;

public class Demo5 {
    ObjectMapper mapper=new ObjectMapper();
    RestHighLevelClient client = ESClient.getClient();
    @Test
    public void bulkCreateDoc() throws Exception{
        ObjectMapper mapper=new ObjectMapper();
        RestHighLevelClient client = ESClient.getClient();
        String index="person";
        String type="man";
        // 1. 准备 json 数据
        Person p1=new Person(1,"张三",23,new Date());
        Person p2=new Person(2,"李四",24,new Date());
        Person p3=new Person(3,"王五",25,new Date());
        String json1 = mapper.writeValueAsString(p1);
        String json2 = mapper.writeValueAsString(p2);
        String json3 = mapper.writeValueAsString(p3);
        // 2. 创建 request 将准备好的数据封装进去
        BulkRequest request=new BulkRequest();
        request.add(new IndexRequest(index,type,p1.getId().toString()).source(json1, XContentType.JSON));
        request.add(new IndexRequest(index,type,p2.getId().toString()).source(json2, XContentType.JSON));
        request.add(new IndexRequest(index,type,p3.getId().toString()).source(json3, XContentType.JSON));
        // 3. 用 client 执行
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        // 4. 输出结果
        System.out.println(response.toString());
    }
    @Test
    public void bulkDeleteDoc() throws Exception{
        ObjectMapper mapper=new ObjectMapper();
        RestHighLevelClient client = ESClient.getClient();
        String index="person";
        String type="man";
        // 1.创建 request 对象
        BulkRequest request=new BulkRequest();
        request.add(new DeleteRequest(index,type,"1"));
        request.add(new DeleteRequest(index,type,"2"));
        request.add(new DeleteRequest(index,type,"3"));
        // 2. client 执行
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        // 3. 输出
        System.out.println(response.toString());
    }
}
