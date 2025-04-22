package com.example.userMgmt.dto;

import lombok.Data;

@Data
public class ReferenceAssignRequest {
    private Long fromUserId;
    private Long toUserId;
    private Long vipReferenceId;
    private Long fromRoleId;
    private Long toRoleId;
}
