/*
 * Chsi
 * Created on 2021-07-14
 */
package com.perfat.boot.stripe;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.model.checkout.Session;
import com.stripe.param.RefundCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@RequestMapping(value = "/stripe")
@Controller
public class StripeCardPayController {

    @RequestMapping(value = "/index")
    public String showHtmlPage() {
        return "html/stripe/index";
    }

    @RequestMapping(value = "/show")
    public String showPay() {
        return "html/show";
    }

    @RequestMapping(value = "/pay")
    @ResponseBody
    public String createSession(HttpServletResponse response) throws StripeException {
        Stripe.apiKey = "sk_test_51JD1N3JEDCE6QOvpx6hIWUg17RyapjxSzuNvfyNNcKMHadNaza7LdaPc3QBReZJ1OSdpdvLYbubVTYRhFi5aLgQR00PdaaNbht";

        final String YOUR_DOMAIN = "http://localhost";
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(YOUR_DOMAIN + "/success.html")
                .setCancelUrl(YOUR_DOMAIN + "/cancel.html")
                .addLineItem(SessionCreateParams.LineItem.builder().setQuantity(1L).setPriceData(SessionCreateParams.LineItem.PriceData.builder().setCurrency("usd").setUnitAmount(2000L)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder().setName("Stubborn Attachments").build())
                                .build())
                        .build())
                .build();
        Session session = Session.create(params);

        Map<String, String> map = new HashMap<>();
        System.out.println("paymentIntent：" + session.getPaymentIntent());
        System.out.println("sessionId：" + session.getId());
        map.put("sessionId", session.getId());
        return new Gson().toJson(map);
    }

    @RequestMapping(value = "/refund")
    @ResponseBody
    public String refund(String id) throws StripeException {
        Stripe.apiKey = "sk_test_51JD1N3JEDCE6QOvpx6hIWUg17RyapjxSzuNvfyNNcKMHadNaza7LdaPc3QBReZJ1OSdpdvLYbubVTYRhFi5aLgQR00PdaaNbht";
        Refund refund = Refund.create(RefundCreateParams.builder()
                .setPaymentIntent(id)
                .setAmount(2000L)
                .build());
        return refund.getStatus();
    }

    @RequestMapping(value = "/result")
    @ResponseBody
    public String webHook(HttpServletRequest request) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try {
            br = request.getReader();
            String str;
            while ( (str = br.readLine()) != null ) {
                sb.append(str);
            }
            br.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( null != br ) {
                try {
                    br.close();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        }
        JSONObject jsonObject = new JSONObject(sb.toString());
        String type = jsonObject.get("type").toString();
        System.out.println(type);
/*
        if ( StringUtils.equals(type, "charge.refunded") ) {
            System.out.println(sb.toString());
        }
*/
        return "success";
    }
}