package com.evshou.admin.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class ESClient {
    public static RestHighLevelClient getClient(){
        //1. 创建 HttpHost 对象
        HttpHost host=new HttpHost("192.168.2.123",9200);
        //2. 创建 RestClientBuilder
        RestClientBuilder rb= RestClient.builder(host);
        //3. 创建 RestHighLevelClient 对象
        return new RestHighLevelClient(rb);
    }
}
