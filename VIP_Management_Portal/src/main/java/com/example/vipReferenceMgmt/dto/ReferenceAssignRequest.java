package com.example.vipReferenceMgmt.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ReferenceAssignRequest {
    private Long fromUserId;
    private Long toUserId;
    private Long vipReferenceId;
    private Long fromRoleId;
    private Long toRoleId;
    private LocalDateTime DateOfLetter;
    private LocalDateTime DateOfReceiving;
    private LocalDateTime DateOfEntry;
    private String nameOfDignitary;
    private String emailId;
    private String designation;
    private String state;
    private String constituency;
    private String prirority;
    private String categoryOfSubject;
    private String subCategoryOfSubject;
    private String subject;
    private List<MultipartFile> files;

    private List<String> documentTypes;

    private List<String> comments;
}
