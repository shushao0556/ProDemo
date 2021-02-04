package sms;

import com.evshou.admin.entity.Person;
import com.evshou.admin.entity.SmsLogs;
import com.evshou.admin.util.ESClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Demo2 {
    ObjectMapper mapper=new ObjectMapper();
    RestHighLevelClient client = ESClient.getClient();
    String index="sms-logs-index";
    String type="sms-logs-type";
    // number_of_replicas=1;number_of_shards=5;
    @Test
    public void createIndex() throws IOException {
        //1. 准备索引的 settings
        Settings.Builder settings = Settings.builder().
                put("number_of_replicas", 1)
                .put("number_of_shards",5);
        //2. 准备索引的结构 mappings
        XContentBuilder mappings = JsonXContent.contentBuilder()
                .startObject()
                    .startObject("properties")
                        .startObject("createDate")
                        .field("type", "date")
                        .field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
                        .endObject()
                        .startObject("sendDate")
                        .field("type", "date")
                        .field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
                        .endObject()
                        .startObject("longCode")
                        .field("type", "keyword")
                        .endObject()
                        .startObject("mobile")
                        .field("type", "keyword")
                        .endObject()
                        .startObject("corpName")
                        .field("type", "text")
                        .field("analyzer", "ik_max_word")
                        .endObject()
                        .startObject("smsContent")
                        .field("type", "text")
                        .field("analyzer", "ik_max_word")
                        .endObject()
                        .startObject("state")
                        .field("type", "integer")
                        .endObject()
                        .startObject("operatorId")
                        .field("type", "integer")
                        .endObject()
                        .startObject("province")
                        .field("type", "keyword")
                        .endObject()
                        .startObject("ipAddr")
                        .field("type", "ip")
                        .endObject()
                        .startObject("replyTotal")
                        .field("type", "integer")
                        .endObject()
                        .startObject("fee")
                        .field("type", "long")
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
    @Test
    public void bulkCreateDoc() throws Exception{
        SmsLogs s1=new SmsLogs("1",new Date(),new Date(),"10690000988","13800000000",
                "途虎养车",null,0,1,"湖南",
                "120.120.120.120",10,1);
        s1.setSmsContent("【途虎养车】亲爱的张三先生/女士，您在途虎购买的货品(单号TH123456)已 到指定安装店多日");
        BulkRequest request=new BulkRequest();
        request.add(new IndexRequest(index, type, s1.getId())
                .source(mapper.writeValueAsString(s1), XContentType.JSON));
        client.bulk(request,RequestOptions.DEFAULT);
        System.out.println("OK!");
    }
}
