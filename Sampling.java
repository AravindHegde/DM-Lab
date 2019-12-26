import java.util.*;
import java.io.*;
public class Sampling {

	public static void main(String[] args) throws FileNotFoundException,IOException {
		// TODO Auto-generated method stub
		BufferedReader csv=new BufferedReader(new FileReader(new File("/root/sampling.csv")));
		String line=csv.readLine();
		ArrayList<DataSet>dataset=new ArrayList<DataSet>();
		while(line!=null) {
			String row[]=line.split(",");
			DataSet ds=new DataSet();
			ds.index=Integer.parseInt(row[0]);
			ds.attr1=Integer.parseInt(row[1]);
			ds.attr2=row[2];
			dataset.add(ds);
			line=csv.readLine();
		}
		//Agggregation
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		double avg=0;
		for(int i=0;i<dataset.size();i++) {
			if(dataset.get(i).attr1<min) {
				min=dataset.get(i).attr1;
			}
			if(dataset.get(i).attr1>max) {
				max=dataset.get(i).attr1;
			}
			avg+=dataset.get(i).attr1;
		}
		avg/=dataset.size();
		System.out.println("For attribute 1");
		System.out.println("Min:"+min+" Max:"+max+" Average:"+avg);
		//Descretization and Sampling
		int sample[]= {0,0,0,0};
		int median=(min+max)/2;
		int mid1=(min+median)/2;
		int mid2=(median+max)/2;
		for(int i=0;i<dataset.size();i++) {
			System.out.println(dataset.get(i).index+","+dataset.get(i).attr1+","+dataset.get(i).attr1);
			if(dataset.get(i).attr1>=min&&dataset.get(i).attr1<mid1) {
				System.out.println(min+"-"+mid1);
				sample[0]++;
			}
			else if(dataset.get(i).attr1>=mid1&&dataset.get(i).attr1<median) {
				System.out.println(mid1+"-"+median);
				sample[1]++;
			}
			else if(dataset.get(i).attr1>=median&&dataset.get(i).attr1<mid2) {
				System.out.println(median+"-"+mid2);
				sample[2]++;
			}
			else if(dataset.get(i).attr1>=mid2&&dataset.get(i).attr1<=max) {
				System.out.println(mid2+"-"+max);
				sample[3]++;
			}
		}
		System.out.println("Sampling...");
		System.out.println("Min-Mid1 "+sample[0]);
		System.out.println("Mid1-Median "+sample[1]);
		System.out.println("Median-Mid2 "+sample[2]);
		System.out.println("Mid2-Max "+sample[3]);
	}

}
class DataSet{
	int index,attr1;
	String attr2;
}
