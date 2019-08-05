package com.yuxia.sbdemo.word;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFComment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class WordTest {

    @Test
    public void test(){
        File file = new File("/Users/yuxia/Downloads/锅炉运行值班员.docx");
        try {
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument doc = new XWPFDocument(fis);
            StringBuilder sb  =  new StringBuilder();
            for(XWPFParagraph p : doc.getParagraphs())
            {
                sb.append(p.getParagraphText());
            }
//            for (String sss:strings){
//                System.out.println(sss);
//                System.out.println("-------");
//            }
            List<String> list1 = Lists.newArrayList();
            List<String> list2 = Lists.newArrayList();
            String str=sb.toString();
            int flag=0;
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='√'){
                    list1.add(StringUtils.substring(str,flag,i+1));
                    flag=i+1;
                }else if(str.charAt(i)=='×'){
                    list2.add(StringUtils.substring(str,flag,i+1));
                    flag=i+1;
                }
            }
            for(String ss:list1){
                System.out.println(ss);
                System.out.println("------------------------");
            }
            for(String ss:list2){
                System.out.println(ss);
                System.out.println("------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
