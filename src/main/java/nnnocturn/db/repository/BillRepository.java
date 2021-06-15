package nnnocturn.db.repository;

import nnnocturn.db.entity.Bill;
import nnnocturn.db.entity.Order;

import java.util.List;

public interface BillRepository {

    void createBill(Bill bill);

    List<Bill> getBills(Order order);

    void updateBill(Bill bill);
}
