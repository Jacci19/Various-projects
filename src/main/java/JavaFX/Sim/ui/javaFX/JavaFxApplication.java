package JavaFX.Sim.ui.javaFX;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import JavaFX.Sim.business.UserInterface;
import JavaFX.Sim.business.logicalObjects.GameState;
import JavaFX.Sim.business.worldObjects.Colony;
import JavaFX.Sim.business.worldObjects.Resource;
import JavaFX.Sim.business.worldObjects.ResourceSpawner;
import JavaFX.Sim.business.worldObjects.World;
import JavaFX.Sim.business.worldObjects.WorldObject;
import JavaFX.Sim.ui.worldObjects.colony.ColonyFX;
import JavaFX.Sim.ui.worldObjects.resource.ResourceFX;
import JavaFX.Sim.ui.worldObjects.resourceSpawner.ResourceSpawnerFX;

/**
 * User Interface with JavaFX. This class starts a JavaFX application and
 * instantiates a {@link World}.
 * 
 * @author Steven Schwenke
 * 
 */
public class JavaFxApplication extends Application implements UserInterface {

	/** World that is represented by this application */
	private static World world;

	private Group rootGroup;

	private static JavaFxApplication instance;

	private Label messageLabel;

	private TimerTask messageLabelTimerTask;

	private Scene scene;

	public static JavaFxApplication getInstance() {
		if (instance == null) {
			throw new RuntimeException(
					"Before getting an instance of the JavaFX application, execute the main-method in this class first. It launches the FX application.");
		}
		return instance;
	}

	private void init(Stage primaryStage) {
		rootGroup = new Group();

		world = World.getInstance();
		world.init(this, 1024d, 768d, true);

		addMessageLabel();

		scene = new Scene(rootGroup, world.getWidth(), world.getHeight());
		scene.setFill(Color.DIMGRAY);
		primaryStage.setScene(scene);
	}

	/**
	 * Adds the message Label that can be used for notifications to the user.
	 */
	private void addMessageLabel() {
		messageLabel = new Label();
		messageLabel.setAlignment(Pos.CENTER);
		messageLabel.setLayoutX(world.getWidth() / 2);
		messageLabel.setLayoutY(10);

		synchronized (rootGroup) {
			rootGroup.getChildren().add(messageLabel);
		}

		postToMessageLabel("Initialized");
	}

	/**
	 * Posts a message to the message label at the top of the screen. This
	 * message will disappear in a while if no new message is shown.
	 * 
	 * @param newMessage
	 *            message to be shown
	 */
	public void postToMessageLabel(String newMessage) {
		messageLabel.setText(newMessage);

		if (messageLabelTimerTask != null)
			messageLabelTimerTask.cancel();

		messageLabelTimerTask = new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						messageLabel.setText("");
					}
				});
			}
		};
		new Timer().schedule(messageLabelTimerTask, 5000, 1000); // TODO What is
																	// the third
																	// parameter?

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		instance = this;

		init(primaryStage);

		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void notifyCreation(final WorldObject newWorldObject) {

		// All new objects are created on the JavaFX Application Thread, even if
		// the call to this method came from another thread, such as a timing
		// thread started by the business logic.
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				synchronized (rootGroup) {
					if (newWorldObject instanceof ResourceSpawner) {
						ResourceSpawnerFX newSpawnerFX = new ResourceSpawnerFX(
								(ResourceSpawner) newWorldObject);
						rootGroup.getChildren().add(newSpawnerFX);
					} else if (newWorldObject instanceof Resource) {
						rootGroup.getChildren().add(
								new ResourceFX((Resource) newWorldObject));
					} else if (newWorldObject instanceof Colony) {
						rootGroup.getChildren().add(
								new ColonyFX((Colony) newWorldObject));
					}
				}
			}
		});
	}

	public Group getRootGroup() {
		return rootGroup;
	}

	// TODO this should be refactored into a separate AnimationFX-class
	@Override
	public void notifyHarvest(final Colony harvester, final Resource harvest) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				final Line line = new Line(harvester.getPosition().getX(),
						harvester.getPosition().getY(), harvest.getPosition()
								.getX(), harvest.getPosition().getY());

				line.setFill(null);

				line.setStroke(new Color(255 / 255, (double) (110d / 255d),
						(double) (3d / 255d), 1));

				line.setStrokeWidth(2);

				synchronized (rootGroup) {
					rootGroup.getChildren().add(line);

					FadeTransition ft = new FadeTransition(Duration
							.millis(1300), line);
					ft.setFromValue(1.0);
					ft.setToValue(0);
					ft.play();

					// remove the line after the animation
					ft.setOnFinished(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							rootGroup.getChildren().remove(line);
						}
					});
				}
			};
		});
	}

	// TODO Das so weiterprogrammieren, sp�ter vlt bei notifyCreation eine Map
	// anlegen, welche von BO auf FX mappt, damit das hier nicht mehr so
	// h�sslich ist
	@Override
	public synchronized void notifyDisappearance(WorldObject object) {

		synchronized (rootGroup) {

			for (final Node n : rootGroup.getChildren()) {
				if (n instanceof ResourceFX) {

					final ResourceFX s = (ResourceFX) n;
					if (s.getRepresentedResource().equals(object)) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								rootGroup.getChildren().remove(s);
							}
						});
						return;
					}
				} else if ((n instanceof ColonyFX)
						&& (object instanceof Colony)) {

					final ColonyFX c = (ColonyFX) n;
					if (c.getRepresentedColony().equals(object)) {
						c.setVisible(false);
						rootGroup.getChildren().remove(c);
						return;
					}
				}
			}
		}
	}

	public Scene getScene() {
		return scene;
	}

	@Override
	public void notifyChangeInGameState(GameState newState) {
		if (newState.equals(GameState.PLAYER_WON)) {
			postToMessageLabel("You won!");
		} else {
			postToMessageLabel("unknown GameState ... what's going on here?");
		}

	}

	@Override
	public void notifyAttack(final Colony attacker, final Colony attacked,
			final long strength) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				final Line line = new Line(attacker.getPosition().getX(),
						attacker.getPosition().getY(), attacked.getPosition()
								.getX(), attacked.getPosition().getY());

				line.setFill(null);

				line.setStroke(Color.RED);

				line.setStrokeWidth(2);

				synchronized (rootGroup) {
					rootGroup.getChildren().add(line);

					FadeTransition ft = new FadeTransition(Duration
							.millis(1300), line);
					ft.setFromValue(1.0);
					ft.setToValue(0);
					ft.play();

					// remove the line after the animation
					ft.setOnFinished(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							rootGroup.getChildren().remove(line);
						}
					});
				}

				final Label labelStrength = new Label("-" + strength);
				labelStrength.setTextFill(Color.RED);
				labelStrength.setLayoutX(attacked.getPosition().getX());
				labelStrength.setLayoutY(attacked.getPosition().getY() - 20);
				synchronized (rootGroup) {
					rootGroup.getChildren().add(labelStrength);

					final TimerTask messageLabelStrengthTimerTask = new TimerTask() {
						@Override
						public void run() {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									rootGroup.getChildren().remove(
											labelStrength);
								}
							});
						}
					};
					new Timer().schedule(messageLabelStrengthTimerTask, 2500);
				}
			};
		});
	}
}
