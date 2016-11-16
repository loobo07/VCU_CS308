public final class DatabaseContract {

	public static final  int    DATABASE_VERSION	= 1;
    public static final  String DATABASE_NAME		= "database.db";
    
    private static final String TEXT_TYPE			= " TEXT";
    private static final String TIMESTAMP_TYPE		= " TIMESTAMP";
    private static final String INT_TYPE			= " INTEGER";
    private static final String DOUBLE_TYPE			= " DOUBLE";
    private static final String ID_TYPE				= " INTEGER";
    
    private static final String COMMA_SEP         	= ",";
    private static final String OPEN_PAREN			= " (";
    private static final String CLOSE_PAREN			= " )";
    private static final String ID                	= "id";
    
    
    private static final String MAKE_TABLE			= "CREATE TABLE ";
    private static final String REMOVE_TABLE		= "DROP TABLE ";
    private static final String IF_EXISTS			= "IF EXISTS ";
    private static final String IF_NOT_EXISTS 		= "IF NOT EXISTS ";
    private static final String PRIMARY_KEY			= " PRIMARY KEY";
    private static final String DEFAULT 			= " DEFAULT";
    private static final String CURRENT_TIMESTAMP 	= " CURRENT_TIMESTAMP";

    // Private to prevent someone from accidentally instantiating the contract class
    private DatabaseContract() {}

    
    public static String getCreateTable(String TABLE) {
    	return MAKE_TABLE + TABLE;
    }
    public static String getCreateTableIfNotExists(String TABLE) {
    	return MAKE_TABLE + IF_NOT_EXISTS + TABLE;
    }
    public static String getDeleteTable(String TABLE) {
    	return REMOVE_TABLE + IF_EXISTS + TABLE;
    }

    public abstract static class Customers {
        public static final String TABLE_NAME       = "Customers";
        public static final String FIRST_NAME 		= "first_name";
        public static final String LAST_NAME		= "last_name";
        public static final String USERNAME 		= "username";
        public static final String PASSWORD 		= "password";
        public static final String BALANCE			= "balance";
        public static final String CREATED_AT		= "created_at";
        public static final String UPDATED_AT 		= "updated_at";
        

        public static final String TABLE = 
                TABLE_NAME + OPEN_PAREN
                + ID + ID_TYPE + PRIMARY_KEY + COMMA_SEP
                + FIRST_NAME + TEXT_TYPE + COMMA_SEP
                + LAST_NAME + TEXT_TYPE + COMMA_SEP
                + USERNAME + TEXT_TYPE + COMMA_SEP
                + PASSWORD + TEXT_TYPE + COMMA_SEP
                + CREATED_AT + TIMESTAMP_TYPE + COMMA_SEP
                + UPDATED_AT + TIMESTAMP_TYPE + DEFAULT + CURRENT_TIMESTAMP 
                + CLOSE_PAREN;
    }
    
    public abstract static class Transactions {
    	public static final String TABLE_NAME       = "Customers";
        public static final String FROM_ACCOUNT		= "from_customer";
        public static final String TO_ACCOUNT 		= "to_customer";
        public static final String AMMOUNT	 		= "username";
        public static final String CREATED_AT		= "created_at";
        public static final String UPDATED_AT 		= "updated_at";
        
        public static final String TABLE = 
        		TABLE_NAME + OPEN_PAREN
        		+ ID + ID_TYPE + PRIMARY_KEY + COMMA_SEP
        		+ FROM_ACCOUNT + ID_TYPE + COMMA_SEP
        		+ TO_ACCOUNT + ID_TYPE + COMMA_SEP
        		+ AMMOUNT + DOUBLE_TYPE + COMMA_SEP 
        		+ CREATED_AT + TIMESTAMP_TYPE + COMMA_SEP
                + UPDATED_AT + TIMESTAMP_TYPE + DEFAULT + CURRENT_TIMESTAMP 
                + CLOSE_PAREN;
    }
}