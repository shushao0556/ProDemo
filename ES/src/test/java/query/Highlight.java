package query;

import com.evshou.admin.util.ESClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;

import java.util.List;

public class Highlight {
    @Test
    public void highlightQuery() throws Exception{
        RestHighLevelClient client = ESClient.getClient();
        String index = "sms-logs-index";
        String type = "sms-logs-type";
        // 1. 创建 SearchRequest 对象
        SearchRequest request=new SearchRequest(index);
        request.types(type);
        // 2. 指定查询条件 (高亮)
        SearchSourceBuilder builder=new SearchSourceBuilder();
        // 2.1 指定查询条件
        builder.query(QueryBuilders.matchQuery("smsContent", "盒马"));
        // 2.2 指定高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("smsContent",10)
                .preTags("<font color='red'>")
                .postTags("</font>");
        request.source(builder.highlighter(highlightBuilder));
        // 3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4. 获取高亮数据，输出
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getHighlightFields().get("smsContent"));
        }
        System.out.println(hits.length);
    }
}
