/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import javafx.scene.control.TextArea;

/**
 *
 * @author Adamastor Chimalange
 */
public class File_Functions {
    
    public static TextArea newFile(TextArea textArea){
        textArea.setText("");
        return textArea;
    }
}
