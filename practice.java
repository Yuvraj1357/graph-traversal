import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class practice
{
    JFrame f;
    practice()
    {
        f=new JFrame("First Page");
        f.setLayout(null);

        JLabel header=new JLabel("<html><div style='text-align: center;'>Welcome<br/>to the<br/> JAVA project</div></html>");
        header.setBounds(400,0,800,300);
        header.setForeground(Color.green);
        Font font = header.getFont();
        font = font.deriveFont(Font.BOLD, 60);
        header.setFont(font);


        JPanel p1=new JPanel();
        p1.setBounds(0,10,1200,250);
        p1.setBackground(Color.red);
        p1.add(header);


        JPanel p2=new JPanel();
        p2.setBounds(0,300,1200,100);
        p2.setBackground(Color.yellow);


        JLabel name=new JLabel("Please enter your name");
        name.setBounds(0,0,800,300);
        name.setForeground(Color.blue);
        name.setFont(font);
        p2.add(name);


        JTextArea input=new JTextArea();
        input.setBounds(430,450,300,40);
        input.setLineWrap(true);
        input.setWrapStyleWord(true);
        input.setBorder(new LineBorder(Color.BLACK));
        input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateText();
            }

            private void updateText() {
                SwingUtilities.invokeLater(() -> {
                    int fontSize = 20;
                    Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
                    input.setFont(font);
                    input.setAlignmentX(Component.CENTER_ALIGNMENT);
                    input.setAlignmentY(Component.CENTER_ALIGNMENT);
                });
            }
        });



        JButton button=new JButton("Start");
        button.setBounds(540,530,100,40);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputData = input.getText();
                new graph_traversal(inputData);
            }
        });




        f.add(p1);
        f.add(p2);
        f.add(input);
        f.add(button);



        f.setSize(1200,650);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);
    }
}

