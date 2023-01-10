import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;//A java swing class for making  a window
    JMenuBar menuBar;//menuBar for containing menus
    JMenu file,edit;//JMenu class used to create Menus
    //menuItems for containing menu items
    JMenuItem newFile,openFile,saveFile;//Menu Items for file menu
    JMenuItem cut,copy,paste,selectAll,close;// Menus Items for edit menu
    JTextArea textArea;// Area for writing text
    TextEditor()//creating a constructor
    {
        frame=new JFrame(); //frame for TextEditor window

        textArea=new JTextArea();//Initialized Text Area For Writing

        menuBar =new JMenuBar();//Initialized menuBar

        //Initialize the menus:-
        file= new JMenu("File");
        edit=new JMenu("Edit");

        //Initialize the Menu Items for File Menu
        newFile=new JMenuItem("NEW");
        openFile=new JMenuItem("OPEN");
        saveFile=new JMenuItem("SAVE");

        //Add Action Listener to File menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Adding menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize the Menu Items for Edit Menu
        cut=new JMenuItem("CUT");
        copy=new JMenuItem("COPY");
        paste=new JMenuItem("PASTE");
        selectAll=new JMenuItem("SELECT ALL");
        close =new JMenuItem("CLOSE WINDOW");

        //Add Action Listener to File menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Adding menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        frame.setJMenuBar(menuBar);//add menuBar to Jframe

        frame.add(textArea);//adding text area to the frame

        frame.setBounds(100,100,800,500);//boundaries for frame

        frame.setVisible(true);//for visible the frame
    }
    @Override//function overriding
    public void actionPerformed(ActionEvent actionEvent)
    {
         if(actionEvent.getSource()==cut)//if the source is cut
         {
             textArea.cut();//Perform Action according to cut
         }
         if(actionEvent.getSource()==copy)
         {
             textArea.copy();//Perform Action according to copy
         }
         if(actionEvent.getSource()==paste)
         {
             textArea.paste();//Perform Action according to paste
         }
         if(actionEvent.getSource()==selectAll)
         {
             textArea.selectAll();//Perform Action according to Select All
         }
         if(actionEvent.getSource()==close)
         {
             System.exit(0);//Perform Action according to close
         }
        //If Source Is New
         if (actionEvent.getSource()==newFile)
         {
             TextEditor newWindow= new TextEditor();
         }
        //If Source Is Open
         if(actionEvent.getSource()==openFile)
         {
             //Open a text file
//             Initialized file chooser
             JFileChooser fileChooser=new JFileChooser("C:");
             //Get choose option from flie chooser
             int chooseOption= fileChooser.showOpenDialog(null);
             //if choose option is equal to approve
             if(chooseOption== JFileChooser.APPROVE_OPTION)
             {
                 File file=fileChooser.getSelectedFile();//selecting the file
                 String filePath=file.getPath();//for getting the path of the file

                 //for exception handling
                 try
                 {
                     //created buffer reader
                     BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
                     //create strings to store content from file
                     String intermediate="",output="";
                     //read content line by line
                     while((intermediate=bufferedReader.readLine())!=null)
                     {
                         output+=intermediate+"\n";
                     }
                     //set output to the text area
                     textArea.setText(output);
                 }
                 catch(Exception exception)
                 {
                    exception.printStackTrace();//print the exception
                 }
             }
         }
         //If the source is save
         if(actionEvent.getSource()==saveFile)
         {
             //save a file
             //Create a file Chooser
             JFileChooser fileChooser=new JFileChooser("C:");
             //Get  chosen option from file chooser
             int chooseOption=fileChooser.showSaveDialog( null);
             // If chosen option is Approve
             if(chooseOption==JFileChooser.APPROVE_OPTION)
             {
                 //Create a file object with selected path
                 File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                 try
                 {
                     //Create Buffered Writer to write content to file
                     BufferedWriter outfile=new BufferedWriter(new FileWriter(file));
                     //get content from text area to outfile
                     textArea.write(outfile);
                     outfile.close();
                 }
                 catch(Exception exception)
                 {
                     exception.printStackTrace();
                 }
             }
         }
    }
    public static void main(String[] args)
    {
        TextEditor textEditor = new TextEditor(); //creating an object for the constructor
    }
}