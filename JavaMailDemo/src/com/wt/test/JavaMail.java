package com.wt.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;


public class JavaMail {
    /**
     * Message���󽫴洢����ʵ�ʷ��͵ĵ����ʼ���Ϣ��
     * Message������Ϊһ��MimeMessage����������������Ҫ֪��Ӧ��ѡ����һ��JavaMail session��
     */
    private MimeMessage message;
    
    /**
     * Session�����JavaMail�е�һ���ʼ��Ự��
     * ÿһ������JavaMail��Ӧ�ó���������һ��Session��������������Session����
     * 
     * JavaMail��ҪProperties������һ��session����
     * Ѱ��"mail.smtp.host"    ����ֵ���Ƿ����ʼ�������
     * Ѱ��"mail.smtp.auth"    �����֤��Ŀǰ����ʼ�����������Ҫ��һ��
     */
    private Session session;
    
    /***
     * �ʼ��Ǽȿ��Ա�����Ҳ���Ա��ܵ���JavaMailʹ����������ͬ������������������ܣ�Transport �� Store�� 
     * Transport ������������Ϣ�ģ���Store�������š�������Ľ̳�����ֻ��Ҫ�õ�Transport����
     */
    private Transport transport;
    
    private String mailHost="";
    private String sender_username="";
    private String sender_password="";

    
    private Properties properties = new Properties();
    /*
     * ��ʼ������
     */
    public JavaMail(boolean debug) {
        InputStream in = JavaMail.class.getResourceAsStream("MailServer.properties");
        try {
            properties.load(in);
            this.mailHost = properties.getProperty("mail.smtp.host");
            this.sender_username = properties.getProperty("mail.sender.username");
            this.sender_password = properties.getProperty("mail.sender.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        session = Session.getInstance(properties);
        session.setDebug(debug);//�������е�����Ϣ
        message = new MimeMessage(session);
    }

    /**
     * �����ʼ�
     * 
     * @param subject
     *            �ʼ�����
     * @param sendHtml
     *            �ʼ�����
     * @param receiveUser
     *            �ռ��˵�ַ
     */
    public void doSendHtmlEmail(String subject, String sendHtml,
            String receiveUser) {
        try {
            // ������
            //InternetAddress from = new InternetAddress(sender_username);
            // ������������÷����˵�Nick name
            InternetAddress from = new InternetAddress(MimeUtility.encodeWord("��Ӱ")+" <"+sender_username+">");
            message.setFrom(from);
            
            // �ռ���
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);//��������CC��BCC
            
            // �ʼ�����
            message.setSubject(subject);
            
            String content = sendHtml.toString();
            // �ʼ�����,Ҳ����ʹ���ı�"text/plain"
            message.setContent(content, "text/html;charset=UTF-8");
            
            // �����ʼ�
            message.saveChanges();
            
            transport = session.getTransport("smtp");
            // smtp��֤���������������ʼ��������û�������
            transport.connect(mailHost, sender_username, sender_password);
            // ����
            transport.sendMessage(message, message.getAllRecipients());
            //System.out.println("send success!");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(transport!=null){
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        JavaMail se = new JavaMail(false);
        se.doSendHtmlEmail("�ʼ�����", "�ʼ�����", "xxx@XX.com");
    }
}
