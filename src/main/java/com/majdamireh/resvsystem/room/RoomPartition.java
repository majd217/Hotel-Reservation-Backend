package com.majdamireh.resvsystem.room;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

public class RoomPartition {
	static void findPartition(int p[], int n, Partition singlePartition) {
		for (int i = 0; i < n; i++) {
			System.out.print(p[i] + " ");
			singlePartition.addRoomSize(p[i]);
		}
	}
	
	//[5]-[1,1,1,1,1]
	// Function to generate all unique partitions of an integer
	public static ArrayList<Partition> findAllCombinations(int n) {
		ArrayList<Partition> listOfPartitions = new ArrayList<>();
		Partition singlePartition = new Partition();
		
		int[] p = new int[n]; // An array to store a partition
		int k = 0;  // Index of last element in a partition
		p[k] = n;  // Initialize first partition as number itself
		int j = 0;
		
		while (true) {
			
			findPartition(p, k + 1, singlePartition);
			System.out.println();
			
			int rem_val = 0;
			while (k >= 0 && p[k] == 1) {
				rem_val += p[k];
				k--;
			}
			
			if (k < 0) {
				listOfPartitions.add(j, singlePartition);
				return listOfPartitions;
			}
			
			p[k]--;
			rem_val++;
			
			while (rem_val > p[k]) {
				p[k + 1] = p[k];
				rem_val = rem_val - p[k];
				k++;
			}
			
			p[k + 1] = rem_val;
			k++;
			
			listOfPartitions.add(j, singlePartition);
			singlePartition = new Partition();
			j++;
			
		}
	}
}


