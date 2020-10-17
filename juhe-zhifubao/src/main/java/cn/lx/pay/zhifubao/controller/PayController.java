package cn.lx.pay.zhifubao.controller;

import cn.lx.pay.zhifubao.vo.PayParamVO;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * cn.lx.pay.zhifubao.controller
 *
 * @Author Administrator
 * @date 15:47
 */
@Controller
@Slf4j
public class PayController {

    @PostMapping("/create/ali/pay")
    public void aliPay(PayParamVO payParamVO,HttpServletRequest request, HttpServletResponse response){
        String header = request.getHeader("User-Agent");
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016101700706706",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHXQPTEYQ4q1M7FWY0rCLSGsmKm51hbbxpFLd+RFrtUlazEc+IZEo6PQcUov7OWU2mQZMZ3HTROUhcRVxQ/TOgbk9a09ZQ2lSzWnJDGnct4yBPkbIh9K+sJr6CZ0gIbyThr2Ak/RIlr7XpG9tTK793/ejutFyzy/0xST8Q4/XrG2o9pbfBajG4FZog0t+0/FYho2t1q6AmSJnwaK4Yn3CZER1PpGDDu6xPU4Zh/5ilL6dUFzzs0xmQnCYMUsjjkd0rlOevzOo3Zy2DMniN+Hf3/FT4KLGs/XtJVVDt1ox73yVm98zozP329YngiXymaZekR4nT9fXlC7eM4Y8N4DOvAgMBAAECggEAHe0hgFN6EPFHqGNVwkVgOWU0s5Et3TFemzi6TI8eLyOqCVLht/y8MF33p8dVYBd8REpxFCGaLftlFQk8nKct98ULhEAbPKrYWQKhClbajGmPZigG4tzuzbePHNNqqHqyA7c7IVJV5cEQDaZb+epNHWEkU0nKyPFLW88ew0QyxTRo4mbfpY+eVrb3G/fBiYXbRem6TcCsbOQ77eTcMPpnOdP0QpwBCZ0lcbMOawpi9LE6RUv0mjRJYi07kUo5zeodenA3GC9RMf9YCCGClvuZVzR4xiOIO6vE0qT9pYa7uOce0dJhUT0bnKXBnZq4sciVVDRkaY5VTvUHdVB0utdScQKBgQDqwVvup7Lw9zVuwteWvlSUx95XbMKFVhX70GTW+qX+1/+duJ46kxqgvm/RwYLCPrNAqyGwQ9FVG9uNg8Vi1KBbQ1eOzbMo6+uiDtUogE8FVZwZWzSw9YKyIvahrWrLoRi5IiAbkN9UzYzuHti3Dy4/N+01lcIJ6RiHnDB2N8zT1wKBgQCTnQPDnZJc27V3slDqaf0iKS5llZ+eNFlnQwmv5Uz+1Wtb9cso+H4mElIUP13vauriHmXw6Ksy29rxtxPNRpVJyDG6wpKVb5ZvkK1634+uaRkC3lbRCxZZTO0NhaPCIhaXFLW2r6U0YHUYb/enI4z9aom0jihZ7juxF2e3+Msj6QKBgB5+aXOxwvO8GOu/UYPaS2BcKgyPKyFo0kg4hLDMND3LTv/s2FjhfOb+dcX4bgTPYjd3Q1QDKzD0Amv6fuxclEvmjnwVSj15j80oQhYVvK4DtdgxWcHW0lhTZFgSD7pNvclmnmcWRXxdiv3vcdUtmqNJn32Da4YgCjirWDwy+V9XAoGAfi11Dj0e4ykbURmnePjoW87/ze275yuwUEhJe4Vx71LW1mCgLIFcs4ZtiskvrnuiE28QjIEV9f9gg8WOs6Vl7w+lEpNHYV1lJjBxWdrHorpLmtwbMc1caTEMYMafWE5zKOmW+nXhrYfWD/GFq+UDm4r58tChRV4SwCnVirisTCECgYEAgpzraHQkr6wodgki6C/8p2WHkR5IRHwDel9dEGXWhlcrQXmNaSoQnZyQmVhnqrMSX9l76j+1RwKx0MN4IqlwxNx9u8LK+kQOlfPHjzTdvDQqPmbiul1uqMErMn1ifbx80WTTTxDd1gpuN2wmfAKHeJ8kR1o/wzypGyPPv1sHDKg=",
                "json",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh10D0xGEOKtTOxVmNKwi0hrJipudYW28aRS3fkRa7VJWsxHPiGRKOj0HFKL+zllNpkGTGdx00TlIXEVcUP0zoG5PWtPWUNpUs1pyQxp3LeMgT5GyIfSvrCa+gmdICG8k4a9gJP0SJa+16RvbUyu/d/3o7rRcs8v9MUk/EOP16xtqPaW3wWoxuBWaINLftPxWIaNrdaugJkiZ8GiuGJ9wmREdT6Rgw7usT1OGYf+YpS+nVBc87NMZkJwmDFLI45HdK5Tnr8zqN2ctgzJ4jfh39/xU+CixrP17SVVQ7daMe98lZvfM6Mz99vWJ4Il8pmmXpEeJ0/X15Qu3jOGPDeAzrwIDAQAB",
                "RSA2");

        AlipayTradeWapPayRequest payRequest = new AlipayTradeWapPayRequest();

        AlipayTradeWapPayModel alipayTradeWapPayModel = new AlipayTradeWapPayModel();
        //封装订单相关参数
        //下面4个是关键入参
        alipayTradeWapPayModel.setOutTradeNo(UUID.randomUUID().toString());
        //alipayTradeWapPayModel.setSubject("Iphone6 16G");
        alipayTradeWapPayModel.setSubject(payParamVO.getSubject());
        //alipayTradeWapPayModel.setTotalAmount("23");
        alipayTradeWapPayModel.setTotalAmount(payParamVO.getTotalAmount());
        alipayTradeWapPayModel.setBody(payParamVO.getBody());
        alipayTradeWapPayModel.setProductCode("FLASH-PAY");

        alipayTradeWapPayModel.setTimeoutExpress("30m");
        //支付宝订单相关参数
        payRequest.setBizModel(alipayTradeWapPayModel);
        //设置同步地址
        payRequest.setReturnUrl("http://192.168.43.210:8081/ali/callback");
        //设置异步地址
        payRequest.setNotifyUrl("http://192.168.43.210:8081/ali/callback");
        AlipayTradeWapPayResponse payResponse = null;
        try {
            payResponse = alipayClient.pageExecute(payRequest);
        } catch (AlipayApiException e) {
            throw new RuntimeException("生成支付异常");
        }
        if(payResponse.isSuccess()){
            log.info("调用成功:{}",payResponse.getBody());
            response.setContentType("text/html;charset=UTF-8");
            try {
                response.getWriter().write(payResponse.getBody());
                response.getWriter().flush();
                response.getWriter().close();

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("返回响应异常");
            }

        } else {
            log.info("调用失败");
        }
    }
}
