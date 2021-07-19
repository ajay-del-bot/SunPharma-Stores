package com.pharma;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Gui {

    JFrame mainframe=new JFrame();
    JPanel top=new JPanel();
    JPanel centrebig=new JPanel();
    JPanel west=new JPanel();
    JPanel centre=new JPanel();
    JPanel westtop=new JPanel();
    JPanel westbottom=new JPanel();
    private final JLabel lblNewLabel = new JLabel("New label");
    /**
     * @wbp.parser.entryPoint
     */
    public void createGui(){
        JLabel jlabel = new JLabel("SUN PHARMA REPORTS");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/sunn4.png"));
        lblNewLabel.setIcon(img);
        jlabel.setFont(new Font("Verdana",1,40));
        top.setBackground(Color.WHITE);
        top.add(jlabel);
        mainframe.getContentPane().setBackground(Color.WHITE);
        mainframe.setSize(new Dimension(800,650));//600
        top.setBorder(new EmptyBorder(16, 16, 16, 16));
        mainframe.getContentPane().add(top,BorderLayout.NORTH);
        centrebig.setBorder(new EmptyBorder(16, 16, 16, 16));
        mainframe.getContentPane().add(centrebig,BorderLayout.CENTER);
        centrebig.setLayout(null);
        west.setBounds(16, 16, 250, 479);
        centrebig.add(west);
        centre.setBackground(Color.WHITE);
        centre.setBounds(266, 16, 500, 479);
        centrebig.add(centre);
        west.setPreferredSize(new Dimension(250,400));
        west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
        westtop.setBackground(Color.WHITE);
        westtop.setLayout(null);
        JComboBox qtype=createList1();
        qtype.setBorder(new EmptyBorder(16, 16, 16, 16));
        westtop.add(qtype);
        west.setBorder(BorderFactory.createLineBorder(Color.blue));
        centre.setBorder(BorderFactory.createLineBorder(Color.blue));
        centre.setLayout(null);
        west.add(westtop);
        westbottom.setBackground(Color.WHITE);
        west.add(westbottom);
        westbottom.setLayout(null);
        lblNewLabel.setBounds(25, 0, 187, 188);
        
        westbottom.add(lblNewLabel);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setVisible(true);

    }

    @SuppressWarnings("unchecked")
	public JComboBox createList1(){
        JComboBox comboBox=new JComboBox();
        comboBox.setBounds(34, 13, 181, 52);
        comboBox.setEditable(true);
        comboBox.addActionListener(new listQuerytype());
        comboBox.addItem("Choose any 1");

        comboBox.addItem("Find a Medicine");
        comboBox.addItem("Expiry");
        comboBox.addItem("History");
        comboBox.addItem("Salewise Profits");
        comboBox.addItem("Slow Moving Drugs");
        comboBox.addItem("Daily Profits");
        comboBox.addItem("Purchasing Assistant");
        

        comboBox.setEditable(false);
        return comboBox;
    }

    class listQuerytype implements ActionListener {
        public void actionPerformed(ActionEvent e){
            JComboBox temp = (JComboBox) e.getSource();
            Object selected = temp.getSelectedItem();

            if(selected.toString().equals("Find a Medicine")){
                GuiQuery2 q2=new GuiQuery2();
                q2.query2gui(westbottom,centre);
            }

            else if(selected.toString().equals("Expiry")){
                GuiQuery3 q3=new GuiQuery3();
                q3.query3gui(westbottom,centre);
            }
            else if(selected.toString().equals("History")){
                GuiQuery4 q4=new GuiQuery4();
                q4.query4gui(westbottom,centre);
            }
            else if(selected.toString().equals("Salewise Profits")){
                GuiQuery5 q5=new GuiQuery5();
                q5.query5gui(westbottom,centre);
            }


            else if(selected.toString().equals("Daily Profits")){
                GuiQuery7 q7=new GuiQuery7();
                q7.query7gui(westbottom,centre);
            }
            else if(selected.toString().equals("Purchasing Assistant")){
                GuiQuery8 q7=new GuiQuery8();
                q7.query8gui(westbottom,centre);
            }

            else if(selected.toString().equals("Slow Moving Drugs")){
                GuiQuery10 q10=new GuiQuery10();
                q10.query10gui(westbottom,centre);
            }

 
            
        }
    }
}





