package com.example.library_management_system.utils;

import com.example.library_management_system.controllers.BookController;
import com.example.library_management_system.controllers.MagazineController;
import com.example.library_management_system.controllers.TransactionController;
import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.modles.BookModel;
import com.example.library_management_system.modles.MagazineModel;
import com.example.library_management_system.modles.PatronModel;
import com.example.library_management_system.modles.TransactionModel;

import java.util.Date;
import java.util.List;

public class TestDb {

    public static void main(String[] args) {

        try{


//            System.out.println();
//            MagazineController magazine = new MagazineController();
//            List<MagazineModel> allmagz = magazine.getAll();
//            for ( MagazineModel mags : allmagz){
//                System.out.println(mags.toString());
//            }
            PatronModel newPatron = new PatronModel("solomon", "solomnchamem@email.com");

            TransactionController transaction = new TransactionController();
            List<TransactionModel> trans = transaction.getAll();
            for ( TransactionModel tran : trans){
                System.out.println(tran.toString());
            }

            TransactionModel transs = new TransactionModel(newPatron.getEmail(), 2, "Book");
            if(transaction.createOne(transs)){
                System.out.println("Hey new field added!");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
