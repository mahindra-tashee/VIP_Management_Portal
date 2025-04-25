package com.example.vipReferenceMgmt.dto;

import lombok.Data;

@Data
public class DashboardStatsResponse {
	private int inboxCount;
    private int sentCount;
    private ChartData chartData;
}
