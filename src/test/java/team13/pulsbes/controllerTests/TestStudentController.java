package team13.pulsbes.controllerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

class TestStudentController {

	@Test
	void testGetAllLectures() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8081/student/getAllLectures");
		CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assert(response.getStatusLine().getStatusCode() == 200);
	}
	
	@Test
	void testBookLecture() {
		
	}
}
