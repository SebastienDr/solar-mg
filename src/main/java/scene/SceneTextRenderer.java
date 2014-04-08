package scene;

import com.jogamp.opengl.util.awt.TextRenderer;
import game.Game;
import templates.Basic3DWindow;

import java.awt.*;

public class SceneTextRenderer extends TextRenderer {

    public static final int HORIZONTAL_FIX = 10;
    public static final int START_VERTICAL_FIX = 10;
    public static final int UPDATE_VERTICAL_GAP = 20;

    public Game game;
    private int actualVerticalFix;

    public SceneTextRenderer(Font font, Game game) {
        super(font);
        this.game = game;
        this.actualVerticalFix = START_VERTICAL_FIX;
    }

    public void render() {
        this.beginRendering(Basic3DWindow.DEFAULT_WIDTH, Basic3DWindow.DEFAULT_HEIGHT);
        this.setColor(0.0f, 1.0f, 0.0f, 1.0f);
        displayWallet();
        //time();
        if (!game.getTransports().isEmpty()) {
            this.draw("Capacity : " + game.getTransports().get(0).getResources() + " %", HORIZONTAL_FIX, 50);
            this.draw("Position : " + game.getTransports().get(0).getPosition(), HORIZONTAL_FIX, 70);
            this.draw("Speed : " + game.getTransports().get(0).getSpeed(), HORIZONTAL_FIX, 90);
        }
        this.endRendering();
    }

    private void displayWallet() {
        this.draw("€ " + game.getPlayer().showWallet(), HORIZONTAL_FIX, START_VERTICAL_FIX);
    }

}
