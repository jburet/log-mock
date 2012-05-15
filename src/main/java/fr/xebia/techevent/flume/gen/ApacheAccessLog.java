package fr.xebia.techevent.flume.gen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Simulate a client using tomcat application
 * Each method corresponding to a page
 */
public class ApacheAccessLog {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApacheAccessLog.class);

    private DateFormat df = new SimpleDateFormat("[dd/MMM/yyyy:hh:mm:ss ZZ]");

    private String loginBaseUrl = "\"GET /login HTTP/1.1\" 200 100 ";
    private String logoutBaseUrl = "\"GET /logout HTTP/1.1\" 200 100 ";
    private String searchBaseUrl = "\"GET /search HTTP/1.1\" 200 100 ";
    private String viewBaseUrl = "\"GET /view HTTP/1.1\" 200 100 ";
    private String buyBaseUrl = "\"GET /buy HTTP/1.1\" 200 100 ";
    private String paySucessUrl = "\"GET /payment HTTP/1.1\" 200 100 ";
    private String payErrorUrl = "\"GET /paymentError HTTP/1.1\" 200 100 ";

    public String login(String ip, UserAgent userAgent) {
        return baseLog(ip, userAgent, loginBaseUrl);
    }

    private String baseLog(String ip, UserAgent userAgent, String url) {
        // TODO Do log 78.236.167.225 - - [09/May/2011:12:56:19 +0200] "GET /x-skill HTTP/1.1" 404 505 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_6) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/11.0.696.57 Safari/534.24"

        StringBuilder sb = new StringBuilder();
        sb.append(ip);
        sb.append(" - - ");
        sb.append(df.format(new Date()));
        sb.append(url);
        sb.append("\"-\" ");
        sb.append("\"");
        sb.append(userAgent.userAgent);
        sb.append("\"");
        return sb.toString();
    }

    public String logout(String ip, UserAgent userAgent) {
        return baseLog(ip, userAgent, logoutBaseUrl);
    }

    public String view(String ip, UserAgent userAgent) {
        return baseLog(ip, userAgent, viewBaseUrl);
    }

    public String search(String ip, UserAgent userAgent) {
        return baseLog(ip, userAgent, searchBaseUrl);
    }

    public String buy(String ip, UserAgent userAgent) {
        return baseLog(ip, userAgent, buyBaseUrl);
    }

    public String paySuccess(String ip, UserAgent userAgent) {
        return baseLog(ip, userAgent, paySucessUrl);
    }

    public String payError(String ip, UserAgent userAgent) {
        return baseLog(ip, userAgent, payErrorUrl);
    }
}
