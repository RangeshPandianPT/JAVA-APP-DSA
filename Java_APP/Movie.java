public class Movie 
   {
    String title;
    String genre;
    double rating;

    Movie(String title, String genre, double rating) 
	{
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    void displayDetails() 
	{
        System.out.println("Movie Title : " + title);
        System.out.println("Genre   : " + genre);
        System.out.println("Rating : " + rating + " / 10");
    }

    public static void main(String[] args) 
	{
  
        Movie movie1 = new Movie("Interstellar", "Sci-Fi", 8.6);

        movie1.displayDetails();
    }
}
