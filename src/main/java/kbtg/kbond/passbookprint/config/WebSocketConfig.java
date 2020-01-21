package kbtg.kbond.passbookprint.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;



@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Autowired
	private WebSocketProperties prop;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
    	
    	// topic
        config.enableSimpleBroker(prop.getTopic());
        // app prefix : /app
        config.setApplicationDestinationPrefixes(prop.getAppPrefix());
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	// endPoint : /ws
        registry.addEndpoint(prop.getEndpoint()).setAllowedOrigins(prop.getAllowedOrigins()) .withSockJS();
    }
}