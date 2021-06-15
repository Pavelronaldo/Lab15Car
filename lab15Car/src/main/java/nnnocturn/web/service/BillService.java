package nnnocturn.web.service;

import nnnocturn.db.dto.OrderDTO;
import nnnocturn.db.entity.Bill;
import nnnocturn.web.bean.AcceptCarBean;
import nnnocturn.web.bean.PayOrderBean;
import nnnocturn.web.bean.TreatmentOrderBean;

import java.util.List;

public interface BillService {

    void createBill(AcceptCarBean acceptCarBean);

    void createBill(TreatmentOrderBean treatmentOrderBean);

    List<Bill> getBillsForOneOrder(OrderDTO order);

    void payForBill(PayOrderBean payOrderBean);
}
