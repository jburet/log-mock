package fr.xebia.techevent.flume.gen;

import akka.actor.UntypedActor;
import akka.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Client extends UntypedActor {

    private static final Logger APACHE_ACCESS_LOGGER = LoggerFactory.getLogger("ApacheAccLog");

    private final String ip;
    private final UserAgent userAgent;
    private final ApacheAccessLog apacheAccessLog;

    private ClientState lastState = ClientState.BEGIN;

    public Client(String ip, UserAgent userAgent, ApacheAccessLog apacheAccessLog) {
        this.ip = ip;
        this.userAgent = userAgent;
        this.apacheAccessLog = apacheAccessLog;
    }

    private void processSession() {
        switch (lastState) {
            case BEGIN:
                // login
                login();
                lastState = ClientState.LOGIN;
                waitAround(3);
                break;
            case LOGIN:
                // logout and Wait 1 second
                lastState = ClientState.LOGOUT;
                logout();
                waitAround(1);
                break;
            case LOGOUT:
                //
                break;
        }
    }

    private void waitAround(int timeToWait) {
        getContext().system().scheduler().scheduleOnce(Duration.create(timeToWait, TimeUnit.SECONDS), self(), "CONTINUE");
    }

    private void login() {
        APACHE_ACCESS_LOGGER.info(apacheAccessLog.login(ip, userAgent));
    }

    private void logout() {
        APACHE_ACCESS_LOGGER.info(apacheAccessLog.logout(ip, userAgent));
    }


    @Override
    public void onReceive(Object o) throws Exception {
        // Peut importe ce que l'on recoit on continue l'execution
        processSession();
    }
}
