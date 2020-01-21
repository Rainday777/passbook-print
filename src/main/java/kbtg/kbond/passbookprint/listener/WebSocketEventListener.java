package kbtg.kbond.passbookprint.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {
	private static final Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);
	
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
	
	@EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		log.info("Received a new web socket connection");
    }
	
	@EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		log.info("web socket disconnected");
        messagingTemplate.convertAndSend("/topic/disconnected","disconnected");
    }

}
