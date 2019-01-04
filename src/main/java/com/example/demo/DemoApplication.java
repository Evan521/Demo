package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

@SpringBootApplication
@ServletComponentScan //目前用于实时监听session的

@EnableCaching
public class DemoApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(DemoApplication.class, args);
        String str = "private String wed;" +
                "private String nihao;";
        String[] a=str.split(";");

        String b=null;

        //String[]  strs=str.split("String");
        for(int i=0,len=a.length;i<len;i++){
            b += a[i].split("String")[1];
        }
        HttpClient();
        System.out.println(b);

        System.out.println("程序启动成功。。。");
    }
    public static void HttpClient() throws IOException {
        JSONObject user = null;
        HttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://172.18.31.112:9000/restFul/test?a=1");
        request.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        ObjectMapper mapper = new ObjectMapper();
        user = mapper.readValue(entity.getContent(), JSONObject.class);
        System.out.println(user);
        xx((byte[]) user.get("result"),(RSAPublicKey)user.get("publicKey"));
    }

    public static void xx(byte[] str,RSAPublicKey rsaPublicKey){
        try{
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            KeyFactory  keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,publicKey);
            byte[] result = cipher.doFinal(str);
            System.out.println("私钥加密、公钥解密 ---解密:"+new String(result));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
