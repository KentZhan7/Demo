//package com.demo.service;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.view.RedirectView;
//
//import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
//import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.auth.oauth2.TokenResponse;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.client.util.DateTime;
//import com.google.api.services.calendar.Calendar.Events;
//import com.google.api.services.calendar.CalendarScopes;
//import com.google.api.services.calendar.model.Event;
//
//@Service
//public class TestGC {
//
//	private final static Log logger = LogFactory.getLog(TestGC.class);
//	private static final String APPLICATION_NAME = "";
//	private static HttpTransport httpTransport;
//	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//	private static com.google.api.services.calendar.Calendar client;
//
//	GoogleClientSecrets clientSecrets;
//	GoogleAuthorizationCodeFlow flow;
//	Credential credential;
//
//	private String clientId = "387545248711-ib4mmoi6d1ak7pgkro7eeqli1e7vk9cm.apps.googleusercontent.com";
//	private String clientSecret = "nEp7OCmRIqnDIOSARU6iLCyJ";
//	private String redirectURI = "http://localhost/pmis/login/google";
//
//	private Set<Event> events = new HashSet<>();
//
//	final DateTime date1 = new DateTime("2017-05-05T16:30:00.000+05:30");
//	final DateTime date2 = new DateTime(new Date());
//
//	public void setEvents(Set<Event> events) {
//		this.events = events;
//	}
//
//	public String oauth2Callback(String code) {
//		StringBuilder str = new StringBuilder();
//		com.google.api.services.calendar.model.Events eventList;
//		String message;
//		try {
//			TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();
//			credential = flow.createAndStoreCredential(response, "userID");
//			client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential)
//					.setApplicationName(APPLICATION_NAME).build();
//			Events events = client.events();
//			eventList = events.list("primary").setTimeMin(date1).setTimeMax(date2).execute();
//			message = eventList.getItems().toString();
//			str.append("My:" + eventList.getItems());
//		} catch (Exception e) {
//			logger.warn("Exception while handling OAuth2 callback (" + e.getMessage() + ")."
//					+ " Redirecting to google connection status page.");
//			message = "Exception while handling OAuth2 callback (" + e.getMessage() + ")."
//					+ " Redirecting to google connection status page.";
//		}
//
//		str.append("cal message:" + message);
//		return str.toString();
//	}
//
//	public Set<Event> getEvents() throws IOException {
//		return this.events;
//	}
//
//	public String authorize() throws Exception {
//		AuthorizationCodeRequestUrl authorizationUrl;
//		if (flow == null) {
//			Details web = new Details();
//			web.setClientId(clientId);
//			web.setClientSecret(clientSecret);
//			clientSecrets = new GoogleClientSecrets().setWeb(web);
//			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//			flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
//					Collections.singleton(CalendarScopes.CALENDAR)).build();
//		}
//		authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectURI);
//		System.out.println("cal authorizationUrl->" + authorizationUrl);
//		return authorizationUrl.build();
//	}
//}