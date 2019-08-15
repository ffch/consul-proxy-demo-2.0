package cn.pomit.consulproxy.config;

import java.util.Properties;

public class MailConfiguration {
	private static MailConfiguration instance = null;
	private String mailUserName;
	private String mailPassword;
	private String mailFrom;
	private String mailFromName;
	private Properties mailproperties;

	public static void initConfiguration(Properties properties) {
		instance = new MailConfiguration();
		instance.mailUserName = properties.getProperty("mail.username");
		instance.mailPassword = properties.getProperty("mail.password");
		instance.mailFrom = properties.getProperty("mail.from");
		instance.mailFromName = properties.getProperty("mail.fromName");
		instance.mailproperties = new Properties();
		instance.mailproperties.put("mail.smtp.host", properties.getProperty("mail.host"));
		instance.mailproperties.put("mail.smtp.port", properties.getProperty("mail.port"));
		instance.mailproperties.put("mail.smtp.auth", properties.getProperty("mail.properties.mail.smtp.auth"));
		instance.mailproperties.put("mail.smtp.starttls.enable",
				properties.getProperty("mail.properties.mail.smtp.starttls.enable"));
		instance.mailproperties.put("mail.smtp.ssl.enable",
				properties.getProperty("mail.properties.mail.smtp.ssl.enable"));
		instance.mailproperties.put("mail.smtp.starttls.required",
				properties.getProperty("mail.properties.mail.smtp.starttls.required"));
	}

	public static MailConfiguration getInstance() {
		return instance;
	}

	public String getMailUserName() {
		return mailUserName;
	}

	public void setMailUserName(String mailUserName) {
		this.mailUserName = mailUserName;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public Properties getMailproperties() {
		return mailproperties;
	}

	public void setMailproperties(Properties mailproperties) {
		this.mailproperties = mailproperties;
	}

	public String getMailFromName() {
		return mailFromName;
	}

	public void setMailFromName(String mailFromName) {
		this.mailFromName = mailFromName;
	}
}
