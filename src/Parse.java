
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
//import java.util.Iterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.events.StartDocument;
import javax.xml.parsers.DocumentBuilder;

import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import treatment.Price;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

//import Exemplegenerale.VmListType;

public class Parse {
	static int time;
	
	public static List<Activity> parse(String url){
		
		List<Activity> tasks = new ArrayList<>();

		try {

			File fXmlFile = new File(url);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName()+
					", time: "+doc.getDocumentElement().getAttributeNode("time").getValue());
			
			time = Integer.parseInt(doc.getDocumentElement().getAttributeNode("time").getValue());
			
			NodeList nList = doc.getElementsByTagName("Task");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("Task id : " + eElement.getAttribute("id"));
					int id = Integer.parseInt(eElement.getAttribute("id"));
					int min = Integer.parseInt(eElement.getAttribute("minTime"));
					int max = Integer.parseInt(eElement.getAttribute("maxTime"));
					String start = eElement.getAttribute("StartTime");
				    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
					Date s=formatter.parse(start);  
 
					String end = eElement.getAttribute("endTime");
				    formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
					Date f =formatter.parse(end);  

					long length = Long.parseLong(eElement.getAttribute("length"));
					Node attachedTo = eElement.getElementsByTagName("attachedTo").item(0);
					if(attachedTo !=null && attachedTo.hasChildNodes())
					{
						NodeList targets = attachedTo.getChildNodes();
						for (int i = 0; i < targets.getLength(); i++) {
							Node target = targets.item(i);
							if (target.getNodeType() == Node.ELEMENT_NODE) {
								Element targetElement = (Element) target;
								System.out.println("Target : " + targetElement.getAttribute("id"));
							}

						}
					}

					Node resources = eElement.getElementsByTagName("Resources").item(0);
					List<Resource> resourceList = new ArrayList<Resource>();
					if(resources !=null && resources.hasChildNodes())
					{
						NodeList resource = resources.getChildNodes();
						for (int i = 0; i < resource.getLength(); i++) {
							Node res = resource.item(i);
							if (res.getNodeType() == Node.ELEMENT_NODE) {
								Element ResourceElement = (Element) res;
								String idRes = ResourceElement.getAttribute("id");
								if(idRes.startsWith("VM")){
									String name = ResourceElement.getAttribute("name");
									String type = ResourceElement.getAttribute("type");
									String location= ResourceElement.getAttribute("location");
									String strategy= ResourceElement.getAttribute("strategy");
									String os= ResourceElement.getAttribute("os");;
									int dcpu = Integer.parseInt(ResourceElement.getAttribute("dcpu"));
									double memory = Double.parseDouble(ResourceElement.getAttribute("memory"));
									resourceList.add(new Resource(idRes, name, type, location, strategy, os, dcpu, memory));
								}
								else if(idRes.startsWith("storage")){
									System.out.println("Not yet implemented !");
								}
							}

						}
					}
					tasks.add(new Activity(id, min, max, resourceList, s, f,  length));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tasks;
	}

	public static void main(String[] argv) throws IOException, ParseException{
		List<Activity> tasks = new ArrayList<>();

		tasks = parse("/home/mehdi/Bureau/simulators/Cloud/Example.xml");
		
		System.out.println("********Activities**********");
		for(Activity act: tasks)
		{
			System.out.println(act);
		}
		
		System.out.println("**********Simulation **************");
		CloudSimOpt.simulation(tasks);
		
		System.out.println("***********Pricing************");
		for(Activity act: tasks)
		{
			String start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(act.getStart());
			String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(act.getEnd());
			start = start.replaceAll("\\s", "T");
			end = end.replaceAll("\\s", "T");
			/*Price.getInstancePrice(act.getResources().get(0).getLocation(), 
					act.getResources().get(0).getOs(), act.getResources().get(0).getDcpu(),
					act.getResources().get(0).getMemory(), act.getMaxTime()-act.getMinTime(),
					start, end, act.getResources().get(0).getStrategy());*/
		}
	}

}

