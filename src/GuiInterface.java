/**
 * File: GuiInterface.java
 * Project: Matrix-Calculator
 * Date: October 2022
 * @author Neti-G
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * The class which creates the GUI where the logic of the class MatrixLogic will be used.
 * 
 * @author Neti
 */
public class GuiInterface{

    MatrixLogic logic = new MatrixLogic();
    private JFrame frame;
    private int frameWidth;
    private int frameHeight;
    private JPanel sidePanel;
    private JPanel mainPanel;
    private int mainPanelWidth;
    private int mainPanelHeight;
    private JPanel sumPanel;
    private JPanel differencePanel;
    private JPanel multiplicationPanel;
    private JPanel determinantPanel;
    private JPanel inversePanel;
    private JPanel currentPanel;
    private final Color mainColor = new Color(67,107,142);
    private final Color sideColor = new Color(46,76,100);
    private final Color textColor = new Color(225,225,225);

    private JButton mainButton;
    private JButton sumButton;
    private JButton differenceButton;
    private JButton multiplicationButton;
    private JButton determinantButton;
    private JButton inverseButton;
    private JButton moreButton;
    
    private JLabel nameLabel;
    private JLabel authorLabel;
    private int componentsCreated = 0;

    private JTextField[][] sumTextFieldsA;
    private JTextField[][] sumTextFieldsB;
    private JTextField[][] sumTextFieldsR;
    private JTextField[][] differenceTextFieldsA;
    private JTextField[][] differenceTextFieldsB;
    private JTextField[][] differenceTextFieldsR;
    private JTextField[][] multiplicationTextFieldsA;
    private JTextField[][] multiplicationTextFieldsB;
    private JTextField[][] multiplicationTextFieldsR;
    private JTextField[][] determinantTextFieldsA;
    private JTextField[][] inverseTextFieldsA;
    private JTextField[][] inverseTextFieldsR;

    private int sumRows;
    private int sumColumns;
    private int differenceRows;
    private int differenceColumns;
    private int multiplicationRows;
    private int multiplicationColumnsNRows;
    private int multiplicationColumns;
    private int determinantRows;
    private int inverseRows;

    private JButton sumResultButton;
    private JButton differenceResultButton;
    private JButton multiplicationResultButton;
    private JButton determinantResultButton;
    private JButton inverseResultButton;

    private JFrame otherFrame=null;
    private JPanel otherSumPanel;
    private JPanel otherDifferencePanel;
    private JPanel otherMultiplicationPanel;
    private JPanel otherDeterminantPanel;
    private JPanel otherInversePanel;
    private JPanel otherCurrentPanel;
    JComboBox<String> optionComboBox;

