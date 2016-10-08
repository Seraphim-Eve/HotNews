package com.springapp.mvc.impl;

import com.springapp.mvc.service.HotNews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Xr on 2016/9/5.
 */
public class HotNewsImpl implements HotNews {

    /**
     * get HotNews from baidu.
     * 默认16条数据.
     * @return
     */
    @Override
    public ArrayList<String> getHotNews() {
        ArrayList<String> dataSet = new ArrayList<String>();
        BufferedReader in = null;
        try {

            String str = "http://top.baidu.com/buzz?b=1&c=513&fr=topbuzz_b1";
            URL url = new URL(str);
            URLConnection conn = url.openConnection();

            conn.connect();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            String result = null;

            //Pattern pattern = Pattern.compile("<td class=\"keyword\">");

            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }

            int count = 1;

            Document document = Jsoup.parse(result);
            Elements elements = document.select("a");
            for (Element element : elements) {
                if (element.hasClass("list-title") && count <= 16) {
                    dataSet.add(element.toString());
                    count++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return dataSet;
    }
}
