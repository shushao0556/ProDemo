package query;

import com.evshou.admin.util.ESClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;

public class Match {
    @Test
    public void match_all() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 request 对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        builder.size(20); //ES 默认只查询10条，如想查更多，指定size
        request.source(builder);
        // 3. 执行查询
        SearchResponse response=client.search(request, RequestOptions.DEFAULT);
        // 4. 输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println("总共有"+response.getHits().getHits().length+"条数据");
    }
    @Test
    public void matchQuery() throws Exception{
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 request 对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("smsContent","收货安装"));
        request.source(builder);
        // 3. 执行查询
        SearchResponse response=client.search(request,RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println("总共有"+response.getHits().getHits().length+"条数据");
    }
    @Test
    public void booleanMatchQuery() throws Exception{
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 request 对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("smsContent","中国 健康").operator(Operator.OR));
        request.source(builder);
        // 3. 执行查询
        SearchResponse response=client.search(request,RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println("总共有"+response.getHits().getHits().length+"条数据");
    }
    @Test
    public void multiMatchQuery() throws Exception{
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 request 对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.multiMatchQuery("北京","province","smsContent"));
        request.source(builder);
        // 3. 执行查询
        SearchResponse response=client.search(request,RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println("总共有"+response.getHits().getHits().length+"条数据");
    }
}
