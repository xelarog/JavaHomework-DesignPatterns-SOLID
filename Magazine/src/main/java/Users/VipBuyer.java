package Users;

public class VipBuyer extends Buyer{

    private String vipStatus;

    public VipBuyer(String name, String vipStatus) {
        super(name);
        this.vipStatus = vipStatus;
    }
}
