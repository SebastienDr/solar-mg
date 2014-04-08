package scene;

import templates.Basic3DWindow;

public class SolarMgWindow extends Basic3DWindow {

    public SolarMgWindow(String title) {
        super(title, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public static void main(String[] args) {
        new SolarMgWindow("Solar management");
    }

}
