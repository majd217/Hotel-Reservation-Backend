package com.majdamireh.resvsystem.room;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Partition {
	HashMap<Integer, Integer> countPerRoomSize = new HashMap<>();
	
	//[1->3,2->1]
	
	public Integer getRoomSizeCount(Integer roomSize) {
		return countPerRoomSize.get(roomSize);
	}
	
	public ArrayList<Integer> getRoomSizes() {
		return new ArrayList<>(countPerRoomSize.keySet());
	}
	
	public Integer getRoomCounts() {
		Integer roomCounts = 0;
		for (Integer roomCountPerSize : countPerRoomSize.values()) {
			roomCounts += roomCountPerSize;
		}
		return roomCounts;
	}
	
	public void addRoomSize(Integer size) {
		countPerRoomSize.put(size, countPerRoomSize.getOrDefault(size, 0) + 1);
	}
}

//[1,4][3,2]
//