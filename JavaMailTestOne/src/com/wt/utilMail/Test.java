package com.wt.utilMail;

public class Test {
	
	public static void main(String[] args) {
		//�������Ҫ�������ʼ�   
		MailSenderInfo mailInfo = new MailSenderInfo();    
		mailInfo.setMailServerHost("smtp.163.com");    
		mailInfo.setMailServerPort("25");    
		mailInfo.setValidate(true);    
		mailInfo.setUserName("walk_wang_tt@163.com");
		
		// ��ͨsmtp�ȷ���ʱ���������������Ȩ��
		// ��ʱ�����Ϊ�������Ȩ�룬�����������������
		mailInfo.setPassword("*******"); 
      	mailInfo.setFromAddress("walk_wang_tt@163.com");    
      	mailInfo.setToAddress("609029365@qq.com");    
      	mailInfo.setSubject("����������� �� hohoTT");    
      	mailInfo.setContent("������������ ��  hohoTT666");    
      	//�������Ҫ�������ʼ�   
      	SimpleMailSender sms = new SimpleMailSender();   
      	sms.sendTextMail(mailInfo);//���������ʽ    
		sms.sendHtmlMail(mailInfo);//����html��ʽ   
	}

}
