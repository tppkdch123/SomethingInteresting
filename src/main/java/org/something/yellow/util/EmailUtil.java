package org.something.yellow.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class EmailUtil {

    @Value("${qq.mail.smtp.username}")
    private String qqEmail;

    @Value("${163.mail.smtp.username}")
    private String email163;

    private static final String EMAIL_REGEX="^[\\w-.]+@[a-z0-9A-Z.-]+.(com|cn)";

    private static Logger logger= LoggerFactory.getLogger(EmailUtil.class);

    private static String LOG_PREFIX="[邮件工具] ";

    @Autowired
    @Qualifier("qqEmailProperties")
    private Properties qqProperties;

    @Autowired
    @Qualifier("qqAccount")
    private Authenticator qqAuthenticator;

    @Autowired
    @Qualifier("emailProperties163")
    private Properties properties163;

    @Autowired
    @Qualifier("account163")
    private Authenticator authenticator163;


    public void sendMessageByQQEmail(String mailname, String subject, String content) throws Exception{
       sendEmail(qqEmail,mailname,subject,content,qqProperties,qqAuthenticator);

    }

    public void sendMessageBy163Email(String mailname,String subject,String content) throws Exception{
        sendEmail(email163,mailname,subject,content,properties163,authenticator163);
    }

    public void sendEmail(String sender,String mailname,String subject,String content,Properties properties,Authenticator authenticator) throws Exception{
            Message message = new MimeMessage(Session.getInstance(properties,authenticator));
            message.setFrom(new InternetAddress(sender));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailname));
            message.setSubject(subject);
            message.setContent(content, "text/html;charset=utf-8");
            try {
                Transport.send(message);
            } catch (MessagingException e) {
                logger.error("{} 邮件发送异常",LOG_PREFIX,e);
            }
    }

}
