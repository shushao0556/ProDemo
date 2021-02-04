package query;

import com.evshou.admin.util.ESClient;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;

public class OtherQuery {
    @Test
    public void getId() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1.创建 GetRequest 对象
        GetRequest request=new GetRequest(index,type,"24");
        // 2. 执行查询
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        // 3. 输出结果
        System.out.println(response.getSourceAsMap());
    }
    @Test
    public void getIds() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.idsQuery().addIds("24","25","27"));
        request.source(builder);
        // 3. 执行
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4. 输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println(response.getHits().getHits().length);
    }
    @Test
    public void getIdsByPrefix() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.prefixQuery("corpName","滴滴"));
        request.source(builder);
        // 3. 执行
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4. 输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println(response.getHits().getHits().length);
    }
    @Test
    public void getIdsByFuzzy() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.fuzzyQuery("corpName","滴滴").prefixLength(2));
        request.source(builder);
        // 3. 执行
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4. 输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println(response.getHits().getHits().length);
    }
    @Test
    public void getIdsByWildCard() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.wildcardQuery("corpName","中国*"));
        request.source(builder);
        // 3. 执行
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4. 输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println(response.getHits().getHits().length);
    }
    @Test
    public void getIdsByRange() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.rangeQuery("replyTotal").gte(20).lte(50));
        request.source(builder);
        // 3. 执行
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4. 输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println(response.getHits().getHits().length);
    }
    @Test
    public void getIdsByRegexp() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.regexpQuery("mobile","180[0-9]{8}"));
        request.source(builder);
        // 3. 执行
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4. 输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println(response.getHits().getHits().length);
    }
}
