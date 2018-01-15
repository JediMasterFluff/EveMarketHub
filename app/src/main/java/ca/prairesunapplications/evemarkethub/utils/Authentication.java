package ca.prairesunapplications.evemarkethub.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fluffy on 29/12/17.
 */

public class Authentication {

	private static final String auth_path = "https://login.eveonline.com/oauth/authorize";

	private static final String response_type = "code";
	private static final String redirect_url = "eveauth-evemerkethub-app://auth/";
	private static final String client_id = "46344bdb1cf74f54a17582ec764b8c6c";
	private static final String scope = "publicData characterNavigationWrite characterWalletRead characterMarketOrdersRead esi-wallet.read_character_wallet.v1 esi-search.search_structures.v1 esi-markets.structure_markets.v1 esi-location.read_online.v1";
	private static final String state = "unique_eve_state132";

	private String final_path;

	public Authentication() {
		final_path = auth_path + "/?response_type=" + response_type + "&redirect_uri=" + redirect_url + "&client_id=" + client_id + "&scope=" + scope + "&state=" + state;

	}

	public boolean auhenticate() {
		OutputStream out;
		try {
			URL url = new URL(final_path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			out = new BufferedOutputStream(connection.getOutputStream());

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
			//writer.write(getClass().getResource("/auth/"));

		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void verifyAuthenicationCode() {

	}
}
