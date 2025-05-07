package org.example;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class meal {
    customer cus;
    LocalTime delievryTime;
    boolean delivered;
    public meal(customer cus, LocalTime deliveryTime, boolean delivered) {
        this.cus = cus;
        this.delievryTime=deliveryTime;
        this.delivered=false;
    }
    public void setcus(customer cus) {
        this.cus = cus;
    }
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
    public customer getCus() {
        return cus;
    }
    public void setDeliveryTime(LocalTime deliveryTime) {
        this.delievryTime=deliveryTime;
    }
    public LocalTime getDeliveryTime() {
        return delievryTime;
    }
    public boolean isDelivered() {
        return delivered;
    }


}
