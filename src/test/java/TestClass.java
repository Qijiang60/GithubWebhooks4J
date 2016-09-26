import com.arsenarsen.githubwebhooks4j.GithubWebhooks4J;
import com.arsenarsen.githubwebhooks4j.WebhooksBuilder;
import com.arsenarsen.githubwebhooks4j.events.EventListener;
import com.arsenarsen.githubwebhooks4j.events.PushEvent;

import java.io.IOException;

/**
 * <br>
 * Created by Arsen on 13.9.16..
 */
public class TestClass {
    static GithubWebhooks4J hooks;

    static {
        try {
            hooks = new WebhooksBuilder().forRequest("/test").onPort(8080).withSuccesMessage("OK %COUNT").addListener(new Listener()).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {

    }

    static class Listener implements EventListener<PushEvent> {
        @Override
        public void handle(PushEvent event) {
            System.out.println("ayy");
        }
    }
}
