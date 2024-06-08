package com.example.repository;

import com.example.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private List<UserModel> users = new ArrayList<>();
    private AtomicLong counter = new AtomicLong();

    public UserRepository() {
        //sample data.
        users.add(new UserModel() {{
            setId(counter.incrementAndGet());
            setName("きじま りく");
            setEmail("kijima@LisB.com");
            setPassword("Life is Beautiful!");
        }});
        users.add(new UserModel() {{
            setId(counter.incrementAndGet());
            setName("かつやま みずき");
            setEmail("mizuki@Rakuten.com");
            setPassword("Go!Rakuten Mobile!");
        }});
        users.add(new UserModel() {{
            setId(counter.incrementAndGet());
            setName("つがる こうじろう");
            setEmail("tsugaru@UCBerkeley.com");
            setPassword("American");
        }});
    }

    //全部返す
    public List<UserModel> findAll() {
        return users;
    }

    //idで検索
    public Optional<UserModel> findById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    //メアドで検索
    public Optional<UserModel> findByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    //登録 or 変更
    public UserModel edit(UserModel user) {
        //新規登録
        if(user.getId() == null) {
            user.setId(counter.incrementAndGet());
            users.add(user);
        }
        else //情報更新
        {
            users.stream().filter(u -> u.getId().equals(user.getId())).findFirst().ifPresent(existringUser -> {
                existringUser.setName(user.getName());
                existringUser.setEmail(user.getEmail());
                existringUser.setPassword(user.getPassword());
            });
        }
        return user;
    }

    //idで削除
    public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}