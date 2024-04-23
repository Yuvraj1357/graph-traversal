import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

class undirected {
    undirected(String name, int n, int edges, String s, Map<Integer, List<Integer>> adjacencyList) {
        JFrame f = new JFrame("Undirected");

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

        JButton directed = new JButton("Directed");
        directed.setBounds(700, 530, 200, 40);
        Font font1 = new Font(directed.getFont().getName(), Font.BOLD, 20);
        directed.setFont(font1);
        directed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new directed(name, n, edges, s, adjacencyList);
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
                // Perform search based on selected option
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
        f.add(directed);

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
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                queue.offer(i);
                visited[i] = true;

                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    result.append(current).append(" ");

                    List<Integer> neighbors = adjacencyList.getOrDefault(current, new ArrayList<>());
                    for (int neighbor : neighbors) {
                        if (!visited[neighbor]) {
                            queue.offer(neighbor);
                            visited[neighbor] = true;
                        }
                    }
                }
            }
        }
        return result.toString();
    }

    private String performDFS(int n, Map<Integer, List<Integer>> adjacencyList) {
        StringBuilder result = new StringBuilder("DFS Result: ");
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsUtil(i, visited, result, adjacencyList);
            }
        }
        return result.toString();
    }

    private void dfsUtil(int node, boolean[] visited, StringBuilder result, Map<Integer, List<Integer>> adjacencyList) {
        visited[node] = true;
        result.append(node).append(" ");

        List<Integer> neighbors = adjacencyList.getOrDefault(node, new ArrayList<>());
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
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
                int count = dfsCount(i, visited, adjacencyList);
                maxCount = Math.max(maxCount, count);
            }
        }

        return "Length of Longest Connected Graph: " + maxCount;
    }

    private int dfsCount(int node, boolean[] visited, Map<Integer, List<Integer>> adjacencyList) {
        visited[node] = true;
        int count = 1;

        List<Integer> neighbors = adjacencyList.getOrDefault(node, new ArrayList<>());
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                count += dfsCount(neighbor, visited, adjacencyList);
            }
        }
        return count;
    }
}
