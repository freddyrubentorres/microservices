package com.ms.account_transaction.constants;

/**
 * @author : Freddy Torres
 * file :  ProcessConstants
 * @since : 11/3/2025, mar
 **/

public class ProcessConstants {
    public static final String API_PATH = "/api";
    //TRANSACTION
    public static final String API_PATH_TRANSACTION = API_PATH+"/transactions";
    public static final String API_PATH_TRANSACTION_PING = "/ping";
    public static final String MSG_INSUFFICIENT_BALANCE="Saldo no disponible.";
    public static final String API_PATH_TRANSACTION_BY_ACCOUNT = "/account/{accountId}";
    public static final String API_PATH_TRANSACTION_BY_CLIENT = "/client/{clientId}";
    //REPORTS
    public static final String API_PATH_REPORTS = API_PATH+"/reports";
    public static final String API_PATH_REPORTS_PING = "/ping";
    //ACCOUNTS
    public static final String API_PATH_ACCOUNTS = API_PATH+"/accounts";
    public static final String API_PATH_ACCOUNTS_PING = "/ping";
    public static final String API_PATH_ACCOUNTS_BY_ID = "/id/{id}";
    public static final String TRANSACTION_ACCOUNT_OPENING = "APERTURA DE CUENTA";
    public static final String API_PATH_ACCOUNTS_DELETE_ID = "/{id}";
    public static final String CLIENT_QUEUE = "ms.clientes.queue";
}
