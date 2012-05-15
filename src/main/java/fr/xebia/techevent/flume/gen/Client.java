package fr.xebia.techevent.flume.gen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {

    private static final Logger APACHE_ACCESS_LOGGER = LoggerFactory.getLogger("ApacheAccLog");

    private final String ip;
    private final UserAgent userAgent;
    private final ApacheAccessLog apacheAccessLog;

    public Client(String ip, UserAgent userAgent, ApacheAccessLog apacheAccessLog) {
        this.ip = ip;
        this.userAgent = userAgent;
        this.apacheAccessLog = apacheAccessLog;
    }

    private void processSession() {
        // login
        APACHE_ACCESS_LOGGER.info(login());
        // logout

    }

    private String login() {
        return apacheAccessLog.login(ip, userAgent);
    }


}
