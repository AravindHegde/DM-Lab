import java.util.*;
import java.io.*;
public class Knn {
	public static double distance(int x1,int y1,int x2,int y2) {
		double ans=Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2);
		return Math.sqrt(ans);
	}
	public static void main(String[] args) throws FileNotFoundException ,IOException{
		BufferedReader csv=new BufferedReader(new FileReader(new File("/root/exam.csv")));
		ArrayList<Data>dataset=new ArrayList<Data>();
		String line=csv.readLine();
		while(line!=null) {
			Data ds=new Data();
			String row[]=line.split(",");
			ds.index=Integer.parseInt(row[0]);
			ds.attr1=Integer.parseInt(row[1]);
			ds.attr2=Integer.parseInt(row[2]);
			ds.attr3=row[3];
			ds.dist=0.0;
			dataset.add(ds);
			line=csv.readLine();
		}
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the value of k");
		int k=sc.nextInt();
		if(k>dataset.size()) {
			System.out.println("K should be lesser");
			return;
		}
		System.out.println("Enter Marks and Attendance");
		int marks=sc.nextInt();
		int att=sc.nextInt();
		for(int i=0;i<dataset.size();i++) {
			dataset.get(i).dist=distance(dataset.get(i).attr1,dataset.get(i).attr2,marks,att);
		}
		Collections.sort(dataset,Data.dis);
		//System.out.println(dataset);
		int eligible=0,notEligible=0;
		for(int i=0,count=0;i<dataset.size()&&count<k;i++,count++) {
			System.out.println(dataset.get(i).dist+"->>"+dataset.get(i).attr3);
			if(dataset.get(i).attr3.equals("eligible"))
				eligible++;
			else {
				notEligible++;
			}
		}
		if(eligible>=notEligible) {
			System.out.println("Eligible");
		}
		else {
			System.out.println("Not Eligible");
		}
	}

}
	class Data{
	int index,attr1,attr2;
	String attr3;
	double dist=0;
	public double getDist() {
		return dist;
	}
	public static Comparator<Data> dis = new Comparator<Data>() {

		public int compare(Data d1, Data d2) {

		   double dist1 = d1.getDist();
		   double dist2 = d2.getDist();
		   return (int)(dist1-dist2);

	   }};
}
