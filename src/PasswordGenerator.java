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
    JPanel jp = new JPanel();
    JLabel jl = new JLabel();
    JTextField jt = new JTextField("Type in the number of characters in the password, then press \"Enter\" ", 35);
    JButton jb = new JButton("Done");
    JCheckBox symbol = new JCheckBox("Any Symbols? (e.g. !@#$%^&*_-+=)");
    JCheckBox lower = new JCheckBox("Any Lowercase Letters?");
    JCheckBox upper = new JCheckBox("Any Uppercase Letters?");
    JCheckBox ambiguous = new JCheckBox("Any Ambiguous Characters? (e.g. { } [ ] ( ) / \\ ' \"\" ` ~ , ; : . < > )");

    private static final long serialVersionUID = 1L;
    public static boolean isDone = false;
    public static boolean isSymbol = false;
    public static boolean isLower = false;
    public static boolean isUpper = false;
    public static boolean isAmbiguous = false;
    public static boolean createPass = false;
    public static int numOfChar = 0;

    public PasswordGenerator() {
        setTitle("Password Generator");
        setVisible(true);
        setSize(550, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
                    numOfChar = Integer.parseInt(input);
                } else {
                    jl.setText("Please input a valid number");
                }
            }
        });

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

        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (numOfChar < 4) {
                    int countOfTrue = 0;
                    if (isSymbol)
                        countOfTrue++;
                    if (isLower)
                        countOfTrue++;
                    if (isUpper)
                        countOfTrue++;
                    if (isAmbiguous)
                        countOfTrue++;
                    if (countOfTrue > numOfChar) {
                        jl.setText(
                                "Please check the number of requirements in relation to the number of characters in the password.");
                    } else {
                        isDone = true;
                    }
                } else {
                    isDone = true;
                }
            }
        });

        jp.add(jb);
        jp.add(jt);
        jp.add(symbol);
        jp.add(lower);
        jp.add(upper);
        jp.add(ambiguous);
        jp.add(jl);

        if (createPass) {
            JLabel finalLabel = new JLabel("The password is: " + passwordGenerator());
            jp.remove(jl);
            jp.add(finalLabel);
        }

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
        createPass = true;
        new PasswordGenerator();
    }

    public static String passwordGenerator() {
        String finalString = "";
        boolean symbolVal = false;
        boolean lowerVal = false;
        boolean upperVal = false;
        boolean ambiguousVal = false;
        if (isSymbol) {
            if (isLower) {
                if (isUpper) {
                    if (isAmbiguous) {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand >= 97 && rand <= 122) {
                                    lowerVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand >= 65 && rand <= 90) {
                                    upperVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand == '!' || (rand >= 35 && rand <= 38) || rand == '*' || rand == '+'
                                        || rand == '-' || rand == '=' || rand == '@' || rand == '_' || rand == '^') {
                                    symbolVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand == '"' || rand == 39 || rand == '(' || rand == ')' || rand == ','
                                        || rand == '.' || rand == '/' || (rand >= 58 && rand <= 60) || rand == 62
                                        || rand == '?' || (rand >= 91 && rand <= 93) || rand == '`'
                                        || (rand >= 123 && rand <= 126)) {
                                    ambiguousVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (symbolVal && lowerVal && upperVal && ambiguousVal
                                        && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    symbolVal = false;
                                    lowerVal = false;
                                    upperVal = false;
                                    ambiguousVal = false;
                                }
                            }
                        }
                    } else {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand >= 97 && rand <= 122) {
                                    lowerVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand >= 65 && rand <= 90) {
                                    upperVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand == '!' || (rand >= 35 && rand <= 38) || rand == '*' || rand == '+'
                                        || rand == '-' || rand == '=' || rand == '@' || rand == '_' || rand == '^') {
                                    symbolVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (symbolVal && lowerVal && upperVal && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    symbolVal = false;
                                    lowerVal = false;
                                    upperVal = false;
                                }
                            }
                        }
                    }
                } else {
                    if (isAmbiguous) {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand >= 97 && rand <= 122) {
                                    lowerVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand == '!' || (rand >= 35 && rand <= 38) || rand == '*' || rand == '+'
                                        || rand == '-' || rand == '=' || rand == '@' || rand == '_' || rand == '^') {
                                    symbolVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand == '"' || rand == 39 || rand == '(' || rand == ')' || rand == ','
                                        || rand == '.' || rand == '/' || (rand >= 58 && rand <= 60) || rand == 62
                                        || rand == '?' || (rand >= 91 && rand <= 93) || rand == '`'
                                        || (rand >= 123 && rand <= 126)) {
                                    ambiguousVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (symbolVal && lowerVal && ambiguousVal && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    symbolVal = false;
                                    lowerVal = false;
                                    ambiguousVal = false;
                                }
                            }
                        }
                    } else {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand >= 97 && rand <= 122) {
                                    lowerVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand == '!' || (rand >= 35 && rand <= 38) || rand == '*' || rand == '+'
                                        || rand == '-' || rand == '=' || rand == '@' || rand == '_' || rand == '^') {
                                    symbolVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (symbolVal && lowerVal && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    symbolVal = false;
                                    lowerVal = false;
                                }
                            }
                        }
                    }
                }
            } else {
                if (isUpper) {
                    if (isAmbiguous) {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand >= 65 && rand <= 90) {
                                    upperVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand == '!' || (rand >= 35 && rand <= 38) || rand == '*' || rand == '+'
                                        || rand == '-' || rand == '=' || rand == '@' || rand == '_' || rand == '^') {
                                    symbolVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand == '"' || rand == 39 || rand == '(' || rand == ')' || rand == ','
                                        || rand == '.' || rand == '/' || (rand >= 58 && rand <= 60) || rand == 62
                                        || rand == '?' || (rand >= 91 && rand <= 93) || rand == '`'
                                        || (rand >= 123 && rand <= 126)) {
                                    ambiguousVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (symbolVal && upperVal && ambiguousVal && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    symbolVal = false;
                                    upperVal = false;
                                    ambiguousVal = false;
                                }
                            }
                        }
                    } else {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand >= 65 && rand <= 90) {
                                    upperVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand == '!' || (rand >= 35 && rand <= 38) || rand == '*' || rand == '+'
                                        || rand == '-' || rand == '=' || rand == '@' || rand == '_' || rand == '^') {
                                    symbolVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (symbolVal && upperVal && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    symbolVal = false;
                                    upperVal = false;
                                }
                            }
                        }
                    }
                } else {
                    if (isAmbiguous) {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand == '!' || (rand >= 35 && rand <= 38) || rand == '*' || rand == '+'
                                        || rand == '-' || rand == '=' || rand == '@' || rand == '_' || rand == '^') {
                                    symbolVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand == '"' || rand == 39 || rand == '(' || rand == ')' || rand == ','
                                        || rand == '.' || rand == '/' || (rand >= 58 && rand <= 60) || rand == 62
                                        || rand == '?' || (rand >= 91 && rand <= 93) || rand == '`'
                                        || (rand >= 123 && rand <= 126)) {
                                    ambiguousVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (symbolVal && ambiguousVal && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    symbolVal = false;
                                    ambiguousVal = false;
                                }
                            }
                        }
                    } else {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand == '!' || (rand >= 35 && rand <= 38) || rand == '*' || rand == '+'
                                        || rand == '-' || rand == '=' || rand == '@' || rand == '_' || rand == '^') {
                                    symbolVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (symbolVal && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    symbolVal = false;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (isLower) {
                if (isUpper) {
                    if (isAmbiguous) {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand >= 97 && rand <= 122) {
                                    lowerVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand >= 65 && rand <= 90) {
                                    upperVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand == '"' || rand == 39 || rand == '(' || rand == ')' || rand == ','
                                        || rand == '.' || rand == '/' || (rand >= 58 && rand <= 60) || rand == 62
                                        || rand == '?' || (rand >= 91 && rand <= 93) || rand == '`'
                                        || (rand >= 123 && rand <= 126)) {
                                    ambiguousVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (lowerVal && upperVal && ambiguousVal && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    lowerVal = false;
                                    upperVal = false;
                                    ambiguousVal = false;
                                }
                            }
                        }
                    } else {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand >= 97 && rand <= 122) {
                                    lowerVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand >= 65 && rand <= 90) {
                                    upperVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (lowerVal && upperVal && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    lowerVal = false;
                                    upperVal = false;
                                }
                            }
                        }
                    }
                } else {
                    if (isAmbiguous) {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand >= 97 && rand <= 122) {
                                    lowerVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand == '"' || rand == 39 || rand == '(' || rand == ')' || rand == ','
                                        || rand == '.' || rand == '/' || (rand >= 58 && rand <= 60) || rand == 62
                                        || rand == '?' || (rand >= 91 && rand <= 93) || rand == '`'
                                        || (rand >= 123 && rand <= 126)) {
                                    ambiguousVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (lowerVal && ambiguousVal && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    lowerVal = false;
                                    upperVal = false;
                                }
                            }
                        }
                    } else {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand >= 97 && rand <= 122) {
                                    lowerVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (lowerVal && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    lowerVal = false;
                                }
                            }
                        }
                    }
                }
            } else {
                if (isUpper) {
                    if (isAmbiguous) {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand >= 65 && rand <= 90) {
                                    upperVal = true;
                                    finalString += String.valueOf((char) rand);
                                } else if (rand == '"' || rand == 39 || rand == '(' || rand == ')' || rand == ','
                                        || rand == '.' || rand == '/' || (rand >= 58 && rand <= 60) || rand == 62
                                        || rand == '?' || (rand >= 91 && rand <= 93) || rand == '`'
                                        || (rand >= 123 && rand <= 126)) {
                                    ambiguousVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (upperVal && ambiguousVal && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    upperVal = false;
                                    ambiguousVal = false;
                                }
                            }
                        }
                    } else {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand >= 65 && rand <= 90) {
                                    upperVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (upperVal && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    upperVal = false;
                                }
                            }
                        }
                    }
                } else {
                    if (isAmbiguous) {
                        while (true) {
                            for (int i = 0; i < numOfChar; i++) {
                                int rand = (int) ((Math.random() * 95) + 33);
                                if (rand == '"' || rand == 39 || rand == '(' || rand == ')' || rand == ','
                                        || rand == '.' || rand == '/' || (rand >= 58 && rand <= 60) || rand == 62
                                        || rand == '?' || (rand >= 91 && rand <= 93) || rand == '`'
                                        || (rand >= 123 && rand <= 126)) {
                                    ambiguousVal = true;
                                    finalString += String.valueOf((char) rand);
                                }
                                if (ambiguousVal && finalString.length() == numOfChar) {
                                    return finalString;
                                } else if (finalString.length() == numOfChar) {
                                    finalString = "";
                                    ambiguousVal = false;
                                }
                            }
                        }
                    } else {
                        return null;
                    }
                }
            }
        }
    }

}
