package assignment4;

/**
 * class that implements an "IMDb Profile" type deal where you can rate movies
 * @author rtp32
 */
public class CMDbProfile {

    // username field
    private String userName;

    // priorityQueue which stores ratings
    private final PriorityQueue<Integer, String> queue = new PriorityQueue<>();


    /**
     * public class constructor
     * @param userName username
     */
    public CMDbProfile(String userName) {
        this.userName = userName;
    }


    /**
     * changes username
     * @param userName new, updated username
     */
    public void changeUserName(String userName) {
        this.userName = userName;
    }


    /**
     * submits a new movie rating
     * @param movie movie title
     * @param rating rating 1-10 of the movie
     * @return boolean saying whether this was a valid rating
     */
    public boolean rate(String movie, int rating) {
        if (rating < 1 || rating > 10)  // assure rating is in bounds
            return false;
        queue.add(rating, movie);
        return true;
    }


    /**
     * changes the current rating of a movie
     * @param movie the movie to be changed
     * @param newRating the new rating of the movie
     * @return boolean saying whether this was a valid rating
     */
    public boolean changeRating(String movie, int newRating) {
        if(newRating >= 1 && newRating <= 10 && queue.poll(movie) != null) {  // assure rating is in bounds & in queue
            queue.add(newRating, movie);
            return true;
        }
        return false;
    }


    /**
     * removes a rating
     * @param movie the title of the movie
     * @return boolean saying whether the rating was in storage
     */
    public boolean removeRating(String movie) {
        return queue.poll(movie) != null;
    }


    /**
     * returns all the movies of the user's highest rating
     * @return a String[]
     */
    public String[] favorite() {
        return queue.returnAllOfTopKey();
    }


    /**
     * returns the user's k highest ratings
     * @param k an int
     * @return a String[]
     */
    public String[] favorite(int k) {
        return queue.peek(k - 1);
    }


    /**
     * returns some basic information about the user
     * @return a String
     */
    public String profileInformation() {
        return userName + " (" + queue.size() + ")\nFavorite Movie: " + queue.peek();
    }


    /**
     * two profiles are equal if they have the same username
     * override of Object .equals(Object o)
     * @param o another CMDbProfile
     * @return a boolean
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof CMDbProfile)  // assure two CMDbProfiles are being compared
            return ((CMDbProfile) o).userName.equals(userName);
        return false;
    }

    protected String getUserName() {
        return userName;
    }
}
