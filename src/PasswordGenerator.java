import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PasswordGenerator extends JFrame {

    private static final long serialVersionUID = 1L;
    JPanel jp = new JPanel();
    JLabel jl = new JLabel();
    JTextField jt = new JTextField("Type important numbers, then press \"Enter\" ", 25);
    JButton jb = new JButton("Done");
    public static boolean isDone = false;
    public static int labelCount = 0;

    public PasswordGenerator() {
        setTitle("Password Generator");
        setVisible(true);
        setSize(375, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        jp.add(jt);
        jt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = jt.getText();
                if (input.length() > 0 && !(input.isEmpty()) && input != null) {
                    jl.setText(input);
                    for (int i = 0; i < input.length(); i++) {
                        char c = input.charAt(i);
                        if (c < '0' || c > '9') {
                            jl.setText("Please input a valid number");
                        }
                    }
                } else {
                    jl.setText("Please input a valid number");
                }

            }
        });
        jp.add(jb);
        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isDone = true;
            }
        });
        jp.add(jl);
        add(jp);
    }

    public static void main(String[] args) {
        new PasswordGenerator();
        while (isDone == false) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException i) {
            }
        }
    }

    public static String passwordGenerator() {
        return "-1";
    }
}
