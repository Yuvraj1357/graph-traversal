import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

class directed {
    private int count = 0; // Count variable for longestConnectedGraphLength method

    directed(String name, int n, int edges, String s, Map<Integer, List<Integer>> adjacencyList) {
        JFrame f = new JFrame("Directed");

        JLabel header = new JLabel(name + ", Now you can search:");
        header.setBounds(300, 10, 800, 100);
        header.setForeground(Color.red);
        Font font = header.getFont();
        font = font.deriveFont(Font.BOLD, 40);
        header.setFont(font);

        JLabel ans = new JLabel("");
        ans.setBounds(230, 280, 800, 90);
        ans.setForeground(Color.black);
        ans.setFont(font);

        JLabel footer = new JLabel("You can search for:");
        footer.setBounds(300, 500, 800, 100);
        footer.setForeground(Color.red);
        footer.setFont(font);

        JButton undirected = new JButton("Undirected");
        undirected.setBounds(700, 530, 200, 40);
        Font font1 = new Font(undirected.getFont().getName(), Font.BOLD, 20);
        undirected.setFont(font1);
        undirected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new undirected(name, n, edges, s, adjacencyList);
            }
        });

        JComboBox<String> search = new JComboBox<>();
        search.addItem("BFS");
        search.addItem("DFS");
        search.addItem("Topological Sorting");
        search.addItem("Length of Longest Connected Graph");
        search.setBounds(330, 100, 400, 30);

        JButton find = new JButton("Find");
        find.setBounds(750, 100, 80, 30);
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSearch = search.getSelectedItem().toString();
                String result = "";
                switch (selectedSearch) {
                    case "BFS":
                        result = performBFS(n, adjacencyList);
                        break;
                    case "DFS":
                        result = performDFS(n, adjacencyList);
                        break;
                    case "Topological Sorting":
                        result = performTopologicalSorting(n, adjacencyList);
                        break;
                    case "Length of Longest Connected Graph":
                        result = longestConnectedGraphLength(n, adjacencyList);
                        break;
                }
                ans.setText(result);
            }
        });

        f.add(ans);
        f.add(find);
        f.add(header);
        f.add(search);
        f.add(footer);
        f.add(undirected);

        f.setSize(1200, 650);
        f.getContentPane().setBackground(Color.lightGray);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);
    }

    private String performBFS(int n, Map<Integer, List<Integer>> adjacencyList) {
        StringBuilder result = new StringBuilder("BFS Result: ");
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                queue.offer(i);
                visited.add(i);

                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    result.append(current).append(" ");

                    List<Integer> neighbors = adjacencyList.getOrDefault(current, new ArrayList<>());
                    for (int neighbor : neighbors) {
                        if (!visited.contains(neighbor)) {
                            queue.offer(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
            }
        }
        return result.toString();
    }

    private String performDFS(int n, Map<Integer, List<Integer>> adjacencyList) {
        StringBuilder result = new StringBuilder("DFS Result: ");
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                dfsUtil(i, visited, result, adjacencyList);
            }
        }
        return result.toString();
    }

    private void dfsUtil(int node, Set<Integer> visited, StringBuilder result, Map<Integer, List<Integer>> adjacencyList) {
        visited.add(node);
        result.append(node).append(" ");

        List<Integer> neighbors = adjacencyList.getOrDefault(node, new ArrayList<>());
        for (int neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfsUtil(neighbor, visited, result, adjacencyList);
            }
        }
    }

    private String performTopologicalSorting(int n, Map<Integer, List<Integer>> adjacencyList) {
        StringBuilder result = new StringBuilder("Topological Sorting: ");
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack, adjacencyList);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(" ");
        }

        return result.toString();
    }

    private void topologicalSortUtil(int node, boolean[] visited, Stack<Integer> stack, Map<Integer, List<Integer>> adjacencyList) {
        visited[node] = true;

        List<Integer> neighbors = adjacencyList.getOrDefault(node, new ArrayList<>());
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                topologicalSortUtil(neighbor, visited, stack, adjacencyList);
            }
        }

        stack.push(node);
    }

    private String longestConnectedGraphLength(int n, Map<Integer, List<Integer>> adjacencyList) {
        int maxCount = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count = 0; // Initialize count for each connected component
                dfsCount(i, visited, adjacencyList);
                maxCount = Math.max(maxCount, count);
            }
        }

        return "Length of Longest Connected Graph: " + maxCount;
    }

    private void dfsCount(int node, boolean[] visited, Map<Integer, List<Integer>> adjacencyList) {
        visited[node] = true;
        count++; // Increment count for each visited node

        List<Integer> neighbors = adjacencyList.getOrDefault(node, new ArrayList<>());
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                dfsCount(neighbor, visited, adjacencyList);
            }
        }
    }
}
