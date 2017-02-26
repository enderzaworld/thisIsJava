/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisisjava;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Imba Gamer
 */
public class test {
    private static final String directory=System.getProperty("user.dir");
    private static final String sample=directory+File.separator+"sample.txt";


    public static void createFile()
    {
        File file=new File(sample);
        try(FileWriter fw=new FileWriter(file))
        {
            fw.write("INSERT ME WHERE MY JAR IS");
            fw.flush();
            fw.close();
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        createFile();
    }
}
