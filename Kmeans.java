import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.util.*;
public class Kmeans {
	public static Record update(List<Record> cluster) {
		Record a=new Record();
		double x=0,y=0;
		for(Record r:cluster) {
			x+=r.attr1;
			y+=r.attr2;
		}
		x=x/cluster.size();
		y=y/cluster.size();
		a.attr1=x;
		a.attr2=y;
		return a;
	}
	public static double dist(double x1,double y1,double x2,double y2) {
		double ans=Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2);
		return Math.sqrt(ans);
	}
	public static void main(String[] args) throws FileNotFoundException,IOException{
		// TODO Auto-generated method stub
		
		BufferedReader csv=new BufferedReader(new FileReader(new File("/root/Desktop/kmeans.csv")));
		String line=csv.readLine();
		ArrayList<Record> dataset=new ArrayList<Record>();
		while(line!=null) {
			String data[]=line.split(",");
			Record row=new Record();
			row.attr1=Double.parseDouble(data[0]);
			row.attr2=Double.parseDouble(data[1]);
			dataset.add(row);
			line=csv.readLine();
		}
		
		ArrayList<Record> centroid=new ArrayList<Record>();
		centroid.add(dataset.get(0));
		centroid.add(dataset.get(1));
		centroid.add(dataset.get(2));
		ArrayList<Record>cluster1=new ArrayList<Record>();
		ArrayList<Record>cluster2=new ArrayList<Record>();
		ArrayList<Record>cluster3=new ArrayList<Record>();
		for(int i=0;i<10;i++) {
			cluster1.clear();
			cluster2.clear();
			cluster3.clear();
			for(i=0;i<dataset.size();i++) {
				Record row=dataset.get(i);
				ArrayList<Double> check=new ArrayList<>();
				double dis1=dist(row.attr1,row.attr2,centroid.get(0).attr1,centroid.get(0).attr2);
				double dis2=dist(row.attr1,row.attr2,centroid.get(1).attr1,centroid.get(1).attr2);
				double dis3=dist(row.attr1,row.attr2,centroid.get(2).attr1,centroid.get(2).attr2);
				check.clear();
				check.add(dis1);
				check.add(dis2);
				check.add(dis3);
				double min=Collections.min(check);
				if(min==dis1) {
					cluster1.add(row);
					
					
				}
				else if(min==dis2) {
					cluster2.add(row);
				}
				else {
					cluster3.add(row);
				}
			}
			centroid.clear();
			centroid.add(update(cluster1));
			centroid.add(update(cluster2));
			centroid.add(update(cluster3));
		}
		System.out.println("Clusters are:");
		System.out.println("Cluster1");
		for(int i=0;i<cluster1.size();i++) {
			System.out.println(cluster1.get(i).attr1+","+cluster1.get(i).attr2);
		}
		System.out.println("Cluster2");
		for(int i=0;i<cluster2.size();i++) {
			System.out.println(cluster2.get(i).attr1+","+cluster2.get(i).attr2);
		}
		System.out.println("Cluster3");
		for(int i=0;i<cluster3.size();i++) {
			System.out.println(cluster3.get(i).attr1+","+cluster3.get(i).attr2);
		}
		
		
	}

}
class Record{
	double attr1;
	double attr2;
}
