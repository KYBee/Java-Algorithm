package day09.p3009;

import java.io.*;
import java.util.*;


public class Main {
	
	static int x = 0, y = 0;
	
	public static void main(String[] args) throws IOException {
	
		System.setIn(new FileInputStream("src/day09/p3009/input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			x = x ^ Integer.parseInt(st.nextToken());
			y = y ^ Integer.parseInt(st.nextToken());
			
		}
		
		System.out.println(x + " " + y);
		
	}

}

