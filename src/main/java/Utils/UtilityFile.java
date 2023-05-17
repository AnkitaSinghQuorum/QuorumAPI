//package com.Utils;
//
//
//import org.slf4j.Logger;
//
//public class UtilityFile {
//    private static boolean root = false;
//
//    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
//    public static Logger getLogger(Class cls) {
//        if (root) {
//            return Logger.getLogger(cls);
//        } else {
//            PropertyConfigurator.configure("src/test/resources/log4j.properties");
//            root = true;
//            return Logger.getLogger(cls);
//        }
//
//    }
//}
