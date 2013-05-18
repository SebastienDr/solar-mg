package scene;

import templates.Basic3DWindow;

/**
 * Created with IntelliJ IDEA.
 * User: sdrouard
 * Date: 17/05/13
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */
public class SolarMgWindow extends Basic3DWindow {

    public SolarMgWindow(String title) {
        super(title, 600, 480);
    }

    public static void main(String[] args) {
        new SolarMgWindow("Solar management");
    }

}
