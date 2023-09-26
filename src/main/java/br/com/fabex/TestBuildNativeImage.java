package br.com.fabex;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TestBuildNativeImage {
    private final static java.util.logging.Logger LOGGER;
    static {
        try {
            LogManager.getLogManager().readConfiguration(TestBuildNativeImage.class.getResourceAsStream("/logging.properties"));
        } catch (IOException | SecurityException | ExceptionInInitializerError ex) {
            java.util.logging.Logger.getLogger(TestBuildNativeImage.class.getName()).log(Level.SEVERE, "Failed to read logging.properties file", ex);
        }
        LOGGER = Logger.getLogger(TestBuildNativeImage.class.getName());
    }

    public static void main(String[] args) throws Exception {
        try{
            LOGGER.info("opsss");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
        }
    }
}
