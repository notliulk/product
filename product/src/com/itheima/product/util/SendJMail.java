package cn.onloc.FirstMaven;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendJMail {
	
	//邮件发送的smtp.163.com服务器地址，在内网的话，很可能需要配置制定的ip才可以，这样的话就要配置特定hosts来跳转。
	//https://blog.csdn.net/xyw591238/article/details/69286638
	
		public static boolean sendMail(String email, String emailMsg) {
		
		String from = "17782271150@163.com"; 				// 邮件发送人的邮件地址
		String to = email; 										// 邮件接收人的邮件地址
		final String username = "17782271150";  	//发件人的邮件帐户
		final String password = "982155420welk";   					//发件人的邮件密码


		//定义Properties对象,设置环境信息
		Properties props = new Properties();

		//设置邮件服务器的地址
		props.setProperty("mail.transport.protocol", "smtp");//设置发送邮件使用的协议
		props.setProperty("mail.smtp.host", "smtp.163.com"); // 指定的smtp服务器
		props.setProperty("mail.smtp.auth", "true");
		//创建Session对象,session对象表示整个邮件的环境信息
		Session session = Session.getInstance(props);
		//设置输出调试信息
		session.setDebug(true);
		try {
			//Message的实例对象表示一封电子邮件
			MimeMessage message = new MimeMessage(session);
			//设置发件人的地址
			message.setFrom(new InternetAddress(from));
			//设置主题
			message.setSubject("测试邮件");
			//设置邮件的文本内容
			//message.setText("Welcome to JavaMail World!");
			message.setContent(emailMsg,"text/html;charset=utf-8");// 
			//从session的环境中获取发送邮件的对象
			Transport transport=session.getTransport();
			//连接邮件服务器
			transport.connect("smtp.163.com",25, username, password);
			//设置收件人地址,并发送消息
			transport.sendMessage(message,new Address[]{new InternetAddress(to)});
			transport.close();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
		
	public static void main(String[] args) {
		sendMail("982155420@qq.com","test javamail邮件");
	}
	
}
