package com.test.pruebatecnica;

import com.test.pruebatecnica.config.StageManager;
import com.test.pruebatecnica.repository.UsuarioRepository;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
 import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.animation.KeyValue;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;

import org.slf4j.Logger;
 import java.awt.*;
import javafx.stage.Stage;
import javafx.scene.control.Control;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Stack;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
public class PruebatecnicaApplication extends Application {

	private static final Logger log = getLogger(StageManager.class);
	public static Parent root;
	public static ConfigurableApplicationContext applicationContext;

	public static Scene scene;
	public static Stage stage;
	public static Stack<URL> bypassSceneStack;

	public static void main(String[] args) {
		System.out.println("Application init");
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		applicationContext = SpringApplication.run(PruebatecnicaApplication.class);
		try{
			Parent loader	  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login.fxml")));
			Scene scene = new Scene(loader);
			stage.setScene(scene);
			stage.show();
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	public static void transitionScene(Event e, String fileName) throws IOException {
		//derive the control that fired the transition
		//Control control = (Control)e.getSource();
		transitionScene(e, fileName); // pass to the next overload
	}

	public static void transitionScene(String fileName) throws IOException {
		URL resourceName = PruebatecnicaApplication.class.getResource(fileName);
		transitionScene(resourceName);
	}

	public static void transitionScene(Control control, String fileName) throws IOException {
		log.debug("Sender: " + control.getId());
		URL resourceName = PruebatecnicaApplication.class.getResource(fileName);
		transitionScene(resourceName); // pass to the main function
	}

	/**
	 * Generic Transition Function to simplify the controllers.
	 * This is for transitions that aren't fired by events.
	 * @param resourceName FXML resource to transition to
	 * @throws IOException
	 */
	public static void transitionScene(URL resourceName) throws IOException {
		log.debug("Transitioning to: " + resourceName.toString());
		Parent parent = FXMLLoader.load(resourceName);

		// Loading FXML will trigger initialize on the associated controller.
		// The controller may redirect the UI to a different FXML instead,
		// so we now check to see if our bypass stack is empty or not.
		if (!bypassSceneStack.isEmpty()) {
			log.debug("Bypass scene set. Loading it instead: " + bypassSceneStack.peek());
			parent = FXMLLoader.load(bypassSceneStack.peek());
			bypassSceneStack.pop(); // pop it if we loaded it

			// we might've loaded another redirect in that child FXML that we just loaded now,
			// so peek again to capture that too.
			// note: this won't cater for more that 2 levels of redirects
			if (!bypassSceneStack.isEmpty()) {
				log.debug("Another bypass scene set. Loading it instead: " + bypassSceneStack.peek());
				parent = FXMLLoader.load(bypassSceneStack.peek());
				bypassSceneStack.pop();
			}
		}
		root = parent;
		scene = new Scene(root);
		log.debug("Configuring Scene");
		stage.setScene(scene);
		log.debug("Rendering Scene");
		stage.show();
	}

	private static void fadeIn(Parent root) {
		log.debug("Fading In");
		DoubleProperty opacity = root.opacityProperty();
		Timeline fadeIn = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
				new KeyFrame(new Duration(1000), new KeyValue(opacity, 1.0))
		);
		fadeIn.play();
	}
}
