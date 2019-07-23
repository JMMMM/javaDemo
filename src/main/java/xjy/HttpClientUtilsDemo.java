package xjy;


/**
 * xjy
 * 用于测试 httpHost 和httpPost 如果设置的url不同，是否会报错
 * 结论：
 *
 * @author jimmy
 * @date 2019-07-23
 */
public class HttpClientUtilsDemo {
    public static void main(String[] args) {
        String response = HttpClientUtil.doPostJson2("https://insight-test.xiaojiaoyu100.com/api/weixin/server/sendMsgByPhone", "", HttpClientUtil.getLiveDefaultHeaders(),
                "https://op.xiaozhibo.com/gorilla/api/v1.5/pay/xhjy/notify");
        System.out.println(response);

    }
}
