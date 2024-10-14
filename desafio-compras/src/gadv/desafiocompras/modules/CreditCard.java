package gadv.desafiocompras.modules;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CreditCard {
    private double credit;
    private List<Shopped> list;
    public CreditCard (double credit) {
        this.credit = credit;
        this.list = new ArrayList<>();
    }
    public double getCredit(){
        return this.credit;
    }

    public List<Shopped> getList() {
        return list;
    }

    public boolean buy(String productDescription, double productValue) {
        if(this.getCredit() < productValue) return false;
        this.list.add(new Shopped(productDescription, productValue));
        this.credit -= productValue;
        return true;
    }
    public void sortShoppingList(Comparator<Shopped> comparator) {
        this.list.sort(comparator);
    }
}
