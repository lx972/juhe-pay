package cn.lx.pay.wx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cn.lx.pay.wx.controller
 *
 * @Author Administrator
 * @date 10:09
 */
@Controller
@Slf4j
public class PayController {

    @PostMapping("/create/wx/pay")
    public void aliPay( HttpServletRequest request, HttpServletResponse response){
        log.info("微信支付创建成功");
    }
}
