package Test;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
		public static WebDriver driver;
		
		
		@BeforeSuite
		public void setUp() {
		
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("http://google.com/");
			
	    }
		public static void captureScreen(WebDriver driver, String xpathfile) throws IOException {
			TakesScreenshot ts = ((TakesScreenshot) driver);
			File source = ts.getScreenshotAs(OutputType.FILE);
			File target = new File(xpathfile);
			FileUtils.copyFile(source, target);
			System.out.println("Screenshot is taken");
			
		}
		
		public static void sendPDFReportByGMail() {
			final String username = "reonatr123@gmail.com";
	 		final String password = "Aa12345678Bb";
	 			 	 				
			Properties props = new Properties();
					
			props.put("mail.smtp.auth", true);
		
			props.put("mail.smtp.starttls.enable", true);
		
			props.put("mail.smtp.host", "smtp.gmail.com");
		
			props.put("mail.smtp.port", "587");
				
			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication()
						{
							return new PasswordAuthentication(username, password);
						}
			});
			
			try {
				 	Message message = new MimeMessage(session);
			        message.setFrom(new InternetAddress("reonatr123@gmail.com"));
			        message.setRecipients(Message.RecipientType.TO,
			                InternetAddress.parse("trantrang.ip@gmail.com"));
			        message.setSubject("Testing Subject");
			        message.setText("PFA");
			        
			        MimeBodyPart messageBodyPart = new MimeBodyPart();

			        Multipart multipart = new MimeMultipart();
			        
			        String file = System.getProperty("user.dir")+ "\\Default test.pdf";
			        String fileName = "Default test.pdf";
			        DataSource source = new FileDataSource(file);
			        messageBodyPart.setDataHandler(new DataHandler(source));
			        messageBodyPart.setFileName(fileName);
			        multipart.addBodyPart(messageBodyPart);
			        message.setContent(multipart);
			        
			        
			        File f = new File(System.getProperty("user.dir")+ "\\Screenshots");
			        File[] paths = f.listFiles();
			        for (File path: paths)
			        {
			        	String picPath = path.toString();
			        	System.out.println(picPath);
			        	String picName = path.getName();
			        	System.out.println(picName);
			        	DataSource sc = new FileDataSource(picPath);
			        	messageBodyPart.setDataHandler(new DataHandler(sc));
			        	messageBodyPart.setFileName(picName);
					    multipart.addBodyPart(messageBodyPart);
					 					    			        	       	
			        }
			             
			        message.setContent(multipart);

			        System.out.println("Sending");

			        Transport.send(message);

			        System.out.println("Done");

			    } catch (MessagingException e) {
			        e.printStackTrace();
			    }
				
			
			}
	
		@AfterSuite
	
	    public void tearDown(){
			driver.quit();
			sendPDFReportByGMail();
	   }

}
