package com.app.dao;

import com.app.model.UserProfile;
import com.app.store.UserProfilesData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    private UserProfilesData userProfilesData;

    @Autowired
    public UserDAO(com.app.store.UserProfilesData userProfilesData) {
        this.userProfilesData = userProfilesData;
    }

    public List<UserProfile> getUserProfiles(){
        return userProfilesData.getUserProfilesList();
    }
}
