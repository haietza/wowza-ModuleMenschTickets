package edu.appstate.lts;

import com.wowza.wms.application.*;
import com.wowza.wms.amf.*;
import com.wowza.wms.client.*;
import com.wowza.wms.module.*;
import com.wowza.wms.request.*;
import com.wowza.wms.stream.*;
import com.wowza.wms.rtp.model.*;
import com.wowza.wms.httpstreamer.model.*;
import com.wowza.wms.httpstreamer.cupertinostreaming.httpstreamer.*;
import com.wowza.wms.httpstreamer.smoothstreaming.httpstreamer.*;

import com.wowza.util.URLUtils;

public class ModuleTokenValidate extends ModuleBase {
	
	private TokenValidate tokenValidate;
	
	public ModuleTokenValidate() {
		super();
	}

	public void onAppStart(IApplicationInstance appInstance) {
		String fullname = appInstance.getApplication().getName() + "/" + appInstance.getName();
		getLogger().info("onAppStart: " + fullname);
	}

	public void onAppStop(IApplicationInstance appInstance) {
		String fullname = appInstance.getApplication().getName() + "/" + appInstance.getName();
		getLogger().info("onAppStop: " + fullname);
	}

	public void onConnect(IClient client, RequestFunction function, AMFDataList params) {
		getLogger().info("onConnect: " + client.getClientId());

		
		//tokenValidate = new TokenValidate(client);		
		//if (!tokenValidate.validate()) {
			//client.rejectConnection("Invalid token.");
		//}
		// else {
			// get video from Mensch store via token/hash
		// }
	}

	public void onConnectAccept(IClient client) {
		getLogger().info("onConnectAccept: " + client.getClientId());
	}

	public void onConnectReject(IClient client) {
		getLogger().info("onConnectReject: " + client.getClientId());
	}

	public void onDisconnect(IClient client) {
		getLogger().info("onDisconnect: " + client.getClientId());
	}

	public void onStreamCreate(IMediaStream stream) {
		getLogger().info("onStreamCreate: " + stream.getSrc());
		
		getLogger().info("Token: " + stream.getQueryStr());
		getLogger().info("Stream name: " + stream.getName());
		
		tokenValidate = new TokenValidate(stream.getClient());		
		if (!tokenValidate.validate()) {
			stream.close();
		}
	}

	public void onStreamDestroy(IMediaStream stream) {
		getLogger().info("onStreamDestroy: " + stream.getSrc());
	}

	public void onHTTPSessionCreate(IHTTPStreamerSession httpSession) {
		getLogger().info("onHTTPSessionCreate: " + httpSession.getSessionId());
	}

	public void onHTTPSessionDestroy(IHTTPStreamerSession httpSession) {
		getLogger().info("onHTTPSessionDestroy: " + httpSession.getSessionId());
	}

}
