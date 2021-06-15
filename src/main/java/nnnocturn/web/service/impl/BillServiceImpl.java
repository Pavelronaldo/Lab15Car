package nnnocturn.web.service.impl;

import nnnocturn.db.dto.OrderDTO;
import nnnocturn.db.entity.Bill;
import nnnocturn.db.entity.Order;
import nnnocturn.db.repository.BillRepository;
import nnnocturn.web.bean.AcceptCarBean;
import nnnocturn.web.bean.PayOrderBean;
import nnnocturn.web.bean.TreatmentOrderBean;
import nnnocturn.web.service.BillService;

import java.util.List;

public class BillServiceImpl implements BillService {

    private final BillRepository BILL_REPOSITORY;

    public BillServiceImpl(BillRepository BILL_REPOSITORY) {
        this.BILL_REPOSITORY = BILL_REPOSITORY;
    }

    /**
     * Create new bill
     *
     * @param acceptCarBean accept car nean
     */
    @Override
    public void createBill(AcceptCarBean acceptCarBean) {
        BILL_REPOSITORY.createBill(getEntity(acceptCarBean));
    }

    /**
     * Create new bill
     *
     * @param treatmentOrderBean which need to create
     */
    @Override
    public void createBill(TreatmentOrderBean treatmentOrderBean) {
        BILL_REPOSITORY.createBill(getEntity(treatmentOrderBean));
    }

    /**
     * Get entity bill
     * @param treatmentOrderBean which need to get bill
     * @return entity bill
     */
    private Bill getEntity(TreatmentOrderBean treatmentOrderBean) {
        Bill bill = new Bill();
        bill.setOrderId(treatmentOrderBean.getId());
        bill.setCost(treatmentOrderBean.getCost());
        bill.setReason(treatmentOrderBean.getComment());
        return bill;
    }

    /**
     * Get entity bill
     * @param acceptCarBean which need to get bill
     * @return entity bill
     */
    private Bill getEntity(AcceptCarBean acceptCarBean) {
        Bill bill = new Bill();
        bill.setOrderId(acceptCarBean.getId());
        bill.setCost(acceptCarBean.getCost());
        bill.setReason(acceptCarBean.getComment());
        return bill;
    }

    /**
     * Get list bill
     *
     * @param orderDTO which need to get bill
     * @return list bill
     */
    @Override
    public List<Bill> getBillsForOneOrder(OrderDTO orderDTO) {
        return BILL_REPOSITORY.getBills(getEntity(orderDTO));
    }

    /**
     * Pay for bill
     *
     * @param payOrderBean which need to pay for bill
     */
    @Override
    public void payForBill(PayOrderBean payOrderBean) {
        BILL_REPOSITORY.updateBill(getEntity(payOrderBean));
    }

    private Bill getEntity(PayOrderBean payOrderBean) {
        Bill bill = new Bill();
        bill.setId(payOrderBean.getIdBill());
        bill.setPaid(true);
        return bill;
    }

    private Order getEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        return order;
    }
}
