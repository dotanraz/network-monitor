package com.app;

import com.app.environment.SystemProperties;
import com.app.environment.YAMLConfig;
import com.app.networkMonitorApp.NetworkMonitorExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@ComponentScan()
public class Main implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	@Autowired
	YAMLConfig yamlConfig;


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Main.class);
		app.run();
	}

	@Override
	public void run(String... args) throws Exception {
//		logger.info("using environment: " + yamlConfig.getEnvironment());
//		logger.info("name: " + yamlConfig.getName());
//		logger.info("postgres path: " + yamlConfig.getPostgresPath());
//		logger.info("postgres user: " + yamlConfig.getPostgresUser());
//		logger.info("postgres password: " + yamlConfig.getPostgresPassword());
		logger.info("network monitor - started\n\n");
		SystemProperties.getInstance().getOsType();
		Timer timer = new Timer("networkMonitor");
		timer.scheduleAtFixedRate(
				new NetworkMonitorExecutor(),
				TimeUnit.SECONDS.toMillis(1),
				TimeUnit.MINUTES.toMillis(10));
	}
}
