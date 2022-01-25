package day09.p4153;


import java.util.*;
import java.io.*;


public class Main {
	
	
	static int[] number = new int[3];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] agrs) throws IOException {
	
		System.setIn(new FileInputStream("src/day09/p4153/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			
			number[0] = Integer.parseInt(st.nextToken());
			number[1] = Integer.parseInt(st.nextToken());
			number[2] = Integer.parseInt(st.nextToken());
			
			if(number[0] == 0 && number[1] == 0 && number[2] == 0) {
				break;
			}
			
			Arrays.sort(number);
			
			if(number[2] * number[2] == number[1] * number[1] + number[0] * number[0]) {
				sb.append("right\n");
			} else {
				sb.append("wrong\n");
			}
			
		}
		
		System.out.println(sb.toString());
		
		
	}

}
