package com.co.challenge.devsubankaccount.utils;

public class Constants {

    public static final String ACCOUNT_PATH = "/api/v1/account";
    public static final String ACCOUNT_PATH_ID = ACCOUNT_PATH + "/{accountId}";
    public static final String ACCOUNT_ID = "accountId";

    public static final String MOVEMENT_PATH = "/api/v1/movement";
    public static final String MOVEMENT_PATH_ID = MOVEMENT_PATH + "/{movementId}";
    public static final String MOVEMENT_ID = "movementId";



    public static final String LOCATION = "location";
    public static final String SLASH = "/";

    public static final int CERO = 0;
    public static final int ONE = 1;
    public static final int THOUSAND = 1000;

    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_PAGE_SIZE = 25;
    public static final String FIRST_LAST_NAME = "firstLastName";


    public static final String NUMBER_ACCOUNT = "numberAccount";
    public static final String ID = "id";

    public static final String CREDIT = "CREDIT";
    public static final String DEBIT = "DEBIT";

    public static final String REPORT_PATH = "/api/v1/report";

    //QUERIES
    public static final String GET_DAY_DEBIT_TRANSACTIONS = "select MV from Movement MV where MV.account.id = ?1 " +
            "and MV.movementType = 'debit' and MV.createdDate > date_format(sysdate(), '%Y-%m-%d') order by MV.createdDate asc";

    public static final String GET_REPORT_BY_DATES = "select acc.numberAccount, acc.accountType, mo.balance, " +
            "mo.createdDate, mo.amount, mo.balance - mo.amount as availableAmount, acc.state " +
            "from Account acc join Movement mo where acc.id = mo.account.Id and acc.personId = :userId " +
            "and mo.createdDate between :fromDate and :toDate";
}
