package com.skybeyondtech.gateway.filter;

import com.skybeyondtech.common.core.util.ServletUtils;
import lombok.Getter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class BlackListUrlFilter extends AbstractGatewayFilterFactory<BlackListUrlFilter.Config> {
    @Override
    public GatewayFilter apply(final Config config) {
        return (exchange, chain) -> {
            final String url = exchange.getRequest().getURI().getPath();
            if (config.matchBlacklist(url)) {
                return ServletUtils.webFluxResponseWriter(exchange.getResponse(), "请求地址不允许访问");
            }
            return chain.filter(exchange);
        };
    }

    public BlackListUrlFilter() {
        super(Config.class);
    }

    @Getter
    public static class Config {
        private List<String> blacklistUrl;

        private final List<Pattern> blacklistUrlPattern = new ArrayList<>();

        public boolean matchBlacklist(final String url) {
            return !blacklistUrlPattern.isEmpty() && blacklistUrlPattern.stream().anyMatch(p -> p.matcher(url).find());
        }

        public void setBlacklistUrl(final List<String> blacklistUrl) {
            this.blacklistUrl = blacklistUrl;
            this.blacklistUrlPattern.clear();
            this.blacklistUrl.forEach(url -> this.blacklistUrlPattern.add(Pattern.compile(url.replaceAll("\\*\\*", "(.*?)"), Pattern.CASE_INSENSITIVE)));
        }
    }

}
