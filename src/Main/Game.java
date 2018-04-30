package Main;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
public class Game {
        public Game(){
            initComponents();
        }
        public void initComponents(){
            JFrame window = new JFrame("Turtloise");
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Point centerPoint = ge.getCenterPoint();
            System.out.println("wgs:"+window.getSize());
            System.out.println("width:"+screenSize.width);
            System.out.println("height:"+screenSize.height);
            System.out.println("cpx:"+centerPoint.x);
            System.out.println("cpy:"+centerPoint.x);
            int dx = screenSize.width / 2 - 512;
            int dy = screenSize.height / 2 - 285;    
            window.setBounds(dx,dy,0,0);
            //170
            //window.setLocationRelativeTo(null);
            window.setUndecorated(true);
            window.setContentPane(new GamePanel());
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.pack();
            window.setVisible(true);
        }
}
