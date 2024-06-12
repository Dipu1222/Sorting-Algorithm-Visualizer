/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sortingvisualizer;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SortingGUI extends JFrame {
    private JButton generateButton, sortButton, pauseButton, resumeButton;
    private JComboBox<String> sortAlgorithmComboBox;
    private JSlider sizeSlider;
    private JPanel graphPanel;
    private int[] array;
    private int arraySize = 100;
    private boolean isPaused = false;

    public SortingGUI() {
        setTitle("Sorting Visualizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        generateButton = new JButton("Generate Array");
        sortButton = new JButton("Sort");
        pauseButton = new JButton("Pause");
        resumeButton = new JButton("Resume");
        String[] algorithms = {"Merge Sort", "Quick Sort", "Selection Sort", "Insertion Sort", "Bubble Sort", "Heap Sort"};
        sortAlgorithmComboBox = new JComboBox<>(algorithms);
        sizeSlider = new JSlider(JSlider.HORIZONTAL, 10, 200, arraySize);
        sizeSlider.setMajorTickSpacing(30);
        sizeSlider.setMinorTickSpacing(5);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);

        controlPanel.add(generateButton);
        controlPanel.add(sortButton);
        controlPanel.add(pauseButton);
        controlPanel.add(resumeButton);
        controlPanel.add(sortAlgorithmComboBox);
        controlPanel.add(new JLabel("Array Size:"));
        controlPanel.add(sizeSlider);
        add(controlPanel, BorderLayout.NORTH);

        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.DARK_GRAY); 
                if (array != null) {
                    int width = getWidth();
                    int height = getHeight();
                    int barWidth = width / array.length;
                    for (int i = 0; i < array.length; i++) {
                        int barHeight = array[i] * height / arraySize;
                        g.setColor(Color.CYAN); 
                        g.fillRect(i * barWidth, height - barHeight, barWidth, barHeight);
                        g.setColor(Color.BLACK); 
                        g.drawRect(i * barWidth, height - barHeight, barWidth, barHeight); // Draw border around each bar
                    }
                }
            }
        };
        add(graphPanel, BorderLayout.CENTER);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateArray();
                graphPanel.repaint();
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String algorithm = (String) sortAlgorithmComboBox.getSelectedItem();
                switch (algorithm) {
                    case "Merge Sort":
                        new Thread(() -> MergeSort.sort(array, SortingGUI.this)).start();
                        break;
                    case "Quick Sort":
                        new Thread(() -> QuickSort.sort(array, SortingGUI.this)).start();
                        break;
                    case "Selection Sort":
                        new Thread(() -> SelectionSort.sort(array, SortingGUI.this)).start();
                        break;
                    case "Insertion Sort":
                        new Thread(() -> InsertionSort.sort(array, SortingGUI.this)).start();
                        break;
                    case "Bubble Sort":
                        new Thread(() -> BubbleSort.sort(array, SortingGUI.this)).start();
                        break;
                    case "Heap Sort":
                        new Thread(() -> HeapSort.sort(array, SortingGUI.this)).start();
                        break;
                }
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPaused = true;
            }
        });

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPaused = false;
                synchronized (SortingGUI.this) {
                    SortingGUI.this.notify();
                }
            }
        });

        sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                arraySize = sizeSlider.getValue();
                generateArray();
                graphPanel.repaint();
            }
        });

        generateArray();
    }

    private void generateArray() {
        array = new int[arraySize];
        Random rand = new Random();
        for (int i = 0; i < arraySize; i++) {
            array[i] = rand.nextInt(arraySize);
        }
    }

    public void repaintGraph() {
        synchronized (this) {
            if (isPaused) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        graphPanel.repaint();
        try {
            Thread.sleep(50);  // Add delay for visualization
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int[] getArray() {
        return array;
    }

    public boolean isPaused() {
        return isPaused;
    }
}






