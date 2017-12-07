/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsystem;

import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author codeGeek
 */
 public class StrictInputVerifier extends InputVerifier {
        private String validString;

        public StrictInputVerifier(String validString) {
            this.validString = validString;
        }

        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            if (textField.getText().toString().length()<10) {
              
                return true;
            } else {
               textField.setBackground(Color.red);
                return false;
            }
        }
    }
