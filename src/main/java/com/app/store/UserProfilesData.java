package com.app.store;

import com.app.model.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserProfilesData {

    private static final List<UserProfile> USER_PROFILES_LIST = new ArrayList<>();

    static {
        USER_PROFILES_LIST.add(new UserProfile(UUID.fromString("7e158e61-8a60-4130-9848-c82c9022dd74"),
                "janetjones", null));
        USER_PROFILES_LIST.add(new UserProfile(UUID.fromString("4d320f75-ede7-4d0a-a6b4-492d7a2601ee"),
                "antonio",null));
    }

    public List<UserProfile> getUserProfilesList(){
        return USER_PROFILES_LIST;
    }
}
