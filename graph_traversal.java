import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.HashMap;
import java.util.List;

class graph_traversal
{
    graph_traversal(String s)
    {
        JFrame f=new JFrame("Graph Traversal");

        JLabel header=new JLabel("Welcome "+s);
        header.setBounds(400,0,600,100);
        header.setForeground(Color.RED);
        Font font = header.getFont();
        font = font.deriveFont(Font.BOLD, 50);
        header.setFont(font);


        JLabel welcome=new JLabel("GRAPH TRAVERSAL PROJECT");
        welcome.setBounds(200,30,900,200);
        welcome.setForeground(Color.GREEN);
        Font wel=header.getFont();
        wel.deriveFont(Font.BOLD,25);
        welcome.setFont(wel);


        JPanel p=new JPanel();
        p.setBounds(300,180,600,400);
        p.setBackground(Color.yellow);
        p.setLayout(null);


        JLabel numberOfNodes=new JLabel("Enter the number of nodes:- ");
        numberOfNodes.setBounds(15,30,400,50);
        numberOfNodes.setForeground(Color.blue);
        Font fontnode = header.getFont();
        fontnode = fontnode.deriveFont(Font.BOLD, 22);
        numberOfNodes.setFont(fontnode);

        JTextField tf=new JTextField("");
        tf.setBounds(330,40,200,30);
        tf.setForeground(Color.black);


        JLabel numberOfEdges=new JLabel("Enter the number of edges:- ");
        numberOfEdges.setBounds(15,100,400,50);
        numberOfEdges.setForeground(Color.blue);
        Font fontedge = header.getFont();
        fontedge = fontedge.deriveFont(Font.BOLD, 22);
        numberOfEdges.setFont(fontedge);


        JTextField tf1=new JTextField("");
        tf1.setBounds(330,110,200,30);
        tf1.setForeground(Color.black);


        JLabel TypeOfGraph=new JLabel("Please select the type of graph");
        TypeOfGraph.setBounds(15,160,400,50);
        TypeOfGraph.setForeground(Color.blue);
        Font type = TypeOfGraph.getFont();
        type = type.deriveFont(Font.BOLD, 22);
        TypeOfGraph.setFont(type);


        JComboBox<String> graphType = new JComboBox<>();
        graphType.addItem("Directed");
        graphType.addItem("Undirected");
        graphType.setBounds(350,170,150,25);


        JLabel Edges=new JLabel("Enter the edges:- ");
        Edges.setBounds(15,200,400,50);
        Edges.setForeground(Color.blue);
        Font fontedge1 = header.getFont();
        fontedge1 = fontedge1.deriveFont(Font.BOLD, 22);
        Edges.setFont(fontedge1);

        JLabel hint= new JLabel("(Enter the edge in given way\n 1-2,1-3,2-3,...\n and do not include space)");
        hint.setBounds(15,220,400,50);


        JTextArea ta=new JTextArea("");
        ta.setBounds(70,260,400,80);
        ta.setForeground(Color.black);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setBorder(new LineBorder(Color.BLACK));

        JLabel err=new JLabel("");
        err.setBounds(550,395,300,20);
        err.setForeground(Color.red);

        JButton submit=new JButton("Submit");
        submit.setBounds(200,360,150,30);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numberOfNodes = Integer.parseInt(tf.getText());
                    int numberOfEdges = Integer.parseInt(tf1.getText());
                    if (numberOfNodes <= 0 || numberOfEdges < 0) {
                        err.setText("Enter the correct Input");
                        return;
                    }

                    String graph = Objects.requireNonNull(graphType.getSelectedItem()).toString();
                    if (!graph.equals("Directed") && !graph.equals("Undirected")) {
                        err.setText("Enter the correct Input");
                        return;
                    }

                    String[] edgeInputs = ta.getText().split(",");
                    if (edgeInputs.length != numberOfEdges) {
                        err.setText("Enter the correct Input");
                        return;
                    }

                    for (String edge : edgeInputs) {
                        String[] vertices = edge.split("-");
                        if (vertices.length != 2) {
                            err.setText("Enter the correct Input");
                            return;
                        }
                        int vertex1 = Integer.parseInt(vertices[0]);
                        int vertex2 = Integer.parseInt(vertices[1]);
                        if (vertex1 < 0 || vertex1 >= numberOfNodes || vertex2 < 0 || vertex2 >= numberOfNodes) {
                            err.setText("Enter the correct Input");
                            return;
                        }
                    }
                    Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
                    for (String edge : edgeInputs) {
                        String[] vertices = edge.split("-");
                        int vertex1 = Integer.parseInt(vertices[0]);
                        int vertex2 = Integer.parseInt(vertices[1]);
                        adjacencyList.computeIfAbsent(vertex1, k -> new ArrayList<>()).add(vertex2);
                        if (graph.equals("Undirected")) {
                            adjacencyList.computeIfAbsent(vertex2, k -> new ArrayList<>()).add(vertex1);
                        }
                    }
                    if(graph.equals("Directed"))
                        new directed(s,numberOfNodes, numberOfEdges, graph, adjacencyList);
                    else new undirected(s,numberOfNodes, numberOfEdges, graph, adjacencyList);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        });




        f.add(err);
        f.add(welcome);
        p.add(submit);
        p.add(ta);
        p.add(graphType);
        p.add(TypeOfGraph);
        p.add(hint);
        p.add(Edges);
        p.add(tf1);
        p.add(tf);
        p.add(numberOfNodes);
        p.add(numberOfEdges);
        f.add(p);
        f.add(header);



        f.setLayout(null);
        f.setSize(1200,650);
        f.setResizable(false);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.getContentPane().setBackground(new Color(130,160,150));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}