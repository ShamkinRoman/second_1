package ru.job4j.storage;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
@ThreadSafe
public class UserStorage {

    private ArrayList<User> stoge = new ArrayList<>();

    public synchronized boolean add(User user) {

        boolean flag = false;

        if (!stoge.contains(user)) {
            stoge.add(user);
            flag = true;
        }
        return flag;
    }

    public synchronized boolean delete(User user) {

        boolean flag = false;

        if (stoge.contains(user)) {
            stoge.remove(user);
            flag = true;
        }
        return flag;
    }

    public synchronized boolean update(User user) {

        boolean flag = false;

        for (User value : stoge) {
            if (user.getId() == value.getId()) {
                int place = stoge.indexOf(value);
                stoge.set(place, user);
                flag = true;
                break;
            }
        }

        return flag;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {

        boolean flag = false;
        int maches = 0;

        User userFrom = null;
        User userTo = null;

        for (User value : stoge) {

            if (value.getId() == fromId) {
                userFrom = value;
                maches++;
            }
            if (value.getId() == toId) {
                userTo = value;
                maches++;
            }
            if (maches == 2) {
                break;
            }
        }

        if (userFrom != null && userTo != null && (userFrom.getAmount() >= amount)) {

            userFrom.setAmount(userFrom.getAmount() - amount);
            userTo.setAmount(userTo.getAmount() + amount);
            flag = true;
        }

        return flag;
    }

    public int size() {
        return stoge.size();
    }
}
