package com.zj.util.mail;

import com.zj.util.excel.ExcelUtil;
import com.zj.util.excel.usermodel.ExcelData;
import com.zj.util.file.FileUtil;
import com.zj.util.file.PropertiesUtil;
import com.zj.util.mail.usermodel.Email;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class JavaMail {
	private static final Logger LOGGER = Logger.getLogger(JavaMail.class);
	private static Transport transport;
    /**
     * 发送邮件
     * 
     * @param headName
     *            邮件头文件名
     * @param sendHtml
     *            邮件内容
     * @param receiveUser
     *            收件人地址
     */
    public static boolean doSendHtmlEmail(String headName, String sendHtml,
            String receiveUser) {
    	boolean result = false;
        try {
            // 设置服务器
            String VALUE_SMTP = PropertiesUtil.getProperty("VALUE_SMTP");
            // 发件人用户名、密码
            String SEND_USER = PropertiesUtil.getProperty("SEND_USER");
            String SEND_UNAME = PropertiesUtil.getProperty("SEND_UNAME");
            String SEND_PWD = PropertiesUtil.getProperty("SEND_PWD");

        	//初始化配置
        	Properties props = System.getProperties();
        	props.setProperty("mail.smtp.host", VALUE_SMTP);
        	props.put("mail.smtp.auth", true);
        	Session s = Session.getInstance(props);
//        s.setDebug(true);
        	Message message = new MimeMessage(s);
            // 发件人
            InternetAddress from = new InternetAddress(SEND_USER);
            message.setFrom(from);
            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);
            // 邮件标题
            message.setSubject(headName);
            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=UTF-8");
            message.saveChanges();
            Transport transport = s.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("send success!");
            result = true;
        } catch (AddressException e) {
        	result = false;
            e.printStackTrace();
        } catch (MessagingException e) {
        	result = false;
            e.printStackTrace();
        }
        return result;
    }
    
    public static boolean sendVariCode(String email,String variCode){
    	String headName = PropertiesUtil.getProperty("emailHead");
    	String sendHtml = PropertiesUtil.getProperty("emailContent", new String[]{variCode});
    	return doSendHtmlEmail(headName, sendHtml, email);
    }
    
    /**
     * 发送带附件的
     * <a href="xi.yang@i-jia.net">yangxi</a>
     * @param headName
     * @param sendHtml
     * @param file
     * @param receiveUser
     * @return
     */
    public static boolean sendEmailWithFile(Email email,File file){
    	if(file == null){
    		LOGGER.error("file is null");
    		return false;
    	}
    	try {
    		// 设置服务器
            String VALUE_SMTP = PropertiesUtil.getProperty("VALUE_SMTP");
            // 发件人用户名、密码
            String SEND_USER = PropertiesUtil.getProperty("SEND_USER");
            String SEND_UNAME = PropertiesUtil.getProperty("SEND_UNAME");
            String SEND_PWD = PropertiesUtil.getProperty("SEND_PWD");

        	//初始化配置
        	Properties props = System.getProperties();
        	props.setProperty("mail.smtp.host", VALUE_SMTP);
        	props.put("mail.smtp.auth", true);
        	Session s = Session.getInstance(props);
//        s.setDebug(true);
        	Message message = new MimeMessage(s);
        	message.setSubject(email.getHeadName());
            // 发件人
            InternetAddress from = new InternetAddress(SEND_USER);
            message.setFrom(from);
            // 收件人
            InternetAddress to = new InternetAddress(email.getEmail());
            message.setRecipient(Message.RecipientType.TO, to);
    		//文件内容
    		Multipart multiPart = new MimeMultipart();
    		BodyPart htmlPart = new MimeBodyPart();
    		htmlPart.setContent(email.getSendHtml(),"text/html;charset=UTF-8");
    		multiPart.addBodyPart(htmlPart);
			//附件
    		BodyPart filePart = new MimeBodyPart();
			DataSource dataSource = new FileDataSource(file);
			filePart.setDataHandler(new DataHandler(dataSource));
			filePart.setFileName(MimeUtility.encodeWord(file.getName()));
			multiPart.addBodyPart(filePart);
			
			message.setContent(multiPart);
			message.saveChanges();
			
			transport = s.getTransport("smtp");
	        // smtp验证，就是你用来发邮件的邮箱用户名密码
	        transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
	        // 发送
	        transport.sendMessage(message, message.getAllRecipients());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				transport.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
    	return true;
    }
    
    /**
     * 发送带excel附件的文件
     * <a href="xi.yang@i-jia.net">yangxi</a>
     * @param email
     * @param excelData
     * @return
     * @throws IOException
     */
    public static boolean sendEmailWithExcel(Email email,ExcelData excelData) throws IOException{
    	ExcelUtil.poiExportExcel(excelData);
		File file = new File(excelData.getFileName());
		JavaMail.sendEmailWithFile(email,file);
//		删除文件
		FileUtil.deleteQuietly(file);
		return true;
    }

    public static void main(String[] args) {
    	PropertiesUtil.load("mail.properties");
        File file = new File("C:\\Users\\ouyang\\Desktop\\品牌数据.xls");
        System.out.println(file.getName());
    }
}
 