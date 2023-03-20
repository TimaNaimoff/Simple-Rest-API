import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Consumer {
    public static void main(String[]args){
        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> jsonToSend=new HashMap<>();
        jsonToSend.put("Name","Valdemar");
        jsonToSend.put("Job","Overlord" );
        HttpEntity<Map<String,String>>request=new HttpEntity<>(jsonToSend);
        String postResponse="POST={"+restTemplate.postForObject("https://reqres.in/api/users/",request,String.class)+"}";
        System.out.println(postResponse);
        String url="https://reqres.in/api/users/2";
        String response="GET={"+restTemplate.getForObject(url,String.class)+"}";
        System.out.println(response);

    }
}
