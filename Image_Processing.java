import javax.swing.JFrame;            // used for jframe obj creation
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;      // used for filechooser obj

import java.awt.Font;                 // used for font obj creation
import java.awt.FlowLayout;           // used for putting the font layout

import java.io.File;                  // for taking input as a file ( here image )
import java.io.FileInputStream;       // reading the input file
import java.io.FileOutputStream;




class Image_Processing{

    public static void main(String[] args) {
        

        // ********************************************     GUI     **********************************************

        /*

         1. creating a jframe object -- gave dimensions
         2. creating a textField
         3. creating a button
         4. adding btn and textField to the jframe
         5. set jframe visibility as true;

         */

        // ********************************************     GUI  DONE   **********************************************  








        // Creating JFrame which is used as a frame for displaying on the windows
        JFrame jf = new JFrame();
        jf.setTitle("Image Processing");
        jf.setSize(400,400);                   
        jf.setLocationRelativeTo(null);                              // to make it come into the center
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);              // used for smooth closing upon clicking red button


        // Setting up a font;
        Font newFont = new Font("Roboto",Font.BOLD,25);


        // Creating a textField
        JTextField text = new JTextField(10);
        text.setFont(newFont);

        
        // Creating a button
        JButton btn = new JButton();
        btn.setText("Browse Image or File");
        btn.setFont(newFont);

        
        // Adding BTN and TEXTFIELD to the JFrame;
        jf.add(btn);
        jf.add(text);

        
        // Setting up the layout for the jFrame
        // Arranges the components in a jframe in specified format
        jf.setLayout( new FlowLayout() );

        // for showing up the jframe in the window
        jf.setVisible(true);



        // btn onclick listener:
        btn.addActionListener(e->{                 // actionListener is an interface whose obj dosent exist, so we used lamba function
                                                   // lambda function is an anonymous function who does the same work as obj of the child class being passed for the abstract parent class
            System.out.println("Process Starting.....");
            String keyText = text.getText();
            int passkey = Integer.parseInt(keyText);
            operate(passkey);                         

        }); 


    }


    public static void operate(int key){

        
        JFileChooser chooseFile = new JFileChooser();      // for displaying dialogue box to choose files from
        chooseFile.showOpenDialog(null);           // to make the dialogue box come in middle
          
        File file = chooseFile.getSelectedFile();         // for storing files, use file object



        
         /*    ************************* logic for encryption and decryption (DO XOR) ***********************
                  
                logic for encryption:
                int a = 50;
                int passkey = 70;

                int public_private_key = a^key;
                encrypt = 116;


                logic for decryption:
                int passkey = 70
                int public_private_key = 116;

                int a = passkey ^ public_private_key;
                a = 50;

        */







        // Reading file input (can have errors so use try, catch statements)


        try{


                // Reading file input
                FileInputStream fileIn = new FileInputStream(file);
                byte[] data = new byte[fileIn.available()];           // data in the form of byte is extracted from the image and stored in the data array of type byte
  
                fileIn.read(data);                               // each byte is now being read by FileInputStream
                    

                int i = 0;
                for( byte b: data ){                        // single byte of the data/ image is accessible now
                    
                    data[i] = (byte)(b ^ key);
                    i++;

                }


                
                // Displaying encrypted file output
                FileOutputStream fileOut = new FileOutputStream(file);
                fileOut.write(data);                                


                fileIn.close();
                fileOut.close();


                // Showing dialogue box upon successful completion
                JOptionPane.showMessageDialog(null, "Done Operation");


        }
        catch( Exception e ){
            e.printStackTrace();
        }

    }


}