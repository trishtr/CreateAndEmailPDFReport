package Test;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class testSample extends BaseClass {

	@Listeners(JyperionListener.class)

	public class TestReport extends BaseClass {

		@Test

	    public void testPDFReportOne(){

	        Assert.assertTrue(false);

	    }
		
		@Test
		public void test2() {
			Assert.assertTrue(false);
		}

	 }
}
