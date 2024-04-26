package com.atcard.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMsg {
    private String from;//定义发件人
    private String to;//定义收件人
    private String host = "smtp.qq.com";//定义邮件服务器
    private String authCode = "oynxkdzfaysjdgia";//定义授权码
    // 获取系统属性
    private Properties properties = System.getProperties();
    private Session session;

    public SendMsg(String from, String to) {
        this.from = from;
        this.to = to;
        setEMailHost();
    }

    public SendMsg(String from, String to, String host, String authCode) {
        this(from, to);
        this.host = host;
        this.authCode = authCode;
        setEMailHost();
    }

    public void setEMailHost() {
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
    }

    public void sendEMail(String subject, String text) throws MessagingException {
        // 获取默认session对象
        session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, authCode); //发件人邮件用户名、授权码
            }
        });


            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject(subject);

            // 设置消息体
            message.setContent(text,"text/html;charset=utf-8");

            // 发送消息
            Transport.send(message);
            System.out.println("Succeed!");

    }

    public void sendVerifyCode(String verifyCode) throws MessagingException {
        sendEMail("TARGET来信", String.format("    <h2>亲爱的ChitChat用户:</h2>" +
                "    </br>" +
                "    <div>您好！感谢使用ChitChat，您的账号正在进行邮箱验证。</div>" +
                "    </br>" +
                "    <div>你的验证码是：</div>" +
                "    </br>" +
                "    <h1 style=\"text-align: center;font-size: 2em;color: cyan;background-color: black;\">%s</h1></br><div>验证码十分钟内有效，请及时使用！</div></br>" +
                "</br>    <div>此致</div>" +
                "    </br>" +
                "    <div style=\"text-align: right;\"></br>" +
                "        ChitChat敬上</br></br>" +
                "        %s</br>" +
                "    </div>",verifyCode,GetTimeUtil.getTimeYMD()));
    }
}
