import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
    JCheckBox symbol, lower, upper, ambiguous;

	public static boolean isDone = false;
    public static String numString = "";

    public static boolean isSymbol = false;
    public static boolean isLower = false;
    public static boolean isUpper = false;
    public static boolean isAmbiguous = false;

    public PasswordGenerator() {
        setTitle("Password Generator");
        setVisible(true);
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        symbol = new JCheckBox("Any Symbols? (e.g. @#$% )");
        lower = new JCheckBox("Any Lowercase Letters?");
        upper = new JCheckBox("Any Uppercase Letters?");
        ambiguous = new JCheckBox("Any Ambiguous Characters? (e.g. { } [ ] ( ) / \\ ' \"\" ` ~ , ; : . < > )");

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
                            break;
                        }
                    }
                    numString += input + " ";
                } else {
                    jl.setText("Please input a valid number");
                }

            }
        });

        jp.add(jl);

        symbol.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (symbol.isSelected()) {
                    isSymbol = true;
                } else {
                    isSymbol = false;
                }
            }
        });

        lower.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (lower.isSelected()) {
                    isLower = true;
                } else {
                    isLower = false;
                }
            }
        });

        upper.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (upper.isSelected()) {
                    isUpper = true;
                } else {
                    isUpper = false;
                }
            }
        });

        ambiguous.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (ambiguous.isSelected()) {
                    isAmbiguous = true;
                } else {
                    isAmbiguous = false;
                }
            }
        });

        jp.add(symbol);
        jp.add(lower);
        jp.add(upper);
        jp.add(ambiguous);

        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isDone = true;
            }
        });

        jp.add(jb);

        add(jp);
    }

    public static void main(String[] args) {
        new PasswordGenerator();
        while (isDone == false) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException i) {}
        }
        System.out.println(numString);
    }

    public static String passwordGenerator() {
        return "-1";
    }
}
