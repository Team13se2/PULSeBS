package team13.pulsbes.controllerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;


class TestStudentController {

	@Test
	void testGetAllLectures() throws ClientProtocolException, IOException {	

	    BasicCookieStore cookieStore = new BasicCookieStore();
	    BasicClientCookie cookie_id = new BasicClientCookie("id", "1");
	    BasicClientCookie cookie_usr = new BasicClientCookie("username", "student1team13@gmail.com");
	    BasicClientCookie cookie_type = new BasicClientCookie("type", "0");
	    cookie_id.setDomain("localhost");
	    cookie_id.setPath("/");
	    cookieStore.addCookie(cookie_id);
	    cookie_usr.setDomain("localhost");
	    cookie_usr.setPath("/");
	    cookieStore.addCookie(cookie_usr);
	    cookie_type.setDomain("localhost");
	    cookie_type.setPath("/");
	    cookieStore.addCookie(cookie_type);
	    CloseableHttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
	 
	    final HttpGet request = new HttpGet("http://localhost:8081/student/getAllLectures");
	 
	    CloseableHttpResponse response = client.execute(request);
	    assert(response.getStatusLine().getStatusCode()== 200);
	}
	
}
