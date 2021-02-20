package com.app.controller;

import com.app.model.UserProfile;
import com.app.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/user/")
public class UserController {

    private UserProfileService userProfileService;

    @Autowired
    public UserController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("all")
    public List<UserProfile> getUserProfiles(){
        return userProfileService.getUserProfiles();
    }

    @PostMapping(
            path = "{userProfileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("userProfileId")UUID userProfileId, @RequestParam("file")MultipartFile file){
        userProfileService.uploadUserProfileImage(userProfileId, file);
    }

    @GetMapping("/{userProfileId}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId){
        return userProfileService.downloadUserProfileImage(userProfileId);
    }
}
