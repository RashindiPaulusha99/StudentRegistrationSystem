package controller;

import javafx.animation.ScaleTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ProgrammeListFormController {
    
    public AnchorPane pnProgramme1;
    public AnchorPane pnProgramme2;
    public AnchorPane pnProgramme3;
    public AnchorPane pnProgramme4;
    public AnchorPane pnProgramme5;

    public void mouseEnterAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof AnchorPane) {
            AnchorPane pane = (AnchorPane) mouseEvent.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), pane);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.GOLD);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            pane.setEffect(glow);
        }
    }

    public void mouseExitAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof AnchorPane) {
            AnchorPane pane = (AnchorPane) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), pane);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            pane.setEffect(null);
        }
    }
}
