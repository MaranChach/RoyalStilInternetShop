package com.trantin.simpleweb.http.utils;

import java.io.*;
import java.net.URISyntaxException;

public class FileUtil {

    public void setShopDesc(String desc) throws URISyntaxException, IOException {
        File file = new File(getClass().getResource("shop-description").toURI());

        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write(desc);
    }

    public String getShopDesc() throws URISyntaxException, IOException {
        StringBuilder desc = new StringBuilder();

        File file = new File(getClass().getResource("shop-description").toURI());

        FileReader fileReader = new FileReader(file);

        int c;

        while((c = fileReader.read()) != -1){
            desc.append((char) c);
        }

        return desc.toString();
    }
}
