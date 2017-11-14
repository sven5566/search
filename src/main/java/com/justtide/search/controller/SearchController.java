package com.justtide.search.controller;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class SearchController {
    @RequestMapping("/search")
    public String search(@RequestParam(value = "value", required = false, defaultValue = "") String value)
            throws UnknownHostException {
        System.out.println("搜索值>>>>>"+value);
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        String result = doSearch2(client,value);
        client.close();
        System.out.println("搜索结果========="+result);
        return result;
    }

    private String doSearch2(TransportClient client,String value) {
        SearchResponse response = client.prepareSearch("gino_product").
                setQuery(new MultiMatchQueryBuilder(value,"productName","brandName","sortName","productKeyword")).
                get();
        return response.toString();
    }
}
