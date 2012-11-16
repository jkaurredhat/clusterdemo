package org.jboss.demos.shared;

import java.io.Serializable;

/**
 * @author <a href="mailto:yyang@redhat.com">Yong Yang</a>
 * @create 11/15/12 8:54 AM
 */
public class ClusterNodeInfo implements Serializable{
    private String ip;
    private String port;

    public ClusterNodeInfo(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    @Override
    public String toString() {
        return ip + ":" +port;
    }
}
