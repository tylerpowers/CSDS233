package assignment5;

public class Art {

    // height of painting
    private int height;

    // price of painting
    private int price;

    // width of painting
    private int width;

    // name of painting
    private String name;

    // name of artist
    private String artistName;


    /**
     * class constructor
     * @param height an int representing the painting's height
     * @param price an int representing the painting's price
     * @param width an int representing the painting's width
     * @param name a String containing the painting's name
     * @param artistName a String containing the artist's name
     */
    public Art(int height, int price, int width, String name, String artistName) {
        this.height = height;
        this.price = price;
        this.width = width;
        this.name = name;
        this.artistName = artistName;
    }


    /**
     * public accessor method for painting height
     * @return an int representing the painting's height
     */
    public int getHeight() {
        return height;
    }


    /**
     * public setter method for painting height
     * @param height an int representing the new value for painting height
     */
    public void setHeight(int height) {
        this.height = height;
    }


    /**
     * public accessor method for painting price
     * @return an int representing the painting's price
     */
    public int getPrice() {
        return price;
    }


    /**
     * public setter method for painting price
     * @param price an int representing the new value for painting price
     */
    public void setPrice(int price) {
        this.price = price;
    }


    /**
     * public accessor method for painting width
     * @return an int representing the painting's width
     */
    public int getWidth() {
        return width;
    }


    /**
     * public setter method for painting width
     * @param width an int representing the new value for painting width
     */
    public void setWidth(int width) {
        this.width = width;
    }


    /**
     * public accessor method for painting name
     * @return a String containing the painting's name
     */
    public String getName() {
        return name;
    }


    /**
     * public setter method for painting name
     * @param name a String representing the new value for painting name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * public accessor method for artist name
     * @return a String containing the artist's name
     */
    public String getArtistName() {
        return artistName;
    }


    /**
     * public setter method for artist name
     * @param artistName a String representing the new value for painting name
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

}
