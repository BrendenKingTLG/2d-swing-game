import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventHandler implements KeyListener {
    public boolean goingUp;
    public boolean goingDown;
    public boolean goingLeft;
    public boolean goingRight;

    @Override
    public void keyTyped(KeyEvent e) { // not needed
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();  //keycode is linked to each key

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            goingUp = true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            goingDown = true;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            goingLeft = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            goingRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();  //keycode is linked to each key

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            goingUp = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            goingDown = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            goingLeft = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            goingRight = false;
        }
    }
}
