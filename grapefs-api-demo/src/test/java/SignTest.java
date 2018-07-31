import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.grapefs.demo.utils.MD5Util;

/**
 * @author: scottpeng
 * @Date: 2018/4/8
 * @Time: 下午2:06
 */
public class SignTest {

    public static void main(String[] args) {

        String data = "{ " +
                "\"agentNo\": \"20170313\", " +
                "\"channelCode\": \"KJ_HICKORY\", " +
                "\"merchantNo\": \"201709071056331\"," +
                "\"productInfo\": { " +
                "\"remitProduct\": [ " +
                "{ " +
                "\"productType\": \"D0\", " +
                "\"remitFee\": \"0.6\" " +
                "} " +
                "], " +
                "\"settProduct\": [ " +
                "{ " +
                "\"creditRate\": \"0.004\", " +
                "\"debitRate\": \"0.004\", " +
                "\"productType\": \"D0\" " +
                "} " +
                "], " +
                "\"tradeProduct\": [   { " +
                "\"debitRate\": \"0.06\", " +
                "\"productType\": \"2\" " +
                "} " +
                "] " +
                "}" +
                "}";

        String clientData = "{\"agentNo\":\"20170314\",\"merchantNo\":\"201801083001129\",\"channelCode\":\"KJ_HICKORY\",\"productInfo\":{\"remitProduct\":[{\"productType\":\"D0\",\"remitFee\":\"0.006\"}],\"settProduct\":[{\"creditRate\":\"0.006\",\"debitRate\":\"0.006\",\"productType\":\"D0\"}],\"tradeProduct\":[{\"creditRate\":\"0.006\",\"debitRate\":\"0.006\",\"productType\":\"2\"}]},\"randomStr\":\"18040823190214407521408555555463\",\"signType\":\"MD5\"}";
        JSONObject jsonObject  = JSON.parseObject(clientData);

        String result = MD5Util.getSign(jsonObject,"64bc6e6198fc440ba2db28d7eb021db5");
        System.out.println(result);


    }
}
