package assignment4;

import java.util.ArrayList;

/**
 * class that implements a group of CMDb users
 * @author rtp32
 */
public class CMDbGroup {

    // all users signed up for a group
    private final static HashTable<CMDbProfile> network = new HashTable<>();

    // one individual group
    private final ArrayList<CMDbProfile> group;


    /**
     * class constructor, creates a new group
     */
    public CMDbGroup() {
        group = new ArrayList<>();
    }


    /**
     * returns a hashtable of every member of every group
     * @return a hashtable
     */
    public static HashTable<CMDbProfile> registeredUsers() {
        return network;
    }


    /**
     * returns the names of all group members
     * @return an array
     */
    public String[] group() {
        String[] arr = new String[group.size()];
        for (int i = 0; i < group.size(); i++) {  // iterates through group
            arr[i] = group.get(i).getUserName();
        }
        return arr;
    }


    /**
     * adds a member to the group and the network
     * @param member a CMDbProfile
     */
    public void addMember(CMDbProfile member) {
        group.add(member);
        network.put(member.getUserName(), member);
    }


    /**
     * adds an array of members to the group and the network
     * @param members an array of CMDbProfile objects
     */
    public void addMember(CMDbProfile[] members) {
        for (CMDbProfile member : members) {  // iterates through list
            group.add(member);
            network.put(member.getUserName(), member);
        }
    }


    /**
     * returns a user's favorite movie
     * @param userName a user's username
     * @return the user's favorite movie as a String
     */
    public static String favorite(String userName) {
        return network.get(userName).favorite(1)[0];
    }


    /**
     * returns an array of each group member's favorite movie
     * @return a String[]
     */
    public String[] groupFavorites() {
        String[] arr = new String[group.size()];
        for (int i = 0; i < group.size(); i++) {  // iterates through group
            arr[i] = group.get(i).favorite(1)[0];
        }
        return arr;
    }

    public String randomMovie(int k) {
        return null;
    }

}
