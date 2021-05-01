package com.company;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<String> list = new ArrayList<>();
    private static int count = 0;

    public static void main(String[] args) {

        Path path = FileSystems.getDefault().getPath("IPv4Address.txt");

        try(BufferedReader dirFile = Files.newBufferedReader(path)){
            String input;
            while ((input = dirFile.readLine()) != null){
                String data = input;
                if (!list.contains(data)){
                    list.add(data);
                    count++;
                }
            }
        }catch (IOException e){
            System.out.println("Error Occurred");
        }

        System.out.println("Number of IPv4 Address is: " + count);

        // now we save them in our file

        Path locPath = FileSystems.getDefault().getPath("local.dat");
        try(ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))){
            for (String address : list){
                locFile.writeUTF(address + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
