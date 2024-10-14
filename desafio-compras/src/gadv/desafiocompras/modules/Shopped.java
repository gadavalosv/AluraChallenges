package gadv.desafiocompras.modules;

public class Shopped implements Comparable<Shopped>{
    private String productDescription;
    private double value;
    public Shopped (String productDescription, double value){
        this.productDescription = productDescription;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getProductDescription() {
        return productDescription;
    }

    @Override
    public int compareTo(Shopped otherShopped) {
        return Double.valueOf(this.getValue()).compareTo(Double.valueOf(otherShopped.getValue()));
    }
}
