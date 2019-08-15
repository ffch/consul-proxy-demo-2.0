package cn.pomit.consulproxy.service.mail;

import java.util.Date;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import cn.pomit.consulproxy.config.MailConfiguration;
import cn.pomit.consulproxy.dto.mail.Email;

public class MailService {

	/**
	 * 发送Html邮件
	 * 
	 * @throws Exception
	 */
	public static void sendHtmlMail(Email email) throws Exception {

		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(MailConfiguration.getInstance().getMailproperties(),
				new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(MailConfiguration.getInstance().getMailUserName(),
								MailConfiguration.getInstance().getMailPassword());
					}
				});
		// 根据session创建一个邮件消息
		Message mailMessage = new MimeMessage(sendMailSession);
		// 设置邮件消息的发送者
		mailMessage.setFrom(new InternetAddress(MailConfiguration.getInstance().getMailFrom(),
				MailConfiguration.getInstance().getMailFromName()));
		// 创建邮件的接收者地址，并设置到邮件消息中
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
		// 设置邮件消息的主题
		mailMessage.setSubject(email.getSubject());
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());

		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		Multipart mainPart = new MimeMultipart();
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart html = new MimeBodyPart();
		// 设置HTML内容
		html.setContent(email.getContent(), "text/html; charset=utf-8");
		mainPart.addBodyPart(html);
		// 将MiniMultipart对象设置为邮件内容
		mailMessage.setContent(mainPart);
		// 发送邮件
		Transport.send(mailMessage);
	}
}
