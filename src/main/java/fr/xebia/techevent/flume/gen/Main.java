package fr.xebia.techevent.flume.gen;

import akka.actor.*;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        m.start(1, 1);
    }

    private void start(int nbClient, int nbIteration) {
        ActorSystem system = ActorSystem.create("LogSystem");

        // Create a client
        ActorRef client1 = system.actorOf(new Props(new UntypedActorFactory() {
            @Override
            public Actor create() {
                return new Client("80.1.2.3", UserAgent.FIREFOX_MAC_3, new ApacheAccessLog());
            }
        }));
        // start
        client1.tell("");
    }
}
