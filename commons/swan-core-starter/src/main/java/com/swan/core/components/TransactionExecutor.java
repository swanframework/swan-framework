package com.swan.core.components;//package org.zongf.sboot.core.components;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.DefaultTransactionDefinition;
//
//import java.util.function.Supplier;
//
///** 事务执行器
// * @author zongf
// * @since 2020-12-03
// */
//public class TransactionExecutor {
//
////    @Autowired
//    private PlatformTransactionManager transactionManager;
//
//    /** 执行事务, 默认
//     * @param runnable
//     * @return
//     * @author zongf
//     * @since 2020-12-03
//     */
//    public void executeWithinTransaction(Runnable runnable) {
//        this.executeWithinTransaction(runnable, new DefaultTransactionDefinition());
//    }
//
//    public void executeWithinTransaction(Runnable runnable, DefaultTransactionDefinition transactionDefinition) {
//
//        // 开启事务
//        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
//
//        try {
//            // 执行目标方法
//            runnable.run();
//        } catch (Exception ex) {
//            transactionManager.rollback(status);
//            throw ex;
//        }
//
//        // 提交事务
//        transactionManager.commit(status);
//    }
//
//    public <T> T executeWithinTransaction(Supplier<T> supplier, DefaultTransactionDefinition transactionDefinition) {
//
//        // 开启事务
//        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
//
//        try {
//            // 执行目标方法
//            T result = supplier.get();
//            // 提交事务
//
//            transactionManager.commit(status);
//            return result;
//        } catch (Exception ex) {
//            transactionManager.rollback(status);
//            throw ex;
//        }
//    }
//
//    public <T> T executeWithinTransaction(Supplier<T> supplier) {
//        return this.executeWithinTransaction(supplier, new DefaultTransactionDefinition());
//    }
//
//}
