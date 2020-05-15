package com.monkey.dao;

import org.springframework.stereotype.Repository;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Repository
public class DeltaDAO {
    private static final URL url;
    static {
        URL url1;
        try {
            url1 = new URL("http://localhost:8089/MonkeDocs/rest/delta");
        } catch (MalformedURLException e) {
            url1 = null;
            e.printStackTrace();
        }
        url = url1;
    }
}
