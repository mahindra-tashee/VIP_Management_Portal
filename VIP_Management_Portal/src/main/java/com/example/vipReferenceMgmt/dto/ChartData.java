package com.example.vipReferenceMgmt.dto;

import java.util.List;

import lombok.Data;

@Data
public class ChartData {
	private List<String> labels;
    private List<Integer> data;
}
