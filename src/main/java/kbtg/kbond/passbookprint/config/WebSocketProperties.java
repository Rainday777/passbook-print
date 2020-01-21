package kbtg.kbond.passbookprint.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix="app.websocket")
public class WebSocketProperties {
    /**
     * Prefix used for WebSocket destination mappings
     */
    private String appPrefix;
    /**
     * Prefix used by topics
     */
    private String topic;
    /**
     * Endpoint that can be used to connect to
     */
    private String endpoint;
    /**
     * Allowed origins
     */
    private String allowedOrigins;
}