package com.app.service;

import com.app.dao.UserDAO;
import com.app.enums.BucketName;
import com.app.model.UserProfile;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@Service
public class UserProfileService {

    private final UserDAO userDAO;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserDAO userDAO, FileStore fileStore) {
        this.fileStore = fileStore;
        this.userDAO = userDAO;
    }

    public List<UserProfile> getUserProfiles(){
        return userDAO.getUserProfiles();
    }

    private UserProfile fetchUser(UUID userProfileId){
        return userDAO.getUserProfiles().stream().filter(user -> user.getUserProfileId()
                .equals(userProfileId)).findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User with id: %s not found!", userProfileId)));
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalStateException("File is empty!");
        }
        if(!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("Invalid image format! Only JPEG & PNG files are supported.");
        }
        UserProfile userProfile = fetchUser(userProfileId);
        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type", file.getContentType());
        metaData.put("Content-length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userProfile.getUserProfileId());
        String name = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path,name,Optional.of(metaData),file.getInputStream());
            userProfile.setUserProfileImageLink(name);
        } catch (IOException e) {
            throw new IllegalStateException("Failed");
        }
    }

    public byte[] downloadUserProfileImage(UUID userProfileId) {
        UserProfile userProfile = fetchUser(userProfileId);
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userProfile.getUserProfileId());
        return userProfile.getUserProfileImageLink().map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }
}
