import java.util.*;
import java.io.*;
public class Ann {
	
	public static void main(String[] args) throws FileNotFoundException ,IOException{
		// TODO Auto-generated method stub
		BufferedReader bf=new BufferedReader(new FileReader(new File("/root/house.csv")));
		String line=bf.readLine();
		Random rand=new Random();
		ArrayList<Data2> dataset=new ArrayList<Data2>();
		while(line!=null) {
			String row[]=line.split(",");
			Data2 ds=new Data2();
			ds.index=Integer.parseInt(row[0]);
			ds.len=Integer.parseInt(row[1]);
			ds.wid=Integer.parseInt(row[2]);
			if(row[3].equals("yes")){
				ds.clas=1;
			}
			else {
				ds.clas=0;
			}
			dataset.add(ds);
			line=bf.readLine();
		}
		double rate=0.02;
		double weight[]=new double[3];
		weight[0]=rand.nextInt(32767*2)-32767;
		weight[1]=rand.nextInt(32767*2)-32767;
		weight[2]=rand.nextInt(32767*2)-32767;
		for(int i=0;i<1000;i++) {
			for(int j=0;j<dataset.size();j++) {
				int res=0;
				double sum=weight[0]+weight[1]*dataset.get(j).len+weight[2]*dataset.get(j).wid;
				if(sum>0) {
					res=1;
				}
				int error=dataset.get(j).clas-res;
				weight[0]+=error*rate;
				weight[1]+=error*rate*dataset.get(j).len;
				weight[2]+=error*rate*dataset.get(j).wid;
			}
		}
		System.out.println("Enter length and width of site");
		Scanner sc=new Scanner(System.in);
		int l=sc.nextInt();
		int w=sc.nextInt();
		double ans=weight[0]+weight[1]*l+weight[2]*w;
		if(ans>0) {
			System.out.println("Available within 2000000");
		}
		else {
			System.out.println("Not Available within 2000000");
		}
	}

}
class Data2{
	int index,len,wid,clas;
}
