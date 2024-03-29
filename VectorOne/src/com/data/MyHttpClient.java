package com.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.CharacterData;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.android.maps.GeoPoint;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MyHttpClient {
	private String server;
	private Cache[] tmp;

	public MyHttpClient(String server) {
		this.server = server;
		/*try {
			System.out.println(isUser("robert", "123"));
			Cache[] test = getCachesfromDatabase("robert");
			System.out.println(test[0].getName());
			System.out.println(test[1].getName());
			System.out.println(test[2].getName());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public boolean isUser(final String username, String password)
			throws ClientProtocolException, IOException {
		
		  HttpClient client = new DefaultHttpClient(); // TODO request with username and password; 
		  HttpGet request = new HttpGet(this.server +
		  "user"); HttpResponse response = client.execute(request);
		  
		  BufferedReader rd = new BufferedReader(new InputStreamReader(response
		  .getEntity().getContent()));
		  
		  String line = ""; while ((line = rd.readLine()) != null) {
		  parseUser(username, line); }
		 

		return true;

	}

	private void parseUser(String username, String line) {
		if (line == "no_user") {
			// create Dialog erro
		} else {
			User user = new User();

			try {
				user.setUsername(username);
				user.setNickname(getValueofTag("nickname", line));
				user.setTeam(getValueofTag("team", line));
				user.setTotalcaches(Integer.valueOf(getValueofTag(
						"totalcaches", line)));
				user.setTotalpoints(Integer.valueOf(getValueofTag(
						"totalpoints", line)));
				user.setImage(123);
				System.out.println(user.toString());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String getValueofTag(String tag, String line)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(line));

		Document doc = builder.parse(is);
		NodeList nodes = doc.getElementsByTagName(tag);
		Element tagelement = (Element) nodes.item(0);
		return getCharacterDataFromElement(tagelement);
	}

	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "?";
	}

	public final Cache[] getCachesfromDatabase(String username)
			throws ClientProtocolException, IOException {
		
		  HttpClient client = new DefaultHttpClient(); // TODO request with  username and password; 
		  HttpGet request = new HttpGet(this.server +
		  "caches"); HttpResponse response = client.execute(request);
		  
		  BufferedReader rd = new BufferedReader(new InputStreamReader(response
		  .getEntity().getContent()));
		  
		  String line = ""; while ((line = rd.readLine()) != null) { return
		  parseCache(line); } return null;
		 


	}

	private Cache[] parseCache(String line) {
		LinkedList<Cache> cachelist = new LinkedList<Cache>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(line));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("cache");
			Cache tmp;
			for (int i = 0; i < nodes.getLength(); i++) {
				tmp = new Cache();
				Element element = (Element) nodes.item(i);
				tmp.setName(getValueofTag("name", element));
				tmp.setGeopoint(new GeoPoint(Integer.parseInt(getValueofTag(
						"longitude", element)), Integer.parseInt(getValueofTag(
						"latitude", element))));
				tmp.setDescripton(getValueofTag("description", element));
				tmp.setfounded(Boolean.valueOf(getValueofTag("found", element)));
				cachelist.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toCacheArry(cachelist);
	}

	private Cache[] toCacheArry(LinkedList<Cache> cachelist) {
		Cache[] caches = new Cache[cachelist.size()];
		for (int i = 0; i < cachelist.size(); i++) {
			caches[i] = cachelist.get(i);
		}

		return caches;
	}

	private String getValueofTag(String tag, Element element) {
		NodeList name = element.getElementsByTagName(tag);
		Element tagvalue = (Element) name.item(0);
		return getCharacterDataFromElement(tagvalue);

	}
	/*
	 * public static void main(String[] args) { new
	 * MyHttpClient("http://www.netroware.co.uk/test/"); }
	 */
}