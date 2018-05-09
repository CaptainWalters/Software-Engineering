/**
 *
 * @author Kieran(132206), Oliver(134730), Vlad (146674)
 *
 */
public abstract class BoardLocation {

    // this class is used to populate each board location and can hold players on the space, what the space is
    // and how much it costs etc.
     
    //@146674 set all class properties to private and some final
    private final int position;
    private final String name;

    public BoardLocation(int position, String name){
        this.position = position;
        this.name = name;
    }

    public abstract String getInformation();

    public String getName(){
         return name;
     }

}
