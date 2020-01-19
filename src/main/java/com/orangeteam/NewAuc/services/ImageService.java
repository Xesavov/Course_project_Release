package com.orangeteam.NewAuc.services;

import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

@Service
public class ImageService {
    final String path = "src/main/resources/templates/img/prod/";

    public byte[] getImage(String name){
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(path + name));
            return stream.readAllBytes();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
