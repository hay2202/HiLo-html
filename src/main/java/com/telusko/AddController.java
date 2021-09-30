package com.telusko;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AddController 
{
    private final String XML = "C:\\Users\\Hay Asa\\IdeaProjects\\Hilo\\data\\data.xml";
    private final String XSL= "C:\\Users\\Hay Asa\\IdeaProjects\\Super_User\\data\\data-style.xsl";
    
	@RequestMapping("/add")
	String toXML(@RequestParam("myquest[]") String[] ques,  @RequestParam("myAns[]") String[] ans, @RequestParam("id") String id,  @RequestParam("mytarget[]") String[] target){
        try {
            File xmlFile = new File(XML);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            Element documentElement = document.getDocumentElement();

            Element nodeElement = document.createElement("State");
            nodeElement.setAttribute("id",id);

            //question
            	for(String a : ques) {
            		Element questionNode = document.createElement("message");
	                questionNode.setTextContent(a);
	                nodeElement.appendChild(questionNode);
            	}
               
           

            //answer
            Element keywordsNode = document.createElement("keywords");
            for (int i = 0; i<ans.length; i++){
            	Element keyword1 = document.createElement("keyword");
                keyword1.setTextContent(ans[i]);
                keyword1.setAttribute("target",target[i]);
                keywordsNode.appendChild(keyword1);
            }


            nodeElement.appendChild(keywordsNode);
            documentElement.appendChild(nodeElement);
            document.replaceChild(documentElement, documentElement);
            Transformer tFormer = TransformerFactory.newInstance().newTransformer(new StreamSource(XSL));
            tFormer.setOutputProperty(OutputKeys.INDENT, "yes");
            Source source = new DOMSource(document);
            Result result = new StreamResult(xmlFile);
            tFormer.transform(source, result);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return "display.jsp";
    }
	
	
	
	@RequestMapping("/show")
	public ModelAndView parseXML(@RequestParam("category")String id){
		 String data = "";
	        QueueDisticnt<String> stateQueue = new QueueDisticnt();
	        File xmlFile = new File(XML);
	        try{
	            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	            Document document = documentBuilder.parse(xmlFile);

	            XPathFactory xPathfactory = XPathFactory.newInstance();
	            XPath xpath = xPathfactory.newXPath();
	            stateQueue.add(id);
	            while(!stateQueue.isEmpty()){
	                String tag = stateQueue.poll();
	                String path = "//State[@id="+tag+"]";
	                XPathExpression expr = xpath.compile(path);
	                NodeList nl = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
	                Element el = (Element) nl.item(0);          // GET FIRST STATE
	                data += el.getElementsByTagName("message").item(0).getTextContent() +"("+tag+")<br>\t<br>";
	                NodeList nlp = el.getElementsByTagName("keyword");
	                if (nlp.getLength() == 0){
	                    data += "_________________________________________________";
	                }
	                String data2 = "";
	                for (int i=0; i<nlp.getLength(); i++){
	                    Element ele = (Element) nlp.item(i);
	                    String temp = ele.getAttributeNode("target").getValue();
	                    stateQueue.add(temp);
	                    data2 +=  ele.getTextContent() + "("+temp+") " + "\t";

	                }
	                data += data2 +"<br>\t&#8595<br>";
	            }
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        ModelAndView mv = new ModelAndView();
			mv.setViewName("allStates.jsp");
			mv.addObject("result",data);
			
	        return mv;
	    }

}