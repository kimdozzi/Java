package effectiveJava.item02;

public class NutritionFacts {
    private int servingSize = -1;
    private int servings = -1;
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;

    public NutritionFacts() {}

    // Setter Method
    public void setServingSize(int val) { servingSize = val; }
    public void setServings(int val) { servings = val; }

    // .........


    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts();
        cocaCola.setServingSize(240);
        cocaCola.setServings(240);
        // ...

    }
}
