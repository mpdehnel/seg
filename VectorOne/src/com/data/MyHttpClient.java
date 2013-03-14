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

import android.util.Log;
import com.google.android.maps.GeoPoint;

public class MyHttpClient {
	private String server;
	private Cache[] tmp;

	// private String TAG = "HTTPCLIENTUSER";

	public MyHttpClient(String server) {
		this.server = server;

	}

	public boolean isUser(final String username, String password)
			throws ClientProtocolException, IOException {

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(this.server + "login.php?username="
				+ username + "&password=" + password);
		HttpResponse response = client.execute(request);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		String line = "";
		while ((line = rd.readLine()) != null) {
			// Log.i(TAG, line);
			if (!line.equalsIgnoreCase("denied")) {
				parseUser(username, password, line);
				return true;
			}
		}

		return false;

	}

	private void parseUser(String username, String password, String line) {
		if (line == "no_user") {
			// create Dialog erro
		} else {
			User user = new User();

			try {
				// Log.i(TAG, line);
				user.setUsername(username);
				user.setTeam(parseTeam(getValueofTag("team", line)));
				user.setTotalcaches(Integer.valueOf(getValueofTag(
						"totalcaches", line)));
				user.setTotalpoints(Integer.valueOf(getValueofTag(
						"totalpoints", line)));
				user.setCurrentPoints(Integer.valueOf(getValueofTag(
						"currentpoints", line)));
				user.setId(Integer.valueOf(getValueofTag("id", line)));
				user.setHardcore(Boolean
						.valueOf(getValueofTag("hardcore", line)));
				// Log.i(TAG, user.toString());
				user.setPassword(password);
				DataClass.user = user;

			} catch (Exception e) {
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

		HttpClient client = new DefaultHttpClient();
		// HttpGet request = new HttpGet(this.server + "caches.php");
		HttpGet request = new HttpGet(this.server + "getAllCaches.php?");
		HttpResponse response = client.execute(request);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));
		// Log.i("HTTPCLIENTUSER", "Caches ------");
		String line = "";
		while ((line = rd.readLine()) != null) {
			// Log.i(TAG, "Caches ------" + line);
			return parseCache(line);
		}
		return null;

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
				String name = getValueofTag("name", element);
				name = name.replace("__", " ");
				tmp.setName(name);
				tmp.setGeopoint(new GeoPoint(Integer.parseInt(getValueofTag(
						"latitude", element)), Integer.parseInt(getValueofTag(
						"longitude", element))));
				tmp.setRating(Float.valueOf(getValueofTag("rating", element)));
				String discription = getValueofTag("description", element);
				discription = discription.replace("__", " ");
				tmp.setDescripton(discription);
				tmp.setfounded(Boolean.valueOf(getValueofTag("found", element)));
				tmp.setRated(Boolean.valueOf(getValueofTag("found", element)));
				tmp.setValue(Integer.valueOf(getValueofTag("value", element)));
				tmp.setTeamcolor(parseTeam(getValueofTag("team", element)));
				tmp.setID(Integer.valueOf(getValueofTag("id", element)));
				tmp.setComments(parseComments(getValueofTag("comments", element)));
				String macaddress = getValueofTag("macaddress", element);

				if (macaddress.equalsIgnoreCase("00:00:00:00:00:00")) {
					tmp.setMacAdd(" ");
				} else {
					tmp.setMacAdd(macaddress);
				}
				cachelist.add(tmp);

			}
		} catch (Exception e) {
			// Log.i(TAG, "Caches ------" + e.getMessage());
			e.printStackTrace();
		}
		return toCacheArry(cachelist);
	}

	private String parseTeam(String valueofTag) {
		int team = Integer.parseInt(valueofTag);
		String teamName = "";
		switch (team) {
		case 1:
			teamName = "Red Reefers";
			break;
		case 2:
			teamName = "Green Gulls";
			break;
		case 3:
			teamName = "Blue Bandits";
			break;
		case 4:
			teamName = "Purple Perils";
			break;
		default:
			teamName = "Unvisited";
		}

		return teamName;
	}

	private String parseComments(String valueofTag) {
		valueofTag = valueofTag.replace("@", "\n");
		valueofTag = valueofTag.replace(":", ":\n");
		valueofTag = valueofTag.replace("__", " ");

		return valueofTag;
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

	public void getportiondata() throws IllegalStateException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(this.server + "getTeamSizes.php");
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		String line = "";
		while ((line = rd.readLine()) != null) {
			// Log.i(TAG, line);
			String numbers[] = line.split(",");
			DataClass.blueportion = Integer.parseInt(numbers[1]);
			DataClass.redportion = Integer.parseInt(numbers[0]);
			DataClass.greenportion = Integer.parseInt(numbers[2]);
			DataClass.purpleportion = Integer.parseInt(numbers[3]);
		}

	}

	// /////////////////////////////////////////////////////////////////
	// //////////////////////////Pushing////////////////////////////////
	// /////////////////////////////////////////////////////////////////
	public int addCacheToDatabase(String Cachename, String MyDescription,
			String Wifiaddress) throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		String url = this.server + "createCache.php?username="
				+ DataClass.user.getUsername() + "&cachename=" + Cachename
				+ "&userlat=" + DataClass.mylat + "&userlon=" + DataClass.mylat
				+ "&description=" + MyDescription + "&macaddress="
				+ Wifiaddress;
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		String line = "";
		if ((line = rd.readLine()) != null) {
			// Log.i("MAIN", line);
			try {
				return Integer.parseInt(line);
			} catch (NumberFormatException e) {
				return -1;
			}
		}
		return -1;
	}

	public String addNewUser(String username, String password,
			String executerequest) {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(this.server + executerequest);
		HttpResponse response;
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String line = rd.readLine();
			// Log.i("HTTPCLIENTUSER", line);
			return parsenewUser(username, password, line);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Cannot add user: Offline";

	}

	private String parsenewUser(String username, String password, String line) {

		if (line.substring(0, 6).equalsIgnoreCase("denied")) {
			return line.substring(7);
		} else {
			parseUser(username, password, line);
			return "Sucsessfully Created";
		}
	}

	public String pushrating(int cachid, float rating)
			throws ClientProtocolException, IOException {
		int rate = (int) rating * 10;

		HttpClient client = new DefaultHttpClient();

		HttpGet request = new HttpGet(this.server + "rateCache.php?cacheID="
				+ cachid + "&rating=" + rate);
		HttpResponse response;
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = rd.readLine();
			return line;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Cannot rate: Offline";

	}

	public String updateCache(String username, int cachid)
			throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(this.server
				+ "visitCacheUpdate.php?cacheID=" + cachid + "&username="
				+ username);
		HttpResponse response;
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = rd.readLine();
			return line;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Cannot update Cache: Offline ";

	}

	public String getVisitedCache(String username)
			throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(this.server
				+ "myVisitedCaches.php?username=" + username);
		HttpResponse response;
		try {
			for (int i = 0; i < DataClass.caches.size(); i++) {
				DataClass.caches.get(i).getCach().setfounded(false);
			}
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = rd.readLine();

			// Log.i("MAIN", line);
			// Log.i(TAG,line);
			String[] visited = line.split(",");
			for (int i = 0; i < DataClass.caches.size(); i++) {
				if (cointains(visited, DataClass.caches.get(i).getCach()
						.get_id())) {
					DataClass.caches.get(i).getCach().setfounded(true);
					DataClass.caches.get(i).getCach().setRated(true);
				} else {
					DataClass.caches.get(i).getCach().setRated(false);
					DataClass.caches.get(i).getCach().setfounded(false);
				}
			}

			return line;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Cannot get visited caches: Offline ";

	}

	private boolean cointains(String[] visited, int get_id) {

		for (int i = 0; i < visited.length; i++) {
			// Log.i(TAG,visited[i]+"--"+get_id);
			if (Integer.parseInt(visited[i]) == get_id) {
				return true;
			}
		}
		return false;
	}

	public String pushTWITTER(String username, String comment)
			throws ClientProtocolException, IOException {
		String comment2 = comment.replace(" ", "__");
		HttpClient client = new DefaultHttpClient();

		HttpGet request = new HttpGet(this.server + "twitter.php?username="
				+ username + "&message=" + comment2);
		HttpResponse response;
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = rd.readLine();
			return line;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Cannot Tweet: Offline";

	}

	public String pushComment(String username, int Cacheid, String comment)
			throws ClientProtocolException, IOException {
		String comment2 = comment.replace(" ", "__");
		HttpClient client = new DefaultHttpClient();

		HttpGet request = new HttpGet(this.server + "cacheComment.php?message="
				+ username + ":" + comment2 + "@" + "&cacheID=" + Cacheid);
		HttpResponse response;
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = rd.readLine();
			return line;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Cannot pushCommet: Offline";

	}

	public String pointsupdate(String username, int pointschange)
			throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(this.server
				+ "updatePoints.php?username=" + username + "&points="
				+ pointschange);
		HttpResponse response;
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = rd.readLine();
			return line;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Cannot update Points: Offline";

	}

	public String usersettings(String username, String unit, String distace,
			String visited, String team, String sortType)
			throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(this.server
				+ "usersettings.php?username=" + username + "&settings="
				+ distace + "_" + unit + "_" + visited + "_" + team + "_"
				+ sortType);
		HttpResponse response;
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = rd.readLine();
			return line;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Cannot update user settings: Offline";

	}

	public String getusersettings(String username)
			throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(this.server
				+ "getUsersettings.php?username=" + username);
		HttpResponse response;
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = rd.readLine();
			parsesettings(line);
			return line;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Cannot get user settings: Offline";

	}

	private void parsesettings(String line) {
		String[] settings = line.split("_");
		// Log.i("MAIN", line);
		// Log.i("MAIN", Arrays.toString(settings));
		if (settings.length == 5) {
			DataClass.user.setSettings_maxdistance(settings[0]);
			DataClass.user.setSettings_unit(settings[1]);
			DataClass.user.setSettings_visited(settings[2]);
			DataClass.user.setSettings_team(settings[3]);
			DataClass.SortType = settings[4];
		}
		// Log.i("MAIN", DataClass.user.getSettings_team());
	}

	public String setuserlog(String username, String log)
			throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		log = log.replace(" ", "_");
		log = log.replace("\n", "__");
		HttpGet request = new HttpGet(this.server + "userLogbook.php?username="
				+ username + "&logbook=" + log);
		HttpResponse response;
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = rd.readLine();
			return line;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Cannot send user log: Offline";

	}

	public String getuserlog(String username) throws ClientProtocolException,
			IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(this.server
				+ "getUserLogbook.php?username=" + username);
		HttpResponse response;
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = rd.readLine();
			parselog(line);
			return line;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Cannot get User Log: Offline";

	}

	private void parselog(String line) {
		// Log.i("MAIN", line);
		line = line.replace("__", "\n");
		line = line.replace("_", " ");
		DataClass.addoldtolog(line);
	}

	public String changepassword(String username, String password)
			throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(this.server
				+ "editPassword.php?username=" + username + "&password="
				+ password);
		HttpResponse response;
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = rd.readLine();
			return line;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Cannot change password: Offline";
	}

	public String sendReport(int get_id) throws ClientProtocolException,
			IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(this.server + "reportCache.php?cacheID="
				+ get_id);
		HttpResponse response;
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = rd.readLine();
			return line;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Cannot send a report: Offline";
	}

}