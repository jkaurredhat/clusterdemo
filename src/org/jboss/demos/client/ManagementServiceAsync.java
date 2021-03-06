package org.jboss.demos.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.jboss.demos.shared.ClusterInfo;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ManagementServiceAsync {

    void getClusterInfo(String targetNodeIp, AsyncCallback<ClusterInfo> callback) throws IllegalArgumentException;

    void invokeOperation( String ip, String name, String[] parameters, AsyncCallback<Boolean> callback);
}
