package query;

import com.evshou.admin.util.ESClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

public class Aggs {
    @Test
    public void aggQuery() throws Exception{
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest 对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定使用的聚合查询方式
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.cardinality("agg").field("province"));
        request.source(builder);
        // 3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4. 获取返回结果
        Cardinality agg = response.getAggregations().get("agg");
        System.out.println(agg.getValue());
    }
}
