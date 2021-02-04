package query;

import com.evshou.admin.util.ESClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class Term {
    ObjectMapper mapper=new ObjectMapper();
    @Test
    public void term() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 request 对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.from(0);
        builder.size(5);
        builder.query(QueryBuilders.termQuery("province","北京"));
        request.source(builder);
        // 3. 执行查询
        SearchResponse response=client.search(request, RequestOptions.DEFAULT);
        // 4. 获取到 _source 中的数据并展示
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
    @Test
    public void terms() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 request 对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.termsQuery("province","北京","上海"));
        request.source(builder);
        // 3. 执行查询
        SearchResponse response=client.search(request, RequestOptions.DEFAULT);
        // 4. 获取到 _source 中的数据并展示
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
}
