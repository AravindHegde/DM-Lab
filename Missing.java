import java.util.*;
import java.io.*;
public class Missing {
	public static ArrayList<Dataset> dataset;
	public static void missingInteger(String val) {
		int avg=0;
		for(int i=0;i<dataset.size();i++) {
			if(!dataset.get(i).attr1.equals(val)) {
				avg+=Integer.parseInt(dataset.get(i).attr1);
			}
			
		}
		avg/=dataset.size();
		for(int i=0;i<dataset.size();i++) {
			if(dataset.get(i).attr1.equals(val)){
				dataset.get(i).attr1=Integer.toString(avg);
			}
		}
	}
	public static void missingString(String val) {
		String maxKey="";
		int max=Integer.MIN_VALUE;
		HashMap<String,Integer>hm=new HashMap<String,Integer>();
		for(int i=0;i<dataset.size();i++) {
			String key=dataset.get(i).attr5;
			if(!key.equals(val)) {
				if(hm.containsKey(key)) {
					hm.put(key, hm.get(key)+1);
				}
				else {
					hm.put(key,1);
				}
				if(hm.get(key)>max) {
					max=hm.get(key);
					maxKey=key;
				}
			}
		}
		for(int i=0;i<dataset.size();i++) {
			String key=dataset.get(i).attr5;
			if(key.equals(val)) {
				dataset.get(i).attr5=maxKey;
			}
		}
	}
	public static void main(String[] args) throws FileNotFoundException,IOException {
		BufferedReader csv=new BufferedReader(new FileReader(new File("/root/missing.csv")));
		String line=csv.readLine();
		dataset=new ArrayList<>();
		while(line!=null) {
			String row[]=line.split(",");
			Dataset ds=new Dataset();
			ds.index=Integer.parseInt(row[0]);
			ds.attr2=Integer.parseInt(row[2]);
			ds.attr1=row[1];
			ds.attr3=row[3];
			ds.attr4=Double.parseDouble(row[4]);
			ds.attr5=row[5];
			dataset.add(ds);
			line=csv.readLine();
		}
		missingInteger("NA");
		missingString("NA");
		FileWriter fw=new FileWriter(new File("/root/output.csv"));
		for(int i=0;i<dataset.size();i++) {
			String l=dataset.get(i).index+","+dataset.get(i).attr1+","+dataset.get(i).attr2+","+dataset.get(i).attr3+","+dataset.get(i).attr4+","+dataset.get(i).attr5;
			//System.out.println(l);
			fw.write(l);
			fw.write('\n');
		}
		fw.close();
		System.out.println("Output is generated as output.csv");
	}

}
class Dataset{
	int index,attr2;
	String attr1,attr3,attr5;
	double attr4;
}
