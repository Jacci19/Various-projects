package JavaFX.Sim.ui.worldObjects.colony;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import JavaFX.Sim.business.logicalObjects.GroupMembership;
import JavaFX.Sim.business.worldObjects.Colony;
import JavaFX.Sim.ui.javaFX.JavaFxApplication;
import JavaFX.Sim.ui.rootButton.RootButton;

/**
 * Graphical representation of a {@link Colony} for JavaFX.
 * 
 * @author Steven Schwenke
 * 
 */
public class ColonyFX extends ImageView {

	/** the ressource spawner that is represented by this object */
	private Colony representedColony;

	public ColonyFX(final Colony representedColony) {
		super();

		this.representedColony = representedColony;

		final ColonyFX thisColonyFX = this;

		Image image = new Image(representedColony.getGroupMembership().equals(GroupMembership.PLAYER) ? "JavaFX/Sim/colonyPlayer.png" : "JavaFX/Sim/colonyFoe.png");

		setImage(image);
		setFitWidth(30);
		setLayoutX(representedColony.getPosition().getX());
		setLayoutY(representedColony.getPosition().getY());
		setPreserveRatio(true);
		setSmooth(true);
		setCache(true);

		this.addEventFilter(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						if (!representedColony.getGroupMembership().equals(
								GroupMembership.PLAYER)
								&& !RootButton.getInstance().isRootMode()) {
							return;
						}
						ColonyFXMouseOverPane mouseOverPane = new ColonyFXMouseOverPane(
								thisColonyFX);

						JavaFxApplication.getInstance().getRootGroup()
								.getChildren().add(mouseOverPane);

						mouseOverPane.appear();
					}
				});
	}

	public Colony getRepresentedColony() {
		return representedColony;
	}
}
