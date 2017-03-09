package yo.zaenk;

import io.undertow.UndertowOptions;
import org.eclipse.jetty.alpn.ALPN;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootUndertowHttp2Application {

	@Value("${app.http.port}")
	private int port;

	@Value("${app.http.host}")
	private String host;

	public static void main(String[] args) {
		ALPN.debug = true;
		SpringApplication.run(SpringBootUndertowHttp2Application.class, args);
	}

	@Bean
	UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
		UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
		factory.addBuilderCustomizers(
				builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true),
				builder -> builder.addHttpListener(port, host));
		return factory;
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
	}
}
