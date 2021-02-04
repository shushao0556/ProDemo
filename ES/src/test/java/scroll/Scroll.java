package scroll;

import com.evshou.admin.util.ESClient;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.BoostingQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

public class Scroll {
    @Test
    public void scrollQuery() throws Exception{
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定 scroll 信息
        request.scroll(TimeValue.timeValueMinutes(1L));
        // 3. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.size(5);
        builder.sort("fee", SortOrder.DESC);
        builder.query(QueryBuilders.matchAllQuery());
        request.source(builder);
        // 4. 获取返回结果 scrollId，source
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        String scrollId = response.getScrollId();
        System.out.println("首页");
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        while (true) {
            // 5. 循环--> 创建 SearchScrollRequest
            SearchScrollRequest scrollRequest=new SearchScrollRequest(scrollId);
            // 6. 指定 scrollId
            scrollRequest.scroll(TimeValue.timeValueMinutes(1L));
            // 7. 执行查询获取返回结果
            SearchResponse searchResponse=client.scroll(scrollRequest,RequestOptions.DEFAULT);
            // 8. 判断是否查询到了数据，输出
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length > 0 && hits != null) {
                System.out.println("下一页");
                for (SearchHit hit : hits) {
                    System.out.println(hit.getSourceAsMap());
                }
            }else {
                // 9. 判断没有查询到数据-->退出循环
                System.out.println("结束");
                break;
            }
        }
        // 10. 创建 ClearScrollRequest
        ClearScrollRequest clearScrollRequest=new ClearScrollRequest();
        // 11. 指定 scrollId
        clearScrollRequest.addScrollId(scrollId);
        // 12. 删除 scrollId
        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        System.out.println("删除scroll："+clearScrollResponse.isSucceeded());
    }
    @Test
    public void deleteByQuery() throws Exception{
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 DeleteByQueryRequest
        DeleteByQueryRequest request=new DeleteByQueryRequest(index);
        request.types(type);
        // 2. 指定检索的条件
        request.setQuery(QueryBuilders.rangeQuery("fee").lt(4));
        // 3. 执行删除
        BulkByScrollResponse response = client.deleteByQuery(request, RequestOptions.DEFAULT);
        // 4. 输出返回结果
        System.out.println(response.toString());
    }
    @Test
    public void boolQuery() throws Exception{
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        bool.should(QueryBuilders.termQuery("province","武汉"));
        bool.should(QueryBuilders.termQuery("province","北京"));
        bool.mustNot(QueryBuilders.termQuery("operatorId",2));
        bool.must(QueryBuilders.matchQuery("smsContent","中国 平安").operator(Operator.AND));
        request.source(builder.query(bool));
        // 3. 执行查询
        SearchResponse response=client.search(request,RequestOptions.DEFAULT);
        // 4. 输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println(response.getHits().getHits().length);
    }
    @Test
    public void bootStingQuery() throws Exception{
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        BoostingQueryBuilder queryBuilder = QueryBuilders.boostingQuery(
                QueryBuilders.matchQuery("smsContent", "收货安装"),
                QueryBuilders.matchQuery("smsContent", "张三")
        );
        queryBuilder.negativeBoost(0.5F);
        request.source(builder.query(queryBuilder));
        // 3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4. 输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println(response.getHits().getHits().length);
    }
    @Test
    public void FilterQuery() throws Exception{
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        bool.filter(QueryBuilders.termQuery("corpName","招商银行"));
        bool.filter(QueryBuilders.rangeQuery("fee").lte(8));
        request.source(builder.query(bool));
        // 3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4. 输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println(response.getHits().getHits().length);
    }
}
