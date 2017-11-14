package com.justtide.search;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.UnknownHostException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchApplicationTests {
    @Test
    public void contextLoads() throws UnknownHostException {
//        TransportClient client = new TransportClient(Settings.EMPTY)
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9200));
//        GetResponse response = client.prepareGet("my_index", "my_type", "1").get();
//        String result = response.getSource().toString();
//        System.out.println("response=" + result);
//        client.close();
    }

//    @Test
//    public void test(){
//        JestClientFactory factory = new JestClientFactory();
//        factory.setHttpClientConfig(new HttpClientConfig.Builder("http://localhost:9200")
//                .multiThreaded(true)
//                .build());
//        JestClient client = factory.getObject();
//    }

}
