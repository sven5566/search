package com.justtide.search.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SearchController {
    @RequestMapping("/search")
    public String search(@RequestParam(value = "value", required = false, defaultValue = "") String value)
            throws UnknownHostException {
        log.info("搜索值>>>>>" + value);
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        String result = doSearch2(client, value);
        client.close();

        log.info("搜索结果=========" + result);
        return result;
    }

    private String doSearch2(TransportClient client, String value) {
        final String productName = "productName";
        final String brandName = "brandName";
        final String sortName = "sortName";
        final String productKeyword = "productKeyword";
        final String englishBranchName = "englishBranchName";
        final MultiMatchQueryBuilder query = new MultiMatchQueryBuilder
                (value, productName, brandName, sortName, productKeyword,englishBranchName);
        query.field("product",100);
        query.field("englishBranchName",100);
        query.field(brandName,80);
        query.field(sortName,20);
        query.field(productKeyword,10);
        SearchResponse response = client.prepareSearch("gino_product").
                setQuery(query).
                get();
        return response.toString();
    }
}
