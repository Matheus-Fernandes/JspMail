/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author u15184
 */
public class AnexosManagedBean 
{
    public List<InputStream> arq;
    public List<String> nomes;

    public List<InputStream> getArq() {
        return arq;
    }

    public void setArq(List<InputStream> arq) {
        this.arq = arq;
    }

    public List<String> getNomes() {
        return nomes;
    }

    public void setNomes(List<String> nomes) {
        this.nomes = nomes;
    }
    
    
    
    public void baixar(int i, HttpServletResponse response) throws FileNotFoundException, IOException
    {
        InputStream is = arq.get(i);
        OutputStream os = response.getOutputStream();       
        
        byte[] buf = new byte[4096];
        int bytesRead;
        while((bytesRead = is.read(buf))!=-1) {
            os.write(buf, 0, bytesRead);
        }
        os.close();            
    }
}
