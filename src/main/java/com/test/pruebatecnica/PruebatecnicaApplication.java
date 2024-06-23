package com.test.pruebatecnica;


import com.test.pruebatecnica.config.SpringFXMLLoader;
import com.test.pruebatecnica.config.StageManager;
import com.test.pruebatecnica.view.FxmlView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
 import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.stage.Stage;
 import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;

@SpringBootApplication
public class PruebatecnicaApplication extends Application {
	protected ConfigurableApplicationContext springContext;
	protected StageManager stageManager;
	@Autowired
	SpringFXMLLoader springFXMLLoader;

	public static void main(final String[] args) {
		Application.launch(args);
	}


	@Override
	public void init() throws Exception {
		springContext = springBootApplicationContext();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stageManager = springContext.getBean(StageManager.class, stage);
		displayInitialScene();
	}

	@Override
	public void stop() throws Exception {
		springContext.close();
	}


	protected void displayInitialScene() {
		stageManager.switchScene(FxmlView.LOGIN);
	}


	private ConfigurableApplicationContext springBootApplicationContext() {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(PruebatecnicaApplication.class);
		String[] args = getParameters().getRaw().stream().toArray(String[]::new);
		return builder.run(args);
	}
	@Bean
	@Lazy(value = true) //Stage only created after Spring context bootstap
	public StageManager stageManager(Stage stage) throws IOException {

		return new StageManager(springFXMLLoader, stage);
	}

}
