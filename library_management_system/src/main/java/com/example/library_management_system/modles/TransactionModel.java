package com.example.library_management_system.modles;

import java.sql.Date;
public class TransactionModel {

    private int id;
    private Date orderDate;
    private Date approvedDate;
    private Date returnDate;
    private Enums.Stautus status;
    private String approvedBy;
    private String orderedBy;
    private int resourceId;


    public TransactionModel(int id,Date orderDate, Date approvedDate,
                            Date returnDate, Enums.Stautus status,
                            String approvedBy, String orderedBy, int resourceId) {
        this.id = id;
        this.orderDate = orderDate;
        this.approvedDate = approvedDate;
        this.returnDate = returnDate;
        this.status = status;
        this.approvedBy = approvedBy;
        this.orderedBy = orderedBy;
        this.resourceId = resourceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Enums.Stautus getStatus() {
        return status;
    }

    public void setStatus(Enums.Stautus status) {
        this.status = status;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", approvedDate=" + approvedDate +
                ", returnDate=" + returnDate +
                ", status=" + status +
                ", approvedBy='" + approvedBy + '\'' +
                ", orderedBy='" + orderedBy + '\'' +
                ", resourceId=" + resourceId +
                '}';
    }
}
