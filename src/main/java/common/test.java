package common;

import com.github.kevinsawicki.http.HttpRequest;
import org.testng.annotations.Test;

public class test {

    @Test
    public void apitest(){
        String response = HttpRequest.get("https://www.baidu.com").body();
        System.out.println(response);
    }

}
