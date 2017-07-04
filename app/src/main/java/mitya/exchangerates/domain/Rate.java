package mitya.exchangerates.domain;

/**
 * Created by Mitya on 04.07.2017.
 */

public class Rate<L, R> {
    private L l;
    private R r;

    public Rate(L l, R r) {
        this.l = l;
        this.r = r;
    }

    public L getL() {
        return l;
    }

    public R getR() {
        return r;
    }

    public void setL(L l) {
        this.l = l;
    }

    public void setR(R r) {
        this.r = r;
    }
}