    /**
     * The constructor.
     * Creates a frame and calls other methods which create components for that frame.
     */
    GuiInterface(){ 
        frame = new JFrame();
        frame.setTitle("Matrix Calculator");
        Image frameIcon = Toolkit.getDefaultToolkit().getImage("./img/Frame Icon.png");
        frame.setIconImage(frameIcon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1000,600);
        frame.setBackground(mainColor);
        frameWidth = frame.getWidth();
        frameHeight = frame.getHeight();
        frame.setMinimumSize(new Dimension(1000,600));
        
        mainPanelWidth = 5*frameWidth/6;
        mainPanelHeight = frameHeight;
        makeSidePanel();
        makeMainPanel();
        makeSumPanel();
        makeDifferencePanel();
        makeMultiplicationPanel();
        makeDeterminantPanel();
        makeInversePanel();
        currentPanel = mainPanel;
 
        frame.add(sidePanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);
        
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Creates the side panel which will hold the buttons that switch between other panels.
     */
    private void makeSidePanel(){
        sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBackground(sideColor);
        sidePanel.setPreferredSize(new Dimension(frameWidth/6,frameHeight));
         
        //=========================================================================================
        //================================== The buttons ==========================================

        mainButton = new JButton("Title");
        mainButton.setBounds(0,0,frameWidth/6,40);
        mainButton.setIcon(new ImageIcon("./img/Main Button.png"));
        mainButton.setHorizontalAlignment(SwingConstants.LEFT);
        mainButton.setFont(new Font("Arial",Font.BOLD,15));
        mainButton.setForeground(textColor);
        mainButton.setBackground(mainColor);
        mainButton.setBorder(null);
        mainButton.setFocusable(false);
        mainButton.setContentAreaFilled(false);
        mainButton.setOpaque(true);
        mainButton.addActionListener(e -> {
            changePanel(currentPanel, mainPanel);
            currentPanel=mainPanel;
            mainButton.setBackground(mainColor);
            changeOtherButtonsColor(mainButton);
        });
        mainButton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                if(currentPanel != mainPanel){
                    mainButton.setBackground(mainColor);
                }
            }
            public void mouseExited(MouseEvent e){
                if(currentPanel != mainPanel)
                    mainButton.setBackground(sideColor);
            }
        });

        sumButton = new JButton("Addition");
        sumButton.setBounds(0,40,frameWidth/6,40);
        sumButton.setIcon(new ImageIcon("./img/Addition Button.png"));
        sumButton.setHorizontalAlignment(SwingConstants.LEFT);
        sumButton.setFont(new Font("Arial",Font.BOLD,15));
        sumButton.setForeground(textColor);
        sumButton.setBackground(null);
        sumButton.setBorder(null);
        sumButton.setFocusable(false);
        sumButton.setContentAreaFilled(false);
        sumButton.setOpaque(true);
        sumButton.addActionListener(e -> {
            changePanel(currentPanel, sumPanel);
            currentPanel = sumPanel;
            sumButton.setBackground(mainColor);
            changeOtherButtonsColor(sumButton);
            frame.getRootPane().setDefaultButton(sumResultButton);
        });
        sumButton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                if(currentPanel != sumPanel)
                    sumButton.setBackground(mainColor);
            }
            public void mouseExited(MouseEvent e){
                if(currentPanel != sumPanel)
                    sumButton.setBackground(sideColor);
            }
        });

        differenceButton = new JButton("Subtraction");
        differenceButton.setBounds(0,80,frameWidth/6,40);
        differenceButton.setIcon(new ImageIcon("./img/Subtraction Button.png"));
        differenceButton.setHorizontalAlignment(SwingConstants.LEFT);
        differenceButton.setFont(new Font("Arial",Font.BOLD,15));
        differenceButton.setForeground(textColor);
        differenceButton.setBackground(null);
        differenceButton.setBorder(null);
        differenceButton.setFocusable(false);
        differenceButton.setContentAreaFilled(false);
        differenceButton.setOpaque(true);
        differenceButton.addActionListener(e -> {
            changePanel(currentPanel, differencePanel);
            currentPanel = differencePanel;
            differenceButton.setBackground(mainColor);
            changeOtherButtonsColor(differenceButton);
            frame.getRootPane().setDefaultButton(differenceResultButton);
        });
        differenceButton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                if(currentPanel != differencePanel)
                    differenceButton.setBackground(mainColor);
            }
            public void mouseExited(MouseEvent e){
                if(currentPanel != differencePanel)
                    differenceButton.setBackground(null);
            }
        });


        multiplicationButton = new JButton("Multiplication");
        multiplicationButton.setBounds(0,120,frameWidth/6,40);
        multiplicationButton.setIcon(new ImageIcon("./img/Multiplication Button.png"));
        multiplicationButton.setHorizontalAlignment(SwingConstants.LEFT);
        multiplicationButton.setFont(new Font("Arial",Font.BOLD,15));
        multiplicationButton.setForeground(textColor);
        multiplicationButton.setBackground(null);
        multiplicationButton.setBorder(null);
        multiplicationButton.setFocusable(false);
        multiplicationButton.setContentAreaFilled(false);
        multiplicationButton.setOpaque(true);
        multiplicationButton.addActionListener(e -> {
            changePanel(currentPanel, multiplicationPanel);
            currentPanel = multiplicationPanel;
            multiplicationButton.setBackground(mainColor);
            changeOtherButtonsColor(multiplicationButton);
            frame.getRootPane().setDefaultButton(multiplicationResultButton);
        });
        multiplicationButton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                if(currentPanel != multiplicationPanel)
                    multiplicationButton.setBackground(mainColor);
            }
            public void mouseExited(MouseEvent e){
                if(currentPanel != multiplicationPanel)
                    multiplicationButton.setBackground(null);
            }
        });


        determinantButton = new JButton("Determinant");
        determinantButton.setBounds(0,160,frameWidth/6,40);
        determinantButton.setIcon(new ImageIcon("./img/Determinant Button.png"));
        determinantButton.setHorizontalAlignment(SwingConstants.LEFT);
        determinantButton.setFont(new Font("Arial",Font.BOLD,15));
        determinantButton.setForeground(textColor);
        determinantButton.setBackground(null);
        determinantButton.setBorder(null);
        determinantButton.setFocusable(false);
        determinantButton.setContentAreaFilled(false);
        determinantButton.setOpaque(true);
        determinantButton.addActionListener(e -> {
            changePanel(currentPanel, determinantPanel);
            currentPanel = determinantPanel;
            determinantButton.setBackground(mainColor);
            changeOtherButtonsColor(determinantButton);
            frame.getRootPane().setDefaultButton(determinantResultButton);
        });
        determinantButton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                if(currentPanel != determinantPanel)
                    determinantButton.setBackground(mainColor);
            }
            public void mouseExited(MouseEvent e){
                if(currentPanel != determinantPanel)
                    determinantButton.setBackground(null);
            }
        });


        inverseButton = new JButton("Inverse");
        inverseButton.setBounds(0,200,frameWidth/6,40);
        inverseButton.setIcon(new ImageIcon("./img/Inverse Button.png"));
        inverseButton.setHorizontalAlignment(SwingConstants.LEFT);
        inverseButton.setFont(new Font("Arial",Font.BOLD,15));
        inverseButton.setForeground(textColor);
        inverseButton.setBackground(null);
        inverseButton.setBorder(null);
        inverseButton.setFocusable(false);
        inverseButton.setContentAreaFilled(false);
        inverseButton.setOpaque(true);
        inverseButton.addActionListener(e -> {
            changePanel(currentPanel, inversePanel);
            currentPanel = inversePanel;
            inverseButton.setBackground(mainColor);
            changeOtherButtonsColor(inverseButton);
            frame.getRootPane().setDefaultButton(inverseResultButton);
        });
        inverseButton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                if(currentPanel != inversePanel)
                    inverseButton.setBackground(mainColor);
            }
            public void mouseExited(MouseEvent e){
                if(currentPanel != inversePanel)
                    inverseButton.setBackground(null);
            }
        });

        moreButton = new JButton("More rows?");
        moreButton.setBounds(0,frameHeight-40,frameWidth/6,40);
        moreButton.setIcon(new ImageIcon("./img/More Button.png"));
        moreButton.setHorizontalAlignment(SwingConstants.LEFT);
        moreButton.setFont(new Font("Arial",Font.BOLD,15));
        moreButton.setForeground(textColor);
        moreButton.setBackground(null);
        moreButton.setBorder(null);
        moreButton.setFocusable(false);
        moreButton.setContentAreaFilled(false);
        moreButton.setOpaque(true);
        moreButton.addActionListener(e -> {
            makeOtherFrame();
        });
        moreButton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                moreButton.setBackground(mainColor);
            }
            public void mouseExited(MouseEvent e){
                moreButton.setBackground(sideColor);
            }
        });


        //=========================================================================================
        //=========================================================================================

        sidePanel.add(mainButton);
        sidePanel.add(sumButton);
        sidePanel.add(differenceButton);
        sidePanel.add(multiplicationButton);
        sidePanel.add(determinantButton);
        sidePanel.add(inverseButton);
        sidePanel.add(moreButton);
        componentsCreated++;

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                if(componentsCreated>6){
                    frameWidth = frame.getWidth();
                    frameHeight = frame.getHeight();
                    moreButton.setBounds(0,frameHeight-39-40,frameWidth/6,40);
                }
            }
        });
    }

    /**
     * Creates the title panel.
     */
    private void makeMainPanel(){

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(67,107,142));
        mainPanel.setPreferredSize(new Dimension(5*frameWidth/6, frameHeight));

        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Arial",Font.BOLD,20));
        nameLabel.setText("Matrix Calculator");
        nameLabel.setForeground(textColor);
        nameLabel.setBounds(mainPanelWidth/2-119, mainPanelHeight/2-20, 238, 22);

        authorLabel = new JLabel("Author: Neti");
        authorLabel.setFont(new Font("Arial",Font.BOLD,13));
        authorLabel.setForeground(textColor);
        authorLabel.setBounds(mainPanelWidth/2-119, mainPanelHeight/2+4, 140, 15);

        mainPanel.add(nameLabel);
        mainPanel.add(authorLabel);
        componentsCreated++;

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                if(currentPanel==mainPanel&&componentsCreated>6){
                    frameWidth = frame.getWidth();
                    frameHeight = frame.getHeight();
                    mainPanelWidth = mainPanel.getWidth();
                    mainPanelHeight =mainPanel.getHeight();
                    nameLabel.setBounds(mainPanelWidth/2-119, mainPanelHeight/2-20, 238, 22);
                    authorLabel.setBounds(mainPanelWidth/2-119, mainPanelHeight/2+4, 140, 15);
                }
            }
        });
        mainPanel.addComponentListener(new ComponentAdapter(){
            public void componentShown(ComponentEvent e) {
                frameWidth = frame.getWidth();
                frameHeight = frame.getHeight();
                mainPanelWidth = mainPanel.getWidth();
                mainPanelHeight = mainPanel.getHeight();
                nameLabel.setBounds(mainPanelWidth/2-119, mainPanelHeight/2-20, 238, 22);
                authorLabel.setBounds(mainPanelWidth/2-119, mainPanelHeight/2+4, 140, 15);
            }
        });
    }

    /**
     * Creates the panel where the sum of matrices will be calculated.
     */
    private void makeSumPanel(){
    // ===========================================================================================
    // ============================ Sum panel ====================================================

        sumPanel = new JPanel();
        sumPanel.setLayout(null);
        sumPanel.setBackground(mainColor);
        sumPanel.setPreferredSize(new Dimension(5*frameWidth/6,frameHeight));

        JLabel title = new JLabel("Matrix Addition");
        title.setFont(new Font("Arial",Font.BOLD,30));
        title.setForeground(textColor);
        title.setBounds(mainPanelWidth/2-108, 20, 216, 100);

        // Creation of the panels for matrices, buttons and combo boxes for the rows and columns of the matrices.

        sumRows = 3;
        sumColumns = 3;

        JPanel matrixPanelA = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                // Left vertical line.
                g.fillRect(sumColumns%2==1? 120-15-sumColumns/2*40-5 : 120-sumColumns/2*40 , sumRows%2==1 ? 100-10-sumRows/2*30-5 : 100-sumRows/2*30 , 2 ,sumRows*30);
                // Top left horizontal line.
                g.fillRect(sumColumns%2==1? 120-15-sumColumns/2*40-5 : 120-sumColumns/2*40 , sumRows%2==1 ? 100-10-sumRows/2*30-5 : 100-sumRows/2*30 , 8 , 2);
                // Bottom left horizontal line.
                g.fillRect(sumColumns%2==1? 120-15-sumColumns/2*40-5 : 120-sumColumns/2*40, sumRows%2==1 ? 100+10+sumRows/2*30+5 : 100+sumRows/2*30-2, 8, 2);
                // Right vertical line.
                g.fillRect(sumColumns%2==1? 120+15+sumColumns/2*40+3 : 120+sumColumns/2*40-2 , sumRows%2==1 ? 100-10-sumRows/2*30-5 : 100-sumRows/2*30 , 2 , sumRows*30);
                // Top right horizontal line.
                g.fillRect(sumColumns%2==1? 120+15+sumColumns/2*40+3-6 : 120+sumColumns/2*40-8 , sumRows%2==1 ? 100-10-sumRows/2*30-5 : 100-sumRows/2*30 , 8 , 2);
                // Bottom right horizontal line.
                g.fillRect(sumColumns%2==1? 120+15+sumColumns/2*40+3-6 : 120+sumColumns/2*40-8 , sumRows%2==1 ? 100+10+sumRows/2*30+5-2 : 100+sumRows/2*30-2 , 8 , 2);
            }
        };
        matrixPanelA.setLayout(new GridBagLayout());
        matrixPanelA.setBounds(mainPanelWidth/40, mainPanelHeight/2-100, 240, 200);
        matrixPanelA.setBackground(mainColor);

        JPanel matrixPanelB = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                // Left vertical line.
                g.fillRect(sumColumns%2==1? 120-15-sumColumns/2*40-5 : 120-sumColumns/2*40 , sumRows%2==1 ? 100-10-sumRows/2*30-5 : 100-sumRows/2*30 , 2 ,sumRows*30);
                // Top left horizontal line.
                g.fillRect(sumColumns%2==1? 120-15-sumColumns/2*40-5 : 120-sumColumns/2*40 , sumRows%2==1 ? 100-10-sumRows/2*30-5 : 100-sumRows/2*30 , 8 , 2);
                // Bottom left horizontal line.
                g.fillRect(sumColumns%2==1? 120-15-sumColumns/2*40-5 : 120-sumColumns/2*40, sumRows%2==1 ? 100+10+sumRows/2*30+5 : 100+sumRows/2*30-2, 8, 2);
                // Right vertical line.
                g.fillRect(sumColumns%2==1? 120+15+sumColumns/2*40+3 : 120+sumColumns/2*40-2 , sumRows%2==1 ? 100-10-sumRows/2*30-5 : 100-sumRows/2*30 , 2 , sumRows*30);
                // Top right horizontal line.
                g.fillRect(sumColumns%2==1? 120+15+sumColumns/2*40+3-6 : 120+sumColumns/2*40-8 , sumRows%2==1 ? 100-10-sumRows/2*30-5 : 100-sumRows/2*30 , 8 , 2);
                // Bottom right horizontal line.
                g.fillRect(sumColumns%2==1? 120+15+sumColumns/2*40+3-6 : 120+sumColumns/2*40-8 , sumRows%2==1 ? 100+10+sumRows/2*30+5-2 : 100+sumRows/2*30-2 , 8 , 2);
            }
        };
        matrixPanelB.setLayout(new GridBagLayout());
        matrixPanelB.setBounds(mainPanelWidth/2-120, mainPanelHeight/2-100, 240, 200);
        matrixPanelB.setBackground(mainColor);

        JPanel matrixPanelResult = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                // Left vertical line.
                g.fillRect(sumColumns%2==1? 120-15-sumColumns/2*40-5 : 120-sumColumns/2*40 , sumRows%2==1 ? 100-10-sumRows/2*30-5 : 100-sumRows/2*30 , 2 ,sumRows*30);
                // Top left horizontal line.
                g.fillRect(sumColumns%2==1? 120-15-sumColumns/2*40-5 : 120-sumColumns/2*40 , sumRows%2==1 ? 100-10-sumRows/2*30-5 : 100-sumRows/2*30 , 8 , 2);
                // Bottom left horizontal line.
                g.fillRect(sumColumns%2==1? 120-15-sumColumns/2*40-5 : 120-sumColumns/2*40, sumRows%2==1 ? 100+10+sumRows/2*30+5 : 100+sumRows/2*30-2, 8, 2);
                // Right vertical line.
                g.fillRect(sumColumns%2==1? 120+15+sumColumns/2*40+3 : 120+sumColumns/2*40-2 , sumRows%2==1 ? 100-10-sumRows/2*30-5 : 100-sumRows/2*30 , 2 , sumRows*30);
                // Top right horizontal line.
                g.fillRect(sumColumns%2==1? 120+15+sumColumns/2*40+3-6 : 120+sumColumns/2*40-8 , sumRows%2==1 ? 100-10-sumRows/2*30-5 : 100-sumRows/2*30 , 8 , 2);
                // Bottom right horizontal line.
                g.fillRect(sumColumns%2==1? 120+15+sumColumns/2*40+3-6 : 120+sumColumns/2*40-8 , sumRows%2==1 ? 100+10+sumRows/2*30+5-2 : 100+sumRows/2*30-2 , 8 , 2);
            }
        };
        matrixPanelResult.setLayout(new GridBagLayout());
        matrixPanelResult.setBounds(mainPanelWidth-260,mainPanelHeight/2-100,240,200);
        matrixPanelResult.setBackground(mainColor);

        JPanel plusSignPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                g.fillRect(0, 13, 30, 5);
                g.fillRect(12, 0, 5, 30);
            }
        };
        plusSignPanel.setLayout(null);
        plusSignPanel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
        plusSignPanel.setBackground(mainColor);

        JPanel equalSignPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                g.fillRect(0, 5, 30, 5);
                g.fillRect(0, 20, 30, 5);
            }
        };
        equalSignPanel.setLayout(null);
        equalSignPanel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
        equalSignPanel.setBackground(mainColor);

        JLabel rowLabel = new JLabel("Rows");
        rowLabel.setFont(new Font("Arial",Font.BOLD,13));
        rowLabel.setForeground(textColor);
        rowLabel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-85, mainPanelHeight/2-150, 100, 30);

        JLabel columnLabel = new JLabel("Columns");
        columnLabel.setFont(new Font("Arial",Font.BOLD,13));
        columnLabel.setForeground(textColor);
        columnLabel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-100, mainPanelHeight/2-150, 100, 30);

        JLabel warningLabel = new JLabel("Error: The data was not entered correctly!");
        warningLabel.setBounds(mainPanelWidth/2-167,mainPanelHeight-mainPanelHeight/6,334,100);
        warningLabel.setForeground(Color.red);
        warningLabel.setFont(new Font("Arial",Font.BOLD,17));
        
        Integer[] nums = {1,2,3,4,5,6};
        JComboBox<Integer> rowComboBox = new JComboBox<>(nums);
        rowComboBox.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-25,mainPanelHeight/2-150,50,30);
        JComboBox<Integer> columnComboBox = new JComboBox<>(nums);
        columnComboBox.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-25,mainPanelHeight/2-150,50,30);
        rowComboBox.setSelectedItem(3);
        columnComboBox.setSelectedItem(3);

        
        GridBagConstraints gbcTemp = new GridBagConstraints();
        gbcTemp.insets = new Insets(5,5,5,5);
        sumTextFieldsA = new JTextField[sumRows][sumColumns];
        sumTextFieldsB = new JTextField[sumRows][sumColumns];
        sumTextFieldsR = new JTextField[sumRows][sumColumns];
        for(int i = 0;i<sumTextFieldsA.length;i++){
        for (int j = 0; j < sumTextFieldsA[0].length; j++) {
            sumTextFieldsA[i][j] = new JTextField();
            sumTextFieldsA[i][j].setPreferredSize(new Dimension(30,20));
            sumTextFieldsA[i][j].setBorder(null);
            sumTextFieldsB[i][j] = new JTextField();
            sumTextFieldsB[i][j].setPreferredSize(new Dimension(30,20));
            sumTextFieldsB[i][j].setBorder(null);
            sumTextFieldsR[i][j] = new JTextField();
            sumTextFieldsR[i][j].setPreferredSize(new Dimension(30,20));
            sumTextFieldsR[i][j].setBorder(null);
            sumTextFieldsR[i][j].setBackground(new Color(163,184,204));
            sumTextFieldsR[i][j].setEditable(false);
            gbcTemp.gridx=j;
            gbcTemp.gridy=i;
            matrixPanelA.add(sumTextFieldsA[i][j],gbcTemp);
            matrixPanelB.add(sumTextFieldsB[i][j],gbcTemp);
            matrixPanelResult.add(sumTextFieldsR[i][j],gbcTemp);
            }
        }

        rowComboBox.addActionListener(e -> {
            matrixPanelA.removeAll();
            matrixPanelA.revalidate();
            matrixPanelA.repaint();
            matrixPanelB.removeAll();
            matrixPanelB.revalidate();
            matrixPanelB.repaint();
            matrixPanelResult.removeAll();
            matrixPanelResult.revalidate();
            matrixPanelResult.repaint();
            sumPanel.remove(warningLabel);
            sumPanel.revalidate();
            sumPanel.repaint();

            sumRows = (Integer)rowComboBox.getSelectedItem();
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            sumTextFieldsA = new JTextField[sumRows][sumColumns];
            sumTextFieldsB = new JTextField[sumRows][sumColumns];
            sumTextFieldsR = new JTextField[sumRows][sumColumns];
            for(int i = 0;i<sumTextFieldsA.length;i++){
                for (int j = 0; j < sumTextFieldsA[0].length; j++) {
                    sumTextFieldsA[i][j] = new JTextField();
                    sumTextFieldsA[i][j].setPreferredSize(new Dimension(30,20));
                    sumTextFieldsA[i][j].setBorder(null);
                    sumTextFieldsB[i][j] = new JTextField();
                    sumTextFieldsB[i][j].setPreferredSize(new Dimension(30,20));
                    sumTextFieldsB[i][j].setBorder(null);
                    sumTextFieldsR[i][j] = new JTextField();
                    sumTextFieldsR[i][j].setPreferredSize(new Dimension(30,20));
                    sumTextFieldsR[i][j].setBorder(null);
                    sumTextFieldsR[i][j].setBackground(new Color(163,184,204));
                    sumTextFieldsR[i][j].setEditable(false);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelA.add(sumTextFieldsA[i][j],gbc);
                    matrixPanelB.add(sumTextFieldsB[i][j],gbc);
                    matrixPanelResult.add(sumTextFieldsR[i][j],gbc);
                }
            }
        });
        columnComboBox.addActionListener(e -> {
            matrixPanelA.removeAll();
            matrixPanelA.revalidate();
            matrixPanelA.repaint();
            matrixPanelB.removeAll();
            matrixPanelB.revalidate();
            matrixPanelB.repaint();
            matrixPanelResult.removeAll();
            matrixPanelResult.revalidate();
            matrixPanelResult.repaint();
            sumPanel.remove(warningLabel);
            sumPanel.revalidate();
            sumPanel.repaint();

            sumColumns = (Integer)columnComboBox.getSelectedItem();
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            sumTextFieldsA = new JTextField[sumRows][sumColumns];
            sumTextFieldsB = new JTextField[sumRows][sumColumns];
            sumTextFieldsR = new JTextField[sumRows][sumColumns];
            for(int i = 0;i<sumTextFieldsA.length;i++){
                for (int j = 0; j < sumTextFieldsA[0].length; j++) {
                    sumTextFieldsA[i][j] = new JTextField();
                    sumTextFieldsA[i][j].setPreferredSize(new Dimension(30,20));
                    sumTextFieldsA[i][j].setBorder(null);
                    sumTextFieldsB[i][j] = new JTextField();
                    sumTextFieldsB[i][j].setPreferredSize(new Dimension(30,20));
                    sumTextFieldsB[i][j].setBorder(null);
                    sumTextFieldsR[i][j] = new JTextField();
                    sumTextFieldsR[i][j].setPreferredSize(new Dimension(30,20));
                    sumTextFieldsR[i][j].setBorder(null);
                    sumTextFieldsR[i][j].setBackground(new Color(163,184,204));
                    sumTextFieldsR[i][j].setEditable(false);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelA.add(sumTextFieldsA[i][j],gbc);
                    matrixPanelB.add(sumTextFieldsB[i][j],gbc);
                    matrixPanelResult.add(sumTextFieldsR[i][j],gbc);
                }
            }
        });

        sumResultButton = new JButton("Calculate");
        sumResultButton.setBounds(mainPanelWidth/2-50,matrixPanelB.getY()+matrixPanelB.getHeight()+30, 100, 50);
        sumResultButton.setFont(new Font("Arial",Font.BOLD,15));
        sumResultButton.setFocusable(false);
        sumResultButton.addActionListener(e -> {

            boolean test = true;
            for (int i = 0; i < sumTextFieldsA.length; i++) {
                for (int j = 0; j < sumTextFieldsA[0].length; j++) {
                    test = test && (checkDouble(sumTextFieldsA[i][j].getText()));
                    test = test && (checkDouble(sumTextFieldsB[i][j].getText()));
                }
            }

            if(test){
                sumPanel.remove(warningLabel);
                sumPanel.revalidate();
                sumPanel.repaint();
                
                double[][] matrixA = new double[sumTextFieldsA.length][sumTextFieldsA[0].length];
                double[][] matrixB = new double[sumTextFieldsB.length][sumTextFieldsB[0].length];
                for (int i = 0; i < sumTextFieldsA.length; i++) {
                    for (int j = 0; j < sumTextFieldsA[0].length; j++) {
                        matrixA[i][j] = Double.valueOf(sumTextFieldsA[i][j].getText());
                        matrixB[i][j] = Double.valueOf(sumTextFieldsB[i][j].getText());
                    }
                }

                DecimalFormat doubleFormat = new DecimalFormat("#.###");
                DecimalFormat intFormat = new DecimalFormat("#");
                double[][] resultMatrix = logic.matrixSum(matrixA, matrixB);
                for (int i = 0; i < resultMatrix.length; i++)
                    for (int j = 0; j < resultMatrix[0].length; j++)
                        sumTextFieldsR[i][j].setText(resultMatrix[i][j]%1==0?intFormat.format(resultMatrix[i][j]):""+doubleFormat.format(resultMatrix[i][j]));
            }
            else{
                sumPanel.add(warningLabel);
                sumPanel.revalidate();
                sumPanel.repaint();
            }
        });

        sumPanel.add(title);
        sumPanel.add(rowComboBox);
        sumPanel.add(rowLabel);
        sumPanel.add(columnComboBox);
        sumPanel.add(columnLabel);
        sumPanel.add(matrixPanelA);
        sumPanel.add(matrixPanelB);
        sumPanel.add(matrixPanelResult);
        sumPanel.add(sumResultButton);
        sumPanel.add(plusSignPanel);
        sumPanel.add(equalSignPanel);
        componentsCreated++;

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                if(currentPanel==sumPanel && componentsCreated>6){
                    frameWidth = frame.getWidth();
                    frameHeight = frame.getHeight();
                    mainPanelWidth = sumPanel.getWidth();
                    mainPanelHeight = sumPanel.getHeight();
                    title.setBounds(mainPanelWidth/2-108, 20, 216, 100);                
                    matrixPanelA.setBounds(mainPanelWidth/40, mainPanelHeight/2-100, 240, 200);
                    matrixPanelB.setBounds(mainPanelWidth/2-120, mainPanelHeight/2-100, 240, 200);
                    matrixPanelResult.setBounds(mainPanelWidth-240-mainPanelWidth/40,mainPanelHeight/2-100,240,200);
                    plusSignPanel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
                    equalSignPanel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
                    rowLabel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-85, mainPanelHeight/2-150, 100, 30);
                    rowComboBox.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-25,mainPanelHeight/2-150,50,30);
                    columnLabel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-100, mainPanelHeight/2-150, 100, 30);
                    columnComboBox.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-25,mainPanelHeight/2-150,50,30);
                    warningLabel.setBounds(mainPanelWidth/2-167,mainPanelHeight-mainPanelHeight/6,334,100);
                    sumResultButton.setBounds(mainPanelWidth/2-50,matrixPanelB.getY()+matrixPanelB.getHeight()+30, 100, 50);
                }
            }
        });

        sumPanel.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                frameWidth = frame.getWidth();
                frameHeight = frame.getHeight();
                mainPanelWidth = sumPanel.getWidth();
                mainPanelHeight = sumPanel.getHeight();
                title.setBounds(mainPanelWidth/2-108, 20, 216, 100);
                matrixPanelA.setBounds(mainPanelWidth/40, mainPanelHeight/2-100, 240, 200);
                matrixPanelB.setBounds(mainPanelWidth/2-120, mainPanelHeight/2-100, 240, 200);
                matrixPanelResult.setBounds(mainPanelWidth-240-mainPanelWidth/40,mainPanelHeight/2-100,240,200);
                plusSignPanel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
                equalSignPanel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
                rowLabel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-85, mainPanelHeight/2-150, 100, 30);
                rowComboBox.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-25,mainPanelHeight/2-150,50,30);
                columnLabel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-100, mainPanelHeight/2-150, 100, 30);
                columnComboBox.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-25,mainPanelHeight/2-150,50,30);
                warningLabel.setBounds(mainPanelWidth/2-167,mainPanelHeight-mainPanelHeight/6,334,100);
                sumResultButton.setBounds(mainPanelWidth/2-50,matrixPanelB.getY()+matrixPanelB.getHeight()+30, 100, 50);
            }
        });
        //===========================================================================================================================
        //===========================================================================================================================
    }

    /**
     * Creates the panel where the difference of matrices will be calculated.
     */
    private void makeDifferencePanel(){
    // ===============================================================================================
    // ============================ Difference Panel =================================================
        differencePanel = new JPanel();
        differencePanel.setLayout(null);
        differencePanel.setBackground(mainColor);
        differencePanel.setPreferredSize(new Dimension(5*frameWidth/6,frameHeight));


        JLabel title = new JLabel("Matrix Subtraction");
        title.setFont(new Font("Arial",Font.BOLD,30));
        title.setForeground(textColor);
        title.setBounds(mainPanelWidth/2-132, 20, 264, 100);
        
        // Creation of the panels for matrices, buttons and combo boxes for the rows and columns of the matrices.

        differenceRows = 3;
        differenceColumns = 3;

        JPanel matrixPanelA = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                // Left vertical line.
                g.fillRect(differenceColumns%2==1? 120-15-differenceColumns/2*40-5 : 120-differenceColumns/2*40 , differenceRows%2==1 ? 100-10-differenceRows/2*30-5 : 100-differenceRows/2*30 , 2 ,differenceRows*30);
                // Top left horizontal line.
                g.fillRect(differenceColumns%2==1? 120-15-differenceColumns/2*40-5 : 120-differenceColumns/2*40 , differenceRows%2==1 ? 100-10-differenceRows/2*30-5 : 100-differenceRows/2*30 , 8 , 2);
                // Bottom left horizontal line.
                g.fillRect(differenceColumns%2==1? 120-15-differenceColumns/2*40-5 : 120-differenceColumns/2*40, differenceRows%2==1 ? 100+10+differenceRows/2*30+5 : 100+differenceRows/2*30-2, 8, 2);
                // Right vertical line.
                g.fillRect(differenceColumns%2==1? 120+15+differenceColumns/2*40+3 : 120+differenceColumns/2*40-2 , differenceRows%2==1 ? 100-10-differenceRows/2*30-5 : 100-differenceRows/2*30 , 2 , differenceRows*30);
                // Top right horizontal line.
                g.fillRect(differenceColumns%2==1? 120+15+differenceColumns/2*40+3-6 : 120+differenceColumns/2*40-8 , differenceRows%2==1 ? 100-10-differenceRows/2*30-5 : 100-differenceRows/2*30 , 8 , 2);
                // Bottom right horizontal line.
                g.fillRect(differenceColumns%2==1? 120+15+differenceColumns/2*40+3-6 : 120+differenceColumns/2*40-8 , differenceRows%2==1 ? 100+10+differenceRows/2*30+5-2 : 100+differenceRows/2*30-2 , 8 , 2);
            }
        };
        matrixPanelA.setLayout(new GridBagLayout());
        matrixPanelA.setBounds(mainPanelWidth/40, mainPanelHeight/2-100, 240, 200);
        matrixPanelA.setBackground(mainColor);

        JPanel matrixPanelB = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                // Left vertical line.
                g.fillRect(differenceColumns%2==1? 120-15-differenceColumns/2*40-5 : 120-differenceColumns/2*40 , differenceRows%2==1 ? 100-10-differenceRows/2*30-5 : 100-differenceRows/2*30 , 2 ,differenceRows*30);
                // Top left horizontal line.
                g.fillRect(differenceColumns%2==1? 120-15-differenceColumns/2*40-5 : 120-differenceColumns/2*40 , differenceRows%2==1 ? 100-10-differenceRows/2*30-5 : 100-differenceRows/2*30 , 8 , 2);
                // Bottom left horizontal line.
                g.fillRect(differenceColumns%2==1? 120-15-differenceColumns/2*40-5 : 120-differenceColumns/2*40, differenceRows%2==1 ? 100+10+differenceRows/2*30+5 : 100+differenceRows/2*30-2, 8, 2);
                // Right vertical line.
                g.fillRect(differenceColumns%2==1? 120+15+differenceColumns/2*40+3 : 120+differenceColumns/2*40-2 , differenceRows%2==1 ? 100-10-differenceRows/2*30-5 : 100-differenceRows/2*30 , 2 , differenceRows*30);
                // Top right horizontal line.
                g.fillRect(differenceColumns%2==1? 120+15+differenceColumns/2*40+3-6 : 120+differenceColumns/2*40-8 , differenceRows%2==1 ? 100-10-differenceRows/2*30-5 : 100-differenceRows/2*30 , 8 , 2);
                // Bottom right horizontal line.
                g.fillRect(differenceColumns%2==1? 120+15+differenceColumns/2*40+3-6 : 120+differenceColumns/2*40-8 , differenceRows%2==1 ? 100+10+differenceRows/2*30+5-2 : 100+differenceRows/2*30-2 , 8 , 2);
            }
        };
        matrixPanelB.setLayout(new GridBagLayout());
        matrixPanelB.setBounds(mainPanelWidth/2-120, mainPanelHeight/2-100, 240, 200);
        matrixPanelB.setBackground(mainColor);

        JPanel matrixPanelResult = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                // Left vertical line.
                g.fillRect(differenceColumns%2==1? 120-15-differenceColumns/2*40-5 : 120-differenceColumns/2*40 , differenceRows%2==1 ? 100-10-differenceRows/2*30-5 : 100-differenceRows/2*30 , 2 ,differenceRows*30);
                // Top left horizontal line.
                g.fillRect(differenceColumns%2==1? 120-15-differenceColumns/2*40-5 : 120-differenceColumns/2*40 , differenceRows%2==1 ? 100-10-differenceRows/2*30-5 : 100-differenceRows/2*30 , 8 , 2);
                // Bottom left horizontal line.
                g.fillRect(differenceColumns%2==1? 120-15-differenceColumns/2*40-5 : 120-differenceColumns/2*40, differenceRows%2==1 ? 100+10+differenceRows/2*30+5 : 100+differenceRows/2*30-2, 8, 2);
                // Right vertical line.
                g.fillRect(differenceColumns%2==1? 120+15+differenceColumns/2*40+3 : 120+differenceColumns/2*40-2 , differenceRows%2==1 ? 100-10-differenceRows/2*30-5 : 100-differenceRows/2*30 , 2 , differenceRows*30);
                // Top right horizontal line.
                g.fillRect(differenceColumns%2==1? 120+15+differenceColumns/2*40+3-6 : 120+differenceColumns/2*40-8 , differenceRows%2==1 ? 100-10-differenceRows/2*30-5 : 100-differenceRows/2*30 , 8 , 2);
                // Bottom right horizontal line.
                g.fillRect(differenceColumns%2==1? 120+15+differenceColumns/2*40+3-6 : 120+differenceColumns/2*40-8 , differenceRows%2==1 ? 100+10+differenceRows/2*30+5-2 : 100+differenceRows/2*30-2 , 8 , 2);
            }
        };
        matrixPanelResult.setLayout(new GridBagLayout());
        matrixPanelResult.setBounds(mainPanelWidth-260,mainPanelHeight/2-100,240,200);
        matrixPanelResult.setBackground(mainColor);

        JPanel minusSignPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                g.fillRect(0, 12, 30, 6);
            }
        };
        minusSignPanel.setLayout(null);
        minusSignPanel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
        minusSignPanel.setBackground(mainColor);

        JPanel equalSignPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                g.fillRect(0, 5, 30, 5);
                g.fillRect(0, 20, 30, 5);
            }
        };
        equalSignPanel.setLayout(null);
        equalSignPanel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
        equalSignPanel.setBackground(mainColor);

        JLabel rowLabel = new JLabel("Rows");
        rowLabel.setFont(new Font("Arial",Font.BOLD,13));
        rowLabel.setForeground(textColor);
        rowLabel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-85, mainPanelHeight/2-150, 100, 30);
        
        JLabel columnLabel = new JLabel("Columns");
        columnLabel.setFont(new Font("Arial",Font.BOLD,13));
        columnLabel.setForeground(textColor);
        columnLabel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-100, mainPanelHeight/2-150, 100, 30);

        JLabel warningLabel = new JLabel("Error: The data was not entered correctly!");
        warningLabel.setBounds(mainPanelWidth/2-167,mainPanelHeight-mainPanelHeight/6,334,100);
        warningLabel.setForeground(Color.red);
        warningLabel.setFont(new Font("Arial",Font.BOLD,17));
        
        Integer[] nums = {1,2,3,4,5,6};
        JComboBox<Integer> rowComboBox = new JComboBox<>(nums);
        rowComboBox.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-25,mainPanelHeight/2-150,50,30);
        JComboBox<Integer> columnComboBox = new JComboBox<>(nums);
        columnComboBox.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-25,mainPanelHeight/2-150,50,30);
        rowComboBox.setSelectedItem(3);
        columnComboBox.setSelectedItem(3);

        
        GridBagConstraints gbcTemp = new GridBagConstraints();
        gbcTemp.insets = new Insets(5,5,5,5);
        differenceTextFieldsA = new JTextField[differenceRows][differenceColumns];
        differenceTextFieldsB = new JTextField[differenceRows][differenceColumns];
        differenceTextFieldsR = new JTextField[differenceRows][differenceColumns];
        for(int i = 0;i<differenceTextFieldsA.length;i++){
            for (int j = 0; j < differenceTextFieldsA[0].length; j++) {
                differenceTextFieldsA[i][j] = new JTextField();
                differenceTextFieldsA[i][j].setPreferredSize(new Dimension(30,20));
                differenceTextFieldsA[i][j].setBorder(null);
                differenceTextFieldsB[i][j] = new JTextField();
                differenceTextFieldsB[i][j].setPreferredSize(new Dimension(30,20));
                differenceTextFieldsB[i][j].setBorder(null);
                differenceTextFieldsR[i][j] = new JTextField();
                differenceTextFieldsR[i][j].setPreferredSize(new Dimension(30,20));
                differenceTextFieldsR[i][j].setBorder(null);
                differenceTextFieldsR[i][j].setBackground(new Color(163,184,204));
                differenceTextFieldsR[i][j].setEditable(false);
                gbcTemp.gridx=j;
                gbcTemp.gridy=i;
                matrixPanelA.add(differenceTextFieldsA[i][j],gbcTemp);
                matrixPanelB.add(differenceTextFieldsB[i][j],gbcTemp);
                matrixPanelResult.add(differenceTextFieldsR[i][j],gbcTemp);
            }
        }

        rowComboBox.addActionListener(e -> {
            matrixPanelA.removeAll();
            matrixPanelA.revalidate();
            matrixPanelA.repaint();
            matrixPanelB.removeAll();
            matrixPanelB.revalidate();
            matrixPanelB.repaint();
            matrixPanelResult.removeAll();
            matrixPanelResult.revalidate();
            matrixPanelResult.repaint();
            differencePanel.remove(warningLabel);
            differencePanel.revalidate();
            differencePanel.repaint();

            differenceRows = (Integer)rowComboBox.getSelectedItem();
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            differenceTextFieldsA = new JTextField[differenceRows][differenceColumns];
            differenceTextFieldsB = new JTextField[differenceRows][differenceColumns];
            differenceTextFieldsR = new JTextField[differenceRows][differenceColumns];
            for(int i = 0;i<differenceTextFieldsA.length;i++){
                for (int j = 0; j < differenceTextFieldsA[0].length; j++) {
                    differenceTextFieldsA[i][j] = new JTextField();
                    differenceTextFieldsA[i][j].setPreferredSize(new Dimension(30,20));
                    differenceTextFieldsA[i][j].setBorder(null);
                    differenceTextFieldsB[i][j] = new JTextField();
                    differenceTextFieldsB[i][j].setPreferredSize(new Dimension(30,20));
                    differenceTextFieldsB[i][j].setBorder(null);
                    differenceTextFieldsR[i][j] = new JTextField();
                    differenceTextFieldsR[i][j].setPreferredSize(new Dimension(30,20));
                    differenceTextFieldsR[i][j].setBorder(null);
                    differenceTextFieldsR[i][j].setBackground(new Color(163,184,204));
                    differenceTextFieldsR[i][j].setEditable(false);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelA.add(differenceTextFieldsA[i][j],gbc);
                    matrixPanelB.add(differenceTextFieldsB[i][j],gbc);
                    matrixPanelResult.add(differenceTextFieldsR[i][j],gbc);
                }
            }
        });
        columnComboBox.addActionListener(e -> {
            matrixPanelA.removeAll();
            matrixPanelA.revalidate();
            matrixPanelA.repaint();
            matrixPanelB.removeAll();
            matrixPanelB.revalidate();
            matrixPanelB.repaint();
            matrixPanelResult.removeAll();
            matrixPanelResult.revalidate();
            matrixPanelResult.repaint();
            differencePanel.remove(warningLabel);
            differencePanel.revalidate();
            differencePanel.repaint();

            differenceColumns = (Integer)columnComboBox.getSelectedItem();
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            differenceTextFieldsA = new JTextField[differenceRows][differenceColumns];
            differenceTextFieldsB = new JTextField[differenceRows][differenceColumns];
            differenceTextFieldsR = new JTextField[differenceRows][differenceColumns];
            for(int i = 0;i<differenceTextFieldsA.length;i++){
                for (int j = 0; j < differenceTextFieldsA[0].length; j++) {
                    differenceTextFieldsA[i][j] = new JTextField();
                    differenceTextFieldsA[i][j].setPreferredSize(new Dimension(30,20));
                    differenceTextFieldsA[i][j].setBorder(null);
                    differenceTextFieldsB[i][j] = new JTextField();
                    differenceTextFieldsB[i][j].setPreferredSize(new Dimension(30,20));
                    differenceTextFieldsB[i][j].setBorder(null);
                    differenceTextFieldsR[i][j] = new JTextField();
                    differenceTextFieldsR[i][j].setPreferredSize(new Dimension(30,20));
                    differenceTextFieldsR[i][j].setBorder(null);
                    differenceTextFieldsR[i][j].setBackground(new Color(163,184,204));
                    differenceTextFieldsR[i][j].setEditable(false);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelA.add(differenceTextFieldsA[i][j],gbc);
                    matrixPanelB.add(differenceTextFieldsB[i][j],gbc);
                    matrixPanelResult.add(differenceTextFieldsR[i][j],gbc);
                }
            }
        });

        differenceResultButton = new JButton("Calculate");
        differenceResultButton.setBounds(mainPanelWidth/2-50,matrixPanelB.getY()+matrixPanelB.getHeight()+30, 100, 50);
        differenceResultButton.setFont(new Font("Arial",Font.BOLD,15));
        differenceResultButton.setFocusable(false);
        differenceResultButton.addActionListener(e -> {

            boolean test = true;
            for (int i = 0; i < differenceTextFieldsA.length; i++) {
                for (int j = 0; j < differenceTextFieldsA[0].length; j++) {
                    test = test && (checkDouble(differenceTextFieldsA[i][j].getText()));
                    test = test && (checkDouble(differenceTextFieldsB[i][j].getText()));
                }
            }

            if(test){
                differencePanel.remove(warningLabel);
                differencePanel.revalidate();
                differencePanel.repaint();
                
                double[][] matrixA = new double[differenceTextFieldsA.length][differenceTextFieldsA[0].length];
                double[][] matrixB = new double[differenceTextFieldsB.length][differenceTextFieldsB[0].length];
                for (int i = 0; i < differenceTextFieldsA.length; i++) {
                    for (int j = 0; j < differenceTextFieldsA[0].length; j++) {
                        matrixA[i][j] = Double.valueOf(differenceTextFieldsA[i][j].getText());
                        matrixB[i][j] = Double.valueOf(differenceTextFieldsB[i][j].getText());
                    }
                }

                DecimalFormat doubleFormat = new DecimalFormat("#.###");
                DecimalFormat intFormat = new DecimalFormat("#");
                double[][] resultMatrix = logic.matrixDifference(matrixA, matrixB);
                for (int i = 0; i < resultMatrix.length; i++)
                    for (int j = 0; j < resultMatrix[0].length; j++)
                        differenceTextFieldsR[i][j].setText(resultMatrix[i][j]%1==0?intFormat.format(resultMatrix[i][j]):""+doubleFormat.format(resultMatrix[i][j]));
            }
            else{
                differencePanel.add(warningLabel);
                differencePanel.revalidate();
                differencePanel.repaint();
            }
        });

        differencePanel.add(title);
        differencePanel.add(rowComboBox);
        differencePanel.add(rowLabel);
        differencePanel.add(columnComboBox);
        differencePanel.add(columnLabel);
        differencePanel.add(matrixPanelA);
        differencePanel.add(matrixPanelB);
        differencePanel.add(matrixPanelResult);
        differencePanel.add(differenceResultButton);
        differencePanel.add(minusSignPanel);
        differencePanel.add(equalSignPanel);
        componentsCreated++;

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                if(currentPanel==differencePanel && componentsCreated>6){
                    frameWidth = frame.getWidth();
                    frameHeight = frame.getHeight();
                    mainPanelWidth = differencePanel.getWidth();
                    mainPanelHeight = differencePanel.getHeight();
                    title.setBounds(mainPanelWidth/2-132, 20, 264, 100);
                    matrixPanelA.setBounds(mainPanelWidth/40, mainPanelHeight/2-100, 240, 200);
                    matrixPanelB.setBounds(mainPanelWidth/2-120, mainPanelHeight/2-100, 240, 200);
                    matrixPanelResult.setBounds(mainPanelWidth-240-mainPanelWidth/40,mainPanelHeight/2-100,240,200);
                    minusSignPanel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
                    equalSignPanel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
                    rowLabel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-85, mainPanelHeight/2-150, 100, 30);
                    rowComboBox.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-25,mainPanelHeight/2-150,50,30);
                    columnLabel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-100, mainPanelHeight/2-150, 100, 30);
                    columnComboBox.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-25,mainPanelHeight/2-150,50,30);
                    warningLabel.setBounds(mainPanelWidth/2-167,mainPanelHeight-mainPanelHeight/6,334,100);
                    differenceResultButton.setBounds(mainPanelWidth/2-50,matrixPanelB.getY()+matrixPanelB.getHeight()+30, 100, 50);
                }
            }
        });

        differencePanel.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e){
                frameWidth = frame.getWidth();
                frameHeight = frame.getHeight();
                mainPanelWidth = differencePanel.getWidth();
                mainPanelHeight = differencePanel.getHeight();
                title.setBounds(mainPanelWidth/2-132, 20, 264, 100);
                matrixPanelA.setBounds(mainPanelWidth/40, mainPanelHeight/2-100, 240, 200);
                matrixPanelB.setBounds(mainPanelWidth/2-120, mainPanelHeight/2-100, 240, 200);
                matrixPanelResult.setBounds(mainPanelWidth-240-mainPanelWidth/40,mainPanelHeight/2-100,240,200);
                minusSignPanel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
                equalSignPanel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
                rowLabel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-85, mainPanelHeight/2-150, 100, 30);
                rowComboBox.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-25,mainPanelHeight/2-150,50,30);
                columnLabel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-100, mainPanelHeight/2-150, 100, 30);
                columnComboBox.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-25,mainPanelHeight/2-150,50,30);
                warningLabel.setBounds(mainPanelWidth/2-167,mainPanelHeight-mainPanelHeight/6,334,100);
                differenceResultButton.setBounds(mainPanelWidth/2-50,matrixPanelB.getY()+matrixPanelB.getHeight()+30, 100, 50);
            }
        });
        // =========================================================================================================================
        // =========================================================================================================================
    }

    /**
     * Creates the panel where the multiplication of matrices will be calculated.
     */
    private void makeMultiplicationPanel(){
        //=================================================================================================
        //============================= Multiplication Panel ==============================================
        
        multiplicationPanel = new JPanel();
        multiplicationPanel.setLayout(null);
        multiplicationPanel.setBackground(mainColor);
        multiplicationPanel.setPreferredSize(new Dimension(5*frameWidth/6,frameHeight));
        
        JLabel title = new JLabel("Matrix Multiplication");
        title.setFont(new Font("Arial",Font.BOLD,30));
        title.setForeground(textColor);
        title.setBounds(mainPanelWidth/2-144, 20, 288, 100);
        
        // Creation of the panels for matrices, buttons and combo boxes for the rows and columns of the matrices.
        
        multiplicationRows = 3;
        multiplicationColumnsNRows = 3;
        multiplicationColumns = 3;
        
        JPanel matrixPanelA = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                // Left vertical line.
                g.fillRect(multiplicationColumnsNRows%2==1? 120-15-multiplicationColumnsNRows/2*40-5 : 120-multiplicationColumnsNRows/2*40 , multiplicationRows%2==1 ? 100-10-multiplicationRows/2*30-5 : 100-multiplicationRows/2*30 , 2 ,multiplicationRows*30);
                // Top left horizontal line.
                g.fillRect(multiplicationColumnsNRows%2==1? 120-15-multiplicationColumnsNRows/2*40-5 : 120-multiplicationColumnsNRows/2*40 , multiplicationRows%2==1 ? 100-10-multiplicationRows/2*30-5 : 100-multiplicationRows/2*30 , 8 , 2);
                // Bottom left horizontal line.
                g.fillRect(multiplicationColumnsNRows%2==1? 120-15-multiplicationColumnsNRows/2*40-5 : 120-multiplicationColumnsNRows/2*40, multiplicationRows%2==1 ? 100+10+multiplicationRows/2*30+5 : 100+multiplicationRows/2*30-2, 8, 2);
                // Right vertical line.
                g.fillRect(multiplicationColumnsNRows%2==1? 120+15+multiplicationColumnsNRows/2*40+3 : 120+multiplicationColumnsNRows/2*40-2 , multiplicationRows%2==1 ? 100-10-multiplicationRows/2*30-5 : 100-multiplicationRows/2*30 , 2 , multiplicationRows*30);
                // Top right horizontal line.
                g.fillRect(multiplicationColumnsNRows%2==1? 120+15+multiplicationColumnsNRows/2*40+3-6 : 120+multiplicationColumnsNRows/2*40-8 , multiplicationRows%2==1 ? 100-10-multiplicationRows/2*30-5 : 100-multiplicationRows/2*30 , 8 , 2);
                // Bottom right horizontal line.
                g.fillRect(multiplicationColumnsNRows%2==1? 120+15+multiplicationColumnsNRows/2*40+3-6 : 120+multiplicationColumnsNRows/2*40-8 , multiplicationRows%2==1 ? 100+10+multiplicationRows/2*30+5-2 : 100+multiplicationRows/2*30-2 , 8 , 2);
            }
        };
        matrixPanelA.setLayout(new GridBagLayout());
        matrixPanelA.setBounds(mainPanelWidth/40, mainPanelHeight/2-100, 240, 200);
        matrixPanelA.setBackground(mainColor);

        JPanel matrixPanelB = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                // Left vertical line.
                g.fillRect(multiplicationColumns%2==1? 120-15-multiplicationColumns/2*40-5 : 120-multiplicationColumns/2*40 , multiplicationColumnsNRows%2==1 ? 100-10-multiplicationColumnsNRows/2*30-5 : 100-multiplicationColumnsNRows/2*30 , 2 ,multiplicationColumnsNRows*30);
                // Top left horizontal line.
                g.fillRect(multiplicationColumns%2==1? 120-15-multiplicationColumns/2*40-5 : 120-multiplicationColumns/2*40 , multiplicationColumnsNRows%2==1 ? 100-10-multiplicationColumnsNRows/2*30-5 : 100-multiplicationColumnsNRows/2*30 , 8 , 2);
                // Bottom left horizontal line.
                g.fillRect(multiplicationColumns%2==1? 120-15-multiplicationColumns/2*40-5 : 120-multiplicationColumns/2*40, multiplicationColumnsNRows%2==1 ? 100+10+multiplicationColumnsNRows/2*30+5 : 100+multiplicationColumnsNRows/2*30-2, 8, 2);
                // Right vertical line.
                g.fillRect(multiplicationColumns%2==1? 120+15+multiplicationColumns/2*40+3 : 120+multiplicationColumns/2*40-2 , multiplicationColumnsNRows%2==1 ? 100-10-multiplicationColumnsNRows/2*30-5 : 100-multiplicationColumnsNRows/2*30 , 2 , multiplicationColumnsNRows*30);
                // Top right horizontal line.
                g.fillRect(multiplicationColumns%2==1? 120+15+multiplicationColumns/2*40+3-6 : 120+multiplicationColumns/2*40-8 , multiplicationColumnsNRows%2==1 ? 100-10-multiplicationColumnsNRows/2*30-5 : 100-multiplicationColumnsNRows/2*30 , 8 , 2);
                // Bottom right horizontal line.
                g.fillRect(multiplicationColumns%2==1? 120+15+multiplicationColumns/2*40+3-6 : 120+multiplicationColumns/2*40-8 , multiplicationColumnsNRows%2==1 ? 100+10+multiplicationColumnsNRows/2*30+5-2 : 100+multiplicationColumnsNRows/2*30-2 , 8 , 2);
            }
        };
        matrixPanelB.setLayout(new GridBagLayout());
        matrixPanelB.setBounds(mainPanelWidth/2-120, mainPanelHeight/2-100, 240, 200);
        matrixPanelB.setBackground(mainColor);

        JPanel matrixPanelResult = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                // Left vertical line.
                g.fillRect(multiplicationColumns%2==1? 120-15-multiplicationColumns/2*40-5 : 120-multiplicationColumns/2*40 , multiplicationRows%2==1 ? 100-10-multiplicationRows/2*30-5 : 100-multiplicationRows/2*30 , 2 ,multiplicationRows*30);
                // Top left horizontal line.
                g.fillRect(multiplicationColumns%2==1? 120-15-multiplicationColumns/2*40-5 : 120-multiplicationColumns/2*40 , multiplicationRows%2==1 ? 100-10-multiplicationRows/2*30-5 : 100-multiplicationRows/2*30 , 8 , 2);
                // Bottom left horizontal line.
                g.fillRect(multiplicationColumns%2==1? 120-15-multiplicationColumns/2*40-5 : 120-multiplicationColumns/2*40, multiplicationRows%2==1 ? 100+10+multiplicationRows/2*30+5 : 100+multiplicationRows/2*30-2, 8, 2);
                // Right vertical line.
                g.fillRect(multiplicationColumns%2==1? 120+15+multiplicationColumns/2*40+3 : 120+multiplicationColumns/2*40-2 , multiplicationRows%2==1 ? 100-10-multiplicationRows/2*30-5 : 100-multiplicationRows/2*30 , 2 , multiplicationRows*30);
                // Top right horizontal line.
                g.fillRect(multiplicationColumns%2==1? 120+15+multiplicationColumns/2*40+3-6 : 120+multiplicationColumns/2*40-8 , multiplicationRows%2==1 ? 100-10-multiplicationRows/2*30-5 : 100-multiplicationRows/2*30 , 8 , 2);
                // Bottom right horizontal line.
                g.fillRect(multiplicationColumns%2==1? 120+15+multiplicationColumns/2*40+3-6 : 120+multiplicationColumns/2*40-8 , multiplicationRows%2==1 ? 100+10+multiplicationRows/2*30+5-2 : 100+multiplicationRows/2*30-2 , 8 , 2);
            }
        };
        matrixPanelResult.setLayout(new GridBagLayout());
        matrixPanelResult.setBounds(mainPanelWidth-260,mainPanelHeight/2-100,240,200);
        matrixPanelResult.setBackground(mainColor);

        JPanel multiplicationSignPanel = new JPanel();
        multiplicationSignPanel.setLayout(null);
        multiplicationSignPanel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
        multiplicationSignPanel.setBackground(null);
        JLabel multiplicationSignLabel = new JLabel();
        multiplicationSignLabel.setIcon(new ImageIcon("./img/Multiplication Sign.png"));
        multiplicationSignLabel.setBounds(0,0,30,30);
        multiplicationSignPanel.add(multiplicationSignLabel);

        JPanel equalSignPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                g.fillRect(0, 5, 30, 5);
                g.fillRect(0, 20, 30, 5);
            }
        };
        equalSignPanel.setLayout(null);
        equalSignPanel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
        equalSignPanel.setBackground(mainColor);
        
        JLabel rowLabel = new JLabel("Rows");
        rowLabel.setFont(new Font("Arial",Font.BOLD,13));
        rowLabel.setForeground(textColor);
        rowLabel.setBounds(matrixPanelA.getX()+240-130, mainPanelHeight/2-150, 100, 30);
        
        JLabel columnNRowLabel = new JLabel("Rows/Columns");
        columnNRowLabel.setFont(new Font("Arial",Font.BOLD,13));
        columnNRowLabel.setForeground(textColor);
        columnNRowLabel.setBounds(matrixPanelB.getX()+240-80, mainPanelHeight/2-150, 156, 30);
        
        JLabel columnLabel = new JLabel("Columns");
        columnLabel.setFont(new Font("Arial",Font.BOLD,13));
        columnLabel.setForeground(textColor);
        columnLabel.setBounds(matrixPanelResult.getX()+240-150, mainPanelHeight/2-150, 100, 30);

        JLabel warningLabel = new JLabel("Error: The data was not entered correctly!");
        warningLabel.setBounds(mainPanelWidth/2-167,mainPanelHeight-mainPanelHeight/6,334,100);
        warningLabel.setForeground(Color.red);
        warningLabel.setFont(new Font("Arial",Font.BOLD,17));

        Integer[] nums = {1,2,3,4,5,6};
        JComboBox<Integer> rowComboBox = new JComboBox<>(nums);
        rowComboBox.setBounds(matrixPanelA.getX()+240-75,mainPanelHeight/2-150,50,30);
        JComboBox<Integer> columnNRowComboBox = new JComboBox<>(nums);
        columnNRowComboBox.setBounds(matrixPanelB.getX()+240-65,mainPanelHeight/2-150,50,30);
        JComboBox<Integer> columnComboBox = new JComboBox<>(nums);
        columnComboBox.setBounds(matrixPanelResult.getX()+240-75,mainPanelHeight/2-150,50,30);
        rowComboBox.setSelectedItem(3);
        columnNRowComboBox.setSelectedItem(3);
        columnComboBox.setSelectedItem(3);

        GridBagConstraints gbcTemp = new GridBagConstraints();
        gbcTemp.insets = new Insets(5,5,5,5);
        multiplicationTextFieldsA = new JTextField[multiplicationRows][multiplicationColumnsNRows];
        multiplicationTextFieldsB = new JTextField[multiplicationColumnsNRows][multiplicationColumns];
        multiplicationTextFieldsR = new JTextField[multiplicationRows][multiplicationColumns];
        for(int i = 0;i<multiplicationTextFieldsA.length;i++){
            for (int j = 0; j < multiplicationTextFieldsA[0].length; j++) {
                multiplicationTextFieldsA[i][j] = new JTextField();
                multiplicationTextFieldsA[i][j].setPreferredSize(new Dimension(30,20));
                multiplicationTextFieldsA[i][j].setBorder(null);
                gbcTemp.gridx=j;
                gbcTemp.gridy=i;
                matrixPanelA.add(multiplicationTextFieldsA[i][j],gbcTemp);
            }
        }
        for(int i = 0;i<multiplicationTextFieldsB.length;i++){
            for (int j = 0; j < multiplicationTextFieldsB[0].length; j++) {
                multiplicationTextFieldsB[i][j] = new JTextField();
                multiplicationTextFieldsB[i][j].setPreferredSize(new Dimension(30,20));
                multiplicationTextFieldsB[i][j].setBorder(null);
                gbcTemp.gridx=j;
                gbcTemp.gridy=i;
                matrixPanelB.add(multiplicationTextFieldsB[i][j],gbcTemp);
            }
        }
        for(int i = 0;i<multiplicationTextFieldsR.length;i++){
            for (int j = 0; j < multiplicationTextFieldsR[0].length; j++) {
                multiplicationTextFieldsR[i][j] = new JTextField();
                multiplicationTextFieldsR[i][j].setPreferredSize(new Dimension(30,20));
                multiplicationTextFieldsR[i][j].setBorder(null);
                multiplicationTextFieldsR[i][j].setBackground(new Color(163,184,204));
                multiplicationTextFieldsR[i][j].setEditable(false);
                gbcTemp.gridx=j;
                gbcTemp.gridy=i;
                matrixPanelResult.add(multiplicationTextFieldsR[i][j],gbcTemp);
            }
        }

        rowComboBox.addActionListener(e -> {
            matrixPanelA.removeAll();
            matrixPanelA.revalidate();
            matrixPanelA.repaint();
            matrixPanelB.removeAll();
            matrixPanelB.revalidate();
            matrixPanelB.repaint();
            matrixPanelResult.removeAll();
            matrixPanelResult.revalidate();
            matrixPanelResult.repaint();
            multiplicationPanel.remove(warningLabel);
            multiplicationPanel.revalidate();
            multiplicationPanel.repaint();

            multiplicationRows = (Integer) rowComboBox.getSelectedItem();
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            multiplicationTextFieldsA = new JTextField[multiplicationRows][multiplicationColumnsNRows];
            multiplicationTextFieldsB = new JTextField[multiplicationColumnsNRows][multiplicationColumns];
            multiplicationTextFieldsR = new JTextField[multiplicationRows][multiplicationColumns];
            for(int i = 0;i<multiplicationTextFieldsA.length;i++){
                for (int j = 0; j < multiplicationTextFieldsA[0].length; j++) {
                    multiplicationTextFieldsA[i][j] = new JTextField();
                    multiplicationTextFieldsA[i][j].setPreferredSize(new Dimension(30,20));
                    multiplicationTextFieldsA[i][j].setBorder(null);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelA.add(multiplicationTextFieldsA[i][j],gbc);
                }
            }
            for(int i = 0;i<multiplicationTextFieldsB.length;i++){
                for (int j = 0; j < multiplicationTextFieldsB[0].length; j++) {
                    multiplicationTextFieldsB[i][j] = new JTextField();
                    multiplicationTextFieldsB[i][j].setPreferredSize(new Dimension(30,20));
                    multiplicationTextFieldsB[i][j].setBorder(null);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelB.add(multiplicationTextFieldsB[i][j],gbc);
                }
            }
            for(int i = 0;i<multiplicationTextFieldsR.length;i++){
                for (int j = 0; j < multiplicationTextFieldsR[0].length; j++) {
                    multiplicationTextFieldsR[i][j] = new JTextField();
                    multiplicationTextFieldsR[i][j].setPreferredSize(new Dimension(30,20));
                    multiplicationTextFieldsR[i][j].setBorder(null);
                    multiplicationTextFieldsR[i][j].setBackground(new Color(163,184,204));
                    multiplicationTextFieldsR[i][j].setEditable(false);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelResult.add(multiplicationTextFieldsR[i][j],gbc);
                }
            }
        });

        columnNRowComboBox.addActionListener(e -> {
            matrixPanelA.removeAll();
            matrixPanelA.revalidate();
            matrixPanelA.repaint();
            matrixPanelB.removeAll();
            matrixPanelB.revalidate();
            matrixPanelB.repaint();
            matrixPanelResult.removeAll();
            matrixPanelResult.revalidate();
            matrixPanelResult.repaint();
            multiplicationPanel.remove(warningLabel);
            multiplicationPanel.revalidate();
            multiplicationPanel.repaint();

            multiplicationColumnsNRows = (Integer) columnNRowComboBox.getSelectedItem();
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            multiplicationTextFieldsA = new JTextField[multiplicationRows][multiplicationColumnsNRows];
            multiplicationTextFieldsB = new JTextField[multiplicationColumnsNRows][multiplicationColumns];
            multiplicationTextFieldsR = new JTextField[multiplicationRows][multiplicationColumns];
            for(int i = 0;i<multiplicationTextFieldsA.length;i++){
                for (int j = 0; j < multiplicationTextFieldsA[0].length; j++) {
                    multiplicationTextFieldsA[i][j] = new JTextField();
                    multiplicationTextFieldsA[i][j].setPreferredSize(new Dimension(30,20));
                    multiplicationTextFieldsA[i][j].setBorder(null);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelA.add(multiplicationTextFieldsA[i][j],gbc);
                }
            }
            for(int i = 0;i<multiplicationTextFieldsB.length;i++){
                for (int j = 0; j < multiplicationTextFieldsB[0].length; j++) {
                    multiplicationTextFieldsB[i][j] = new JTextField();
                    multiplicationTextFieldsB[i][j].setPreferredSize(new Dimension(30,20));
                    multiplicationTextFieldsB[i][j].setBorder(null);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelB.add(multiplicationTextFieldsB[i][j],gbc);
                }
            }
            for(int i = 0;i<multiplicationTextFieldsR.length;i++){
                for (int j = 0; j < multiplicationTextFieldsR[0].length; j++) {
                    multiplicationTextFieldsR[i][j] = new JTextField();
                    multiplicationTextFieldsR[i][j].setPreferredSize(new Dimension(30,20));
                    multiplicationTextFieldsR[i][j].setBorder(null);
                    multiplicationTextFieldsR[i][j].setBackground(new Color(163,184,204));
                    multiplicationTextFieldsR[i][j].setEditable(false);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelResult.add(multiplicationTextFieldsR[i][j],gbc);
                }
            }
        });

        columnComboBox.addActionListener(e -> {
            matrixPanelA.removeAll();
            matrixPanelA.revalidate();
            matrixPanelA.repaint();
            matrixPanelB.removeAll();
            matrixPanelB.revalidate();
            matrixPanelB.repaint();
            matrixPanelResult.removeAll();
            matrixPanelResult.revalidate();
            matrixPanelResult.repaint();
            multiplicationPanel.remove(warningLabel);
            multiplicationPanel.revalidate();
            multiplicationPanel.repaint();

            multiplicationColumns = (Integer) columnComboBox.getSelectedItem();
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            multiplicationTextFieldsA = new JTextField[multiplicationRows][multiplicationColumnsNRows];
            multiplicationTextFieldsB = new JTextField[multiplicationColumnsNRows][multiplicationColumns];
            multiplicationTextFieldsR = new JTextField[multiplicationRows][multiplicationColumns];
            for(int i = 0;i<multiplicationTextFieldsA.length;i++){
                for (int j = 0; j < multiplicationTextFieldsA[0].length; j++) {
                    multiplicationTextFieldsA[i][j] = new JTextField();
                    multiplicationTextFieldsA[i][j].setPreferredSize(new Dimension(30,20));
                    multiplicationTextFieldsA[i][j].setBorder(null);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelA.add(multiplicationTextFieldsA[i][j],gbc);
                }
            }
            for(int i = 0;i<multiplicationTextFieldsB.length;i++){
                for (int j = 0; j < multiplicationTextFieldsB[0].length; j++) {
                    multiplicationTextFieldsB[i][j] = new JTextField();
                    multiplicationTextFieldsB[i][j].setPreferredSize(new Dimension(30,20));
                    multiplicationTextFieldsB[i][j].setBorder(null);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelB.add(multiplicationTextFieldsB[i][j],gbc);
                }
            }
            for(int i = 0;i<multiplicationTextFieldsR.length;i++){
                for (int j = 0; j < multiplicationTextFieldsR[0].length; j++) {
                    multiplicationTextFieldsR[i][j] = new JTextField();
                    multiplicationTextFieldsR[i][j].setPreferredSize(new Dimension(30,20));
                    multiplicationTextFieldsR[i][j].setBorder(null);
                    multiplicationTextFieldsR[i][j].setBackground(new Color(163,184,204));
                    multiplicationTextFieldsR[i][j].setEditable(false);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelResult.add(multiplicationTextFieldsR[i][j],gbc);
                }
            }
        });

        multiplicationResultButton = new JButton("Calculate");
        multiplicationResultButton.setBounds(mainPanelWidth/2-50,matrixPanelB.getY()+matrixPanelB.getHeight()+30, 100, 50);
        multiplicationResultButton.setFont(new Font("Arial",Font.BOLD,15));
        multiplicationResultButton.setFocusable(false);
        multiplicationResultButton.addActionListener(e -> {
            boolean test = true;
            for (int i = 0; i < multiplicationTextFieldsA.length; i++)
                for (int j = 0; j < multiplicationTextFieldsA[0].length; j++) 
                    test = test && (checkDouble(multiplicationTextFieldsA[i][j].getText()));

            for (int i = 0; i < multiplicationTextFieldsB.length; i++)
                for (int j = 0; j < multiplicationTextFieldsB[0].length; j++)
                    test = test && (checkDouble(multiplicationTextFieldsB[i][j].getText()));

            if(test){
                multiplicationPanel.remove(warningLabel);
                multiplicationPanel.revalidate();
                multiplicationPanel.repaint();
                
                double[][] matrixA = new double[multiplicationTextFieldsA.length][multiplicationTextFieldsA[0].length];
                double[][] matrixB = new double[multiplicationTextFieldsB.length][multiplicationTextFieldsB[0].length];
                for (int i = 0; i < multiplicationTextFieldsA.length; i++)
                    for (int j = 0; j < multiplicationTextFieldsA[0].length; j++)
                        matrixA[i][j] = Double.valueOf(multiplicationTextFieldsA[i][j].getText());

                for (int i = 0; i < multiplicationTextFieldsB.length; i++)
                    for (int j = 0; j < multiplicationTextFieldsB[0].length; j++)
                        matrixB[i][j] = Double.valueOf(multiplicationTextFieldsB[i][j].getText());

                DecimalFormat doubleFormat = new DecimalFormat("#.###");
                DecimalFormat intFormat = new DecimalFormat("#");
                double[][] resultMatrix = logic.matrixMultiplication(matrixA, matrixB);
                
                for (int i = 0; i < resultMatrix.length; i++)
                    for (int j = 0; j < resultMatrix[0].length; j++)
                    multiplicationTextFieldsR[i][j].setText(resultMatrix[i][j]%1==0?intFormat.format(resultMatrix[i][j]):""+doubleFormat.format(resultMatrix[i][j]));
            }
            else{
                multiplicationPanel.add(warningLabel);
                multiplicationPanel.revalidate();
                multiplicationPanel.repaint();
            }
        });

        multiplicationPanel.add(title);
        multiplicationPanel.add(rowComboBox);
        multiplicationPanel.add(rowLabel);
        multiplicationPanel.add(columnComboBox);
        multiplicationPanel.add(columnLabel);
        multiplicationPanel.add(columnNRowComboBox);
        multiplicationPanel.add(columnNRowLabel);
        multiplicationPanel.add(matrixPanelA);
        multiplicationPanel.add(matrixPanelB);
        multiplicationPanel.add(matrixPanelResult);
        multiplicationPanel.add(multiplicationResultButton);
        multiplicationPanel.add(multiplicationSignPanel);
        multiplicationPanel.add(equalSignPanel);
        componentsCreated++;

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                if(currentPanel==multiplicationPanel && componentsCreated>6){
                    frameWidth = frame.getWidth();
                    frameHeight = frame.getHeight();
                    mainPanelWidth = multiplicationPanel.getWidth();
                    mainPanelHeight = multiplicationPanel.getHeight();
                    title.setBounds(mainPanelWidth/2-144, 20, 288, 100);
                    matrixPanelA.setBounds(mainPanelWidth/40, mainPanelHeight/2-100, 240, 200);
                    matrixPanelB.setBounds(mainPanelWidth/2-120, mainPanelHeight/2-100, 240, 200);
                    matrixPanelResult.setBounds(mainPanelWidth-240-mainPanelWidth/40,mainPanelHeight/2-100,240,200);
                    multiplicationSignPanel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
                    equalSignPanel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
                    rowLabel.setBounds(matrixPanelA.getX()+240-130, mainPanelHeight/2-150, 100, 30);
                    rowComboBox.setBounds(matrixPanelA.getX()+240-75,mainPanelHeight/2-150,50,30);
                    columnNRowLabel.setBounds(matrixPanelB.getX()+240-180, mainPanelHeight/2-150, 156, 30);
                    columnNRowComboBox.setBounds(matrixPanelB.getX()+240-65,mainPanelHeight/2-150,50,30);
                    columnLabel.setBounds(matrixPanelResult.getX()+240-150, mainPanelHeight/2-150, 100, 30);
                    columnComboBox.setBounds(matrixPanelResult.getX()+240-75,mainPanelHeight/2-150,50,30);
                    warningLabel.setBounds(mainPanelWidth/2-167,mainPanelHeight-mainPanelHeight/6,334,100);
                    multiplicationResultButton.setBounds(mainPanelWidth/2-50,matrixPanelB.getY()+matrixPanelB.getHeight()+30, 100, 50);
                }
            }
        });

        multiplicationPanel.addComponentListener(new ComponentAdapter(){
            public void componentShown(ComponentEvent e){
                frameWidth = frame.getWidth();
                frameHeight = frame.getHeight();
                mainPanelWidth = multiplicationPanel.getWidth();
                mainPanelHeight = multiplicationPanel.getHeight();
                title.setBounds(mainPanelWidth/2-144, 20, 288, 100);
                matrixPanelA.setBounds(mainPanelWidth/40, mainPanelHeight/2-100, 240, 200);
                matrixPanelB.setBounds(mainPanelWidth/2-120, mainPanelHeight/2-100, 240, 200);
                matrixPanelResult.setBounds(mainPanelWidth-240-mainPanelWidth/40,mainPanelHeight/2-100,240,200);
                multiplicationSignPanel.setBounds((matrixPanelB.getX()+matrixPanelA.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
                equalSignPanel.setBounds((matrixPanelResult.getX()+matrixPanelB.getX()+240)/2-15,mainPanelHeight/2-15,30,30);
                rowLabel.setBounds(matrixPanelA.getX()+240-130, mainPanelHeight/2-150, 100, 30);
                rowComboBox.setBounds(matrixPanelA.getX()+240-75,mainPanelHeight/2-150,50,30);
                columnNRowLabel.setBounds(matrixPanelB.getX()+240-180, mainPanelHeight/2-150, 156, 30);
                columnNRowComboBox.setBounds(matrixPanelB.getX()+240-65,mainPanelHeight/2-150,50,30);
                columnLabel.setBounds(matrixPanelResult.getX()+240-150, mainPanelHeight/2-150, 100, 30);
                columnComboBox.setBounds(matrixPanelResult.getX()+240-75,mainPanelHeight/2-150,50,30);
                warningLabel.setBounds(mainPanelWidth/2-167,mainPanelHeight-mainPanelHeight/6,334,100);
                multiplicationResultButton.setBounds(mainPanelWidth/2-50,matrixPanelB.getY()+matrixPanelB.getHeight()+30, 100, 50);
            }
        });
        //===========================================================================================================================
        //===========================================================================================================================
    }

    /**
     * Creates the panel where the determinant of matrices will be calculated.
     */
    private void makeDeterminantPanel(){
        //==============================================================================================
        //============================== Determinant Panel =============================================
        determinantPanel = new JPanel();
        determinantPanel.setLayout(null);
        determinantPanel.setBackground(mainColor);
        determinantPanel.setPreferredSize(new Dimension(5*frameWidth/6,frameHeight));

        JLabel title = new JLabel("Matrix Determinant");
        title.setFont(new Font("Arial",Font.BOLD,30));
        title.setForeground(textColor);
        title.setBounds(mainPanelWidth/2-50-mainPanelWidth/10+50-136, 20, 272, 100);

        // Creation of the panel for the determinant, buttons and combo boxes for the rows of the determinant.
        determinantRows = 3;

        JPanel matrixPanelA = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                // Left vertical line.
                g.fillRect(determinantRows%2==1? 160-15-determinantRows/2*40-5 : 160-determinantRows/2*40 , determinantRows%2==1 ? 160-10-determinantRows/2*30-5 : 160-determinantRows/2*30 , 2 ,determinantRows*30);
                // Right vertical line.
                g.fillRect(determinantRows%2==1? 160+15+determinantRows/2*40+3 : 160+determinantRows/2*40-2 , determinantRows%2==1 ? 160-10-determinantRows/2*30-5 : 160-determinantRows/2*30 , 2 , determinantRows*30);
            }
        };
        matrixPanelA.setLayout(new GridBagLayout());
        matrixPanelA.setBounds(mainPanelWidth/2-160-mainPanelWidth/10, mainPanelHeight/2-130, 320, 320);
        matrixPanelA.setBackground(mainColor);

        JPanel equalSignPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                g.fillRect(0, 5, 30, 5);
                g.fillRect(0, 20, 30, 5);
            }
        };
        equalSignPanel.setLayout(null);
        equalSignPanel.setBackground(mainColor);
        equalSignPanel.setBounds((matrixPanelA.getX()+320+mainPanelWidth-200+mainPanelWidth/10)/2-15-mainPanelWidth/10,mainPanelHeight/2+30-15,30,30);

        JLabel resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial",Font.BOLD,30));
        resultLabel.setForeground(textColor);
        resultLabel.setBackground(Color.red);
        resultLabel.setBounds(mainPanelWidth-200-mainPanelWidth/10,equalSignPanel.getY(),200+mainPanelWidth/10,30);


        JLabel rowLabel = new JLabel("Rows");
        rowLabel.setFont(new Font("Arial",Font.BOLD,13));
        rowLabel.setForeground(textColor);
        rowLabel.setBounds(matrixPanelA.getX()+160-50, mainPanelHeight/2-150, 100, 30);

        JLabel warningLabel = new JLabel("Error: The data was not entered correctly!");
        warningLabel.setFont(new Font("Arial",Font.BOLD,17));
        warningLabel.setBounds(mainPanelWidth/2-167-mainPanelWidth/10,mainPanelHeight-mainPanelHeight/8,334,100);
        warningLabel.setForeground(Color.red);
        

        Integer[] nums = {1,2,3,4,5,6,7,8};
        JComboBox<Integer> rowComboBox = new JComboBox<>(nums);
        rowComboBox.setBounds(matrixPanelA.getX()+160+10,mainPanelHeight/2-150,50,30);
        rowComboBox.setSelectedItem(3);

        GridBagConstraints gbcTemp = new GridBagConstraints();
        gbcTemp.insets = new Insets(5,5,5,5);
        determinantTextFieldsA = new JTextField[determinantRows][determinantRows];
        for(int i = 0;i<determinantTextFieldsA.length;i++){
            for (int j = 0; j < determinantTextFieldsA[0].length; j++) {
                determinantTextFieldsA[i][j] = new JTextField();
                determinantTextFieldsA[i][j].setPreferredSize(new Dimension(30,20));
                determinantTextFieldsA[i][j].setBorder(null);
                gbcTemp.gridx=j;
                gbcTemp.gridy=i;
                matrixPanelA.add(determinantTextFieldsA[i][j],gbcTemp);
            }
        }

        rowComboBox.addActionListener(e -> {
            matrixPanelA.removeAll();
            matrixPanelA.revalidate();
            matrixPanelA.repaint();
            resultLabel.setText("");
            resultLabel.revalidate();
            resultLabel.repaint();
            determinantPanel.remove(warningLabel);
            determinantPanel.revalidate();
            determinantPanel.repaint();

            determinantRows = (Integer)rowComboBox.getSelectedItem();
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            determinantTextFieldsA = new JTextField[determinantRows][determinantRows];
            for(int i = 0;i<determinantTextFieldsA.length;i++){
                for (int j = 0; j < determinantTextFieldsA[0].length; j++) {
                    determinantTextFieldsA[i][j] = new JTextField();
                    determinantTextFieldsA[i][j].setPreferredSize(new Dimension(30,20));
                    determinantTextFieldsA[i][j].setBorder(null);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelA.add(determinantTextFieldsA[i][j],gbc);
                }
            }
        });
        determinantResultButton = new JButton("Calculate");
        determinantResultButton.setBounds(mainPanelWidth/2-50-mainPanelWidth/10,mainPanelHeight/2+160+15, 100, 50);
        determinantResultButton.setFont(new Font("Arial",Font.BOLD,15));
        determinantResultButton.setFocusable(false);
        determinantResultButton.addActionListener(e -> {

            boolean test = true;
            for (int i = 0; i < determinantTextFieldsA.length; i++)
                for (int j = 0; j < determinantTextFieldsA[0].length; j++)
                    test = test && (checkDouble(determinantTextFieldsA[i][j].getText()));

            if(test){
                determinantPanel.remove(warningLabel);
                determinantPanel.revalidate();
                determinantPanel.repaint();
                
                double[][] matrixA = new double[determinantTextFieldsA.length][determinantTextFieldsA[0].length];
                for (int i = 0; i < determinantTextFieldsA.length; i++)
                    for (int j = 0; j < determinantTextFieldsA[0].length; j++)
                        matrixA[i][j] = Double.valueOf(determinantTextFieldsA[i][j].getText());

                DecimalFormat doubleFormat = new DecimalFormat("#.#####");
                DecimalFormat intFormat = new DecimalFormat("#");
                double result = logic.matrixDeterminant(matrixA);
                resultLabel.setText(result%1==0?intFormat.format(result):""+doubleFormat.format(result));
            }
            else{
                determinantPanel.add(warningLabel);
                determinantPanel.revalidate();
                determinantPanel.repaint();
            }
        });

        determinantPanel.add(title);
        determinantPanel.add(matrixPanelA);
        determinantPanel.add(equalSignPanel);
        determinantPanel.add(resultLabel);
        determinantPanel.add(rowLabel);
        determinantPanel.add(rowComboBox);
        determinantPanel.add(determinantResultButton);
        componentsCreated++;

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                if(currentPanel==determinantPanel && componentsCreated>6){
                    frameWidth = frame.getWidth();
                    frameHeight = frame.getHeight();
                    mainPanelWidth = determinantPanel.getWidth();
                    mainPanelHeight = determinantPanel.getHeight();
                    title.setBounds(mainPanelWidth/2-50-mainPanelWidth/10+50-136, 20, 272, 100);
                    matrixPanelA.setBounds(mainPanelWidth/2-160-mainPanelWidth/10, mainPanelHeight/2-130, 320, 320);
                    equalSignPanel.setBounds((matrixPanelA.getX()+320+mainPanelWidth-200+mainPanelWidth/10)/2-15-mainPanelWidth/10,mainPanelHeight/2+30-15,30,30);
                    resultLabel.setBounds(mainPanelWidth-200-mainPanelWidth/10,equalSignPanel.getY(),200+mainPanelWidth/10,30);
                    rowLabel.setBounds(matrixPanelA.getX()+160-50, mainPanelHeight/2-150, 100, 30);
                    rowComboBox.setBounds(matrixPanelA.getX()+160+10,mainPanelHeight/2-150,50,30);
                    warningLabel.setBounds(mainPanelWidth/2-167-mainPanelWidth/10,mainPanelHeight-mainPanelHeight/8,334,100);
                    determinantResultButton.setBounds(mainPanelWidth/2-50-mainPanelWidth/10,mainPanelHeight/2+160+15, 100, 50);
                }
            }
        });

        determinantPanel.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                frameWidth = frame.getWidth();
                frameHeight = frame.getHeight();
                mainPanelWidth = determinantPanel.getWidth();
                mainPanelHeight = determinantPanel.getHeight();
                title.setBounds(mainPanelWidth/2-50-mainPanelWidth/10+50-136, 20, 272, 100);
                matrixPanelA.setBounds(mainPanelWidth/2-160-mainPanelWidth/10, mainPanelHeight/2-130, 320, 320);
                equalSignPanel.setBounds((matrixPanelA.getX()+320+mainPanelWidth-200+mainPanelWidth/10)/2-15-mainPanelWidth/10,mainPanelHeight/2+30-15,30,30);
                resultLabel.setBounds(mainPanelWidth-200-mainPanelWidth/10,equalSignPanel.getY(),200+mainPanelWidth/10,30);
                rowLabel.setBounds(matrixPanelA.getX()+160-50, mainPanelHeight/2-150, 100, 30);
                rowComboBox.setBounds(matrixPanelA.getX()+160+10,mainPanelHeight/2-150,50,30);
                warningLabel.setBounds(mainPanelWidth/2-167-mainPanelWidth/10,mainPanelHeight-mainPanelHeight/8,334,100);
                determinantResultButton.setBounds(mainPanelWidth/2-50-mainPanelWidth/10,mainPanelHeight/2+160+15, 100, 50);
            }
        });
    //================================================================================================================================================================
    //================================================================================================================================================================
    }


    /**
     * Creates the panel where the inverse of matrices will be calculated.
     */
    private void makeInversePanel(){
        //=======================================================================================
        //================================ Inverse Panel ========================================
        inversePanel = new JPanel();
        inversePanel.setLayout(null);
        inversePanel.setBackground(mainColor);
        inversePanel.setSize(5*frameWidth/6,frameHeight);

        JLabel title = new JLabel("Inverse Matrix");
        title.setFont(new Font("Arial",Font.BOLD,30));
        title.setForeground(textColor);
        title.setBounds(mainPanelWidth/2-102, 20, 204, 100);

        // Creation of the panels for matrices, buttons and combo boxes for the rows and columns of the matrices.
        inverseRows = 3;

        JPanel matrixPanelA = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                // Left vertical line.
                g.fillRect(inverseRows%2==1? 160-15-inverseRows/2*40-5 : 160-inverseRows/2*40 , inverseRows%2==1 ? 160-10-inverseRows/2*30-5 : 160-inverseRows/2*30 , 2 ,inverseRows*30);
                // Top left horizontal line.
                g.fillRect(inverseRows%2==1? 160-15-inverseRows/2*40-5 : 160-inverseRows/2*40 , inverseRows%2==1 ? 160-10-inverseRows/2*30-5 : 160-inverseRows/2*30 , 8 , 2);
                // Bottom left horizontal line.
                g.fillRect(inverseRows%2==1? 160-15-inverseRows/2*40-5 : 160-inverseRows/2*40, inverseRows%2==1 ? 160+10+inverseRows/2*30+5 : 160+inverseRows/2*30-2, 8, 2);
                // Right vertical line.
                g.fillRect(inverseRows%2==1? 160+15+inverseRows/2*40+3 : 160+inverseRows/2*40-2 , inverseRows%2==1 ? 160-10-inverseRows/2*30-5 : 160-inverseRows/2*30 , 2 , inverseRows*30);
                // Top right horizontal line.
                g.fillRect(inverseRows%2==1? 160+15+inverseRows/2*40+3-6 : 160+inverseRows/2*40-8 , inverseRows%2==1 ? 160-10-inverseRows/2*30-5 : 160-inverseRows/2*30 , 8 , 2);
                // Bottom right horizontal line.
                g.fillRect(inverseRows%2==1? 160+15+inverseRows/2*40+3-6 : 160+inverseRows/2*40-8 , inverseRows%2==1 ? 160+10+inverseRows/2*30+5-2 : 160+inverseRows/2*30-2 , 8 , 2);
            }
        };


        matrixPanelA.setLayout(new GridBagLayout());
        matrixPanelA.setBounds(mainPanelWidth/12, mainPanelHeight/2-130, 320, 320);
        matrixPanelA.setBackground(mainColor);

        JPanel matrixPanelResult = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                // Left vertical line.
                g.fillRect(inverseRows%2==1? 160-15-inverseRows/2*40-5 : 160-inverseRows/2*40 , inverseRows%2==1 ? 160-10-inverseRows/2*30-5 : 160-inverseRows/2*30 , 2 ,inverseRows*30);
                // Top left horizontal line.
                g.fillRect(inverseRows%2==1? 160-15-inverseRows/2*40-5 : 160-inverseRows/2*40 , inverseRows%2==1 ? 160-10-inverseRows/2*30-5 : 160-inverseRows/2*30 , 8 , 2);
                // Bottom left horizontal line.
                g.fillRect(inverseRows%2==1? 160-15-inverseRows/2*40-5 : 160-inverseRows/2*40, inverseRows%2==1 ? 160+10+inverseRows/2*30+5 : 160+inverseRows/2*30-2, 8, 2);
                // Right vertical line.
                g.fillRect(inverseRows%2==1? 160+15+inverseRows/2*40+3 : 160+inverseRows/2*40-2 , inverseRows%2==1 ? 160-10-inverseRows/2*30-5 : 160-inverseRows/2*30 , 2 , inverseRows*30);
                // Top right horizontal line.
                g.fillRect(inverseRows%2==1? 160+15+inverseRows/2*40+3-6 : 160+inverseRows/2*40-8 , inverseRows%2==1 ? 160-10-inverseRows/2*30-5 : 160-inverseRows/2*30 , 8 , 2);
                // Bottom right horizontal line.
                g.fillRect(inverseRows%2==1? 160+15+inverseRows/2*40+3-6 : 160+inverseRows/2*40-8 , inverseRows%2==1 ? 160+10+inverseRows/2*30+5-2 : 160+inverseRows/2*30-2 , 8 , 2);
            }
        };
        matrixPanelResult.setLayout(new GridBagLayout());
        matrixPanelResult.setBounds(mainPanelWidth-mainPanelWidth/12-320,mainPanelHeight/2-130,320,320);
        matrixPanelResult.setBackground(mainColor);
        
        JPanel equalSignPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(textColor);
                g.fillRect(0, 5, 30, 5);
                g.fillRect(0, 20, 30, 5);
            }
        };
        equalSignPanel.setLayout(null);
        equalSignPanel.setBackground(mainColor);
        equalSignPanel.setBounds((matrixPanelA.getX()+320+matrixPanelResult.getX())/2-15,mainPanelHeight/2+30-15,30,30);
        
        JLabel rowLabel = new JLabel("Rows");
        rowLabel.setFont(new Font("Arial",Font.BOLD,13));
        rowLabel.setForeground(textColor);
        rowLabel.setBounds(mainPanelWidth/2-50, mainPanelHeight/2-150, 100, 30);

        JLabel warningLabel = new JLabel("Error: The data was not entered correctly!");
        warningLabel.setFont(new Font("Arial",Font.BOLD,17));
        warningLabel.setBounds(mainPanelWidth/2-167,mainPanelHeight-mainPanelHeight/8,334,100);
        warningLabel.setForeground(Color.red);
        
        Integer[] nums = {1,2,3,4,5,6,7,8};
        JComboBox<Integer> rowComboBox = new JComboBox<>(nums);
        rowComboBox.setBounds(mainPanelWidth/2+5,mainPanelHeight/2-150,50,30);
        rowComboBox.setSelectedItem(3);


        GridBagConstraints gbcTemp = new GridBagConstraints();
        gbcTemp.insets = new Insets(5,5,5,5);
        inverseTextFieldsA = new JTextField[inverseRows][inverseRows];
        inverseTextFieldsR = new JTextField[inverseRows][inverseRows];
        for(int i = 0;i<inverseTextFieldsA.length;i++){
            for (int j = 0; j < inverseTextFieldsA[0].length; j++) {
                inverseTextFieldsA[i][j] = new JTextField();
                inverseTextFieldsA[i][j].setPreferredSize(new Dimension(30,20));
                inverseTextFieldsA[i][j].setBorder(null);
                inverseTextFieldsR[i][j] = new JTextField();
                inverseTextFieldsR[i][j].setPreferredSize(new Dimension(30,20));
                inverseTextFieldsR[i][j].setBorder(null);
                inverseTextFieldsR[i][j].setBackground(new Color(163,184,204));
                inverseTextFieldsR[i][j].setEditable(false);
                gbcTemp.gridx=j;
                gbcTemp.gridy=i;
                matrixPanelA.add(inverseTextFieldsA[i][j],gbcTemp);
                matrixPanelResult.add(inverseTextFieldsR[i][j],gbcTemp);
            }
        }

        rowComboBox.addActionListener(e -> {
            matrixPanelA.removeAll();
            matrixPanelA.revalidate();
            matrixPanelA.repaint();
            matrixPanelResult.removeAll();
            matrixPanelResult.revalidate();
            matrixPanelResult.repaint();
            inversePanel.remove(warningLabel);
            inversePanel.revalidate();
            inversePanel.repaint();
            
            inverseRows = (Integer)rowComboBox.getSelectedItem();
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            inverseTextFieldsA = new JTextField[inverseRows][inverseRows];
            inverseTextFieldsR = new JTextField[inverseRows][inverseRows];
            for(int i = 0;i<inverseTextFieldsA.length;i++){
                for (int j = 0; j < inverseTextFieldsA[0].length; j++) {
                    inverseTextFieldsA[i][j] = new JTextField();
                    inverseTextFieldsA[i][j].setPreferredSize(new Dimension(30,20));
                    inverseTextFieldsA[i][j].setBorder(null);
                    inverseTextFieldsR[i][j] = new JTextField();
                    inverseTextFieldsR[i][j].setPreferredSize(new Dimension(30,20));
                    inverseTextFieldsR[i][j].setBorder(null);
                    inverseTextFieldsR[i][j].setBackground(new Color(163,184,204));
                    inverseTextFieldsR[i][j].setEditable(false);
                    gbc.gridx=j;
                    gbc.gridy=i;
                    matrixPanelA.add(inverseTextFieldsA[i][j],gbc);
                    matrixPanelResult.add(inverseTextFieldsR[i][j],gbc);
                }
            }
        });

        inverseResultButton = new JButton("Calculate");
        inverseResultButton.setBounds(mainPanelWidth/2-50,mainPanelHeight/2+160+15, 100, 50);
        inverseResultButton.setFont(new Font("Arial",Font.BOLD,15));
        inverseResultButton.setFocusable(false);
        inverseResultButton.addActionListener(e -> {

            boolean test = true;
            for (int i = 0; i < inverseTextFieldsA.length; i++)
                for (int j = 0; j < inverseTextFieldsA[0].length; j++)
                    test = test && (checkDouble(inverseTextFieldsA[i][j].getText()));

            if(test){
                inversePanel.remove(warningLabel);
                inversePanel.revalidate();
                inversePanel.repaint();
                
                double[][] matrixA = new double[inverseTextFieldsA.length][inverseTextFieldsA[0].length];
                for (int i = 0; i < inverseTextFieldsA.length; i++)
                    for (int j = 0; j < inverseTextFieldsA[0].length; j++)
                        matrixA[i][j] = Double.valueOf(inverseTextFieldsA[i][j].getText());

                DecimalFormat doubleFormat = new DecimalFormat("#.###");
                DecimalFormat intFormat = new DecimalFormat("#");
                double[][] resultMatrix = logic.matrixInverse(matrixA);
                for (int i = 0; i < resultMatrix.length; i++)
                    for (int j = 0; j < resultMatrix[0].length; j++)
                        inverseTextFieldsR[i][j].setText(resultMatrix[i][j]%1==0?intFormat.format(resultMatrix[i][j]):""+doubleFormat.format(resultMatrix[i][j]));
            }
            else{
                inversePanel.add(warningLabel);
                inversePanel.revalidate();
                inversePanel.repaint();
            }
        });


        inversePanel.add(title);
        inversePanel.add(matrixPanelA);
        inversePanel.add(matrixPanelResult);
        inversePanel.add(equalSignPanel);
        inversePanel.add(rowLabel);
        inversePanel.add(rowComboBox);
        inversePanel.add(inverseResultButton);
        componentsCreated++;

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                if(currentPanel==inversePanel && componentsCreated>6){
                    frameWidth = frame.getWidth();
                    frameHeight = frame.getHeight();
                    mainPanelWidth = inversePanel.getWidth();
                    mainPanelHeight = inversePanel.getHeight();
                    title.setBounds(mainPanelWidth/2-102, 20, 204, 100);
                    matrixPanelA.setBounds(mainPanelWidth/12, mainPanelHeight/2-130, 320, 320);
                    matrixPanelResult.setBounds(mainPanelWidth-mainPanelWidth/12-320,mainPanelHeight/2-130,320,320);
                    equalSignPanel.setBounds((matrixPanelA.getX()+320+matrixPanelResult.getX())/2-15,mainPanelHeight/2+30-15,30,30);
                    rowComboBox.setBounds(mainPanelWidth/2+5,mainPanelHeight/2-150,50,30);     
                    rowLabel.setBounds(mainPanelWidth/2-50, mainPanelHeight/2-150, 100, 30);
                    warningLabel.setBounds(mainPanelWidth/2-167,mainPanelHeight-mainPanelHeight/8,334,100);
                    inverseResultButton.setBounds(mainPanelWidth/2-50,mainPanelHeight/2+160+15, 100, 50);
                }
            }
        });

        inversePanel.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                frameWidth = frame.getWidth();
                frameHeight = frame.getHeight();
                mainPanelWidth = inversePanel.getWidth();
                mainPanelHeight = inversePanel.getHeight();
                title.setBounds(mainPanelWidth/2-102, 20, 204, 100);
                matrixPanelA.setBounds(mainPanelWidth/12, mainPanelHeight/2-130, 320, 320);
                matrixPanelResult.setBounds(mainPanelWidth-mainPanelWidth/12-320,mainPanelHeight/2-130,320,320);
                equalSignPanel.setBounds((matrixPanelA.getX()+320+matrixPanelResult.getX())/2-15,mainPanelHeight/2+30-15,30,30);
                rowComboBox.setBounds(mainPanelWidth/2+5,mainPanelHeight/2-150,50,30);     
                rowLabel.setBounds(mainPanelWidth/2-50, mainPanelHeight/2-150, 100, 30);
                warningLabel.setBounds(mainPanelWidth/2-167,mainPanelHeight-mainPanelHeight/8,334,100);
                inverseResultButton.setBounds(mainPanelWidth/2-50,mainPanelHeight/2+160+15, 100, 50);
            }
        });
        //==========================================================================================================================
        //==========================================================================================================================
    }

    /**
     * Checks if a string can be converted to a double.
     * @param str The string we want to check if it can be converted to a double.
     * @return true if str can be converted to a double, false otherwise.
     */
    private boolean checkDouble(String str){
        try {
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException e){
        return false;
        }
    }

    /**
     * Changes the color of all the buttons accept the button passed as an argument.
     * @param button the button whose color will not be changed.
     */
    private void changeOtherButtonsColor(JButton button){
        if(button != mainButton)
            mainButton.setBackground(sideColor);

        if(button != sumButton)
            sumButton.setBackground(sideColor);

        if(button != differenceButton)
            differenceButton.setBackground(sideColor);

        if(button != multiplicationButton)
            multiplicationButton.setBackground(sideColor);

        if(button != determinantButton)
            determinantButton.setBackground(sideColor);
        
        if(button != inverseButton)
            inverseButton.setBackground(sideColor);
    }

    /**
     * Removes a panel from the frame and adds another.
     * @param removePanel the panel to be removed from the frame.
     * @param addPanel the panel to be added to the frame.
     */
    private void changePanel(JPanel removePanel, JPanel addPanel){
        frame.remove(removePanel);
        frame.add(addPanel);
        addPanel.setVisible(false);
        addPanel.setVisible(true);
        frame.validate();
        frame.repaint();
    }

    /**
     * Creates a new frame (otherFrame) and calls other methods which create components for that frame.
     */
    private void makeOtherFrame(){
        if(otherFrame==null){
            otherFrame = new JFrame();
            Image frameIcon = Toolkit.getDefaultToolkit().getImage("./img/Frame Icon.png");
            otherFrame.setIconImage(frameIcon);
            otherFrame.setTitle("Matrix Calculator");
            otherFrame.setLayout(null);
            otherFrame.setSize(715,500);
            otherFrame.setResizable(false);
            otherFrame.getContentPane().setBackground(mainColor);
            otherFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            String[] optionList ={"Addition", "Subtraction", "Multiplication", "Determinant", "Inverse"};
            optionComboBox = new JComboBox<>(optionList);
            optionComboBox.setBounds(295,50,110,30);
            optionComboBox.setSelectedIndex(0);
            
            
            makeOtherSumPanel();
            makeOtherDifferencePanel();
            makeOtherMultiplicationPanel();
            makeOtherDeterminantPanel();
            makeOtherInversePanel();
            otherCurrentPanel = otherSumPanel;
            

            otherFrame.add(otherSumPanel);
            otherFrame.add(optionComboBox);
            otherFrame.setVisible(true);
            optionComboBox.addActionListener(e -> {
                if(optionComboBox.getSelectedIndex()==0){
                    otherChangePanel(otherCurrentPanel, otherSumPanel);
                    otherCurrentPanel = otherSumPanel;
                }
                if(optionComboBox.getSelectedIndex()==1){
                    otherChangePanel(otherCurrentPanel, otherDifferencePanel);
                    otherCurrentPanel = otherDifferencePanel;
                }
                if(optionComboBox.getSelectedIndex()==2){
                    otherChangePanel(otherCurrentPanel, otherMultiplicationPanel);
                    otherCurrentPanel = otherMultiplicationPanel;
                }
                if(optionComboBox.getSelectedIndex()==3){
                    otherChangePanel(otherCurrentPanel, otherDeterminantPanel);
                    otherCurrentPanel = otherDeterminantPanel;
                }
                if(optionComboBox.getSelectedIndex()==4){
                    otherChangePanel(otherCurrentPanel, otherInversePanel);
                    otherCurrentPanel = otherInversePanel;
                }
            });

            otherFrame.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e){
                    moreButton.setEnabled(true);
                    otherFrame = null;
                }

                @Override
                public void windowOpened(WindowEvent e){
                    moreButton.setEnabled(false);
                }
            });
        }
    }

    /**
     * Creates the panel where the sum of matrices will be calculated for otherFrame.
     */
    private void makeOtherSumPanel(){

        otherSumPanel = new JPanel();
        otherSumPanel.setLayout(null);
        otherSumPanel.setBounds(0,80,715,500);
        otherSumPanel.setBackground(mainColor);

        JLabel matrixLabelA = new JLabel("First Matrix");
        matrixLabelA.setBounds(93,40,64,15);
        matrixLabelA.setForeground(Color.white);
        JTextArea textAreaMatrixA = new JTextArea();
        textAreaMatrixA.setText("1 2 3\n4 5 6\n7 8 9");
        UIManager.put("ScrollBar.width", 15);
        JScrollPane scrollPaneMatrixA = new JScrollPane(textAreaMatrixA);
        scrollPaneMatrixA.setBounds(25,60,200,180);
        scrollPaneMatrixA.setBorder(null);

        JLabel matrixLabelB = new JLabel("Second Matrix");
        matrixLabelB.setBounds(309,40,82,15);
        matrixLabelB.setForeground(Color.white);
        JTextArea textAreaMatrixB = new JTextArea();
        textAreaMatrixB.setText("1 2 3\n4 5 6\n7 8 9");
        UIManager.put("ScrollBar.width", 15);
        JScrollPane scrollPaneMatrixB = new JScrollPane(textAreaMatrixB);
        scrollPaneMatrixB.setBounds(250,60,200,180);
        scrollPaneMatrixB.setBorder(null);

        JLabel matrixLabelR = new JLabel("Result");
        matrixLabelR.setBounds(557,40,36,15);
        matrixLabelR.setForeground(Color.white);
        JTextArea textAreaMatrixR = new JTextArea();
        textAreaMatrixR.setEditable(false);
        UIManager.put("ScrollBar.width", 15);
        JScrollPane scrollPaneMatrixR = new JScrollPane(textAreaMatrixR);
        scrollPaneMatrixR.setBounds(475,60,200,180);
        scrollPaneMatrixR.setBorder(null);

        JLabel warningLabel = new JLabel("Error: The data was not entered correctly!");
        warningLabel.setBounds(200,340,300,20);
        warningLabel.setForeground(Color.red);
        warningLabel.setFont(new Font("Arial",Font.BOLD,15));

        JButton resultButton = new JButton("Calculate");
        resultButton.setBounds(300,270,100,40);
        resultButton.setFont(new Font("Arial",Font.BOLD,15));
        resultButton.setFocusable(false);
        resultButton.addActionListener(e -> {
            String[] stringRowsA = textAreaMatrixA.getText().split("\n");
            for (int i = 0; i < stringRowsA.length; i++)
                stringRowsA[i]=stringRowsA[i].trim();
            
            String[][] stringMatrixA = new String[stringRowsA.length][];
            for (int i = 0; i < stringMatrixA.length; i++) {
                String[] stringNumsA = stringRowsA[i].split("[, ]+");
                stringMatrixA[i] = stringNumsA;
            }

            String[] stringRowsB = textAreaMatrixB.getText().split("\n");
            for (int i = 0; i < stringRowsB.length; i++)
                stringRowsB[i]=stringRowsB[i].trim();

            String[][] stringMatrixB = new String[stringRowsB.length][];
            for (int i = 0; i < stringMatrixB.length; i++) {
                String[] stringNumsB = stringRowsB[i].split("[, ]+");
                stringMatrixB[i] = stringNumsB;
            }
            
            boolean test = true;
            if(stringMatrixA.length != stringMatrixB.length)
                test = false;
            else
                for (int i = 0; i < stringMatrixA.length; i++){
                        if(stringMatrixA[i].length != stringMatrixB[i].length)
                            test = false;
                }

            double[][] resultMatrix=new double[stringMatrixA.length][stringMatrixA[0].length];
            try{    
                double[][] matrixA = new double[stringMatrixA.length][stringMatrixA[0].length];
                double[][] matrixB = new double[stringMatrixB.length][stringMatrixB[0].length];
                for (int i = 0; i <  matrixA.length; i++)
                    for (int j = 0; j <  matrixA[0].length; j++)
                        matrixA[i][j] = Double.valueOf(stringMatrixA[i][j]);

                for (int i = 0; i <  matrixB.length; i++)
                    for (int j = 0; j <  matrixB[0].length; j++)
                        matrixB[i][j] = Double.valueOf(stringMatrixB[i][j]);
                
                resultMatrix = logic.matrixSum(matrixA, matrixB);
            } 
            catch(Exception l){
                test = false;
            }
            if(test){
                otherSumPanel.remove(warningLabel);
                otherSumPanel.revalidate();
                otherSumPanel.repaint();

                DecimalFormat doubleFormat = new DecimalFormat("#.###");
                DecimalFormat intFormat = new DecimalFormat("#");
                String resultMatrixString = "";
                for (int i = 0; i < resultMatrix.length; i++) {
                    for (int j = 0; j < resultMatrix[0].length; j++) {
                        resultMatrixString += (resultMatrix[i][j]%1==0? intFormat.format(resultMatrix[i][j]): ""+doubleFormat.format(resultMatrix[i][j]))+"  ";
                    }
                    resultMatrixString +="\n";
                }
                textAreaMatrixR.setText(resultMatrixString);
            }
            else
                otherSumPanel.add(warningLabel);
                otherSumPanel.revalidate();
                otherSumPanel.repaint();
        });
            
        otherSumPanel.add(scrollPaneMatrixA);
        otherSumPanel.add(scrollPaneMatrixB);
        otherSumPanel.add(scrollPaneMatrixR);
        otherSumPanel.add(matrixLabelA);
        otherSumPanel.add(matrixLabelB);
        otherSumPanel.add(matrixLabelR);
        otherSumPanel.add(resultButton);
    }

    /**
     * Creates the panel where the difference of matrices will be calculated for otherFrame
     */
    private void makeOtherDifferencePanel(){

        otherDifferencePanel = new JPanel();
        otherDifferencePanel.setLayout(null);
        otherDifferencePanel.setBounds(0,80,715,500);
        otherDifferencePanel.setBackground(mainColor);

        JLabel matrixLabelA = new JLabel("First Matrix");
        matrixLabelA.setBounds(93,40,64,15);
        matrixLabelA.setForeground(Color.white);
        JTextArea textAreaMatrixA = new JTextArea();
        textAreaMatrixA.setText("1 2 3\n4 5 6\n7 8 9");
        UIManager.put("ScrollBar.width", 15);
        JScrollPane scrollPaneMatrixA = new JScrollPane(textAreaMatrixA);
        scrollPaneMatrixA.setBounds(25,60,200,180);
        scrollPaneMatrixA.setBorder(null);

        JLabel matrixLabelB = new JLabel("Second Matrix");
        matrixLabelB.setBounds(309,40,82,15);
        matrixLabelB.setForeground(Color.white);
        JTextArea textAreaMatrixB = new JTextArea();
        textAreaMatrixB.setText("1 2 3\n4 5 6\n7 8 9");
        UIManager.put("ScrollBar.width", 15);
        JScrollPane scrollPaneMatrixB = new JScrollPane(textAreaMatrixB);
        scrollPaneMatrixB.setBounds(250,60,200,180);
        scrollPaneMatrixB.setBorder(null);

        JLabel matrixLabelR = new JLabel("Result");
        matrixLabelR.setBounds(557,40,36,15);
        matrixLabelR.setForeground(Color.white);
        JTextArea textAreaMatrixR = new JTextArea();
        textAreaMatrixR.setEditable(false);
        UIManager.put("ScrollBar.width", 15);
        JScrollPane scrollPaneMatrixR = new JScrollPane(textAreaMatrixR);
        scrollPaneMatrixR.setBounds(475,60,200,180);
        scrollPaneMatrixR.setBorder(null);

        JLabel warningLabel = new JLabel("Error: The data was not entered correctly!");
        warningLabel.setBounds(200,340,300,20);
        warningLabel.setForeground(Color.red);
        warningLabel.setFont(new Font("Arial",Font.BOLD,15));

        JButton resultButton = new JButton("Calculate");
        resultButton.setBounds(300,270,100,40);
        resultButton.setFont(new Font("Arial",Font.BOLD,15));
        resultButton.setFocusable(false);
        resultButton.addActionListener(e -> {
            String[] stringRowsA = textAreaMatrixA.getText().split("\n");
            for (int i = 0; i < stringRowsA.length; i++)
                stringRowsA[i]=stringRowsA[i].trim();
            
            String[][] stringMatrixA = new String[stringRowsA.length][];
            for (int i = 0; i < stringMatrixA.length; i++) {
                String[] stringNumsA = stringRowsA[i].split("[, ]+");
                stringMatrixA[i] = stringNumsA;
            }

            String[] stringRowsB = textAreaMatrixB.getText().split("\n");
            for (int i = 0; i < stringRowsB.length; i++)
                stringRowsB[i]=stringRowsB[i].trim();

            String[][] stringMatrixB = new String[stringRowsB.length][];
            for (int i = 0; i < stringMatrixB.length; i++) {
                String[] stringNumsB = stringRowsB[i].split("[, ]+");
                stringMatrixB[i] = stringNumsB;
            }
            
            boolean test = true;
            if(stringMatrixA.length != stringMatrixB.length)
                test = false;
            else
                for (int i = 0; i < stringMatrixA.length; i++){
                        if(stringMatrixA[i].length != stringMatrixB[i].length)
                            test = false;
                }

            double[][] resultMatrix=new double[stringMatrixA.length][stringMatrixA[0].length];
            try{    
                double[][] matrixA = new double[stringMatrixA.length][stringMatrixA[0].length];
                double[][] matrixB = new double[stringMatrixB.length][stringMatrixB[0].length];
                for (int i = 0; i <  matrixA.length; i++)
                    for (int j = 0; j <  matrixA[0].length; j++)
                        matrixA[i][j] = Double.valueOf(stringMatrixA[i][j]);

                for (int i = 0; i <  matrixB.length; i++)
                    for (int j = 0; j <  matrixB[0].length; j++)
                        matrixB[i][j] = Double.valueOf(stringMatrixB[i][j]);
                
                resultMatrix = logic.matrixDifference(matrixA, matrixB);
            } 
            catch(Exception l){
                test = false;
            }
            if(test){
                otherDifferencePanel.remove(warningLabel);
                otherDifferencePanel.revalidate();
                otherDifferencePanel.repaint();

                DecimalFormat doubleFormat = new DecimalFormat("#.###");
                DecimalFormat intFormat = new DecimalFormat("#");
                String resultMatrixString = "";
                for (int i = 0; i < resultMatrix.length; i++) {
                    for (int j = 0; j < resultMatrix[0].length; j++) {
                        resultMatrixString += (resultMatrix[i][j]%1==0? intFormat.format(resultMatrix[i][j]): ""+doubleFormat.format(resultMatrix[i][j]))+"  ";
                    }
                    resultMatrixString +="\n";
                }
                textAreaMatrixR.setText(resultMatrixString);
            }
            else
                otherDifferencePanel.add(warningLabel);
                otherDifferencePanel.revalidate();
                otherDifferencePanel.repaint();
        });
            
        otherDifferencePanel.add(scrollPaneMatrixA);
        otherDifferencePanel.add(scrollPaneMatrixB);
        otherDifferencePanel.add(scrollPaneMatrixR);
        otherDifferencePanel.add(matrixLabelA);
        otherDifferencePanel.add(matrixLabelB);
        otherDifferencePanel.add(matrixLabelR);
        otherDifferencePanel.add(resultButton);
    }


    /**
     * Creates the panel where the multiplication of matrices will be calculated for otherFrame
     */
    private void makeOtherMultiplicationPanel(){

        otherMultiplicationPanel = new JPanel();
        otherMultiplicationPanel.setLayout(null);
        otherMultiplicationPanel.setBounds(0,80,715,500);
        otherMultiplicationPanel.setBackground(mainColor);

        JLabel matrixLabelA = new JLabel("First Matrix");
        matrixLabelA.setBounds(93,40,64,15);
        matrixLabelA.setForeground(Color.white);
        JTextArea textAreaMatrixA = new JTextArea();
        textAreaMatrixA.setText("1 2\n3 4\n5 6");
        UIManager.put("ScrollBar.width", 15);
        JScrollPane scrollPaneMatrixA = new JScrollPane(textAreaMatrixA);
        scrollPaneMatrixA.setBounds(25,60,200,180);
        scrollPaneMatrixA.setBorder(null);

        JLabel matrixLabelB = new JLabel("Second Matrix");
        matrixLabelB.setBounds(309,40,82,15);
        matrixLabelB.setForeground(Color.white);
        JTextArea textAreaMatrixB = new JTextArea();
        textAreaMatrixB.setText("1 2 3\n4 5 6");
        UIManager.put("ScrollBar.width", 15);
        JScrollPane scrollPaneMatrixB = new JScrollPane(textAreaMatrixB);
        scrollPaneMatrixB.setBounds(250,60,200,180);
        scrollPaneMatrixB.setBorder(null);

        JLabel matrixLabelR = new JLabel("Result");
        matrixLabelR.setBounds(557,40,36,15);
        matrixLabelR.setForeground(Color.white);
        JTextArea textAreaMatrixR = new JTextArea();
        textAreaMatrixR.setEditable(false);
        UIManager.put("ScrollBar.width", 15);
        JScrollPane scrollPaneMatrixR = new JScrollPane(textAreaMatrixR);
        scrollPaneMatrixR.setBounds(475,60,200,180);
        scrollPaneMatrixR.setBorder(null);

        JLabel warningLabel = new JLabel("Error: The data was not entered correctly!");
        warningLabel.setBounds(200,340,300,20);
        warningLabel.setForeground(Color.red);
        warningLabel.setFont(new Font("Arial",Font.BOLD,15));

        JButton resultButton = new JButton("Calculate");
        resultButton.setBounds(300,270,100,40);
        resultButton.setFont(new Font("Arial",Font.BOLD,15));
        resultButton.setFocusable(false);
        resultButton.addActionListener(e -> {
            String[] stringRowsA = textAreaMatrixA.getText().split("\n");
            for (int i = 0; i < stringRowsA.length; i++)
                stringRowsA[i]=stringRowsA[i].trim();
            
            String[][] stringMatrixA = new String[stringRowsA.length][];
            for (int i = 0; i < stringMatrixA.length; i++) {
                String[] stringNumsA = stringRowsA[i].split("[, ]+");
                stringMatrixA[i] = stringNumsA;
            }

            String[] stringRowsB = textAreaMatrixB.getText().split("\n");
            for (int i = 0; i < stringRowsB.length; i++)
                stringRowsB[i]=stringRowsB[i].trim();

            String[][] stringMatrixB = new String[stringRowsB.length][];
            for (int i = 0; i < stringMatrixB.length; i++) {
                String[] stringNumsB = stringRowsB[i].split("[, ]+");
                stringMatrixB[i] = stringNumsB;
            }
            
            boolean test = true;
            
            for (int i = 0; i < stringMatrixA.length; i++)
                if(stringMatrixA[i].length != stringMatrixB.length)
                    test = false;
            for (int i = 0; i < stringMatrixA.length; i++) 
                if(stringMatrixA[0].length != stringMatrixA[i].length)
                    test = false;
            for (int i = 0; i < stringMatrixB.length; i++) 
                if(stringMatrixB[0].length != stringMatrixB[i].length)
                    test = false;
            
            double[][] resultMatrix=new double[stringMatrixA.length][stringMatrixB[0].length];
            try{    
                double[][] matrixA = new double[stringMatrixA.length][stringMatrixA[0].length];
                double[][] matrixB = new double[stringMatrixB.length][stringMatrixB[0].length];
                for (int i = 0; i <  matrixA.length; i++)
                    for (int j = 0; j <  matrixA[0].length; j++)
                        matrixA[i][j] = Double.valueOf(stringMatrixA[i][j]);

                for (int i = 0; i <  matrixB.length; i++)
                    for (int j = 0; j <  matrixB[0].length; j++)
                        matrixB[i][j] = Double.valueOf(stringMatrixB[i][j]);
                
                resultMatrix = logic.matrixMultiplication(matrixA, matrixB);
            } 
            catch(Exception l){
                test = false;
            }
            if(test){
                otherMultiplicationPanel.remove(warningLabel);
                otherMultiplicationPanel.revalidate();
                otherMultiplicationPanel.repaint();

                DecimalFormat doubleFormat = new DecimalFormat("#.###");
                DecimalFormat intFormat = new DecimalFormat("#");
                String resultMatrixString = "";
                for (int i = 0; i < resultMatrix.length; i++) {
                    for (int j = 0; j < resultMatrix[0].length; j++) {
                        resultMatrixString += (resultMatrix[i][j]%1==0? intFormat.format(resultMatrix[i][j]): ""+doubleFormat.format(resultMatrix[i][j]))+"  ";
                    }
                    resultMatrixString +="\n";
                }
                textAreaMatrixR.setText(resultMatrixString);
            }
            else
                otherMultiplicationPanel.add(warningLabel);
                otherMultiplicationPanel.revalidate();
                otherMultiplicationPanel.repaint();
        });
            
        otherMultiplicationPanel.add(scrollPaneMatrixA);
        otherMultiplicationPanel.add(scrollPaneMatrixB);
        otherMultiplicationPanel.add(scrollPaneMatrixR);
        otherMultiplicationPanel.add(matrixLabelA);
        otherMultiplicationPanel.add(matrixLabelB);
        otherMultiplicationPanel.add(matrixLabelR);
        otherMultiplicationPanel.add(resultButton);
    }

    /**
     * Creates the panel where the determinant of matrices will be calculated for otherFrame.
     */
    private void makeOtherDeterminantPanel(){

        otherDeterminantPanel = new JPanel();
        otherDeterminantPanel.setLayout(null);
        otherDeterminantPanel.setBounds(0,80,715,500);
        otherDeterminantPanel.setBackground(mainColor);

        JLabel matrixLabelA = new JLabel("Matrix");
        matrixLabelA.setBounds(332,40,36,15);
        matrixLabelA.setForeground(Color.white);

        JTextArea textAreaMatrixA = new JTextArea();
        textAreaMatrixA.setText("1 2 3\n4 5 6\n7 8 9");
        UIManager.put("ScrollBar.width", 15);
        JScrollPane scrollPaneMatrixA = new JScrollPane(textAreaMatrixA);
        scrollPaneMatrixA.setBounds(250,60,200,180);
        scrollPaneMatrixA.setBorder(null);

        JLabel warningLabel = new JLabel("Error: The data was not entered correctly!");
        warningLabel.setBounds(200,340,300,20);
        warningLabel.setForeground(Color.red);
        warningLabel.setFont(new Font("Arial",Font.BOLD,15));
        
        JLabel resultLabel = new JLabel("Result");
        resultLabel.setBounds(532,115,36,15);
        resultLabel.setForeground(Color.white);
        
        JTextField resultField = new JTextField();
        resultField.setBounds(500,135,100,30);
        resultField.setEditable(false);
        resultField.setBorder(null);
        
        JButton resultButton = new JButton("Calculate");
        resultButton.setBounds(300,270,100,40);
        resultButton.setFont(new Font("Arial",Font.BOLD,15));
        resultButton.setFocusable(false);
        resultButton.addActionListener(e -> {
            String[] stringRowsA = textAreaMatrixA.getText().split("\n");
            for (int i = 0; i < stringRowsA.length; i++)
                stringRowsA[i]=stringRowsA[i].trim();
            
            String[][] stringMatrixA = new String[stringRowsA.length][];
            for (int i = 0; i < stringMatrixA.length; i++) {
                String[] stringNumsA = stringRowsA[i].split("[, ]+");
                stringMatrixA[i] = stringNumsA;
            }

            
            boolean test = true;
            if(stringMatrixA.length != stringMatrixA[0].length)
                test = false;
            else
                for (int i = 0; i < stringMatrixA.length; i++)
                    if(stringMatrixA[0].length != stringMatrixA[i].length)
                        test = false;

            double result=0;
            try{    
                double[][] matrixA = new double[stringMatrixA.length][stringMatrixA[0].length];
                for (int i = 0; i <  matrixA.length; i++)
                    for (int j = 0; j <  matrixA[0].length; j++)
                        matrixA[i][j] = Double.valueOf(stringMatrixA[i][j]);
                
                result = logic.matrixDeterminant(matrixA);
            } 
            catch(Exception l){
                test = false;
            }
            if(test){
                otherDeterminantPanel.remove(warningLabel);
                otherDeterminantPanel.revalidate();
                otherDeterminantPanel.repaint();

                DecimalFormat doubleFormat = new DecimalFormat("#.###");
                DecimalFormat intFormat = new DecimalFormat("#");
                String resultString = ""+(result%1==0? intFormat.format(result): ""+doubleFormat.format(result));

                resultField.setText(resultString);
            }
            else
                otherDeterminantPanel.add(warningLabel);
                otherDeterminantPanel.revalidate();
                otherDeterminantPanel.repaint();
        });
            
        otherDeterminantPanel.add(scrollPaneMatrixA);
        otherDeterminantPanel.add(matrixLabelA);
        otherDeterminantPanel.add(resultField);
        otherDeterminantPanel.add(resultLabel);
        otherDeterminantPanel.add(matrixLabelA);
        otherDeterminantPanel.add(resultButton);
    }

    /**
     * Creates the panel where the inverse of matrices will be calculated for otherFrame.
     */
    private void makeOtherInversePanel(){

        otherInversePanel = new JPanel();
        otherInversePanel.setLayout(null);
        otherInversePanel.setBounds(0,80,715,500);
        otherInversePanel.setBackground(mainColor);

        JLabel matrixLabelA = new JLabel("Matrix");
        matrixLabelA.setBounds(207,40,36,15);
        matrixLabelA.setForeground(Color.white);
        JTextArea textAreaMatrixA = new JTextArea();
        textAreaMatrixA.setText("1 8 2\n2 6 4\n1 2 3");
        UIManager.put("ScrollBar.width", 15);
        JScrollPane scrollPaneMatrixA = new JScrollPane(textAreaMatrixA);
        scrollPaneMatrixA.setBounds(125,60,200,180);
        scrollPaneMatrixA.setBorder(null);

        JLabel matrixLabelR = new JLabel("Result");
        matrixLabelR.setBounds(457,40,36,15);
        matrixLabelR.setForeground(Color.white);
        JTextArea textAreaMatrixR = new JTextArea();
        textAreaMatrixR.setEditable(false);
        UIManager.put("ScrollBar.width", 15);
        JScrollPane scrollPaneMatrixR = new JScrollPane(textAreaMatrixR);
        scrollPaneMatrixR.setBounds(375,60,200,180);
        scrollPaneMatrixR.setBorder(null);

        JLabel warningLabel = new JLabel("Error: The data was not entered correctly!");
        warningLabel.setBounds(200,340,300,20);
        warningLabel.setForeground(Color.red);
        warningLabel.setFont(new Font("Arial",Font.BOLD,15));

        JButton resultButton = new JButton("Calculate");
        resultButton.setBounds(300,270,100,40);
        resultButton.setFont(new Font("Arial",Font.BOLD,15));
        resultButton.setFocusable(false);
        resultButton.addActionListener(e -> {
            String[] stringRowsA = textAreaMatrixA.getText().split("\n");
            for (int i = 0; i < stringRowsA.length; i++)
                stringRowsA[i]=stringRowsA[i].trim();
            
            String[][] stringMatrixA = new String[stringRowsA.length][];
            for (int i = 0; i < stringMatrixA.length; i++) {
                String[] stringNumsA = stringRowsA[i].split("[, ]+");
                stringMatrixA[i] = stringNumsA;
            }
            
            boolean test = true;
            if(stringMatrixA.length != stringMatrixA[0].length)
                test = false;
            else
                for (int i = 0; i < stringMatrixA.length; i++)
                    if(stringMatrixA[0].length != stringMatrixA[i].length)
                        test = false;

            double[][] resultMatrix=new double[stringMatrixA.length][stringMatrixA[0].length];
            try{    
                double[][] matrixA = new double[stringMatrixA.length][stringMatrixA[0].length];
                for (int i = 0; i <  matrixA.length; i++)
                    for (int j = 0; j <  matrixA[0].length; j++)
                        matrixA[i][j] = Double.valueOf(stringMatrixA[i][j]);
                
                resultMatrix = logic.matrixInverse(matrixA);
            } 
            catch(Exception l){
                test = false;
            }
            if(test){
                otherInversePanel.remove(warningLabel);
                otherInversePanel.revalidate();
                otherInversePanel.repaint();

                DecimalFormat doubleFormat = new DecimalFormat("#.###");
                DecimalFormat intFormat = new DecimalFormat("#");
                String resultMatrixString = "";
                for (int i = 0; i < resultMatrix.length; i++) {
                    for (int j = 0; j < resultMatrix[0].length; j++) {
                        resultMatrixString += (resultMatrix[i][j]%1==0? intFormat.format(resultMatrix[i][j]): ""+doubleFormat.format(resultMatrix[i][j]))+"  ";
                    }
                    resultMatrixString +="\n";
                }
                textAreaMatrixR.setText(resultMatrixString);
            }
            else
                otherInversePanel.add(warningLabel);
                otherInversePanel.revalidate();
                otherInversePanel.repaint();
        });
            
        otherInversePanel.add(scrollPaneMatrixA);
        otherInversePanel.add(scrollPaneMatrixR);
        otherInversePanel.add(matrixLabelA);
        otherInversePanel.add(matrixLabelR);
        otherInversePanel.add(resultButton);
    }


    /**
     * Removes a panel from the otherFrame and adds another.
     * @param removePanel the panel to be removed from otherFrame.
     * @param addPanel the panel to be added to otherFrame.
     */
    private void otherChangePanel(JPanel removePanel, JPanel addPanel){
        otherFrame.remove(removePanel);
        otherFrame.add(addPanel);
        otherFrame.validate();
        otherFrame.repaint();
    }
}
