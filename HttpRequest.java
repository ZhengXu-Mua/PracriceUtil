package test;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class HttpRequest {

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000); 
            out = new PrintWriter(conn.getOutputStream());
            URLEncoder.encode(param,"utf-8");
            out.print(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    

    public static void main(String[] args){
        String s=HttpRequest.sendPost("http://test1.sendinfo.com.cn/zybdata/prodata/getStatInfo.htm", "req_data={\"beginTime\":\"2018-09-01 00:00:00\",\"endTime\":\"2018-09-05 00:00:00\",\"groupCode\":\"LR9bmhLZIt\",\"sign\":\"a635f8a3410774d4b9349722f7527b5e\",\"appCode\":\"2016081700000041\"}");
        System.out.println(s);
    }
}
