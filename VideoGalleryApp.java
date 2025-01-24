import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class VideoGalleryApp extends JFrame {
    private JPanel videoGrid;
    
    public VideoGalleryApp() {
        setTitle("Video Gallery");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create video grid panel with GridLayout to match CSS grid
        videoGrid = new JPanel(new GridLayout(0, 3, 20, 20)); // 3 columns with 20px gaps
        videoGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Add video containers
        addVideoContainer("https://www.youtube.com/embed/video1");
        addVideoContainer("https://www.youtube.com/embed/video2");
        addVideoContainer("https://www.youtube.com/embed/video3");
        
        // Add scroll pane with custom scrollbar
        JScrollPane scrollPane = new JScrollPane(videoGrid);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        customizeScrollbar(scrollPane);
        
        add(scrollPane, BorderLayout.CENTER);
        
        // Set responsive window size
        setSize(1024, 768);
        setLocationRelativeTo(null);
    }
    
    private void addVideoContainer(String videoUrl) {
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 26)));
        
        // Create video panel (in real implementation, you'd use JWebView or similar)
        JPanel videoPanel = new JPanel();
        videoPanel.setPreferredSize(new Dimension(300, 169)); // 16:9 aspect ratio
        videoPanel.setBackground(Color.BLACK);
        
        // Add hover effect
        videoPanel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                container.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 51)));
            }
            
            public void mouseExited(MouseEvent e) {
                container.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 26)));
            }
        });
        
        container.add(videoPanel, BorderLayout.CENTER);
        videoGrid.add(container);
    }
    
    private void customizeScrollbar(JScrollPane scrollPane) {
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(100, 100, 100);
                this.trackColor = new Color(241, 241, 241);
            }
            
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }
            
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            
            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VideoGalleryApp().setVisible(true);
        });
    }
}
