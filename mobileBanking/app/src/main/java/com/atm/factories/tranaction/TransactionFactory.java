package com.example.ferin.atm.factories.tranaction;


import com.example.ferin.atm.domain.transaction.Transaction;
import com.example.ferin.atm.domain.transaction.impl.BalanceEnquiry;
import com.example.ferin.atm.domain.transaction.impl.Transfer;
import com.example.ferin.atm.domain.transaction.impl.Withdraw;

public class TransactionFactory {

        public static Transaction getTransaction(String value){
            Transaction chain = setUpChain();
            return chain.handleRequest(value);
        }

        public static Transaction setUpChain(){
            Transaction balanceEnquiry = new BalanceEnquiry();
            Transaction transfer = new Transfer();
            Transaction withdraw = new Withdraw();

            balanceEnquiry.setNextTransaction(transfer);
            transfer.setNextTransaction(withdraw);

            return balanceEnquiry;
        }


}
