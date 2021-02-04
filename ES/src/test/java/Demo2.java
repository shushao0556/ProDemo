import com.evshou.admin.util.ESClient;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.junit.Test;

import java.io.IOException;

public class Demo2 {
    RestHighLevelClient client = ESClient.getClient();
    // number_of_replicas=1;number_of_shards=5;
    @Test
    public void createIndex() throws IOException {
        String index="person";
        String type="man";
        //1. 准备索引的 settings
        Settings.Builder settings = Settings.builder().
                put("number_of_replicas", 1)
                .put("number_of_shards",3);
        //2. 准备索引的结构 mappings
        XContentBuilder mappings = JsonXContent.contentBuilder()
                .startObject()
                    .startObject("properties")
                        .startObject("name")
                        .field("type", "text")
                        .endObject()
                        .startObject("age")
                        .field("type", "integer")
                        .endObject()
                        .startObject("birthday")
                        .field("type", "date")
                        .field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
                        .endObject()
                    .endObject()
                .endObject();
        // 3. 将 settings 和 mappings 封装到一个Request对象
        CreateIndexRequest request = new CreateIndexRequest(index)
                .settings(settings)
                .mapping(type,mappings);
        //4. 通过 client 对象去连接 ES 并执行创建索引
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        // 5. 输出
        System.out.println("response:"+response.toString());
    }
}
