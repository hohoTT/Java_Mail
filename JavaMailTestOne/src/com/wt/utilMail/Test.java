package com.wt.utilMail;

public class Test {
	
	public static void main(String[] args) {
		//这个类主要是设置邮件   
		MailSenderInfo mailInfo = new MailSenderInfo();    
		mailInfo.setMailServerHost("smtp.163.com");    
		mailInfo.setMailServerPort("25");    
		mailInfo.setValidate(true);    
		mailInfo.setUserName("walk_wang_tt@163.com");
		
		// 开通smtp等服务时，设置你的邮箱授权码
		// 此时输入的为邮箱的授权码，而不是你的邮箱密码
		mailInfo.setPassword("*******"); 
      	mailInfo.setFromAddress("walk_wang_tt@163.com");    
      	mailInfo.setToAddress("609029365@qq.com");    
      	mailInfo.setSubject("设置邮箱标题 如 hohoTT");    
      	mailInfo.setContent("设置邮箱内容 如  hohoTT666");    
      	//这个类主要来发送邮件   
      	SimpleMailSender sms = new SimpleMailSender();   
      	sms.sendTextMail(mailInfo);//发送文体格式    
		sms.sendHtmlMail(mailInfo);//发送html格式   
	}

}
