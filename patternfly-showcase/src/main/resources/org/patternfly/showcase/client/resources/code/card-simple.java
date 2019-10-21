import static org.patternfly.client.components.Card.body;
import static org.patternfly.client.components.Card.footer;
import static org.patternfly.client.components.Card.header;
import static org.patternfly.client.components.Components.card;

Card card = card()
        .header(header().textContent("Header"))
        .body(body().textContent("Body"))
        .footer(footer().textContent("Footer"));