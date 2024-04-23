import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

class undirected
{
    undirected(int n,int edges, String s, Map<Integer, List<Integer>> adjacencyList)
    {
        JFrame f=new JFrame("Undirected");



        JLabel header=new JLabel("Yuvraj, Now you can search:");
        header.setBounds(300,10,800,100);
        header.setForeground(Color.red);
        Font font = header.getFont();
        font = font.deriveFont(Font.BOLD, 40);
        header.setFont(font);


        JLabel footer=new JLabel("You can search for:");
        footer.setBounds(300,500,800,100);
        footer.setForeground(Color.red);
        footer.setFont(font);


        JComboBox<String> search = new JComboBox<>();
        search.addItem("BFS");
        search.addItem("DFS");
        search.addItem("TOPOLOGICAL ORDER");
        search.addItem("Number of Connected Components");
        search.addItem("Connected Component with their values");
        search.addItem("Maximum length connected component");
        search.setBounds(330,100,400,30);


        JButton undirected=new JButton("Directed");
        undirected.setBounds(700,530,200,40);
        Font font1 = new Font(undirected.getFont().getName(), Font.BOLD, 20);
        undirected.setFont(font1);
        undirected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new directed(n,edges,s, adjacencyList);
            }
        });


        JButton find=new JButton("Find");
        find.setBounds(750,100,80,30);


        f.add(find);
        f.add(header);
        f.add(footer);
        f.add(undirected);
        f.add(search);


        f.setSize(1200,650);
        f.getContentPane().setBackground(Color.lightGray);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);
    }
}
