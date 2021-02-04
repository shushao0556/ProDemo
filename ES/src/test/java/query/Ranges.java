package query;

import com.evshou.admin.util.ESClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Ranges {
    @Test
    public void RangeQuery() throws Exception{
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest 对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定使用的聚合查询方式
        SearchSourceBuilder builder=new SearchSourceBuilder();
        // 日期
        request.source(builder.aggregation(
                AggregationBuilders.dateRange("agg").format("yyyy").field("createDate")
                        .addUnboundedTo(2000).addUnboundedFrom(2000)
        ));
        // 数值
        request.source(builder.aggregation(AggregationBuilders.range("agg").field("fee")
                .addUnboundedTo(5).addRange(5,10).addUnboundedFrom(10)));
        // 3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4. 获取返回结果
        Range agg = response.getAggregations().get("agg");
        for (Range.Bucket bucket : agg.getBuckets()) {
            String key = bucket.getKeyAsString();
            long docCount = bucket.getDocCount();
            System.out.println(key+"/"+docCount);
        }
    }
    @Test
    public void DateRangeQuery() throws Exception{
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest 对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定使用的聚合查询方式
        SearchSourceBuilder builder=new SearchSourceBuilder();
        request.source(builder.aggregation(
                AggregationBuilders.dateRange("agg").format("yyyy").field("createDate")
                .addUnboundedTo(2000).addUnboundedFrom(2000)
        ));
        // 3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4. 获取返回结果
        Range agg = response.getAggregations().get("agg");
        for (Range.Bucket bucket : agg.getBuckets()) {
            String key = bucket.getKeyAsString();
            long docCount = bucket.getDocCount();
            System.out.println(key+"/"+docCount);
        }
    }
    @Test
    public void extendedRangeQuery() throws Exception{
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest 对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定使用的聚合查询方式
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.extendedStats("agg").field("fee"));
        request.source(builder);
        // 3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4. 获取返回结果
        ExtendedStats agg = response.getAggregations().get("agg");
        System.out.println(agg.getMax()+"\t"+agg.getMin()+"\t"+agg.getSum());
    }
    @Test
    public void geoPolygonQuery() throws Exception{
        RestHighLevelClient client = ESClient.getClient();
        String index = "map-maps";
        String type = "map";
        // 1. 创建 SearchRequest 对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定使用的聚合查询方式
        SearchSourceBuilder builder=new SearchSourceBuilder();
        List<GeoPoint> list=new ArrayList<>();
        list.add(new GeoPoint(30.670825,104.058129));
        list.add(new GeoPoint(30.668247,104.056458));
        list.add(new GeoPoint(30.669443,104.060994));
        builder.query(QueryBuilders.geoPolygonQuery("location",list));
        request.source(builder);
        // 3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4. 获取返回结果
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsMap());
        }
    }
}
